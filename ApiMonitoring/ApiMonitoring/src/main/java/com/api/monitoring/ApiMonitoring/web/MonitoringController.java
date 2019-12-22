package com.api.monitoring.ApiMonitoring.web;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MonitoringController 
{

	@Autowired
	MonitoringRepository monitoringRepository;
	
	@Autowired
	MonitoringBusinessLogic monitoringBL;
	
	@PostMapping("/add")
	public void createMonitor(@RequestBody monitor monitors  )
	{
		 monitoringRepository.save(monitors);
	}
	@GetMapping("/get")
	public List<monitor> getAllMonitors() throws InterruptedException 
	{
		
		
		
		return monitoringRepository.findAll();
	}
	
	
//	@GetMapping("/runUrls/{id}")
//	public void runUrls(@PathVariable long id) 
//	{
//		//monitoringBL.start(true, id);
//		 System.out.println(monitoringRepository.findById(id).get());
//		
//		
////	}
//	
//
	@GetMapping("/runUrls/{id}")
	public void runUrls(@PathVariable long id) 
	{
		//monitoringBL.start(true, id);
		
		
		 System.out.println(monitoringRepository.findUrl(id));

	}
	
//	  @GetMapping("/stop/{id}")
//	    public monitor stopUrls(@PathVariable long id)
//	  {
//		  monitoringRepository.updateIsExecuting(id, false);
//	        return monitoringRepository.findById(id).get();
//	    }
//	
//	@GetMapping("/findOne/{id}")
//	public monitor getOneMonitor(@PathVariable long id ) 
//	{
//		return monitoringRepository.findById(id).get();
//	}
//	
//
//	
//	@PutMapping(path="/update")
//	public String updateMonitor(@RequestBody monitor monitors)
//	{
//		monitoringRepository.save(monitors);
//		System.out.println("update success");
//		return "updated";
//		
//	}
//	@DeleteMapping(path="/delete/{id}")
//	public String delete(@PathVariable("id") Long id)
//	{
//		System.out.println("deleting");
//		monitoringRepository.deleteById(id);
//		return "deleted";
//	}
//	
//	@GetMapping(path="/getTotalCounts")
//	public long getTotalCounts() 
//	{
//		
//		return monitoringRepository.count();
//	}
//	
//	@GetMapping(path="/getSuccessCounts")
//	public long getSuccessCounts() 
//	{
//		return monitoringRepository.findTotalSuccessCount();
//	}
//	@GetMapping(path="/start/{id}")
//	public void start(@PathVariable long id)
//	{
//		monitoringBL.start(true,id);
//	}
//	
//	@GetMapping(path="/stopping/{id}")
//	public void stop(@PathVariable long id)
//	{
//		monitoringRepository.updateIsExecuting(id, false);
//		
//	}
	@GetMapping(path="/id/{id}")
	public void getId(@PathVariable long id)
	{
		System.out.println(monitoringRepository.findId(id));
	}
	
	}
	

