package com.lin.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 * @author 林
 *
 */
public class Static {
	public static final String APPID = "wxdd76438574cc239e";
	public static final String APPSECRET = "8fb4f41bd36156e171631d371750b519";
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "lwt";
	/**
	 * 错误提示
	 */
	public static String EROORMSG = "抱歉，服务号异常，请稍后重试！";
	/**
	 * 提供菜单内容
	 * @return String
	 */
	public static String menu(){
		StringBuffer sb=new StringBuffer();
		sb.append("本机器人提供聊天功能\n\n");
		sb.append("回复【1】进入聊天模式\n");
		sb.append("回复【2】了解全通公司\n");
		sb.append("回复【?】查看菜单");
		return sb.toString();
	}
	/**
	 * 进入聊天室提示
	 * @return
	 */
	public static String chat(){
		StringBuffer sb=new StringBuffer();
		sb.append("你好！本机器人提供聊天功能\n\n");
		sb.append("回复【中山天气】查天气预报\n");
		sb.append("回复【顺丰快递】查物流\n");
		sb.append("回复【娱乐新闻】查新闻\n");
		sb.append("回复【广州到湛江的火车】查火车\n");
		sb.append("更多内容等你发现\n回复任意内容调戏我\n");
		sb.append("回复【?】查看菜单");
		return sb.toString();
	}
	/**
	 * 提供公司信息
	 * @return
	 */
	public static List<NewsItem> info(){
		List<NewsItem> newsItems=new ArrayList<NewsItem>();
		NewsItem newsItem=new NewsItem();
		newsItem.setTitle("全通教育");
		newsItem.setDescription("广东全通教育股份有限公司正式成立于2005年6月，成立于2005年，注册资本600万，办公面积5000平方米，由超过800名的专业研发与运营人员构成...");
		newsItem.setPicUrl("http://image.jobcn.com/companyimages/logos/20/93/209366_logo.jpg");
		newsItem.setUrl("http://www.qtone.cn/");
		
		newsItems.add(newsItem);
		
		return newsItems;
		
	}
}
