package objects;

import java.io.Serializable;

public class Address implements Serializable{
	
	/// Constants
	public static final String CITYNAME = "No city name";
	public static final String DISTRICTNAME = "No district name";
	public static final String STREETNAME = "No street name";
	
	/// Object's properties
	private String cityName;
	private String districtName;
	private String streetName;
	
	/// Constructor methods
	
	public Address() {
		this(Address.CITYNAME,Address.DISTRICTNAME,Address.STREETNAME);
	}
	
	public Address(String cityName, String districtName, String streetName) {
		this.cityName=cityName;
		this.districtName=districtName;
		this.streetName=streetName;
	}

	/// other Method
	public String toString() {
		return streetName+ ", "+districtName+", "+cityName;
	}
	
	
	/// getter and setter methods
	public String getCityName() {
		return cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	
}
