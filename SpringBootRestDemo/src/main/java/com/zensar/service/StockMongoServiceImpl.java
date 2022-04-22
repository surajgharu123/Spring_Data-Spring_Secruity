package com.zensar.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zensar.dto.Stock;
import com.zensar.entity.StockDocument;
import com.zensar.entity.StockEntity;
import com.zensar.repository.StockMongoRepo;

@Service

public class StockMongoServiceImpl implements StockService{

	@Autowired
	StockMongoRepo stockMongoRepo;
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public boolean deleteAllStocks() {
		// TODO Auto-generated method stub
		stockMongoRepo.deleteAll();
		return false;
	}

	@Override
	public boolean deleteAllStockById(int stockId) {
		// TODO Auto-generated method stub
		stockMongoRepo.deleteById(stockId);
		return true;
	}

	@Override
	public Stock updateStock(int stockId, Stock updateStock) {
		// TODO Auto-generated method stub
		Optional<StockDocument> stockDOptional = stockMongoRepo.findById(stockId);
		if(stockDOptional.isPresent())
		{
			StockDocument stockDocument = stockDOptional.get();
			stockDocument.setmarketName(updateStock.getMarket());
			stockDocument.setName(updateStock.getName());
			stockDocument.setPrice(updateStock.getPrice());
			stockMongoRepo.save(stockDocument);
		}
		return null;
	}

	@Override
	public Stock createNewStock(Stock stock) {
		// TODO Auto-generated method stub
		StockDocument stockDocument = convertDTOIEntity(stock);
		stockDocument = stockMongoRepo.save(stockDocument);
		return convertEntityIDTO(stockDocument);
	}

	@Override
	public Stock getStockById(int stockId) {
		// TODO Auto-generated method stub
		Optional<StockDocument> stockDocumentOptional = stockMongoRepo.findById(stockId);
		if(stockDocumentOptional.isPresent())
		{
			StockDocument stockDocument = stockDocumentOptional.get();
			return convertEntityIDTO(stockDocument);
		}
		return null;
	}

	@Override
	public List<Stock> getAllStocks() {
		// TODO Auto-generated method stub
		List<StockDocument> stockDocuments = stockMongoRepo.findAll();
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockDocument> itrStockEntities = stockDocuments.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		// TODO Auto-generated method stub
		List<StockDocument> stockDocuments = stockMongoRepo.findByName(stockName);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockDocument> itrStockEntities = stockDocuments.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}

	@Override
	public List<Stock> getStocksSortedByName(String sortType) {
		// TODO Auto-generated method stub
        List<StockDocument> stockDocuments = null;
		
		if("ASC".equalsIgnoreCase(sortType))
			stockDocuments = stockMongoRepo.findByOrderByNameAsc();
		else
			stockDocuments = stockMongoRepo.findByOrderByNameDesc();
		
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockDocument> itrStockEntities = stockDocuments.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}

	@Override
	public List<Stock> getStocksByPage(int startIndex, int records) {
		// TODO Auto-generated method stub
		Pageable myPageable = PageRequest.of(startIndex, records);
		Page<StockDocument> stockDocuments = stockMongoRepo.findAll(myPageable);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockDocument> itrStockEntities = stockDocuments.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}

	@Override
	public List<Stock> getStocksByNameLike(String namelike) {
		// TODO Auto-generated method stub
		List<StockDocument> stockDocuments = stockMongoRepo.findByNameContains(namelike);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockDocument> itrStockEntities = stockDocuments.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}
	
	private Stock convertEntityIDTO(StockDocument stockDocument) {
		//return new StockEntity(stock.getId(), stock.getName(), stock.getMarket(), stock.getPrice());
		TypeMap<StockDocument, Stock> typeMap = modelMapper.typeMap(StockDocument.class, Stock.class);
		typeMap.addMappings(mapper -> {
			mapper.map(StockDocument::getmarketName, Stock::setMarket);
		});
		Stock stock = modelMapper.map(stockDocument, Stock.class);
		return stock;
	}
	private StockDocument convertDTOIEntity(Stock stock) {
		
		TypeMap<Stock, StockDocument> typeMap = modelMapper.typeMap(Stock.class, StockDocument.class);
		typeMap.addMappings(mapper -> {
			mapper.map(Stock::getMarket, StockDocument::setmarketName);
		});
		StockDocument stockDocument = modelMapper.map(stock, StockDocument.class);
		
		return stockDocument;
	}

}
