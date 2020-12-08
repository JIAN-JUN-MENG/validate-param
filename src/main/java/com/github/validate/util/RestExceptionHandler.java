//package com.github.validate.util;
//
//import org.springframework.beans.TypeMismatchException;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//
///**
// * 异常增强，以JSON的形式返回给客服端 SPRING 程序异常
// *
// * @author MENG
// * @version 2019/2/21
// * @see
// */
//@EnableWebMvc
//@ControllerAdvice
//public class RestExceptionHandler
//{
////    400错误 JSON  格式转换错误
//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    @ResponseBody
//    public Object requestNotReadable(HttpMessageNotReadableException ex)
//    {
//        ex.printStackTrace();
////         TODO 发送邮件
//
//        return ValidateUtil.returnError(ValidateCodeEnum.HTTP_MESSAGE_NOT_READABLE_EXCEPTION);
//    }
//
//    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
//    @ResponseBody
//    public Object requestNotReadable(HttpMediaTypeNotSupportedException ex)
//    {
////        ex.printStackTrace();
//
//        return ValidateUtil.returnError(ValidateCodeEnum.HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION);
//    }
//
//    //400错误
//    @ExceptionHandler({TypeMismatchException.class})
//    @ResponseBody
//    public Object requestTypeMismatch(TypeMismatchException ex)
//    {
////        ex.printStackTrace();
//
//        return ValidateUtil.returnError(ValidateCodeEnum.TYPE_MISMATCH_EXCEPTION);
//    }
//
//    //400错误
//    @ExceptionHandler({MissingServletRequestParameterException.class})
//    @ResponseBody
//    public Object requestMissingServletRequest(MissingServletRequestParameterException ex)
//    {
////        ex.printStackTrace();
//
//        return ValidateUtil.returnError(ValidateCodeEnum.MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION);
//    }
//
//    //405错误
//    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
//    @ResponseBody
//    public Object httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex)
//    {
////        ex.printStackTrace();
//
//        return ValidateUtil.returnError(ValidateCodeEnum.HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION);
//    }
//
//    //406错误
//    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
//    @ResponseBody
//    public Object httpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex)
//    {
////        ex.printStackTrace();
//
//        return ValidateUtil.returnError(ValidateCodeEnum.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION);
//    }
//
//}
