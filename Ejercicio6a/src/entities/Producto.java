package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Producto {
	
	final private DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/MM/uuuu - HH:mm");
	private int id;
	private String name;
	private String description;
	private double price;
	private int stock;
	private boolean shippingIncluded;
	private LocalDateTime disabledOn;
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isShippingIncluded() {
		return shippingIncluded;
	}
	public void setShippingIncluded(boolean shippingIncluded) {
		this.shippingIncluded = shippingIncluded;
	}
	
	public LocalDateTime getDisabledOn() {
		return disabledOn;
	}

	public void setDisabledOn(LocalDateTime disabledOn) {
		this.disabledOn = disabledOn;
	}
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
			+ ", stock=" + stock + ", shippingIncluded=" + shippingIncluded + ", disabledOn=" + (disabledOn==null?null:disabledOn.format(formato)) + "]\n";
	}
	
	
	

}
