package com.zensar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zensar.entity.StockEntity;

public interface StockRepo extends JpaRepository<StockEntity, Integer> {
	
	public List<StockEntity> findByName(String stockName);
	
	@Query(value = "SELECT se FROM StockEntity AS se WHERE se.name=:stockName") //JPQL
	public List<StockEntity> getStocksByName(String stockName);
	
	@Query(value = "SELECT * FROM STOCKS WHERE name=:stockName", nativeQuery = true) //JPQL
	public List<StockEntity> getStocksByNameSQL(String stockName);
	//public List<StockEntity> findByMarket(String stockname);
	
	public List<StockEntity> findByNameContains(String stockNameLike);
	public List<StockEntity> findByNameContaining(String stockNameLike);
	public List<StockEntity> findByNameIsContaining(String stockName);
	
	@Query(value = "SELECT se FROM StockEntity AS se WHERE se.name LIKE %:nameLike%") //JPQL
	public List<StockEntity> getByNameLikeQuery(String nameLike);
	
	@Query(value = "SELECT * FROM STOCKS WHERE name LIKE %:stockName% ", nativeQuery = true) //JPQL
	public List<StockEntity> getStocksByNameLikeSQL(String stockName);
	
	
	//Sorting 
	public List<StockEntity> findByOrderByNameAsc();
	public List<StockEntity> findByOrderByNameDesc();
	
	@Query(value = "SELECT se FROM StockEntity AS se ORDER BY se.name") //JPQL
	public List<StockEntity> sortAllByNames();
	
	@Query(value = "SELECT se FROM StockEntity AS se ORDER BY se.name DESC") //JPQL
	public List<StockEntity> sortAllByNamesDesc();
		
}
