package com.itheima.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected File upload;// 文件对象 名字就是页面写的那个name属性的值<input type="file" //
	protected String uploadContentType; // 文件类型 等于uploadContentType 页面的属性值 +//
	protected String uploadFileName; // 文件名字 页面的属性值 + FileName
	protected int page = 1;
	protected int rows = 5;
	protected T t;

	public BaseAction() {
		init();
	}

	private void init() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		try {
			t = (T) ((Class) type.getActualTypeArguments()[0]).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	protected void page2json(Page<T> page,String[] strs) {
		Map<String, Object> map = new HashMap<>();
		map.put("total", page.getTotalElements());
		map.put("rows", page.getContent());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(strs);
		String json = JSONObject.fromObject(map, jsonConfig).toString();
		outjson(json);
	}
	protected void list2json(List<T> list,String[] strs) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(strs);
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		outjson(json);
	}
	private void outjson(String json) {
		try {
			getResponse().setContentType("application/json;charset=utf-8");
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public T getModel() {
		return t;
	}

	protected ValueStack getVS() {
		return ServletActionContext.getValueStack(ServletActionContext.getRequest());
	}

	public HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

}
