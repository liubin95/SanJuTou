
package com.sanjutou.shopping.entity.result;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.io.Serializable;

/**
 * 返回的消息。
 * <br>
 * Code 全长为 8 位 ASCII 字符，结构定义如下：
 * <table>
 * <tr>
 * <td>位数</td>
 * <td>内容</td>
 * <td>备注</td>
 * </tr>
 * <tr>
 * <td>0</td>
 * <td>消息类型</td>
 * <td>S: 成功，E: 失败，W: 警告</td>
 * </tr>
 * <tr>
 * <td>1 - 3</td>
 * <td>子系统编号</td>
 * <td>各系统和子系统自己决定，如：COD、SYN、ENT、GDN，<br>
 * 只要保证每个系统中的子系统不重复即可。<br>
 * 此外，以下编号预留：SYS、APP、COM，不要使用。
 * </td>
 * </tr>
 * <td>4 - 7</td>
 * <td>返回代码标识</td>
 * <td>4 位整数，不足的部分在左侧补 0。</td>
 * </tr>
 * </table>
 * 如：ECOM0001、WGDN3456。
 *
 * @author admin
 */
public class CodeMessage implements Serializable {
    /**
     * 默认的序列化版本号。
     */
    private static final long serialVersionUID = 1L;

    /**
     * 是否成功。
     */
    private boolean succeeded;

    /**
     * 状态码。
     */
    private String code;

    /**
     * 错误信息。
     */
    private String msg;

    /**
     * 默认构造函数。
     */
    public CodeMessage() {
        // 什么也不做
    }

    /**
     * 带消息格式的构造函数。
     * 
     * @param succeeded succeeded
     * @param code code
     * @param messageFormat messageFormat
     * @param args args
     */
    public CodeMessage(boolean succeeded, String code, String messageFormat, Object... args) {
        this.succeeded = succeeded;
        this.code = code;
        this.msg = messageFormat;
        if (args != null && args.length > 0 && !StringUtils.isEmpty(msg)) {
            msg = String.format(msg, args);
        }
    }

    /**
     * 拷贝构造式的构造函数。
     * 
     * @param source source
     * @param args args
     */
    public CodeMessage(CodeMessage source, Object... args) {
        succeeded = source.isSucceeded();
        code = source.getCode();
        msg = source.getMsg();
        if (args != null && args.length > 0 && !StringUtils.isEmpty(msg)) {
            msg = String.format(msg, args);
        }
    }

    /**
     * 拷贝 CodeMessage 实例的信息。
     * 
     * @param source source
     * @param args args
     * @return CodeMessage
     */
    public CodeMessage setCodeMsg(CodeMessage source, Object... args) {
        if (source != this && source != null) {
            succeeded = source.isSucceeded();
            code = source.getCode();
            msg = source.getMsg();
            if (args != null && args.length > 0 && !StringUtils.isEmpty(msg)) {
                msg = String.format(msg, args);
            }
        }
        return this;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
