<template>
  <a-layout id="components-layout-demo-top-side">
    <the-header></the-header>

      <router-view/>

    <a-layout-footer style="text-align: center">
      Ebook store free
    </a-layout-footer>
  </a-layout>
</template>


<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from "@/components/the-header.vue";

export default defineComponent({
  name: 'app',
  components:{
    TheHeader,
  }
});
</script>


<style scoped>
#components-layout-demo-top-side .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
