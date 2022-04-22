package com.zensar.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import com.zensar.dto.Stock;
import com.zensar.entity.StockEntity;
import com.zensar.exception.InvalidStockIdException;
import com.zensar.repository.StockRepo;
import java.util.Optional;


@Service
@Primary
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepo stockRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean deleteAllStocks() {
// TODO Auto-generated method stub
		stockRepo.deleteAll();
		return true;
	}

	@Override
	public boolean deleteAllStockById(int stockId) {
// TODO Auto-generated method stub
		if (stockRepo.existsById(stockId)) {
			stockRepo.deleteById(stockId);
		}

		return true;
	}

	@Override
	public Stock updateStock(int stockId, Stock updateStock) {
// TODO Auto-generated method stub
		if (stockRepo.existsById(stockId)) {
			Optional<StockEntity> otStockEntity = stockRepo.findById(stockId);
			if (otStockEntity.isPresent()) {
				StockEntity stockEntity = otStockEntity.get();
				stockEntity.setmarketName(updateStock.getMarket());
				stockEntity.setName(updateStock.getName());
				stockEntity.setPrice(updateStock.getPrice());
				stockRepo.save(stockEntity);
			}
		}
		return null;
	}

	@Override
	public Stock createNewStock(Stock stock) {
// TODO Auto-generated method stub
		StockEntity stockEntity = convertDTOIEntity(stock);
		stockEntity = stockRepo.save(stockEntity);
		return convertEntityIDTO(stockEntity);
	}

	@Override
	public Stock getStockById(int stockId) {
        // TODO Auto-generated method stub
		Optional<StockEntity> otStockEntity = stockRepo.findById(stockId);
		if(otStockEntity.isPresent())
		{
			StockEntity stockEntity = otStockEntity.get();
			return convertEntityIDTO(stockEntity);
		}
		throw new InvalidStockIdException(" " + stockId);
	}

	@Override
	public List<Stock> getAllStocks() {
// TODO Auto-generated method stub
		List<StockEntity> stockEntities = stockRepo.findAll();
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockEntity> itrStockEntities = stockEntities.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	}

	private Stock convertEntityIDTO(StockEntity stockEntity) {
		//return new StockEntity(stock.getId(), stock.getName(), stock.getMarket(), stock.getPrice());
		TypeMap<StockEntity, Stock> typeMap = modelMapper.typeMap(StockEntity.class, Stock.class);
		typeMap.addMappings(mapper -> {
			mapper.map(StockEntity::getmarketName, Stock::setMarket);
		});
		Stock stock = modelMapper.map(stockEntity, Stock.class);
		return stock;
	}
	private StockEntity convertDTOIEntity(Stock stock) {
		
		TypeMap<Stock, StockEntity> typeMap = modelMapper.typeMap(Stock.class, StockEntity.class);
		typeMap.addMappings(mapper -> {
			mapper.map(Stock::getMarket, StockEntity::setmarketName);
		});
		StockEntity stockEntity = modelMapper.map(stock, StockEntity.class);
		
		return stockEntity;
	}

	@Override
	public List<Stock> getStocksByName(String stockName) {
		// TODO Auto-generated method stub
		List<StockEntity> stockEntities = stockRepo.getStocksByName(stockName);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockEntity> itrStockEntities = stockEntities.iterator();
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
		List<StockEntity> stockEntities = null;
		
		if("ASC".equalsIgnoreCase(sortType))
			stockEntities = stockRepo.sortAllByNames();
		else
			stockEntities = stockRepo.sortAllByNamesDesc();
		
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockEntity> itrStockEntities = stockEntities.iterator();
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
		Page<StockEntity> stockEntities = stockRepo.findAll(myPageable);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockEntity> itrStockEntities = stockEntities.iterator();
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
		List<StockEntity> stockEntities = stockRepo.getStocksByNameLikeSQL(namelike);
		List<Stock> stocksDTO = new ArrayList<Stock>();
		Iterator<StockEntity> itrStockEntities = stockEntities.iterator();
		while(itrStockEntities.hasNext())
		{
			Stock stock = convertEntityIDTO(itrStockEntities.next());
			stocksDTO.add(stock);
		}
		
		
		return stocksDTO;
	
	}

}
