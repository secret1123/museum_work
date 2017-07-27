package mw.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by AnLu on
 * 2017/7/27 15:10.
 * ssm
 */
@ControllerAdvice //建议 AOP
public class ExceptionHandlerTest {

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public String handleMaxUploadSizeExceededException(HttpServletRequest request, HttpServletResponse response){
        request.setAttribute("message","超出图片大小限制");
        return "/work/index.jsp";
    }
}
