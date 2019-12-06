/**
 * 分类模块接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例

var baseUrl = `${base.dev}` + '/category';

const cate = {
    /**
     * 查询所有分类
     *
     * @returns
     */
    queryAllCategory() {
        return axios.get(baseUrl + '/queryAllCategory');
    }
}
export default cate;