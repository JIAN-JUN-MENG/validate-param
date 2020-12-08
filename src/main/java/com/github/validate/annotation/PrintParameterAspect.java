package com.github.validate.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 注解RemoveEmptyOrNullParamter aop
 *
 * @author MENG
 * @version 2017/7/13
 * @see
 */
@Aspect
@Component
public class PrintParameterAspect
{
    private static Logger logger = LoggerFactory.getLogger(PrintParameterAspect.class);

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.github.validate.annotation.PrintParameter)")
    public void pointCutMethod()
    {
    }

    /**
     * 方法执行之前
     *
     * @param joinPoint 参数
     */
    @Before("pointCutMethod()&&@annotation(printParameter)")
    public void before(JoinPoint joinPoint, PrintParameter printParameter)
    {
        //参数值
        Object[] objects = joinPoint.getArgs();

        Signature signature = joinPoint.getSignature();

        MethodSignature methodSignature = (MethodSignature) signature;

        //参数名
        String[] parameterNames = methodSignature.getParameterNames();

        //类位置
        String className = joinPoint.getTarget().getClass().getName();

        //函数名称
        String methodName = joinPoint.getSignature().getName();

        logger.info("<==========================================================================================================");

        logger.info("请求接口 : " + className + "-" + methodName + "（"+printParameter.description() +"）");

        //打印参数
        for (int i = 0; i < objects.length; i++)
        {
            Object obj = objects[i];

            if (obj != null)
            {
                if(obj instanceof HttpServletRequest || obj instanceof HttpServletResponse)
                {
                    continue;
                }

                logger.info("请求参数 : " + parameterNames[i] + " : "+ obj.toString());
            }
            else
            {
                logger.info("请求参数 : " + parameterNames[i] + " : null");
            }
        }
        logger.info("<==========================================================================================================");

    }

    @AfterReturning(returning="rvt", pointcut="pointCutMethod()&&@annotation(printParameter)")
    public void after(JoinPoint joinPoint, Object rvt, PrintParameter printParameter)
    {
        //类位置
        String className = joinPoint.getTarget().getClass().getName();

        //函数名称
        String methodName = joinPoint.getSignature().getName();

//        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().serializeNulls().create();

        logger.info("<==========================================================================================================");

        logger.info("返回接口 : " + className + "-" + methodName + "（"+printParameter.description() +"）");

        if (rvt != null)
        {
            logger.info("返回数据 : " + rvt.toString());
        }
        else
        {
            logger.info("返回数据 : null");
        }

        logger.info("<==========================================================================================================");
    }

}
