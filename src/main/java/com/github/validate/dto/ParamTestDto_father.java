package com.github.validate.dto;

import com.github.validate.annotation.BaseValidate;
import com.github.validate.annotation.RequireField;


/**
 * 父级 参数验证测试 Data transfer object
 *
 * @author MENG
 * @version 2020/3/24
 */
public class ParamTestDto_father
{
    @RequireField
    public String testField;

    public String testField2;

    public String getTestField()
    {
        return this.testField;
    }

    public void setTestField(String testField)
    {
        this.testField = testField;
    }
}
