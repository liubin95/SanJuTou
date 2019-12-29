const routers = [
    {
        path: '/',
        meta: {
            title: '三巨头商城'
        },
        component: (resolve) => require(['./views/index.vue'], resolve)
    },
    {
        path: '/top',
        meta: {
            title: ''
        },
        component: (resolve) => require(['./components/windowTop.vue'], resolve)
    },
    {
        path: '/category/:id',
        meta: {
            title: '分类首页'
        },
        component: (resolve) => require(['@/views/category'], resolve)
    },
    {
        path: '/goods/:id',
        meta: {
            title: '商品详情'
        },
        component: (resolve) => require(['@/views/goods'], resolve)
    },
    {
        path: '/new-oder/:id/:num',
        meta: {
            title: '下单首页'
        },
        component: (resolve) => require(['@/views/newOder'], resolve)
    },
    {
        path: '/login',
        meta: {
            title: '登录页'
        },
        component: (resolve) => require(['@/components/login'], resolve)
    }
];
export default routers;