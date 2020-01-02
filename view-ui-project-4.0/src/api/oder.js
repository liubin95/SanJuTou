/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例
import qs from 'qs'; // 根据需求是否导入qs模块

var baseUrl = `${base.dev}` + '/oderInfo';

const oderInfo = {
    /**
     *新增订单接口。
     *
     * @param {object} oderInfo
     */
    newOder(oderInfo) {
        return axios.post(baseUrl + '/newOder', qs.stringify(oderInfo))
    }

}
export default oderInfo;