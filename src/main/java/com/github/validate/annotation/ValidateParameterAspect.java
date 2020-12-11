package com.github.validate.annotation;

import com.github.validate.util.ValidateCodeEnum;
import com.github.validate.util.ValidateConstants;
import com.github.validate.util.ValidateResult;
import com.github.validate.util.ValidateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * 注解RemoveEmptyOrNullParamter aop
 *
 * @author MENG
 * @version 2017/7/13
 */
@Aspect
@Component
public class ValidateParameterAspect
{
    private static Logger logger = LoggerFactory.getLogger(ValidateParameterAspect.class);

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.github.validate.annotation.ValidateParameter)")
    public void pointCutMethod()
    {
    }

    /**
     * 环绕
     *
     * @param pjp ProceedingJoinPoint
     * @return Object
     * @throws Throwable 抛出异常
     */
    @Around("pointCutMethod()")
    public Object Around(ProceedingJoinPoint pjp) throws Throwable
    {
        //参数值
        Object[] objects = pjp.getArgs();

        BaseValidate validate = new BaseValidate();

        for(Object object : objects)
        {
//            Method mothod = null;
//
//            try
//            {
//                mothod = object.getClass().getMethod(ValidateConstants.BASE_VALIDATE_METHOD_NAME);
//            }
//            catch (NoSuchMethodException e)
//            {
//                continue;
//            }
//            ValidateResult validateResult = (ValidateResult)mothod.invoke(object);

            if(object instanceof HttpServletRequest || object instanceof HttpServletResponse)
            {
                continue;
            }
            else
            {
                if(object == null)
                {
                    continue;
                }

                ValidateResult validateResult = validate.validate(object);

                Method mothod = null;

                Class objClazz = object.getClass();

                Method[] objMethods = objClazz.getDeclaredMethods();

                Class objSupperClazz = object.getClass().getSuperclass();

                Method[] objSuperMethods = objSupperClazz.getDeclaredMethods();

                try
                {
                    mothod = objClazz.getSuperclass().getMethod(ValidateConstants.BASE_VALIDATE_SIGN_METHOD_NAME);

                    boolean signFlag = (boolean) mothod.invoke(object);

                    if(signFlag == false)
                    {
                        return ValidateUtil.returnError(ValidateCodeEnum.VALIDATE_SIGN_ERROR);
                    }
                }
                catch (NoSuchMethodException e)
                {
                    try
                    {
                        mothod = object.getClass().getMethod(ValidateConstants.BASE_VALIDATE_SIGN_METHOD_NAME);

                        boolean signFlag = (boolean) mothod.invoke(object);

                        if (signFlag == false)
                        {
                            return ValidateUtil.returnError(ValidateCodeEnum.VALIDATE_SIGN_ERROR);
                        }
                    }
                    catch (NoSuchMethodException e2)
                    {}
                }

                if(!validateResult.getCode().equals(ValidateConstants.RESULT_SUCCESS_CODE))
                {
                    return validateResult;
                }
            }
        }

        return pjp.proceed(pjp.getArgs());
    }

}
