package com.api.monitoring.ApiMonitoring.web;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MonitoringController {

	@Autowired
	MonitoringRepository monitoringRepository;
	
	@Autowired
	MonitoringBusinessLogic monitoringBL;
	
//	@PostMapping("/add")
//	public monitor createMonitor(@RequestBody monitor monitors  ) {
//		return monitoringRepository.save(monitors);
//	}
	
	
	
	private String jsonToString(String json) 
	{
		
		
		JSONObject obj = new JSONObject(json);
		Set<String> key=obj.keySet();

		String[] keyString=new String[key.size()];
		int i=0;
		for(String k:key)
		{
			keyString[i++]=k;
		}
	    String jsonString = (String)obj.get(keyString[0]);
		return jsonString;
		
	}
	
	

	@PostMapping("/create")
	public void createMonitor(@RequestBody monitor monitors)
	{
		 monitoringRepository.save(monitors);
		 
	}
	
	@GetMapping("/get/{id}")
	public String findUrl(@PathVariable long id)
	{
		String urlJson=monitoringRepository.findUrl(id);
		String url=jsonToString(urlJson);
		System.out.println(url);
		return url;
		
	}

	
	
	
	@GetMapping("/jsonBody/{id}")
	public String jsonBody(@PathVariable long id)
	{
		String urlJson=monitoringRepository.findJsonBody(id);
		
		System.out.println(urlJson);
		String jsonBody=jsonToString(urlJson);
		System.out.println(jsonBody);
		return urlJson;
		
	}
	
	
	
	
	@GetMapping("/runUrls/{id}")
	public monitor runUrls(@PathVariable long id) {
//	
		monitoringBL.start(true, id);
		return monitoringRepository.findById(id).get();
		
	}
	
	  @GetMapping("/stop/{id}")
	    public monitor stopUrls(@PathVariable long id) {

		  monitoringRepository.updateIsExecuting(id, false);
	        return monitoringRepository.findById(id).get();
	    }
	
	@GetMapping("/findOne/{id}")
	public monitor getOneMonitor(@PathVariable long id ) {
		return monitoringRepository.findById(id).get();
	}
	
	@GetMapping("/get")
	public List<monitor> getAllMonitors() throws InterruptedException {
		
		
		
		return monitoringRepository.findAll();
	}
	
	@PutMapping(path="/update")
	public String updateMonitor(@RequestBody monitor monitors)
	{
		monitoringRepository.save(monitors);
		System.out.println("update success");
		return "updated";
		
	}
	@DeleteMapping(path="/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		System.out.println("deleting");
		monitoringRepository.deleteById(id);
		return "deleted";
	}
	
	@GetMapping(path="/getTotalCounts")
	public long getTotalCounts() {
		
		return monitoringRepository.count();
	}
	
	@GetMapping(path="/getSuccessCounts")
	public long getSuccessCounts() {
		return monitoringRepository.findTotalSuccessCount();
	}
	

	
	
	@GetMapping(path="/start/{id}")
	public void start(@PathVariable long id) {
		monitoringBL.start(true,id);
	}
	
	@GetMapping(path="/stopping/{id}")
	public void stop(@PathVariable long id) {
		monitoringRepository.updateIsExecuting(id, false);
	
	}
	
	@GetMapping(path="/statuscode")
	public void status()
	{
		monitoringRepository.updateStatusCode(123, 10);
		
	}
	
	

}