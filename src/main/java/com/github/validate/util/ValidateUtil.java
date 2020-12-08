package com.github.validate.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具类
 *
 * @author MENG
 * @version 2018/7/11
 * @see
 */
public class ValidateUtil
{
    /**
     * 检查Email 是否合法
     * @param email
     * @return
     */
    public static boolean checkEmail(String email)
    {
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 检查phone 是否合法
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone)
    {
        String regex = "^1[3|4|5|7|8][0-9]{9}$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(phone);

        return m.matches();
    }

    /**
     * 返会 成功
     *
     * @return
     */
    public static ValidateResult returnSuccess()
    {
        ValidateResult responseResult = new ValidateResult();

        responseResult.setCode(ValidateCodeEnum.SUCCESS.getCode());

        responseResult.setMessage(ValidateCodeEnum.SUCCESS.getMessage());

        return responseResult;
    }

    /**
     * 返会 成功
     *
     * @return
     */
    public static ValidateResult returnSuccess(Object data)
    {
        ValidateResult responseResult = new ValidateResult();

        responseResult.setCode(ValidateCodeEnum.SUCCESS.getCode());

        responseResult.setMessage(ValidateCodeEnum.SUCCESS.getMessage());

        responseResult.setData(data);

        return responseResult;
    }

    /**
     * 返会 错误
     *
     * @return
     */
    public static ValidateResult returnError(String code,String message)
    {
        ValidateResult responseResult = new ValidateResult();

        responseResult.setCode(code);

        responseResult.setMessage(message);

        return responseResult;
    }

    /**
     * 返会 错误
     *
     * @return
     */
    public static ValidateResult returnError(ValidateCodeEnum codeEnum)
    {
        ValidateResult responseResult = new ValidateResult();

        responseResult.setCode(codeEnum.getCode());

        responseResult.setMessage(codeEnum.getMessage());

        return responseResult;
    }

    /**
     * 获取对象所有属性 包含父级
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object)
    {
        Class clazz = object.getClass();

        List<Field> fieldList = new ArrayList<>();

        while (clazz != null)
        {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));

            clazz = clazz.getSuperclass();
        }

        Field[] fields = new Field[fieldList.size()];

        fieldList.toArray(fields);

        return fields;
    }

    /**
     * 根据字段名获取字段
     *
     * @param fieldNames
     * @param fields
     * @return
     */
    public static Field[] getFieldsByFieldName(String[] fieldNames,Field[] fields)
    {
        List<Field> fieldList = new ArrayList<>();

        if(fieldNames != null && fields != null && fieldNames.length > 0 && fields.length > 0)
        {
            for(Field field : fields)
            {
                for(String str : fieldNames)
                {
                    if(field.getName().equals(str))
                    {
                        fieldList.add(field);
                    }
                }
            }
        }

        return (Field[])fieldList.toArray(new Field[fieldList.size()]);
    }

    public static void main(String[] args)
    {
        // 2: 已知 sqrt (2)约等于 1.414，要求不用 数学库，求 sqrt (2)精确到小数点后 10 位。

//        double high = 1.415;
//
//        double low = 1.414;
//
//        double mid = (1.414+1.415)/2;
//
//        double condition = 0.0000000001;
//
//        while(high-low > condition)
//        {
//            if(mid*mid < 1.415)
//            {
//               high = mid;
//            }
//            else
//            {
//                low = mid;
//            }
//
//            mid = (high+low)/2;
//        }
//
//        System.out.println("sqrt (2) = " + mid);


        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
//        for (String temp : a) {
//            if("2".equals(temp)){
//                a.remove(temp);
//            }
//        }



        Iterator<String> it = a.iterator();
        while(it.hasNext()){
            String temp = it.next();
                if("2".equals(temp)){
                it.remove();
            }
        }
        System.out.println(a.size() +""+ a);

    }
}
