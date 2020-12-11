package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 必填字段
 *
 * @author MENG
 * @version 2019/1/21
 */
@Inherited
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpecialAndRequireFieldMany
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return 返回描述
     */
    String description() default "";

    /**
     * 特殊必填验证注解
     *
     * @return 返回注解
     */
    SpecialAndRequireField[] values() default {};
}
