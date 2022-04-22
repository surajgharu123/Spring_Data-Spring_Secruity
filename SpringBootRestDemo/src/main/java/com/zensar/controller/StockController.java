package com.zensar.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.dto.Stock;
import com.zensar.service.StockService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/stock")

/*
 * Using RequestMapping we can set our base url
 */

/**
 * 
 * @author SS66260 Allowing Every Port which is coming from Client side to
 *         access Restful Api CrossOrigin means our Reastful Api runing on 9090
 *         port suppose on client side React JS running with port number 8080
 *         than estiblish a communication between both use @CrossOrigin ( *
 *         means making public) ("http://localhost/4200" means for specific)
 *
 */
//http://localhost:9000/stock/stocks/4
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
	StockService stockService;
	//http://localhost:9000/

	@GetMapping(value = "/employee")
	public Boolean testRequestParam(@RequestParam(value = "eid", required = true) int empId) {
		System.out.println("Emp ID: " + empId);
		return true;
	}

	@GetMapping(value = "/employee2")
	public Boolean testHeaderParam(@RequestHeader(value = "auth-token") String authToken) {
		System.out.println("Auth token: " + authToken);
		return true;
	}

	@PutMapping(value = "/stock/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Updating stocks by Id ", notes = "This REST API returns updated stock by Id")
	public Stock updateStock(@ApiParam(value = "Stock id", name = "id") @PathVariable("id") int stockId,
			@RequestBody Stock updatedStock) {
//		Stock existingStock = getStock(stockId);
//		existingStock.setName(updatedStock.getName());
//		existingStock.setMarket(updatedStock.getMarket());
//		existingStock.setPrice(updatedStock.getPrice());
//		return existingStock;
		return stockService.updateStock(stockId, updatedStock);
	}

	@DeleteMapping(value = "/stock")
	@ApiOperation(value = "Deleting stocks ", notes = "This REST API Delete All Stocks")
	public Boolean deleteAllStocks() {
		// boolean deletingStocks = stocks.removeAll(stocks);
//		stocks.clear();

		return stockService.deleteAllStocks();

	}

	@DeleteMapping(value = "/stock/{id}")
	@ApiOperation(value = "Delete Stocks by Id ", notes = "This REST API Delete Stocks by Id")
	public Boolean deleteStocksByID(@ApiParam(value = "Stock id", name = "id") @PathVariable("id") int stockId) {
		// boolean deletingStocks = stocks.removeAll(stocks);
//		for (Stock stock : stocks) {
//			if (stock.getId() == stockId) {
//				stocks.remove(stock);
//				return true;
//			}
//		}
		return stockService.deleteAllStockById(stockId);

	}

	@PostMapping(value = "/stock", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Adding stocks ", notes = "This REST API returns added stock")
	public Stock createStock(@RequestBody Stock stock) {
//		stock.setId(stocks.size() + 1);
//		stocks.add(stock);
		return stockService.createNewStock(stock);
	}

	@GetMapping(value = "/stock/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Reads stocks by Id ", notes = "This REST API returns stock by Id")
	public Stock getStock(@ApiParam(value = "Stock id", name = "id") @PathVariable("id") int stockId) {
//		for (Stock stock : stocks) {
//			if (stock.getId() == stockId) {
//				return stock;
//			}
//		}
		return stockService.getStockById(stockId);
	}

	@GetMapping(value = "/stock", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Reads all stocks", notes = "This REST API returns list of all stocks")
	public ResponseEntity<List<Stock>> getAllStocks() {
		return new ResponseEntity<List<Stock>> (stockService.getAllStocks(), HttpStatus.OK);
	}

	@PutMapping(value = "/stocks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Updating stocks", notes = "This REST API returns list of all stocks")
	public ResponseEntity<Stock> updateStocks(@PathVariable("id") int stockId, Stock stock) {
		return new ResponseEntity<Stock>(stockService.updateStock(stockId, stock), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/name/{name}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByName(@PathVariable("name") String stockName) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByName(stockName), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/sort/{sortType}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksSortedByName(@PathVariable("sortType") String sortType) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksSortedByName(sortType), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/{startIndex}/{records}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByPage(@PathVariable("startIndex") int startIndex,
			@PathVariable("records") int records) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByPage(startIndex, records), HttpStatus.OK);
	}

	@GetMapping(value = "/stock/like/{namelike}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Stock>> getStocksByNameLike(@PathVariable("namelike") String namelike) {
		return new ResponseEntity<List<Stock>>(stockService.getStocksByNameLike(namelike), HttpStatus.OK);
	}

}
