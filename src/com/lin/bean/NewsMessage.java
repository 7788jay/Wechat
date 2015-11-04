package com.lin.bean;

import java.util.List;
/**
 * 图文消息类
 * @author 林
 *
 */
public class NewsMessage extends BaseMessage{
	private int ArticleCount;//图文消息个数，限制为10条以内 
	private List<NewsItem> Articles;//多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应 
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<NewsItem> getArticles() {
		return Articles;
	}
	public void setArticles(List<NewsItem> articles) {
		Articles = articles;
	}
	
}
