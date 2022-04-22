package com.zensar.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection  = "stocks")
public class StockDocument {
	@Id	
	private int id;

	private String name;
	
	private String marketName;
	
	private int price;

	public StockDocument(int id, String name, String marketName, int price) {
		super();
		this.id = id;
		this.name = name;
		this.marketName = marketName;
		this.price = price;
	}

	public StockDocument() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getmarketName() {
		return marketName;
	}

	public void setmarketName(String market) {
		this.marketName = market;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockEntity [id=" + id + ", name=" + name + ", market=" + marketName + ", price=" + price + "]";
	}
}
