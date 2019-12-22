/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例

var baseUrl = `${base.dev}` + '/sku';

const sku = {
    /**
     * 根据选项集合查询sku
     *
     * @param {Array} params 属性id及所选值数组
     * @returns
     */
    querySkuByPropertyOptions(params) {
        return axios.post(baseUrl + '/querySkuByPropertyOptions', params, { headers: { 'Content-Type': 'application/json' } });
    },
}
export default sku;