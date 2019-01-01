package com.ebei.message.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @Description : 封装基础类
 *				 结构如下
 *              {
 *                  status:200,
 *                  message:"请求成功",
 *                  data:{}
 *              }
 * @time 创建时间 : 2018年7月5日
 * @author : DeanFan
 * @Copyright (c) 2018 TinyCat
 * @version
 */
public class ResponseEx<T> {
	private int status;
	private String message;
	private T data;

	private ResponseEx() {
	}

	private ResponseEx(int status) {
		this.status = status;
	}

	private ResponseEx(int status, String message) {
		this.status = status;
		this.message = message;
	}

	private ResponseEx(int status, T data) {
		this.status = status;
		this.data = data;
	}

	public static <T> ResponseEx<T> createSuccess() {
		return createSuccess(ResponseCodeDesc.SUCCESS_DESC);
	}

	public static <T> ResponseEx<T> createSuccess(int status) {
		return createSuccess(status, null);
	}

	public static <T> ResponseEx<T> createSuccess(T t) {
		return createSuccess(ResponseCode.SUCCESS, t);
	}

	public static <T> ResponseEx<T> createNoDataSuccess(T t) {
		return createNoDataSuccess(ResponseCode.NO_DATA, t);
	}

	public static <T> ResponseEx<T> createSuccess(int code, T t) {
		return new ResponseEx<T>(code, t);
	}

	public static <T> ResponseEx<T> createNoDataSuccess(int code, T t) {
		return new ResponseEx<T>(code, t);
	}

	public static <T> ResponseEx<T> createSuccess(String message) {
		return new ResponseEx<T>(ResponseCode.SUCCESS, message);
	}

	public static <T> ResponseEx<T> createNoDataSuccess(String message) {
		return new ResponseEx<T>(ResponseCode.NO_DATA, message);
	}

	public static <T> ResponseEx<T> createError(String message) {
		return createError(ResponseCode.BUSINESS_ERROR, message);
	}

	public static <T> ResponseEx<T> createError(int code, String message) {
		return new ResponseEx<T>(code, message);
	}

	public static <T> ResponseEx<T> createException(String message) {
		return createError(ResponseCode.ERROR, message);
	}

	@JSONField(serialize = false)
	public boolean isSuccess() {
		return this.status == ResponseCode.SUCCESS;
	}

	/**
	 * 
	 * Description : 对象转JSON
	 * @param features 自定义序列
	 * @return
	 */
	@JSONField(serialize = false)
	public String toJson(SerializerFeature... features) {
		SerializerFeature[] SerializerFeatureArray;
		if (features != null) {
			SerializerFeatureArray = new SerializerFeature[6 + features.length];
		} else {
			SerializerFeatureArray = new SerializerFeature[6];
		}

		SerializerFeatureArray[0] = SerializerFeature.PrettyFormat;
		SerializerFeatureArray[1] = SerializerFeature.WriteMapNullValue;
		SerializerFeatureArray[2] = SerializerFeature.WriteNullStringAsEmpty;
		SerializerFeatureArray[3] = SerializerFeature.DisableCircularReferenceDetect;
		SerializerFeatureArray[4] = SerializerFeature.WriteNullListAsEmpty;
		SerializerFeatureArray[5] = SerializerFeature.WriteDateUseDateFormat;

		if (features != null) {
			int i = 5;
			for (SerializerFeature serializerFeature : features) {
				i++;
				SerializerFeatureArray[i] = serializerFeature;
			}
		}

		return JSONObject.toJSONString(this, SerializerFeatureArray);
	}

	/**
	 * 
	 * Description : 对象转JSON
	 * @param complexPropertyPreFilter 手动过滤对象
	 * @param features 自定义序列
	 * @return
	 */
	@JSONField(serialize = false)
	public String toJson(ComplexPropertyPreFilter complexPropertyPreFilter, SerializerFeature... features) {
		SerializerFeature[] SerializerFeatureArray;
		if (features != null) {
			SerializerFeatureArray = new SerializerFeature[6 + features.length];
		} else {
			SerializerFeatureArray = new SerializerFeature[6];
		}

		SerializerFeatureArray[0] = SerializerFeature.PrettyFormat;
		SerializerFeatureArray[1] = SerializerFeature.WriteMapNullValue;
		SerializerFeatureArray[2] = SerializerFeature.WriteNullStringAsEmpty;
		SerializerFeatureArray[3] = SerializerFeature.DisableCircularReferenceDetect;
		SerializerFeatureArray[4] = SerializerFeature.WriteNullListAsEmpty;
		SerializerFeatureArray[5] = SerializerFeature.WriteDateUseDateFormat;

		if (features != null) {
			int i = 5;
			for (SerializerFeature serializerFeature : features) {
				i++;
				SerializerFeatureArray[i] = serializerFeature;
			}
		}

		return JSONObject.toJSONString(this, complexPropertyPreFilter, SerializerFeatureArray);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
//	public static void main(String[] args) {
//		ResponseEx<Object> responseEx = ResponseEx.createSuccess();
//		
//		// 对象
//		Map<String,Object> map = new HashMap<String,Object>();
//		
//		map.put("userId", 1L);
//		map.put("userName", "张三");
//		
//		responseEx.setData(map);
//		
//		// 可直接使用个构造函数
////		ResponseEx<Object> responseEx = ResponseEx.createSuccess(map);
//		System.out.println(responseEx.toJson());
//		
//		// 集合
//		List<User> userList = new ArrayList<User>();
//		
//		User user = new User();
//		
//		user.setUserId(2L);
//		user.setUserName("李四");
//		user.setCreator("admin");
//		user.setCreateDate(new Date());
//		
//		userList.add(user);
//		
//		User user2 = new User();
//		
//		user2.setUserId(3L);
//		user2.setUserName("王五");
//		user2.setCreator("admin");
//		user2.setCreateDate(new Date());
//		
//		userList.add(user2);
//		
//		responseEx.setData(userList);
//		
//		System.out.println(responseEx.toJson());
//		
//		// 忽略属性
//		Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();
//		excludes.put(User.class, new String[] { "creator", "createDate" });
//		ComplexPropertyPreFilter complexPropertyPreFilter = new ComplexPropertyPreFilter();
//		complexPropertyPreFilter.setExcludes(excludes);
//
//		System.out.println(responseEx.toJson(complexPropertyPreFilter));
//
//		// 包含属性
//		Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>();
//		includes.put(ResponseEx.class, new String[] { "data","message","status" });
//		includes.put(User.class, new String[] { "userId","userName" });
//		complexPropertyPreFilter = new ComplexPropertyPreFilter();
//		complexPropertyPreFilter.setIncludes(includes);
//
//		System.out.println(responseEx.toJson(complexPropertyPreFilter));
//
//	}
}
