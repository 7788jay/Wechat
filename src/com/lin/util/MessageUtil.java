package com.lin.util;

import com.lin.bean.NewsItem;
import com.lin.bean.NewsMessage;
import com.lin.bean.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 微信消息封装
 * @author 林
 *
 */
public class MessageUtil {
	public static final String MESSAGE_TEXT = "text";//文本
	public static final String MESSAGE_NEWS = "news";//
	public static final String MESSAGE_IMAGE = "image";//图片
	public static final String MESSAGE_VOICE = "voice";//音频
	public static final String MESSAGE_MUSIC = "music";//音乐
	public static final String MESSAGE_VIDEO = "video";//视频
	public static final String MESSAGE_LINK = "link";//链接
	public static final String MESSAGE_LOCATION = "location";//位置
	public static final String MESSAGE_EVNET = "event";//事件
	public static final String MESSAGE_SUBSCRIBE = "subscribe";//订阅事件
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";//取消订阅事件
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	/**
	 * xml转为map集合
	 * @param request
	 * @return

	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/**
	 * 将文本消息对象转为xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		String xml=xstream.toXML(textMessage);
		System.out.println(xml);
		return xml;
	}
	
	/**
	 * 图文消息转为xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new NewsItem().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * 创建一条文本消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return xml字符串
	 */
	public static String CreateTextMessage(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	} 
	/**
	 * 图文消息的组装
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String CreateNewsMessage(String toUserName,String fromUserName,List<NewsItem> newsItems){
		String message = null;
		NewsMessage newsMessage = new NewsMessage();
		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsItems);
		newsMessage.setArticleCount(newsItems.size());
		
		message = newsMessageToXml(newsMessage);
		return message;
	}
}
