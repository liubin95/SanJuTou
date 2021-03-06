import Vue from 'vue';
import ViewUI from 'view-design';
import VueRouter from 'vue-router';
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';
import 'view-design/dist/styles/iview.css';
import axios from 'axios'
import api from './api' // 导入api接口
import store from './store/index';
import vRegion from 'v-region';

Vue.use(vRegion);
Vue.prototype.$api = api; // 将api挂载到vue的原型上
Vue.use(VueRouter);
Vue.use(ViewUI);
Vue.prototype.$axios = axios

Vue.prototype.$Message.config({
    top: 100,
    duration: 3,
});
// 路由配置
const RouterConfig = {
    mode: 'history',
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    ViewUI.LoadingBar.start();
    Util.title(to.meta.title);
    next();
});

router.afterEach((to, from, next) => {
    ViewUI.LoadingBar.finish();
    window.scrollTo(0, 0);
});

new Vue({
    el: '#app',
    router: router,
    render: h => h(App),
    components: { App },
    store,
});
