<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
      >
        <a-menu-item key="welcome">
            <MailOutlined />
                <span>欢迎</span>
        </a-menu-item>

        <a-sub-menu v-for="item in level1" :key="item.id">
<!--          一级菜单-->
          <template v-slot:title>
            <span><user-outlined />{{item.name}}</span>
          </template>
<!--          二级菜单-->
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined /><span>{{child.name}}</span>
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>


    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <div class="welcome" v-show="isShowWelcome">
        <h1>欢迎使用!</h1>
      </div>
      <a-list v-show="!isShowWelcome" item-layout="vertical" size="large"  :grid="{ gutter: 20, column: 3 }"  :data-source="ebooks">
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
            <a-list-item-meta :description="item.description">
              <template #title>
                <router-link :to="'/doc?ebookId?' + item.id">
                  {{item.name}}
                </router-link>
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
import {message} from "ant-design-vue";
import {Tool} from "@/util/tool"

// 前端数据
// const listData: Record<string, string>[] = [];
// // for (let i = 0; i < 23; i++) {
// //   listData.push({
// //     href: 'https://www.antdv.com/',
// //     title: `ant design vue part ${i}`,
// //     avatar: 'https://joeschmoe.io/api/v1/random',
// //     description:
// //         'Ant Design, a design language for background applications, is refined by Ant UED Team.',
// //     content:
// //         'We supply a series of design principles, practical patterns and high quality design resources (Sketch and Axure), to help people create their product prototypes beautifully and efficiently.',
// //   });
// // }
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

    const level1 = ref()
    let categorys: any;
    /**
     * 查询所有分类
     * */
    const handleQueryCategory = () =>{
      axios.get('/category/all').then((response) =>{
        const data = response.data;
        if(data.success){
          categorys = data.content;
          console.log("原始数组: ", categorys);

          level1.value = []
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构: ", level1.value);
        }else{
          message.error(data.message);
        }
      });
    };

    const isShowWelcome = ref(true);
    let categoryId2 = 0;

    const handleQueryEbook = () =>{
      axios.get("/ebook/list",{
        params: {
          page: 1,
          size: 1000,
          categoryId2: categoryId2
        }
      }).then((response)=>{
        // data, content 可以在 console.log(response)中看到结构
        const data = response.data
        // ref()需要.value 来接受值
        ebooks.value = data.content.list
        // console.log(response); // axios拦截器会返回
      });
    }

    const handleClick = (value: any) =>{
      // console.log("menu click", value);
      if(value.key === 'welcome'){
        isShowWelcome.value = true;
      }else{
        categoryId2 = value.key;
        isShowWelcome.value = false;
        handleQueryEbook();
      }
      // isShowWelcome.value = value.key === 'welcome';
    };


    // onMounted()是一个生命周期函数
    onMounted(()=>{
      handleQueryCategory();
      // handleQueryEbook();
    });

    return{
      ebooks,

      //   返回前端页面数据
      //   listData,
      pagination,
      actions,

      handleClick,
      level1,
      isShowWelcome

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


