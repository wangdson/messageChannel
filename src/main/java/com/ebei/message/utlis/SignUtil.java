package com.ebei.message.utlis;

import org.apache.commons.lang.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: Huangweicai
 * @date 2018-09-03 12:51
 * @Description:
 */
public class SignUtil {


    /**
     * 获取对象的签名
     * @param oj
     * @param key
     * @return
     */
    public static String getSignKey(Object oj,String key) {
        if (oj == null) {
            return null;
        }
        Map ojMap = null;
        if (oj instanceof Map) {
            ojMap = (Map) oj;
        } else {
            ojMap = toMap(oj);
        }
        String str = buildSignStr(ojMap) + "&key=" + key;
//        System.out.println("铭文Sign:" + str + "\n密文Sign:" + MD5Util.MD5(str));
        return MD5Util.MD5(str);
    }


    /**
     * 比较签名
     * @param oj
     * @param sign
     * @param key
     * @return
     */
    public static boolean checkSignKey(Object oj, String sign,String key) {
        if (oj == null || StringUtils.isEmpty(sign)) {
            return false;
        }
        Map ojMap = null;
        if (oj instanceof Map) {
            ojMap = (Map) oj;
        } else {
            ojMap = toMap(oj);
        }
        String str = buildSignStr(ojMap) + "&key=" + key;
        System.out.println("铭文Sign:" + str + "\n密文Sign:" + MD5Util.MD5(str));
        return MD5Util.MD5(str).equalsIgnoreCase(sign);
    }

    public static void main(String[] args) {
        Long t = System.currentTimeMillis();
//        SMSEmayBean bean = new SMSEmayBean();
//        bean.setContent("啦啦啦啦啦啦");
//        bean.setPhone("18502519033");
//        bean.setPreName("XXX公司");
//        bean.setCurTime(t);
//        System.out.println(getSignKey(bean,"ebei_sign_key"));

        System.out.println(t);
        Map<String, Object> params = new HashMap<>();
        params.put("cdkey", "8SDK-EMY-6699-SBTQK");
        params.put("password", "8F2FE6CB3D187F3B3248B9D8C74BA879");
        params.put("content", "12123123123");
        params.put("phone", "18112372653");
        params.put("preName", "正荣物业");
        params.put("curTime", t);
        System.out.println(getSignKey(params, "ebei_message_sign_key"));
//        System.out.println(checkSignKey(params, "D3f3064a6560c51045a56e9f6a1937b9","ebei_message_sign_key"));
    }


    private static String buildSignStr(Map<String, Object> params) {
        params.remove("sign");
        StringBuilder sb = new StringBuilder();
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<String, Object>(params);
        // 遍历排序的字典,并拼接"key=value"格式
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            if (sb.length()!=0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }

    public static Map toMap(Object bean) {
        Class<? extends Object> clazz = bean.getClass();
        Map<Object, Object> returnMap = new HashMap<Object, Object>();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = null;
                    result = readMethod.invoke(bean, new Object[0]);
                    if (null != propertyName) {
                        propertyName = propertyName.toString();
                    }
                    if (null != result) {
                        result = result.toString();
                    }
                    returnMap.put(propertyName, result);
                }
            }
        } catch (IntrospectionException e) {
            System.out.println("分析类属性失败");
        } catch (IllegalAccessException e) {
            System.out.println("实例化 JavaBean 失败");
        } catch (IllegalArgumentException e) {
            System.out.println("映射错误");
        } catch (InvocationTargetException e) {
            System.out.println("调用属性的 setter 方法失败");
        }
        return returnMap;
    }

}
