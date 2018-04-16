package com.nic.deputation.customexception;



import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalException {
	/*@ExceptionHandler(IOException.class)
	public ModelAndView  processIOException(IOException ioe)
	{
		ModelAndView modelAndView = new ModelAndView("exceptionPage");
		modelAndView.addObject("name", ioe.getClass().getSimpleName());
		modelAndView.addObject("message", ioe.getMessage());
	 
	     return modelAndView;
	}
	@ExceptionHandler(DefaultException.class)
	public ModelAndView  processCustomException(DefaultException defaultException)
	{
		ModelAndView modelAndView = new ModelAndView("exceptionPage");
		modelAndView.addObject("name", defaultException.getName());
		modelAndView.addObject("message", defaultException.getMessage());
	 
	     return modelAndView;
	}*/
	
	@ExceptionHandler(Exception.class)
	public ModelAndView  processIOException(Exception ioe)
	{
		ModelAndView modelAndView = new ModelAndView("globalException");
		/*modelAndView.addObject("name", ioe.getClass().getSimpleName());
		modelAndView.addObject("message", ioe.getMessage());*/
	 
	     return modelAndView;
	}
}
