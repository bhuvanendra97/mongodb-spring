package com.api.monitoring.ApiMonitoring.web;

//import java.io.Serializable;
import java.net.URL;

import javax.annotation.Generated;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

//@Entity
//@Table(name="monitor")
@Document
public class monitor 
{
	
	@Id
	
	private long Id;
	private String monitorName;
	private String methodType;
	private String url;
	private Long time;
	private String eMail;
	private int statusCode;
	private float apdex;
	private float totalRuns;
	private float successCount;
	private Object jsonBody;
	private Object headers;
	
	private boolean isExecuting = true;
	

	protected monitor() {
		
	}


	public monitor(long id, String monitorName, String methodType, String url, Long time, String eMail, int statusCode,
			float apdex, float totalRuns, float successCount, Object jsonBody, Object headers, boolean isExecuting) {
		super();
		Id = id;
		this.monitorName = monitorName;
		this.methodType = methodType;
		this.url = url;
		this.time = time;
		this.eMail = eMail;
		this.statusCode = statusCode;
		this.apdex = apdex;
		this.totalRuns = totalRuns;
		this.successCount = successCount;
		this.jsonBody = jsonBody;
		this.headers = headers;
		this.isExecuting = isExecuting;
	}


	public long getId() {
		return Id;
	}


	public void setId(long id) {
		Id = id;
	}


	public String getMonitorName() {
		return monitorName;
	}


	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}


	public String getMethodType() {
		return methodType;
	}


	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Long getTime() {
		return time;
	}


	public void setTime(Long time) {
		this.time = time;
	}


	public String geteMail() {
		return eMail;
	}


	public void seteMail(String eMail) {
		this.eMail = eMail;
	}


	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public float getApdex() {
		return apdex;
	}


	public void setApdex(float apdex) {
		this.apdex = apdex;
	}


	public float getTotalRuns() {
		return totalRuns;
	}


	public void setTotalRuns(float totalRuns) {
		this.totalRuns = totalRuns;
	}


	public float getSuccessCount() {
		return successCount;
	}


	public void setSuccessCount(float successCount) {
		this.successCount = successCount;
	}


	public Object getJsonBody() {
		return jsonBody;
	}


	public void setJsonBody(Object jsonBody) {
		this.jsonBody = jsonBody;
	}


	public Object getHeaders() {
		return headers;
	}


	public void setHeaders(Object headers) {
		this.headers = headers;
	}


	public boolean isExecuting() {
		return isExecuting;
	}


	public void setExecuting(boolean isExecuting) {
		this.isExecuting = isExecuting;
	}


	@Override
	public String toString() {
		return "monitor [Id=" + Id + ", monitorName=" + monitorName + ", methodType=" + methodType + ", url=" + url
				+ ", time=" + time + ", eMail=" + eMail + ", statusCode=" + statusCode + ", apdex=" + apdex
				+ ", totalRuns=" + totalRuns + ", successCount=" + successCount + ", jsonBody=" + jsonBody
				+ ", headers=" + headers + ", isExecuting=" + isExecuting + "]";
	}


	


	
	

}