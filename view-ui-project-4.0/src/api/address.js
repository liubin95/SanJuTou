/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例
import qs from 'qs'; // 根据需求是否导入qs模块

var baseUrl = `${base.dev}` + '/addressInfo';

const addressInfo = {
    /**
     *
     *获取用户地址信息
     * @returns
     */
    queryAddressByCustomId() {
        return axios.get(baseUrl + '/queryAddressByCustomId');
    },
    /**
     *新增地址信息
     *
     * @param {object} address
     */
    addAddress(address) {
        return axios.post(baseUrl + '/addAddress', qs.stringify(address))
    }

}
export default addressInfo;