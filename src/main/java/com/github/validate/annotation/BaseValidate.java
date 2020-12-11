package com.github.validate.annotation;

import com.github.validate.util.ValidateCodeEnum;
import com.github.validate.util.ValidateConstants;
import com.github.validate.util.ValidateResult;
import com.github.validate.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


/**
 * 后台验证 基类
 *
 * @author MENG
 * @version 2019/1/21
 */
public class BaseValidate
{
    /**
     * 公共 验证必填字段
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult requireField(Object thiz, Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(RequireField.class))
            {
                RequireField obj = field.getAnnotation(RequireField.class);

                String description = obj.description();// 获取注解描述

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                Boolean isFill = true;

                isFill = isFill(value);

                if(isFill == false)
                {
                    return publicReturn(description,field, ValidateCodeEnum.REQUIRE_FIELD);
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 特殊必填验证（多项）
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult specialAndRequireFieldMany(Object thiz,Field... fields)
            throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(SpecialAndRequireFieldMany.class))
            {
                SpecialAndRequireFieldMany obj = field.getAnnotation(SpecialAndRequireFieldMany.class);

                String description = obj.description();// 获取注解描述

                SpecialAndRequireField[] values = obj.values();// 获取必填字段名称

                if(values != null && values.length > 0)
                {
                    for(SpecialAndRequireField specialAndRequireField : values)
                    {
                        ValidateResult validateResult = validateSpecialAndRequireField(specialAndRequireField,thiz,field,fields);

                        if(!validateResult.isSuccess())
                        {
                            return validateResult;
                        }

                    }
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     *
     *
     * @param obj SpecialAndRequireField注解对象
     * @param thiz 当前验证对象
     * @param field 验证的当前字段
     * @param fields 验证的所有字段
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    private ValidateResult validateSpecialAndRequireField(SpecialAndRequireField obj,Object thiz,Field field,Field[] fields) throws IllegalAccessException
    {
//        for (Field field : fields)
//        {
//            if (field.isAnnotationPresent(SpecialAndRequireField.class))
//            {
//                SpecialAndRequireField obj = field.getAnnotation(SpecialAndRequireField.class);

                String description = obj.description();// 获取注解描述

                String[] fieldAndNames = obj.fieldNames();// 获取必填字段名称

                String[] values = obj.values();// 获取必填字段名称

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                if (values != null && values.length > 0)
                {
                    boolean isExists = false;

                    for(String str : values)
                    {
                        if(str.toString().equals(value))
                        {
                            isExists = true;

                            break;
                        }
                    }

                    if(isExists == false)
                    {
                        return ValidateUtil.returnSuccess();
                    }
                }

                Boolean isFill = true;

                isFill = isFill(value);

                //当有fieldAndNames 有值 此必填 彼必填
                if(isFill == true && fieldAndNames != null && fieldAndNames.length > 0)
                {
                    Field[] relationFields = ValidateUtil.getFieldsByFieldName(fieldAndNames,fields);

                    for(Field relationField : relationFields)
                    {
                        relationField.setAccessible(true);

                        Boolean relationIsFill = isFill(relationField.get(thiz));

                        if(relationIsFill == false)
                        {
                            return publicReturn(description,relationField, ValidateCodeEnum.REQUIRE_FIELD);
                        }
                    }
                }

//            }
//        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 特殊必填 验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult specialAndRequireField(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(SpecialAndRequireField.class))
            {
                SpecialAndRequireField obj = field.getAnnotation(SpecialAndRequireField.class);

                String description = obj.description();// 获取注解描述

                String[] fieldAndNames = obj.fieldNames();// 获取必填字段名称

                String[] values = obj.values();// 获取必填字段名称

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                if (values != null && values.length > 0)
                {
                    boolean isExists = false;

                    for(String str : values)
                    {
                        if(str.toString().equals(value))
                        {
                            isExists = true;

                            break;
                        }
                    }

                    if(isExists == false)
                    {
                        return ValidateUtil.returnSuccess();
                    }
                }

                Boolean isFill = true;

                isFill = isFill(value);

                //当有fieldAndNames 有值 此必填 彼必填
                if(isFill == true && fieldAndNames != null && fieldAndNames.length > 0)
                {
                    Field[] relationFields = ValidateUtil.getFieldsByFieldName(fieldAndNames,fields);

                    for(Field relationField : relationFields)
                    {
                        relationField.setAccessible(true);

                        Boolean relationIsFill = isFill(relationField.get(thiz));

                        if(relationIsFill == false)
                        {
                            return publicReturn(description,relationField, ValidateCodeEnum.REQUIRE_FIELD);
                        }
                    }
                }

            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 特殊选填验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult specialOrRequireField(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(SpecialOrRequireField.class))
            {
                SpecialOrRequireField obj = field.getAnnotation(SpecialOrRequireField.class);

                String description = obj.description();// 获取注解描述

                String[] fieldOrNames = obj.fieldNames();// 获取选填字段名称

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                Boolean isFill = true;

                isFill = isFill(value);

                if(isFill == false)
                {
                    //当有fieldAndNfieldOrNamesames 此彼二者选填
                    if(fieldOrNames != null && fieldOrNames.length > 0)
                    {
                        Field[] relationFields = ValidateUtil.getFieldsByFieldName(fieldOrNames,fields);

                        Boolean relationIsFill = false;

                        for(Field relationField : relationFields)
                        {
                            relationField.setAccessible(true);

                            relationIsFill = isFill(relationField.get(thiz));

                            if(relationIsFill == true)
                            {
                                break;
                            }
                        }

                        if(relationIsFill == false)
                        {
                            return publicReturn(description, field.getName() + "," + StringUtils.join(fieldOrNames, ","),ValidateCodeEnum.SPECIAL_OR_REQUIRE_FIELD);
                        }
                    }
                    else
                    {
                        return publicReturn(description,field,ValidateCodeEnum.REQUIRE_FIELD);
                    }
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }


    /**
     * 判断是否有值
     *
     * @param value 验证值
     * @return boolean
     */
    public boolean isFill(Object value)
    {
        Boolean isFill = true;

        if(value == null)
        {
            isFill = false;
        }

        if(value instanceof String)
        {
            if (value == null || "".equals(value.toString()))
            {
                isFill = false;
            }
        }
        else if(value instanceof List)
        {
            List list = (List)value;

            //反射执行list中每个对象 调用Mehtod  —（validate）
            if (value == null || list.size() == 0)
            {
                isFill = false;
            }
        }

        return isFill;
    }

