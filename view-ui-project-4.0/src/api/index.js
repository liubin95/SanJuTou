/** 
 * api接口的统一出口
 */
import specification from '@/api/specification';
import spu from '@/api/spu';
import cate from '@/api/cate';
import flashSale from '@/api/FlashSale';
import property from '@/api/property';
import sku from '@/api/sku';

// 其他模块的接口……

// 导出接口
export default {
    specification,
    spu,
    cate,
    flashSale,
    property,
    sku
    // ……
}