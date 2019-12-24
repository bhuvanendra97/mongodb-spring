package com.api.monitoring.ApiMonitoring.web;

import java.util.List;
import java.util.concurrent.Future;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface MonitoringRepository extends MongoRepository<monitor, Long> {

	   @Query(value="{'_id':?0}",fields="{'url':1,'_id':0}") 

	    String findUrl(long id);
	 
	   
	   
	   @Query(value="{'_id':?0}",fields="{'methodType':1,'_id':0}") 

	    String findMethodType( long id); 

	     

	    @Query(value="{'_id':?0}",fields="{'url':1,'_id':0}") 

	    Long findTime(long id); 
//	@Query("Select m.id from monitor m where m.id!= :id and isExecuting = false")
//	List<?> findId(@Param("id") long id);
	    
	    @Query(value="{'_id':?0},{$and{'_id':{$ne:?0},'isExecuting':false}",fields="{'_id':1,isExecuting:0}")
	    List<?> findId(long id);
	

//	@Query("Select count(m.isExecuting)  from monitor m where  m.isExecuting = false and m.id = :id ")
//	int findIsExecuting(@Param("id") long id);

	@Query(value="{$and{'_id':?0,'isExecuting':false}",fields="{isExecuting:1}",count=true)
	int findIsExecuting(long id);
	
	@Query(value="{'_id':?0}",fields="{'header':1,'_id':0}")
	String findHeaderName(long id);
	
	
	@Query(value="{'_id':?0}",fields="{'jsonBody':1,'_id':0}")
	String findJsonBody(long id);
	
	@Query(value="{'_id':?0}",fields="{'successCount':1,'_id':0}")
	float findSuccessCount(long id);
//	
	@Query(value="{$match:{'successCount':200}}",fields="{'_id':1}",count=true)
	long findTotalSuccessCount();
	
	@Query(value="{'_id':?0}",fields="{'totalRuns':1,'_id':0}")
	float findTotalRuns(long id);
//	
	@Transactional
	
	//@Modifying(clearAutomatically = true)
	//@Query("update monitor m set m.statusCode = :statusCode where m.id = :id")
	@Query(value="{'_id':?0},{$set:{'statusCode':?0}}")
	public void updateStatusCode(long id, int statusCode );
//	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update monitor m set m.successCount = :successCount,m.totalRuns =:totalRuns,m.apdex =:apdex  where m.id = :id")
//	public void updateApdex(@Param("id") long id,@Param("successCount") float successCount
//			,@Param("totalRuns") float totalRuns, @Param("apdex") float apdex
//			);
	@Query(value="{'_id':?0},{$set:{'successCount':?0},{'totalRuns':?0},{'apdex':?0}}")
	public void updateApdex(long id,float successCount,float totalRuns,float apdex);
	
	
	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update monitor m set m.isExecuting = :isExecuting where m.id=:id")
//	public void updateIsExecuting(@Param("id") long id,@Param("isExecuting") boolean isExecuting
		
	
	@Query(value="{'_id':?0},{$set:{'isExecuting':?0}}")
	
	public void updateIsExecuting( long id, boolean isExecuting);
}
