/**
 * 分类模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例
import qs from 'qs'; // 根据需求是否导入qs模块


var baseUrl = `${base.dev}` + '/customer';

const customer = {
    /**
     *登录
     *
     * @param {object} param
     * @returns
     */
    login(param) {
        return axios.post(baseUrl + '/login', qs.stringify(param));
    },

    /**
     *注册
     *
     * @param {object} param
     * @returns
     */
    reg(param) {
        return axios.post(baseUrl + '/reg', qs.stringify(param));
    },
    /**
     *检查登录
     *
     * @returns
     */
    checkLogin() {
        return axios.get(baseUrl + '/checkLogin');
    }
}
export default customer;