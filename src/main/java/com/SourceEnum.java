package com;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 调用方来源身份声明枚举
 *
 * 该枚举作为将数据落地至通用表  或查询通用表所使用的身份声明
 *
 * 仅适用于续签项目范围以内
 *
 * @author fanyuexing
 * @date 2020/11/12 17:03
 */
@Getter
@AllArgsConstructor
public enum SourceEnum {

    /**
     * 客户总览信息查询
     */
    CUSTOMER_OVERVIEW_INFORMATION("1","客户总览信息查询"),

    /**
     * 0-财富客户
     * 1-银杏会员
     * 2-白金会员
     * 3-黑金会员
     * 4-黑钻会员
     * 5-超级黑钻会员
     */
    CUSTOMER_LEVEL_DICTIONARY("2","客戶等级字典查询"),

    /**
     * 客戶详情查询
     */
    CUSTOMER_DETAILS("3","客戶详情查询"),

    /**
     * 客戶详情之到期明细查询
     */
    CUSTOMER_DUE_TO_DETAILS("4","客戶详情之到期明细查询"),

    /**
     * 历史到期明细查询
     */
    HISTORY_EXPIRATION_DETAILS("5","历史到期明细列表"),

    /**
     * 历史到期数据总览
     */
    HISTORY_DATA_OVERVIEW("6","历史到期数据总览"),
    ;

    /**
     * 来源编号
     */
    private final String source;

    /**
     * 来源的中文释义
     */
    private final String message;


    /**
     * 获取指定编号的枚举
     * @param source 编号
     * @return 未查询到匹配来源编号情况返回null
     */
    public static SourceEnum of(String source){
        if (StringUtils.isEmpty(source)){
            return null;
        }
        for (SourceEnum sourceEnum : SourceEnum.values()) {
            if (sourceEnum.getSource().equals(source)){
                return sourceEnum;
            }
        }
        return null;
    }

    /**
     * 获取指定编码对应的message
     * @param source 来源编号
     * @return 返回指定来源编号对应的枚举中文释义  未匹配情况返回null
     */
    public static String getMessage(String source){
        SourceEnum sourceEnum = of(source);
        if (Objects.isNull(sourceEnum)){
            return null;
        }
        return sourceEnum.getMessage();
    }

}
