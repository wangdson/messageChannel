package com.ebei.message.vo;

/**
 * 
 * @Description : 通用API返回编码常量类
 * @time 创建时间 : 2018年7月5日
 * @author : DeanFan
 * @Copyright (c) 2018 TinyCat
 * @version
 */
public class ResponseCode {

	/**
	 * 成功
	 */
	public static final Integer SUCCESS = 200;

	/**
	 * Token失效，或者未传Token，或者没有权限
	 */
	public static final Integer UNAUTHORIATION = 401;

	/**
	 * 内部服务器错误
	 */
	public static final Integer ERROR = 500;

	/**
	 * 	业务错误 例 : 参数不匹配或参数验证不正确
	 */
	public static final Integer BUSINESS_ERROR = 400;

	/**
	 * 无数据
	 */
	public static final Integer NO_DATA = 204;
	
}
