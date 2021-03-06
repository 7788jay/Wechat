package com.lin.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class HtmlFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		chain.doFilter(new MyRequest(request), response);
		

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	public class MyRequest extends HttpServletRequestWrapper{
		private HttpServletRequest request;
		
		public MyRequest(HttpServletRequest request) {
			super(request);
			this.request=request;
		}

		@Override
		public String getParameter(String name) {
			String value=request.getParameter(name);
			if(value==null){
				return null;
			}
			if(!this.request.getMethod().equalsIgnoreCase("get")){
				return value;
			}
			try {
				value = new String(value.getBytes("iso8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			return value;
		}
		
		
	}

}
