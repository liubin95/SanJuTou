/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例

var baseUrl = `${base.dev}` + '/property';

const property = {
    /**
     *
     *根据spu查询属性列表
     * @param {number} spuId
     * @returns
     */
    queryPropertyBySpu(spuId) {
        return axios.get(baseUrl + '/queryPropertyBySpu', {
            params: {
                spuId: spuId
            }
        });
    }

}
export default property;