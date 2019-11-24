
package com.sanjutou.shopping.entity.result;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * Code 生成工具类。
 *
 * @author admin
 */
public class CodeBuilder {
    /**
     * 消息类型。
     */
    private String codeType;

    /**
     * 子系统编号。
     */
    private String subSystemCode;

    /**
     * 消息编码。
     */
    private int code;

    /**
     * 从 CODE 字符串解析对应的 CodeBuilder。
     *
     * @param text CODE字符串
     * @return CodeBuilder
     */
    public static CodeBuilder parse(String text) {
        CodeBuilder ret = new CodeBuilder();
        int i = 16;
        if (text != null && text.length() >= i) {
            ret.codeType(text.substring(0, 1));
            ret.subSystemCode(text.substring(1, 4));
            ret.code(Integer.valueOf(text.substring(4, 8)));
        }
        return ret;
    }

    /**
     * 从指定参数生成 CODE 字符串。
     *
     * @param codeType      codeType
     * @param subSystemCode subSystemCode
     * @param code          code
     * @return String CODE字符串
     */
    public static String build(String codeType, String subSystemCode, int code) {
        // 校正 codeType：只保留 1 位
        if (StringUtils.isEmpty(codeType)) {
            codeType = "_";
        } else if (codeType.length() > 1) {
            codeType = codeType.substring(0, 1);
        }

        // 校正 subSystemCode：调整到 3 位
        int subSystemCodeLength = subSystemCode != null ? subSystemCode.length() : 0;
        int j = 3;
        if (subSystemCodeLength > j) {
            subSystemCode = subSystemCode.substring(0, 3);
        } else if (subSystemCodeLength < j) {
            StringBuilder sb = new StringBuilder();
            for (int i = subSystemCodeLength; i < j; i++) {
                sb.append('_');
            }
            if (subSystemCode != null) {
                sb.append(subSystemCode);
            }
            subSystemCode = sb.toString();
        }

        // 校正 code: 调整到 0 - 9999
        code = code % 10000;
        if (code < 0) {
            code = code + 10000;
        }

        // 返回
        return String.format("%s%s%04d", codeType, subSystemCode, code);
    }

    /**
     * 生成对应的 Code 字符串。
     *
     * @return Code 字符串
     */
    public String build() {
        return build(codeType, subSystemCode, code);
    }

    public String codeType() {
        return codeType;
    }

    public CodeBuilder codeType(String codeType) {
        this.codeType = codeType;
        return this;
    }

    public String subSystemCode() {
        return subSystemCode;
    }

    public CodeBuilder subSystemCode(String subSystemCode) {
        this.subSystemCode = subSystemCode;
        return this;
    }

    public int code() {
        return code;
    }

    public CodeBuilder code(int code) {
        this.code = code;
        return this;
    }
}
