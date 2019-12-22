package com.api.monitoring.ApiMonitoring.web;

import java.util.List;
import java.util.concurrent.Future;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;



@Repository
public interface MonitoringRepository extends MongoRepository<monitor, Long> {


	@Query(value="{'_id':?0}",fields="{'url':1,'_id':0}")
	List<String> findUrl(long id);
	
	@Query(value="{'_id':?0}",fields="{'methodType':1,'_id':0}")
	String findMethodType( long id);
	
	@Query(value="{'_id':?0}",fields="{'url':1,'_id':0}")
	Long findTime(long id);

//	@Query("Select id from monitor  where id != :id and isExecuting = false")
//	List<?> findId(@Param("id") long id);
	
	@Query(value="{$and:[{'_id':{$ne:?0}},{'isExecuting':false}",fields="{'_id':1,'isExecuting':0}")
	List<?> findId(long id);
//	//.....................................
//	@Query("Select count(m.isExecuting)  from monitor m where  m.isExecuting = false and m.id = :id ")
//	int findIsExecuting(@Param("id") long id);
//
//	
//	@Query("Select m.headerName from monitor m where m.id = :id ")
//	String findHeaderName(@Param("id") long id);
//	
//	@Query("Select m.headerValue from monitor m where m.id = :id ")
//	String findHeaderValue(@Param("id") long id);
//	
//	@Query("Select m.jsonBody from monitor m where m.id = :id ")
//	String findJsonBody(@Param("id") long id);
//	
//	@Query("Select m.successCount from monitor m where m.id = :id")
//	float findSuccessCount(@Param("id") long id);
//	
//	@Query("Select count(m.id) from monitor m where m.statusCode =200")
//	long findTotalSuccessCount();
//	
//	@Query("Select m.totalRuns from monitor m where m.id = :id")
//	float findTotalRuns(@Param("id") long id);
//	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update monitor m set m.statusCode = :statusCode where m.id = :id")
//	public void updateStatusCode(@Param("id") long id,@Param("statusCode") int statusCode );
//	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update monitor m set m.successCount = :successCount,m.totalRuns =:totalRuns,m.apdex =:apdex  where m.id = :id")
//	public void updateApdex(@Param("id") long id,@Param("successCount") float successCount
//			,@Param("totalRuns") float totalRuns, @Param("apdex") float apdex
//			);
//	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update monitor m set m.isExecuting = :isExecuting where m.id=:id")
//	public void updateIsExecuting(@Param("id") long id,@Param("isExecuting") boolean isExecuting
//			);
//	
	
	
}