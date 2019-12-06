/**
 * 秒杀接口列表
 */
import base from './base'; // 导入接口域名列表
import axios from '@/libs/http'; // 导入http中创建的axios实例

var baseUrl = `${base.dev}` + '/FlashSale';

const flashSale = {
    /**
     * 查询秒杀
     *
     * @returns
     */
    queryFlashSale() {
        return axios.get(baseUrl + '/queryFlashSale');
    }
}
export default flashSale;