package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 打印参数
 *
 * @author MENG
 * @version 2017/7/13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PrintParameter
{
    /**
     * 函数描述
     *
     * @return 自定义返回描述
     */
    String description()  default "";
}
