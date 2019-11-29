/**
 * article模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例
import qs from 'qs'; // 根据需求是否导入qs模块

var baseUrl = `${base.dev}` + '/specification';

const specification = {
    /**
     * 分类下规格及选项
     *
     * @param {String} categoryId 分类id
     * @returns
     */
    queryCategorySpec(categoryId) {
        return axios.get(baseUrl + '/queryCategorySpec', {
            params: {
                categoryId: categoryId
            }
        });
    },
    // 新闻详情,演示    
    articleDetail(id, params) {
        return axios.get(`${base.sq}/topic/${id}`, {
            params: params
        });
    },
    // post提交    
    login(params) {
        return axios.post(`${base.sq}/accesstoken`, qs.stringify(params));
    }
    // 其他接口…………
}
export default specification;