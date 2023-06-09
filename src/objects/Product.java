package objects;

import java.io.Serializable;

public class Product implements Serializable {
	
	public static String PRODUCT_ID = "No id";
	public static String PRODUCT_NAME = "No name";
	public static double PRODUCT_PRICE = 0.0;
	public static int PRODUCT_TOTAL = 0;
	
	private String product_id;
	private String product_name;
	private double product_price;
	private int product_total;
	
	public Product() {
		this(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_PRICE, Product.PRODUCT_TOTAL);
	}
	
	public Product(String product_id, String product_name, double product_price, int product_total) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_total = product_total;
	}


	public String getProduct_id() {
		return product_id;
	}


	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}


	public double getProduct_price() {
		return product_price;
	}


	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}


	public int getProduct_total() {
		return product_total;
	}


	public void setProduct_total(int product_total) {
		this.product_total = product_total;
	}

	@Override
	public String toString() {
		return String.format("%-10s%-25s%8.2f%10d", product_id, product_name, product_price, product_total);
	}
	
}
