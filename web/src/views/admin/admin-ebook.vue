<template>

  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
    <p>
      <a-form layout="inline" :model="param">
        <a-form-item>
          <a-input v-model:value="param.name" placeholder="名称"></a-input>
        </a-form-item>

        <a-form-item>
            <a-button type="primary" @click="handleQuery({page: 1,size: pagination.pageSize})">
              查询
            </a-button>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" @click="add()">
            新增
          </a-button>
        </a-form-item>

      </a-form>
    </p>
      <a-table
          :columns="columns"

          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
<!--        record 对应每一行的数据-->
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
<!--            a-popconfirm 气泡确认框 -->
            <a-popconfirm
                title="是否确认删除？删除后不可恢复！谨慎操作！"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button danger>
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>

    </a-layout-content>
  </a-layout>

<!--  点击编辑按钮-->
  <a-modal
    title="电子书表单"
    v-model:visible="modalVisible"
    :confirm-loading="modalLoading"
    @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col=" { span: 14 }">
      <a-form-item label="封面">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="名称">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="分类">
        <a-cascader
          v-model:value="categoryIds"
          :field-names="{label: 'name', value:'id', children:'children'}"
          :options="level1"
        />

      </a-form-item>
      <a-form-item label="描述">
        <a-input v-model:value="ebook.description" type="text" />
      </a-form-item>

    </a-form>
  </a-modal>

</template>
<script setup>
</script>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import {message} from 'ant-design-vue';
import {Tool} from "@/util/tool";


export default defineComponent({
  name: 'AdminEbook',


  setup(){
    const param = ref();
    param.value = {};
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const loading = ref(false);

    const columns = [
      {
        title: '封面',
        dataIndex: 'cover',
        slots: { customRender: 'cover' }
      },
      {
        title: '名称',
        dataIndex: 'name'
      },
      {
        title: '分类一',
        dataIndex: 'category1Id'
      },
      {
        title: '分类二',
        dataIndex: 'category2Id'
      },
      {
        title: '文档数',
        dataIndex: 'docCount'
      },
      {
        title: '阅读数',
        dataIndex: 'viewCount'
      },
      {
        title: '点赞数',
        dataIndex: 'voteCount'
      },
      {
        title: '操作',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     * */
    const handleQuery = (params: any)=>{
      loading.value = true;
      axios.get("/ebook/list", {
        params:{
          page: params.page,
          size: params.size,
          name: param.value.name  // param是从响应式变量中得到的; params 是 params:any参数传递的
        }
      }).then((response)=>{
        loading.value = false;
        const data = response.data;
        if(data.success){
          ebooks.value = data.content.list;
          //   重置分页按钮
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        }else{
          message.error(data.message);
        }
      });
    };
    /**
     * 表格点击页码时触发
     * */
    const handleTableChange = (pagination: any) => {
      console.log("看看自带的分页参数都有啥: " + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };
    // 表单
    const categoryIds = ref();
    const ebook = ref();
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () =>{
      modalLoading.value = true;
      ebook.value.category1Id = categoryIds.value[0];
      ebook.value.category2Id = categoryIds.value[1];
      // 调用 ebook/save
      axios.post("/ebook/save", ebook.value).then((response)=>{
        const data = response.data; // data = commonResp
        // console.log("save data: ", response.data);
        // console.log("ebook.value: ",ebook.value);
        modalLoading.value = false;
        if(data.success){
          // 成功后就关闭修改的弹窗页面
          modalVisible.value = false;
          // 重新加载数据
          handleQuery({
            // page, size 必须和PageReq.java中变量名一样才能接收到数据
            //  current 当前页
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        }else{
          message.error(data.message);
        }
      });
    };
    /**
    * 编辑
    * */
    const edit = (record: any)=>{
      modalVisible.value = true;
      ebook.value = Tool.copy(record);
      categoryIds.value = [ebook.value.category1Id, ebook.value.category2Id]
    };

    /**
     * 新增
     * */
    const add = ()=>{
      modalVisible.value = true;
      ebook.value = {};
    };
    /**
     * 删除
     * */
    const handleDelete = (id: number) =>{
      axios.delete("/ebook/delete/" + id).then((response)=>{
        const data = response.data;
        console.log("delete id:", id);
        if(data.success){
          handleQuery({
            page: pagination.value.current,
            size: pagination.value.pageSize
          });
        }
      });
    };

    const level1 = ref();
    /**
     * 查询所有分类
    * */
    const handleQueryCategory = () =>{
      loading.value = true;
      axios.get('/category/all').then((response) =>{
        loading.value = false;
        const data = response.data;
        if(data.success){
          const categorys = data.content;
          console.log("原始数组: ", categorys);

          level1.value = []
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构: ", level1.value);
        }else{
          message.error(data.message);
        }
      });
    };


    onMounted(()=>{
      handleQueryCategory();
      handleQuery({
        // page, size 必须和PageReq.java中变量名一样才能接收到数据
        page: 1,
        size: pagination.value.pageSize,
      });
    });

    return{
      param,
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,
      handleQuery,

      edit,
      add,

      ebook,
      modalVisible,
      modalLoading,
      handleModalOk,
      categoryIds,
      level1,

      handleDelete


    }
  }
})
</script>