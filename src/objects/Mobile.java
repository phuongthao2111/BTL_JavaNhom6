package objects;

import java.io.Serializable;

public class Mobile extends Product implements Serializable {
	
	public static int STORAGECAPACITY = 0;
	public static int BATERYCAPACITY = 0;
	public static int RELEASETIME = 0;
	
	// dung lượng bộ nhớ
	private int storageCapacity;
	// Dung lượng pin
	private int batteryCapacity;
	// Năm sản xuất
	private int releaseTime;
	
	public Mobile() {
		this(Mobile.PRODUCT_ID, Mobile.PRODUCT_NAME, Mobile.PRODUCT_PRICE, Mobile.PRODUCT_TOTAL, 
				Mobile.STORAGECAPACITY, Mobile.BATERYCAPACITY, Mobile.RELEASETIME);
	}
	
	public Mobile(String product_id, String product_name, double product_price, int product_total, 
			int storageCapacity, int batteryCapacity, int releaseTime) {
		super(product_id, product_name, product_price, product_total);
		this.storageCapacity = storageCapacity;
		this.batteryCapacity = batteryCapacity;
		this.releaseTime = releaseTime;
	}


	public int getStorageCapacity() {
		return storageCapacity;
	}


	public void setStorageCapacity(int storageCapacity) {
		this.storageCapacity = storageCapacity;
	}


	public int getBatteryCapacity() {
		return batteryCapacity;
	}


	public void setBatteryCapacity(int batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}


	public int getReleaseTime() {
		return releaseTime;
	}


	public void setReleaseTime(int releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Product p = (Product) obj;
		return p.getProduct_id().equals(getProduct_id());
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				String.format("%13s%13s%10d", storageCapacity + "GB", batteryCapacity + "MAH", releaseTime);
	}
}
