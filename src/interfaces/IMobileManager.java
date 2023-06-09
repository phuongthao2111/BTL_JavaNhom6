package interfaces;
import java.util.List;

import objects.Mobile;

public interface IMobileManager {

	/**
	 * Add a mobile to mobile list.<br>
	 * @param m
	 * @return
	 */
	public boolean addMobile(Mobile m);
	
	/**
	 * Edit a mobile by product_id in mobile list.<br>
	 * @param m
	 * @return
	 */
	public boolean editMobile(Mobile m);
	
	/**
	 * Delete a mobile product in mobile list.<br>
	 * @param m
	 * @return
	 */
	public boolean delMobile(Mobile m);
	
	/**
	 * Search mobile list by name.<br>
	 * @param name
	 * @return
	 */
	public List<Mobile> searchMobile(String name);
	
	/**
	 * Search mobile list by price.<br>
	 * @param name
	 * @return
	 */
	public List<Mobile> searchMobile(double price);
	
	/**
	 * Search mobile list by storageCapacity.<br>
	 * @param name
	 * @return
	 */
	public List<Mobile> searchMobile(int storageCapacity);
	
	/**
	 * Search mobile list by battery capacity.<br>
	 * @param name
	 * @return
	 */
	public List<Mobile> searchMobileByBattery(int batteryCapacity);
	
	/**
	 * Search mobile list by release time.<br>
	 * @param name
	 * @return
	 */
	public List<Mobile> searchMobileByRelease(int releaseTime);
	
	/**
	 * Sort mobile list by price
	 * @param price
	 * @return
	 */
	public List<Mobile> sortedMobile(double price);
}