    /**
     * 公共 验证字段长度合法性
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult lengthIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(LengthIllegal.class))
            {
                LengthIllegal obj = field.getAnnotation(LengthIllegal.class);

                String description = obj.description();// 获取注解描述

                int start = obj.start();//开始长度

                int end = obj.end();//结束长度

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.LENGTH_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }

                //判断长度是否合法
                if(value instanceof List)
                {
                    if (((List)value).size() < start || ((List)value).size() > end)
                    {
                        return publicReturn(description,field, ValidateCodeEnum.LENGTH_ILLEGAL);
                    }
                }
                else if(value instanceof String)
                {
                    if (value.toString().length() < start || value.toString().length() > end)
                    {
                        return publicReturn(description,field, ValidateCodeEnum.LENGTH_ILLEGAL);
                    }
                }

            }
        }

        return ValidateUtil.returnSuccess();
    }





    /**
     * 是否是数字 int 类型 和 范围验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult integerIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(IntegerIllegal.class))
            {
                IntegerIllegal obj = field.getAnnotation(IntegerIllegal.class);

                String description = obj.description();// 获取注解描述

                //数字范围
                int start = obj.start();
                int end = obj.end();


                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }

                //判断是否是数字格式
                int tmp = 0;

                try
                {
                    tmp = Integer.valueOf(value.toString());
                }
                catch (Exception e)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }


                if (tmp < start || tmp > end)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 是否是数字 double 类型 和 范围验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult doubleIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(DoubleIllegal.class))
            {
                DoubleIllegal obj = field.getAnnotation(DoubleIllegal.class);

                String description = obj.description();// 获取注解描述

                //数字范围
                double start = obj.start();
                double end = obj.end();


                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }

                //判断是否是数字格式
                double tmp = 0;

                try
                {
                    tmp = Double.valueOf(value.toString());
                }
                catch (Exception e)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }


                if (tmp < start || tmp > end)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }


    /**
     * 是否是数字 long 类型 和 范围验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult longIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(LongIllegal.class))
            {
                LongIllegal obj = field.getAnnotation(LongIllegal.class);

                String description = obj.description();// 获取注解描述

                //数字范围
                long start = obj.start();
                long end = obj.end();


                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }

                //判断是否是数字格式
                long tmp = 0;

                try
                {
                    tmp = Long.valueOf(value.toString());
                }
                catch (Exception e)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }


                if (tmp < start || tmp > end)
                {
                    return publicReturn(description,field,ValidateCodeEnum.NUMBER_ILLEGAL);
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 是否是日期类型
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult dateIllegal(Object thiz,Field... fields) throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(DateIllegal.class))
            {
                DateIllegal obj = field.getAnnotation(DateIllegal.class);

                String description = obj.description();// 获取注解描述

                //数字范围
                String format = obj.format();

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.DATE_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }


                //判断是否是日期格式
                try
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

                    simpleDateFormat.parse(value.toString());
                }
                catch (Exception e)
                {
                    return publicReturn(description,field,ValidateCodeEnum.DATE_ILLEGAL);
                }

            }
        }

        return ValidateUtil.returnSuccess();
    }


    /**
     * 是否是Boolean类型
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult booleanIllegal(Object thiz,Field... fields) throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(BooleanIllegal.class))
            {
                BooleanIllegal obj = field.getAnnotation(BooleanIllegal.class);

                String description = obj.description();// 获取注解描述

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                //判断是否为空
                if (value == null || "".equals(value))
                {
//                    return publicReturn(description,field,ValidateCodeEnum.DATE_ILLEGAL);
                    return ValidateUtil.returnSuccess();
                }


                //判断是否是boolean格式
                try
                {
                    Boolean.valueOf(value.toString());
                }
                catch (Exception e)
                {
                    return publicReturn(description,field,ValidateCodeEnum.BOOLEAN_ILLEGAL);
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 是否是LIST类型
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult listIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(ListIllegal.class))
            {
                ListIllegal obj = field.getAnnotation(ListIllegal.class);

                String description = obj.description();// 获取注解描述

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                if(value != null)
                {
                    if(value instanceof List)
                    {
                        List list = (List)value;

                        if (list.size() == 0)
                        {
                            return ValidateUtil.returnSuccess();
                        }

                        BaseValidate validate = new BaseValidate();

                        //反射执行list中每个对象 调用Mehtod  —（validate）
                        for(Object object : list)
                        {
//                            Method mothod = null;

                            try
                            {
//                                mothod = object.getClass().getMethod(ValidateConstants.BASE_VALIDATE_METHOD_NAME);
//
//                                ValidateResult validateResult = (ValidateResult)mothod.invoke(object);

                                ValidateResult validateResult = validate.validate(object);

                                if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
                                {
                                    return validateResult;
                                }
                            }
                            catch (Exception e)
                            {
                                continue;
                            }
                        }
                    }
                    else
                    {
                        return publicReturn(description,field,ValidateCodeEnum.LIST_ILLEGAL);
                    }
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 是否是对象（除MAP LIST STRING）
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult modelIllegal(Object thiz,Field... fields)
        throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(ModelIllegal.class))
            {
                ModelIllegal obj = field.getAnnotation(ModelIllegal.class);

                String description = obj.description();// 获取注解描述

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                if(value != null)
                {
                    if(value instanceof List)
                    {
                        return publicReturn(description,field,ValidateCodeEnum.MODEL_ILLEGAL);
                    }
                    if(value instanceof Map)
                    {
                        return publicReturn(description,field,ValidateCodeEnum.MODEL_ILLEGAL);
                    }
                    if(value instanceof String)
                    {
                        return publicReturn(description,field,ValidateCodeEnum.MODEL_ILLEGAL);
                    }
                    else
                    {
                        //反射执行对象 调用Mehtod  —（validate）
                        Method mothod = null;

                        try
                        {
                            BaseValidate validate = new BaseValidate();

                            ValidateResult validateResult = validate.validate(value);

                            if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
                            {
                                validateResult.setMessage(field.getName() + " { " + validateResult.getMessage() + " } ");

                                return validateResult;
                            }
                        }
                        catch (Exception e)
                        {
                            return ValidateUtil.returnSuccess();
                        }
                    }
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 正则验证
     *
     * @param thiz  验证的当前对象
     * @param fields 需要验证的field
     * @return validateResult
     * @throws IllegalAccessException 私有字段访问权限异常
     */
    protected ValidateResult regularIllegal(Object thiz,Field... fields) throws IllegalAccessException
    {
        if(fields == null || fields.length == 0)
        {
            fields = thiz.getClass().getDeclaredFields();
        }

        for (Field field : fields)
        {
            if (field.isAnnotationPresent(RegularIllegal.class))
            {
                RegularIllegal obj = field.getAnnotation(RegularIllegal.class);

                String description = obj.description();// 获取注解描述

                String reg = obj.reg();

                field.setAccessible(true); // 设置属性是可以访问的(私有的也可以)

                Object value = field.get(thiz);

                if(value != null)
                {
                    try
                    {
                        String content = (String)value;

                        boolean isMatch= Pattern.matches(reg, content);

                        if(isMatch != true)
                        {
                            return publicReturn(description,field,ValidateCodeEnum.REGULAR_ILLEGAL);
                        }
                    }
                    catch (Exception e)
                    {
                        return publicReturn(description,field,ValidateCodeEnum.REGULAR_ILLEGAL);
                    }
                }
            }
        }

        return ValidateUtil.returnSuccess();
    }


