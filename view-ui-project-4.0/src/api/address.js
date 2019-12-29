/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例

var baseUrl = `${base.dev}` + '/addressInfo';

const addressInfo = {
    /**
     *
     *获取用户地址信息
     * @returns
     */
    queryAddressByCustomId(spuId) {
        return axios.get(baseUrl + '/queryAddressByCustomId');
    }

}
export default addressInfo;