package com.kibou.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kibou.common.exception.ErrorInfo;
import com.kibou.common.exception.JunkWordException;

@ControllerAdvice/*(basePackages={}) //basePackages - 指定包下的controller的异常才会使用当前处理*/
public class GlocalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";
	
	//curl http://localhost:8080/users/100 , 给一个没有指定不存在用户的id
	@ExceptionHandler(value=RuntimeException.class)
	public ModelAndView handleException(HttpServletRequest request,Throwable cause){
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", cause);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
	
	
	//curl http://localhost:8080/json
	@ExceptionHandler(value=JunkWordException.class)
	@ResponseBody
	public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, JunkWordException e) throws Exception {
        ErrorInfo<String> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(ErrorInfo.ERROR);
        r.setData("Some Data");
        r.setUrl(req.getRequestURL().toString());
        return r;
    }
}
