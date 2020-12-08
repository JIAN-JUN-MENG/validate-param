package com.github.validate.dto;

import com.github.validate.annotation.*;


/**
 * Model 参数验证测试 Data transfer object
 *
 * @author MENG
 * @version 2020/3/24
 * @see
 */
public class ParamTestModelDto extends BaseValidate
{
    /**
     * 必填选项
     */
    @RequireField
    private String travelId;

    public String getTravelId()
    {
        return this.travelId;
    }

    public void setTravelId(String travelId)
    {
        this.travelId = travelId;
    }
}
