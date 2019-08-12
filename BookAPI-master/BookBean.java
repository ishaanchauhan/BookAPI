package com.bcs.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class BookBean {

	private int id;
	
	@NotNull(message="@bookName:must not be null")
	@Pattern(regexp="[a-zA-Z]{1,100}$", message="Book name should be alphabetical and less than or equal to 100 characters.")
	private String bookName;
	
	@NotNull(message="@author:must not be null")
	@Pattern(regexp="[a-zA-Z]{1,100}$", message="Author name should be alphabetical and less than or equal to 100 characters.")
	private String author;
	
	@NotNull
	@Pattern(regexp="^\\d+(\\.\\d{1,2})?$", message="Book price should be decimal and upto 2 decimals.")
	private double price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
