const routers = [
    {
        path: '/',
        meta: {
            title: ''
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
            title: ''
        },
        component: (resolve) => require(['@/views/category'], resolve)
    }
];
export default routers;