package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * ${DESCRIPTION}
 *
 * @author MENG
 * @version 2020/3/26
 * @see
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegularIllegal
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return
     */
    String description() default "";

    String reg();
}
