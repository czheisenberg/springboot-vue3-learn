<template>

  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
    <p>
      <a-button type="primary" @click="add()" size="large">
        新增
      </a-button>
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
      <a-form-item label="分类一">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="分类二">
        <a-input v-model:value="ebook.category1Id" />
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


export default defineComponent({
  name: 'AdminEbook',

  setup(){
    const param = ref();
    param.value = {};
    const ebooks = ref();
    const pagination = ref({
      current: 1,
      pageSize: 3,
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
        title: '分类',
        dataIndex: 'category1Id'
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
          size: params.size
        }
      }).then((response)=>{
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content.list;

      //   重置分页按钮
        pagination.value.current = params.page;
        pagination.value.total = data.content.total;
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
    const ebook = ref({});
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () =>{
      modalLoading.value = true;
      // 调用 ebook/save
      axios.post("/ebook/save", ebook.value).then((response)=>{
        const data = response.data; // data = commonResp
        console.log("save data: ", response.data);
        console.log("ebook.value: ",ebook.value);
        if(data.success){
          // 成功后就关闭修改的弹窗页面
          modalVisible.value = false;
          modalLoading.value = false;

          // 重新加载数据
          handleQuery({
            // page, size 必须和PageReq.java中变量名一样才能接收到数据
            //  current 当前页
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        }
      });
    };
    /**
    * 编辑
    * */
    const edit = (record: any)=>{
      modalVisible.value = true;
      ebook.value = record;
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


    onMounted(()=>{
      handleQuery({
        // page, size 必须和PageReq.java中变量名一样才能接收到数据
        page: 1,
        size: pagination.value.pageSize,
      });
    });

    return{
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChange,

      edit,
      add,

      ebook,
      modalVisible,
      modalLoading,
      handleModalOk,

      handleDelete


    }
  }
})
</script>