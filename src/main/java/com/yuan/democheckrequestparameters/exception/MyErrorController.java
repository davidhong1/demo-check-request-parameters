package com.yuan.democheckrequestparameters.exception;

import com.yuan.democheckrequestparameters.domain.BRStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: mac
 * @date: 2019-01-22
 * @description:
 */
@Slf4j
@Controller
public class MyErrorController extends BasicErrorController {

    private static final SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MyErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {

        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));

        HttpStatus status = getStatus(request);

        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status.value());
        String message = (String) body.get("message");
        if (message == null || message.equals("No message available")) {
            map.put("msg", status.getReasonPhrase());
        } else {
            if (message.startsWith(BRStatus.PARAS_ERROR.getStatus()+"")) {
                map.put("status", BRStatus.PARAS_ERROR.getStatus());
                map.put("msg", message.substring(3));
            } else {
                map.put("msg", body.get("message"));
            }

        }
        map.put("timestamp", sdf4.format(new Date()));

        return new ResponseEntity<>(map, status);
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {

        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());

        Map<String, Object> model = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        //指定自定义的视图
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }

}
