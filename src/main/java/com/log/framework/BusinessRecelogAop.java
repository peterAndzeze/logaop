package com.log.framework;

import com.alibaba.fastjson.JSON;
import com.log.common.PayBusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by wodezuiaishinageren on 2017/11/23.
 * 定义日志切面
 *
 */
@Component
@Aspect
public class BusinessRecelogAop {
    static {
        System.out.println("切面信息");
    }
    /**
     * 配置切点信息
     */
    @Pointcut("execution(* com.log.controller..*.*(..))")
    public void executService(){
        System.out.println("切面信息定义");
    }
    @Before("executService()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        System.out.println("前置信息。。。。");
        /**被代理类*/
        String className=joinPoint.getClass().getName();
        //用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        System.out.println("方法名："+signature.getName());
        System.out.println("aop代理类："+signature.getDeclaringTypeName());

        Object [] args=joinPoint.getArgs();
        System.out.println("请求参数 只有值args******："+JSON.toJSONString(args));
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Map<String, String[]> params= request.getParameterMap();
        System.out.println("请求参数 属性和值params*****"+ JSON.toJSONString(params));


        /*//获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        Enumeration<String> enumeration = request.getParameterNames();
        Map<String,String> parameterMap = Maps.newHashMap();
        while (enumeration.hasMoreElements()){
            String parameter = enumeration.nextElement();
            parameterMap.put(parameter,request.getParameter(parameter));
        }
        String str = JSON.toJSONString(parameterMap);
        if(obj.length > 0) {
            System.out.println("请求的参数信息为："+str);
        }*/
    }


    /**
     *
     * 后置通知(得到被代理方法返回值)
     * 这里需要注意的是:
     *      如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     * @param joinPoint
     * @param returnKey
     */
    @AfterReturning(value = "executService()",returning = "returnKey")
    public void doAfterAdvice(JoinPoint joinPoint,String returnKey){
        System.out.println("执行后置通知。。。"+returnKey);
    }

    /**
     * 执行方法之后
     */
    @After("executService()")
    public void doAfter(){
        System.out.println("方法结束之后***");
    }
    /**
     * 后置异常通知
     * @param  joinPoint
     * @param exception
     */
    @AfterThrowing(value = "executService()",throwing = "exception")
    public void doExceptionAdvice(JoinPoint joinPoint,Throwable exception){
        System.out.println("方法："+joinPoint.getSignature().getName()+"发生异常："+exception.getMessage());
        if(exception instanceof PayBusinessException){
            System.out.println("发生了业务异常!!!!!");
        }
    }
}
