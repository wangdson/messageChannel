package com.ebei.message.vo;

/**
 * 
@Description: 封装基础类--分页--内容对象
				{@link ResponsePageEx}
 * @time 创建时间 : 2018年7月5日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class TPageBean<T> {

    private T records;
    private Integer total; // 总条数
    private Integer size; // 每页条数
    private Integer pages; // 总页数
    private Integer current; // 当前页

    private TPageBean(T t, Integer total) {
        this.records = t;
        this.total = total;
    }
    
    private TPageBean(T t, Integer total, Integer size, Integer current) {
        this.records = t;
        this.total = total;
        this.size = size;
        this.current = current;
        
        if(size != null && size > 0){
        	pages = (total / size);
    		if (size != 0 && total % size != 0) {
    			pages = pages + 1;
    		}
		}    
    }

    public static <T> TPageBean<T> createSuccess(T t, Integer total) {
        return new TPageBean<T>(t, total);
    }
    
    public static <T> TPageBean<T> createSuccess(T t, Integer total, Integer size, Integer current) {
        return new TPageBean<T>(t, total, size, current);
    }

	public T getRecords() {
		return records;
	}

	public void setRecords(T records) {
		this.records = records;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

}
