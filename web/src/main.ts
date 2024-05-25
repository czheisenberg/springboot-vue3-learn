import { createApp } from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue';
import 'ant-design-vue/dist/reset.css';
import router from './router';
import store from './store';
import axios from 'axios';

axios.defaults.baseURL = process.env.VUE_APP_SERVER;

const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

console.log('环境: ', process.env.NODE_ENV);
console.log('服务端地址: ', process.env.VUE_APP_SERVER );