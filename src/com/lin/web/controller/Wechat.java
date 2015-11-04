package com.lin.web.controller;

import com.lin.service.MessageService;
import com.lin.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Wechat {
	@Resource 
	private MessageService messageService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String echostr = request.getParameter("echostr");
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				response.getWriter().print(echostr);
			} else {
				System.out.println("--不是微信--");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void message(HttpServletRequest request,HttpServletResponse response){
		
		try {
			//消息处理
			String message=messageService.processRequest(request,response);
			response.getWriter().print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
