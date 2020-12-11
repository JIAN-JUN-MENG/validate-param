package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 移除参数中为 空字符串 或者 null 的参数
 *
 * @author MENG
 * @version 2017/7/13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoveEmptyOrNullParameter
{
    /**
     * 描述
     *
     * @return 自定义描述
     */
    String description()  default "";
}
