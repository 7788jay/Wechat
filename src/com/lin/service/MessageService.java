package com.lin.service;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lin.bean.Static;
import com.lin.util.MessageUtil;
import com.lin.util.TulingUtil;

/**
 * 消息处理服务类
 * @author 林
 *
 */
@Service("messageService")
public class MessageService {
	@Resource private TuLingService tuLingService;
	public String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, String> map = MessageUtil.xmlToMap(request);
		//发送方账号
		String fromUserName = map.get("FromUserName");
		//公众账号
		String toUserName = map.get("ToUserName");
		//消息类型
		String msgType = map.get("MsgType");
		//消息内容
		String content = map.get("Content");
		
		String message=null;
		if(MessageUtil.MESSAGE_TEXT.equals(msgType)){//判断信息类型，如果是文本类型
			if(content!=null){
				
				if("?".equals(content)||"？".equals(content)){//判断用户输入
					message=MessageUtil.CreateTextMessage(toUserName, fromUserName, Static.menu());
				}
				else if("1".equals(content)){//判断用户输入,符合进入聊天模式
					message=MessageUtil.CreateTextMessage(toUserName, fromUserName, Static.chat());
				}
				else if("2".equals(content)){//判断用户输入
					message=MessageUtil.CreateNewsMessage(toUserName, fromUserName, Static.info());
				}
				else {
					JSONObject result=tuLingService.createTLRequest(content,fromUserName);
					if(null==result){
						message=MessageUtil.CreateTextMessage(toUserName, fromUserName, Static.EROORMSG);
					}else{
						message=TulingUtil.ContentToWechat(toUserName, fromUserName,result);
					}
				}
			}
		}
		else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
			String eventType = map.get("Event");
			if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
				message = MessageUtil.CreateTextMessage(toUserName, fromUserName, Static.menu());
			}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
			}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
			}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
			}
		}
		return message;
	}
	
	
}
