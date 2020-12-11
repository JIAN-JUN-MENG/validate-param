package com.github.validate.annotation;

import java.lang.annotation.*;


/**
 * 自定义单个实体  不是LIST不是MAP...
 *
 * @author MENG
 * @version 2019/1/21
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ModelIllegal
{
    /**
     * 用于 描述字段的名称 也可用于提示用户
     *
     * @return 自定义返回描述
     */
    String description() default "";

}
