package com.lin.bean;
/**
 * AccessToken封装
 * @author 林
 *
 */
public class AccessToken {
	private String token;//获取到的凭证 
	private int expiresIn;//凭证有效时间，单位：秒 
	private int savetime;//记住保存时间
	
	public int getSavetime() {
		return savetime;
	}
	public void setSavetime(int savetime) {
		this.savetime = savetime;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
