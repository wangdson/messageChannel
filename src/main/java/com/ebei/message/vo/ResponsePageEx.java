package com.ebei.message.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @Description : 封装基础类--分页
 *              结构如下
 *              {
 *                  status:200,
 *                  message:"请求成功",
 *                  data:{
 *                      records:[],      --返回内容(集合)
 *                      total:100, --总共条数
 *                      size:10,    --每页条数
 *                      pages:10,   --总共页数
 *                      current:1   --当前页
 *                  }
 *              }
 *
 *              基础类 {@link ResponseEx}
 * @time 创建时间 : 2018年7月5日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class ResponsePageEx<T> {
	private int status;
	private String message;

	private TPageBean<T> data;

	private ResponsePageEx(int status) {
		this.status = status;
	}

	private ResponsePageEx(int status, String message) {
		this.status = status;
		this.message = message;
	}

	private ResponsePageEx(int status, TPageBean<T> data) {
		this(status, "", data);
	}

	private ResponsePageEx(int status, String message, TPageBean<T> data) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public static <T> ResponsePageEx<T> createSuccess(T t, Integer totalCount) {
		return createSuccess(t, "", totalCount);
	}

	public static <T> ResponsePageEx<T> createSuccess() {
		return new ResponsePageEx<T>(ResponseCode.SUCCESS, "", null);
	}
	
	public static <T> ResponsePageEx<T> createSuccess(T t, String message, Integer totalCount) {
		return new ResponsePageEx<T>(ResponseCode.SUCCESS, message, TPageBean.createSuccess(t, totalCount));
	}
	
	public static <T> ResponsePageEx<T> createSuccess(T t, Integer totalCount, Integer size, Integer current) {
		return createSuccess(t, "", totalCount, size, current);
	}

	public static <T> ResponsePageEx<T> createSuccess(T t, String message, Integer totalCount, Integer size, Integer current) {
		return new ResponsePageEx<T>(ResponseCode.SUCCESS, message, TPageBean.createSuccess(t, totalCount, size, current));
	}

	@JSONField(serialize = false)
	public boolean isSuccess() {
		return this.status >= 0;
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

	public static <T> ResponsePageEx<T> createError(String message) {
		return createError(ResponseCode.ERROR, message);
	}

	public static <T> ResponsePageEx<T> createException(String message) {
		return createError(ResponseCode.BUSINESS_ERROR, message);
	}

	public static <T> ResponsePageEx<T> createError(int status, String message) {
		return new ResponsePageEx<T>(status, message);
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

	public TPageBean<T> getData() {
		return data;
	}

	public void setData(TPageBean<T> data) {
		this.data = data;
	}
	
//	public static void main(String[] args) {
//		ResponsePageEx<Object> responsePageEx = ResponsePageEx.createSuccess();
//		// 集合
//		List<User> userList = new ArrayList<User>();
//		int totalCount = 2;
//		int current = 1;
//		int size = 1;
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
//		TPageBean<Object> pageBean = TPageBean.createSuccess(userList, totalCount, size, current);
//		responsePageEx.setData(pageBean);
//		
//		// 可直接使用个构造函数
//		// ResponsePageEx<Object> responsePageEx = ResponsePageEx.createSuccess(userList,totalCount, size, current);
//		
//		System.out.println(responsePageEx.toJson());
//	}
}
