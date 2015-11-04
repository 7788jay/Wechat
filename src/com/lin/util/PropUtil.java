package com.lin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import com.lin.bean.AccessToken;


public class PropUtil {
	static Properties prop = new Properties(); 
	static File file=new File("src/accesstoken.properties");
	public static AccessToken readProp() throws Exception{
		AccessToken accessToken=new AccessToken();
		FileInputStream in=new FileInputStream(file);
		prop.load(in);
		String token=prop.getProperty("token");
		int expiresIn=Integer.parseInt(prop.getProperty("expiresIn"));
		Long savetime=Long.parseLong(prop.getProperty("savetime"));
		Long currenttime= new Date().getTime();
		if(currenttime-savetime<7200*1000){
			accessToken.setToken(token);
			accessToken.setExpiresIn(expiresIn);
			return accessToken;
		}else{
			return null;
		}
	}
	
	public static void writeProp(AccessToken token) throws Exception{
		FileOutputStream out=new FileOutputStream(file);
		prop.setProperty("token", token.getToken());
		prop.setProperty("expiresIn", String.valueOf(token.getExpiresIn()));
		System.out.println(new Date().getTime());
		prop.setProperty("savetime", String.valueOf(new Date().getTime()));
		prop.store(out, "");
		out.close();
	}
}
