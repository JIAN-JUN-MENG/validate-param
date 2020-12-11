package com.github.validate.dto;

import com.github.validate.annotation.*;

import java.util.List;


/**
 * 参数验证测试 Data transfer object
 *
 * 使用必看
 *
 * 1: 参数验证注解可叠加,基本类型的参数必须以String类型定义，否则会有参数转换错误
 *
 * 3: spring boot 扫描器必须扫描 com.github.validate 目录
 *
 *    ComponentScan({"com.github.**","......"})
 *
 * 4: 需要验证的接口需要加上 ValidateParameter 注解
 *
 * @author MENG
 * @version 2020/3/24
 */
public class ParamTestDto extends ParamTestDto_father
{
    /**
     * 必填参数注解
     *
     * 参数 : description = 返回描述
     *
     * 备注 :
     */
    @RequireField
    private String requireField;

    /**
     * 必填参数注解
     *
     * 参数 : description = 返回描述
     *
     * 备注 :
     */
    @RequireField(description = "自定义参数不符合规范的描述")
    private String requireFieldDesc;

    /**
     * 特殊必填参数注解
     *
     * 参数 : description = 返回描述
     *
     * 参数 : fieldNames = {"fieldName"}; fieldNames为字段名
     *
     * 参数 : values = {"andOne匹配的值"};
     *
     *  当 andOne = 1时, andTwo andThree 字段必填
     *
     */
    @SpecialAndRequireField(fieldNames = {"andTwo","andThree"},values = {"1"})
    private String andOne;

    /**
     * 特殊必填参数注解
     *
     * 参数 : description = 返回描述
     *
     * 参数 : fieldNames = {"fieldName"}; fieldNames为字段名
     *
     * 参数 : values = {"andOne匹配的值"};
     *
     *  当 andTwo 不为空时, andTwo andThree 字段必填
     *
     */
    @SpecialAndRequireField(fieldNames = {"andOne","andThree"})
    private String andTwo;

    private String andThree;


    /**
     *
     * 参数 values = [@SpecialAndRequireField,@SpecialAndRequireField]
     *
     * 参数 : description = 返回描述
     *
     * 当 andOneOrAndTwo = 1 时 ，andTwo andThree 字段必填
     *
     * 当 andOneOrAndTwo = 2 时 ，andOne 字段必填
     *
     */
    @SpecialAndRequireFieldMany(values = {@SpecialAndRequireField(fieldNames = {"andTwo"},values = {"1"})
            ,@SpecialAndRequireField(fieldNames = {"andOne"},values = {"2"})},description = "返回描述")
    private String andOneOrAndTwo;



    /**
     * 特殊必填参数注解
     *
     * 参数 : description = 返回描述
     *
     * 备注 : 1: 字段 Or1 , Or2 和 Or3 其中必须有一个不为空
     */
    @SpecialOrRequireField(fieldNames = {"orTwo","orThree"})
    private String orOne;

    private String orTwo;

    private String orThree;

    /**
     * 实体类嵌套参数验证注解 一对一
     *
     * 参数 : description = 返回描述
     *
     * 备注 : 1: 实体字段并加上 @ModelIllegal 注解,才可以继续验证中实体中的字段
     *
     * 对象不能为空,需要加上 @RequireField 字段
     *
     */
    @ModelIllegal
    private ParamTestModelDto paramTestModelDto;

    /**
     * List实体类嵌套参数验证注解 一对多
     *
     * 参数 : description = 返回描述
     *
     * 备注 : 1:List字段并加上 @ListIllegal 注解,才可以继续验证中实体中的字段
     */
    @ListIllegal
    @RequireField
    private List<ParamTestModelDto> paramTestModelDtoList;

    /**
     * 自定义正则参数验证注解
     *
     * 参数 : * reg = 正则, description = 返回描述
     *
     * 备注 : 1: reg 参数必填,需要符合正则规则
     */
    @RegularIllegal(reg = "^1[3|4|5|7|8][0-9]{9}$",description = "xxx符合规范")
    private String reg;

    /**
     * 字符长度参数验证注解
     *
     * 参数 : * start = 最低长度, * end = 最长长度, description = 返回描述
     *
     * 备注 : 1: start,end 参数必填 ,>= start, end <=
     */
    @LengthIllegal(start = 1,end = 10)
    private String length;

    /**
     * Integer类型参数验证注解
     *
     * 参数 : start = 最小值, end = 最大值, description = 返回描述
     *
     * 备注 : 1: >= start , <= end
     */
    @IntegerIllegal
    private String intNum;
    @IntegerIllegal(start = 1, end = 5)
    private String intScopeNum;

