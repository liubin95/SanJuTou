/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例
import qs from 'qs'; // 根据需求是否导入qs模块

var baseUrl = `${base.dev}` + '/spu';

const spu = {
    /**
     * 根据分类查询spu列表
     *
     * @param {Number} categoryId 分类id
     * @returns
     */
    querySpuByCategoryId(categoryId) {
        return axios.get(baseUrl + '/querySpuByCategoryId', {
            params: {
                categoryId: categoryId
            }
        });
    },
    /**
     * 根据规格选项id集合查找spu列表
     *
     * @param {Array} params 规格id及所选值数组
     * @returns
     */
    querySpuBySpecOptions(params) {
        return axios.post(baseUrl + '/querySpuBySpecOptions', params, { headers: { 'Content-Type': 'application/json' } });
    },
    /**
     *根据商品id获取详情
     *
     * @param {number} spuId 商品id
     */
    querySpuById(spuId) {
        return axios.get(baseUrl + '/querySpuById', {
            params: {
                spuId: spuId
            }
        })
    },
    /**
     *根据skuId查询相关spu
     *
     * @param {Number} skuId
     * @returns
     */
    querySpuBySku(skuId) {
        return axios.get(baseUrl + '/querySpuBySku', {
            params: {
                skuId: skuId
            }
        })
    }
}
export default spu;