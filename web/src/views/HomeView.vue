<template>
  <a-layout>
  <a-layout-sider width="200" style="background: #fff">
    <a-menu
        v-model:selectedKeys="selectedKeys2"
        v-model:openKeys="openKeys"
        mode="inline"
        :style="{ height: '100%', borderRight: 0 }"
    >
      <a-sub-menu key="sub1">
        <template #title>
              <span>
                <user-outlined />
                subnav 1
              </span>
        </template>
        <a-menu-item key="1">option1</a-menu-item>
        <a-menu-item key="2">option2</a-menu-item>
        <a-menu-item key="3">option3</a-menu-item>
        <a-menu-item key="4">option4</a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="sub2">
        <template #title>
              <span>
                <laptop-outlined />
                subnav 2
              </span>
        </template>
        <a-menu-item key="5">option5</a-menu-item>
        <a-menu-item key="6">option6</a-menu-item>
        <a-menu-item key="7">option7</a-menu-item>
        <a-menu-item key="8">option8</a-menu-item>
      </a-sub-menu>
      <a-sub-menu key="sub3">
        <template #title>
              <span>
                <notification-outlined />
                subnav 3
              </span>
        </template>
        <a-menu-item key="9">option9</a-menu-item>
        <a-menu-item key="10">option10</a-menu-item>
        <a-menu-item key="11">option11</a-menu-item>
        <a-menu-item key="12">option12</a-menu-item>
      </a-sub-menu>
    </a-menu>
  </a-layout-sider>


  <a-layout-content
      :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
  >

    <a-list  item-layout="vertical" size="large"  :grid="{ gutter: 20, column: 5 }"  :data-source="ebooks">
      <template #footer>
        <div>
          <b>books</b>
        </div>
      </template>
      <template #renderItem="{ item }">
        <a-list-item key="item.name">
          <template #actions>
          <span v-for="{ icon, text } in actions" :key="icon">
            <component :is="icon" style="margin-right: 8px" />
            {{ text }}
          </span>
          </template>
<!--          <template #extra>-->
<!--            <img-->
<!--                width="272"-->
<!--                alt="logo"-->
<!--                src="https://gw.alipayobjects.com/zos/rmsportal/mqaQswcyDLcXyDKnZfES.png"-->
<!--            />-->
<!--          </template>-->
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.href">{{ item.name }}</a>
            </template>
            <template #avatar><a-avatar :src="item.cover" /></template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>

  </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent , onMounted, ref} from 'vue';
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import axios from 'axios';

// 前端数据
const listData: Record<string, string>[] = [];
for (let i = 0; i < 23; i++) {
  listData.push({
    href: 'https://www.antdv.com/',
    title: `ant design vue part ${i}`,
    avatar: 'https://joeschmoe.io/api/v1/random',
    description:
        'Ant Design, a design language for background applications, is refined by Ant UED Team.',
    content:
        'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
  });
}
// pagination 分页
const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 3,
};
// 图标
const actions: Record<string, any>[] = [
  { icon: StarOutlined, text: '156' },
  { icon: LikeOutlined, text: '156' },
  { icon: MessageOutlined, text: '666' },
];

export default defineComponent({
  name: 'HomeView',
  setup(){
    const ebooks = ref()

    // onMounted()是一个生命周期函数
    onMounted(()=>{
      axios.get("/ebook/list").then((response)=>{
        // data, content 可以在 console.log(response)中看到结构
        const data = response.data
        // ref()需要.value 来接受值
        ebooks.value = data.content
        // console.log(response); // axios拦截器会返回
      });
    });

    return{
      ebooks,

    //   返回前端页面数据
      listData,
      pagination,
      actions

    }
  }
});
</script>

<style scoped>
  .ant-avatar {
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 8%;
    margin: 5px 0;
  }

</style>


