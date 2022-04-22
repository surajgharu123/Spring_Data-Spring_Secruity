package com.zensar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zensar.entity.StockDocument;
import com.zensar.entity.StockEntity;

public interface StockMongoRepo extends MongoRepository<StockDocument, Integer> {
	
	public List<StockDocument> findByName(String stockName);
	public List<StockDocument> findByNameContains(String stockNameLike);
	
	public List<StockDocument> findByOrderByNameAsc();
	public List<StockDocument> findByOrderByNameDesc();
	
}
