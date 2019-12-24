package com.api.monitoring.ApiMonitoring.web;

import java.net.HttpURLConnection;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

@EnableAsync
@EnableScheduling
@Service
public class MonitoringBusinessLogic {
	
	monitor monitors;
	@Autowired
	MonitoringRepository monitoringRepository;   
	    
	
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	public void start(boolean bool, long id) {
		
		String url = monitoringRepository.findUrl(id);
		String methodType = monitoringRepository.findMethodType(id);
		Long time = monitoringRepository.findTime(id);
//		String jsonBody = monitoringRepository.findJsonBody(id);
//		String headerName = monitoringRepository.findHeaderName(id);
//		String headerValue=monitoringRepository.findHeaderValue(id);
		
		Runnable runne = new Runnable() {

			@SuppressWarnings({ "deprecation" })
			@Override
			public void run() {
				
					int stopped = monitoringRepository.findIsExecuting(id);
					//System.out.println(stopped + " stopped value");
					
					if(stopped == 0) {					
						try {
							//monitoringRepository.updateIsExecuting(id, false);
							
//							 
//	                         Map<String, String> headers = new TreeMap<String, String>() ;
//	                         headers.put("Content-Type", "application/json");
//	                         headers.put("Authorization", "Basic YXM6YXM=");
//	                       
//	                         System.out.println(headers);
//	                         HttpResponse<JsonNode> getResponse=Unirest.
//	                                 get(url).headers(headers)
//	                              .asJson();
//	                                      statuscod = getResponse.getStatus();
//	                                      System.out.println(statuscod);
							int statusCode = sendHttpUnirest(url,methodType);
							System.out.println(statusCode+"  "+id);
							
							
							monitoringRepository.updateStatusCode(id, statusCode);
							float successCount=monitoringRepository.findSuccessCount(id);
						float totalRuns = monitoringRepository.findTotalRuns(id);
						if(statusCode==200) {
								successCount++;
							}
							totalRuns++;
							float apdex = calculateApdex(successCount,totalRuns);
							
							monitoringRepository.updateApdex(id, successCount, totalRuns, apdex);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("cought");
							e.printStackTrace();
					}
						
					
					
					}
				
					else {
						Thread.currentThread().stop();
					}
					
			}
			
		};
	 monitoringRepository.updateIsExecuting(id, true);
		 ScheduledFuture<?> runneHandle = 
	    			scheduler.scheduleAtFixedRate(runne, time,time, SECONDS);
		 
		 
			 scheduler.schedule(new Runnable() {
				    @Override
		    		public void run() {
				    
				   
				   	if(bool == true) {
				    		runneHandle.toString();
			    	}
			    	else {
			    		runneHandle.cancel(true);
			    		System.out.println("cancelled");
			 
				    	}
		    		}
		    	}, time, SECONDS); 
		 
		 
			
	}

	
	
	

	    
	    public int sendHttpUnirest(String url,String methodType  ) throws Exception
	    {
	        int statuscod = 0 ;
	        Unirest.config().reset();
        	Unirest.config().enableCookieManagement(false);
	        
	        switch(methodType)
	        {
	            case "GET":
	            	
	                HttpResponse<JsonNode> getResponse=Unirest.
	                get(url)
                   .asJson();
	                    statuscod=getResponse.getStatus();
	                    break;
	            case "POST":
	                HttpResponse<String> postResponse=Unirest.post(url)
	                
	                .asString();
	                statuscod=postResponse.getStatus();
	                break;
	            case "PUT":
	                HttpResponse<String> putResponse=Unirest.put(url)
	              
	                .asString();
	                statuscod=putResponse.getStatus();
	                break;
	            case "DELETE":
	                HttpResponse<String> deleteResponse=Unirest.delete(url)
	                .asString();
	                statuscod=deleteResponse.getStatus();
	                break;   
	        }
	        return statuscod;
	       
	    }
	    
	 
	    
	  
	 public float calculateApdex(float successCount,float totalRuns) {
		 if(totalRuns!=0) {
			 return successCount/totalRuns;
		 }
		 return 0;
	 }
	 
	
	

}