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
public @interface ValidateParameter
{
    /**
     * 函数描述
     *
     * @return 返回自定义描述
     */
    String description()  default "";
}