    /**
     * Double类型参数验证注解
     *
     * 参数 : start = 最小值, end = 最大值, description = 返回描述
     *
     * 备注 : 1: >= start , <= end
     */
    @DoubleIllegal
    private String douNum;
    @DoubleIllegal(start = 1.5, end = 5.5)
    private String douScopeNum;


    /**
     * Long类型参数验证注解
     *
     * 参数 : start = 最小值, end = 最大值, description = 返回描述
     *
     * 备注 : 1: >= start , <= end
     */
    @LongIllegal
    private String longNum;
    @LongIllegal(start = 1L, end = 5L)
    private String longScopeNum;

    /**
     * Date类型参数验证注解
     *
     * 参数 : format = 日期格式, description = 返回描述
     *
     * 备注 : 1: format 默认等于 "yyyy-MM-dd"
     */
    @DateIllegal
    private String dateDefault;
    @DateIllegal(format = "yyyy-MM-dd HH:mm:ss")
    private String dateCustomer;

    /**
     * 包含有 validateSign 函数,会自动执行验签函数
     *
     * @return boolean
     */
    public Boolean validateSign()
    {
        String sign = "";//获取sign

        //TODO 验签

        return true;
    }






    public String getAndOne()
    {
        return this.andOne;
    }

    public void setAndOne(String andOne)
    {
        this.andOne = andOne;
    }

    public String getAndTwo()
    {
        return this.andTwo;
    }

    public void setAndTwo(String andTwo)
    {
        this.andTwo = andTwo;
    }

    public String getAndThree()
    {
        return this.andThree;
    }

    public void setAndThree(String andThree)
    {
        this.andThree = andThree;
    }

    public String getOrOne()
    {
        return this.orOne;
    }

    public void setOrOne(String orOne)
    {
        this.orOne = orOne;
    }

    public String getOrTwo()
    {
        return this.orTwo;
    }

    public void setOrTwo(String orTwo)
    {
        this.orTwo = orTwo;
    }

    public String getOrThree()
    {
        return this.orThree;
    }

    public void setOrThree(String orThree)
    {
        this.orThree = orThree;
    }

    public String getReg()
    {
        return reg;
    }

    public void setReg(String reg)
    {
        this.reg = reg;
    }

    public ParamTestModelDto getParamTestModelDto()
    {
        return this.paramTestModelDto;
    }

    public void setParamTestModelDto(ParamTestModelDto paramTestModelDto)
    {
        this.paramTestModelDto = paramTestModelDto;
    }

    public List<ParamTestModelDto> getParamTestModelDtoList()
    {
        return this.paramTestModelDtoList;
    }

    public void setParamTestModelDtoList(
        List<ParamTestModelDto> paramTestModelDtoList)
    {
        this.paramTestModelDtoList = paramTestModelDtoList;
    }

    public String getLength()
    {
        return length;
    }

    public void setLength(String length)
    {
        this.length = length;
    }

    public String getIntNum()
    {
        return intNum;
    }

    public void setIntNum(String intNum)
    {
        this.intNum = intNum;
    }

    public String getIntScopeNum()
    {
        return intScopeNum;
    }

    public void setIntScopeNum(String intScopeNum)
    {
        this.intScopeNum = intScopeNum;
    }

    public String getDouNum()
    {
        return douNum;
    }

    public void setDouNum(String douNum)
    {
        this.douNum = douNum;
    }

    public String getDouScopeNum()
    {
        return douScopeNum;
    }

    public void setDouScopeNum(String douScopeNum)
    {
        this.douScopeNum = douScopeNum;
    }

    public String getLongNum()
    {
        return longNum;
    }

    public void setLongNum(String longNum)
    {
        this.longNum = longNum;
    }

    public String getLongScopeNum()
    {
        return longScopeNum;
    }

    public void setLongScopeNum(String longScopeNum)
    {
        this.longScopeNum = longScopeNum;
    }

    public String getDateDefault()
    {
        return dateDefault;
    }

    public void setDateDefault(String dateDefault)
    {
        this.dateDefault = dateDefault;
    }

    public String getDateCustomer()
    {
        return dateCustomer;
    }

    public void setDateCustomer(String dateCustomer)
    {
        this.dateCustomer = dateCustomer;
    }

    public String getRequireField()
    {
        return this.requireField;
    }

    public void setRequireField(String requireField)
    {
        this.requireField = requireField;
    }

    public String getRequireFieldDesc()
    {
        return this.requireFieldDesc;
    }

    public void setRequireFieldDesc(String requireFieldDesc)
    {
        this.requireFieldDesc = requireFieldDesc;
    }

    public String getAndOneOrAndTwo()
    {
        return andOneOrAndTwo;
    }

    public void setAndOneOrAndTwo(String andOneOrAndTwo)
    {
        this.andOneOrAndTwo = andOneOrAndTwo;
    }
}
