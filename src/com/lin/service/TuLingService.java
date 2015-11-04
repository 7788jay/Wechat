package com.lin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.lin.util.TulingUtil;



/**
 * 图灵消息处理service
 * @author 林
 *
 */
@Service
public class TuLingService {
	public JSONObject createTLRequest(String content,String userid){
		//图灵机器人数据库接口
		String APIKEY = "02961f0e0d6736935fe8a4e4b4222b1c";
		StringBuffer sb = new StringBuffer();
		JSONObject json = null;
		try {
			String INFO = URLEncoder.encode(content, "utf-8"); 
			String getURL = "http://www.tuling123.com/openapi/api?key="
					+ APIKEY + "&info=" + INFO+"&userid="+userid;
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));

			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			System.out.println(sb.toString());
			json = new JSONObject(sb.toString());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
