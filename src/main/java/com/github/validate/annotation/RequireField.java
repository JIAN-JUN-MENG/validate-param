package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 必填字段
 *
 * @author MENG
 * @version 2019/1/21
 * @see
 */
@Inherited
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireField
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return
     */
    String description() default "";

}
