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
    <pre>
      {{ebooks}}
    </pre>
  </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent , onMounted, ref} from 'vue';
import axios from 'axios';

export default defineComponent({
  name: 'HomeView',
  setup(){
    console.log("setup");
    const ebooks = ref()

    // onMounted()是一个生命周期函数
    onMounted(()=>{
      console.log("onMounted")
      axios.get("http://localhost:9999/ebook/list?name=php").then((response)=>{
        // data, content 可以在 console.log(response)中看到结构
        const data = response.data
        // ref()需要.value 来接受值
        ebooks.value = data.content
        console.log(response);
      });
    });

    return{ebooks}
  }
});
</script>
