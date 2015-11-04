package com.lin.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.lin.bean.NewsItem;

/**
 * 图灵消息处理
 * @author 林
 *
 */
public class TulingUtil {                   
	public static final String TEXT  ="100000";//  文本类数据  
	public static final String TRUNK ="305000";//  列车  
	public static final String FLIGHT = "306000";//  航班  
	public static final String LINK  = "200000";//  网址类数据  
	public static final String NEWS  ="302000";//  新闻  
	public static final String VIDEO ="308000";//  菜谱、视频、小说  
	/*40001  key的长度错误（32位）  
	40002  请求内容为空  
	40003  key错误或帐号未激活  
	40004  当天请求次数已用完  
	40005  暂不支持该功能  
	40006  服务器升级中  
	40007  服务器数据格式异常  */
	public static String ContentToWechat(String toUserName,String fromUserName,JSONObject jsonObject) throws Exception{
		String code=jsonObject.getString("code");
		String message="";
		String content=jsonObject.getString("text");
		List<NewsItem> newsItems=null;
		if(TEXT.equals(code)){
			message=MessageUtil.CreateTextMessage(toUserName, fromUserName, content);
		}
		else if(LINK.equals(code)){//链接类型
			String url=jsonObject.getString("url");
			message=MessageUtil.CreateTextMessage(toUserName, fromUserName, content+"--><a href=\""+url+"\">请点击</a>");
		}
		else if(NEWS.equals(code)){//查找新闻
			/*参数          说明
			article  标题  
			source  来源  
			detailurl  详情地址  
			icon  图标地址 */ 
			newsItems=new ArrayList<NewsItem>();
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for (int i = 0; i < (jsonArray.length()>3?3:jsonArray.length()); i++) {
				JSONObject jsoItem=(JSONObject) jsonArray.get(i);
				NewsItem newsItem=new NewsItem();
				newsItem.setTitle(jsoItem.getString("article"));
				newsItem.setPicUrl(jsoItem.getString("icon"));
				newsItem.setUrl(jsoItem.getString("detailurl"));
				newsItems.add(newsItem);
			}
			message=MessageUtil.CreateNewsMessage(toUserName, fromUserName, newsItems);
		}
		else if(FLIGHT.equals(code)){//查找航班
			/*route  航班路线  
			starttime  起飞时间  
			endtime  到达时间  
			state  航班状态  
			detailurl  详情  
			icon  图标地址  */
			newsItems=new ArrayList<NewsItem>();
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for (int i = 0; i < (jsonArray.length()>5?5:jsonArray.length()); i++) {
				JSONObject jsoItem=(JSONObject) jsonArray.get(i);
				NewsItem newsItem=new NewsItem();
				newsItem.setTitle(jsoItem.getString("route"));
				newsItem.setDescription(jsoItem.getString("starttime")+"--"+jsoItem.getString("endtime"));
				newsItem.setPicUrl(jsoItem.getString("icon"));
				newsItem.setUrl(jsoItem.getString("detailurl"));
				newsItems.add(newsItem);
			}
			message=MessageUtil.CreateNewsMessage(toUserName, fromUserName, newsItems);
		}
		else if(TRUNK.equals(code)){//查找火车
			/*trainnum  车次  
			start  起始站  
			terminal  到达站  
			starttime  开车时间  
			endtime  到达时间  
			detailurl  详情地址  
			icon  图标地址 */

			newsItems=new ArrayList<NewsItem>();
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for (int i = 0; i < (jsonArray.length()>4?4:jsonArray.length()); i++) {
				JSONObject jsoItem=(JSONObject) jsonArray.get(i);
				NewsItem newsItem=new NewsItem();
				String checiString=jsoItem.getString("start")+"--"+jsoItem.getString("terminal")+"\n"+jsoItem.getString("starttime")+"--"+jsoItem.getString("endtime");
				newsItem.setTitle(checiString);
				newsItem.setDescription("");
				newsItem.setPicUrl(jsoItem.getString("icon"));
				newsItem.setUrl(jsoItem.getString("detailurl"));
				newsItems.add(newsItem);
			}
			message=MessageUtil.CreateNewsMessage(toUserName, fromUserName, newsItems);
		}
		else if(VIDEO.equals(code)){//查找菜谱
			/*name  名称  
			info  详情  
			detailurl  详情链接  
			icon  图标地址  */
			newsItems=new ArrayList<NewsItem>();
			JSONArray jsonArray = jsonObject.getJSONArray("list");
			for (int i = 0; i < (jsonArray.length()>4?4:jsonArray.length()); i++) {
				JSONObject jsoItem=(JSONObject) jsonArray.get(i);
				NewsItem newsItem=new NewsItem();
				newsItem.setTitle(jsoItem.getString("name"));
				newsItem.setDescription("");
				newsItem.setPicUrl(jsoItem.getString("icon"));
				newsItem.setUrl(jsoItem.getString("detailurl"));
				newsItems.add(newsItem);
			}
			message=MessageUtil.CreateNewsMessage(toUserName, fromUserName, newsItems);
		}
		else {
			message=MessageUtil.CreateTextMessage(toUserName, fromUserName, "抱歉！你太智能了，我跟不上！");
		}
		return message;
		
	}

}
