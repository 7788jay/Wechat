package com.lin.util;


import net.sf.json.JSONObject;

import com.lin.bean.AccessToken;
import com.lin.bean.Static;

public class AcessTokenUtil {
	/**
	 * 获取AccessToken
	 * @return AccessToken
	 * @throws Exception
	 */
	public static AccessToken getAccessToken() throws Exception{
		
		AccessToken token = PropUtil.readProp();
		if(null==token){
			System.out.println("yaona");
			token=new AccessToken();
			String url = Static.ACCESS_TOKEN_URL.replace("APPID", Static.APPID).replace("APPSECRET", Static.APPSECRET);
			JSONObject jsonObject = HttpUtil.doGetRequest(url);
			if(jsonObject!=null){
				token.setToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				PropUtil.writeProp(token);
			}
		}
		return token;
	}
	public static void main(String[] args) throws Exception {
//		AccessToken token = new AccessToken();
//		token.setToken("ggg");
//		token.setExpiresIn(444);
//		PropUtil.writeProp(token);
		System.out.println(getAccessToken().getToken());
//		System.out.println(PropUtil.readProp().getToken());
	}
}
