package com.github.validate.enums;

/**
 * ${DESCRIPTION}
 *
 * @author MENG
 * @version 2020/3/24
 */
public enum RequireEnum
{
    AND("0"),//代表 此字段填 另关联字段必填

    OR("1"),//代表 此字段填 另关联字段选填
    ;

    private String value;

    public String getValue()
    {
        return this.value;
    }

    RequireEnum(String value)
    {
        this.value = value;
    }
}
