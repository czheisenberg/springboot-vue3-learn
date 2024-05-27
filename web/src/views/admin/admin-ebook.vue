<template>

  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >

      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="ebooks"
          :pagination="pagination"
          :loading="loading"
          @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary">
              编辑
            </a-button>
            <a-button type="danger">
              删除
            </a-button>
          </a-space>
        </template>
      </a-table>

    </a-layout-content>
  </a-layout>
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
        slots: { customRender: 'category' }
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
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }
    ];

    /**
     * 数据查询
     * */
    const handleQuery = (params: any)=>{
      loading.value = true;
      axios.get("/ebook/list", params).then((response)=>{
        loading.value = false;
        const data = response.data;
        ebooks.value = data.content;

      //   重置分页按钮
        pagination.value.current = params.page;
      });
    };
    /**
     * 表格点击页码时触发
     * */
    const handleTableChage = (pagination: any) => {
      console.log("看看自带的分页参数都有啥: " + pagination);
      handleQuery({
        page: pagination.current,
        size: pagination.pageSize
      });
    };

    onMounted(()=>{
      handleQuery({});
    });

    return{
      ebooks,
      pagination,
      columns,
      loading,
      handleTableChage
    }
  }
})
</script>