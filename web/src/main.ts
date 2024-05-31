import { createApp } from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue';
import router from './router';
import store from './store';
import axios from 'axios';

axios.defaults.baseURL = process.env.VUE_APP_SERVER;

// axios拦截器
// 拦截请求
axios.interceptors.request.use(function(config){
    console.log('请求参数: ', config);
    return config;
}, error => {
    return Promise.reject(error);
});
// 拦截返回
axios.interceptors.response.use(function(response){
    console.log('返回结果: ', response);
    return response;
},error => {
    // Promise.reject,逻辑不再往后走，也就不会进到我们的业务代码里
    return Promise.reject(error);
});


const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

// 全局使用图标
const icons: any = Icons;
for (const i in icons) {
    app.component(i, icons[i]);
}

console.log('环境: ', process.env.NODE_ENV);
console.log('服务端地址: ', process.env.VUE_APP_SERVER );