    /**
     * 公共ruturn 函数
     *
     * @param description 自定义返回错误描述
     * @param field 验证的字段
     * @param codeEnum 默认返回的错误描述
     * @return validateResult
     */
    public ValidateResult publicReturn(String description,Field field,ValidateCodeEnum codeEnum)
    {
        if (description != null && !"".equals(description))
        {
            return ValidateUtil.returnError(codeEnum.getCode(), description);
        }
        else
        {
            return ValidateUtil.returnError(codeEnum.getCode(), "字段名 : " + field.getName() + " " + codeEnum.getMessage());
        }
    }

    /**
     * 公共ruturn 函数
     *
     * @param description 自定义返回错误描述
     * @param fieldNames 验证的字段的名称
     * @param codeEnum 默认返回的错误描述
     * @return validateResult
     */
    public ValidateResult publicReturn(String description,String fieldNames,ValidateCodeEnum codeEnum)
    {
        if (description != null && !"".equals(description))
        {
            return ValidateUtil.returnError(codeEnum.getCode(), description);
        }
        else
        {
            return ValidateUtil.returnError(codeEnum.getCode(), "字段名 : " + fieldNames + " " + codeEnum.getMessage());
        }
    }


    /**
     * 公共验证函数
     *
     * @param thiz 验证对象
     * @return validateResult
     * @throws IllegalAccessException 是否有访问字段权限
     * @throws InvocationTargetException 反射异常
     */
    public ValidateResult validate(Object thiz)
        throws IllegalAccessException, InvocationTargetException
    {
        ValidateResult validateResult = null;

        //获取子类或者父类的所有属性
        Field[] fields = ValidateUtil.getAllFields(thiz);

        validateResult = requireField(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = specialAndRequireField(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = specialAndRequireFieldMany(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = specialOrRequireField(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = lengthIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = integerIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = doubleIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = longIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = dateIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = booleanIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = listIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = modelIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        validateResult = regularIllegal(thiz,fields);

        if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
        {
            return validateResult;
        }

        return ValidateUtil.returnSuccess();
    }

    /**
     * 公共验签
     *
     * @return Boolean
     * @throws Exception 抛出公共异常
     */
    protected Boolean validateSign() throws Exception
    {
        return true;
    }



}
