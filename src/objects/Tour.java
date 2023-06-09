/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objects;

import interfaces.TourManager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.*;
import javax.swing.JTextField;

/**
 *
 * @author Dang Kieu Trang
 */

public class Tour extends Product implements TourManager, Serializable {

	// constants
	public static final String TOUR_TIME = "None";
	public static final String TOUR_TRAVEL = "None";
	public static final int TOUR_NUMBERPERSON = 0;

	// properties
	private String tour_time; // thời gian hành trình
	private String tour_travel; // điểm đến hành trình
	private int tour_numberPerson;

	// other properties

	public JTextField txtTourId;
	public JTextField txtTourName;
	public JTextField txtTourPrice;
	public JTextField txtTourTotal;
	public JTextField txtTourTime;
	public JTextField txtTourTravel;
	public JTextField txtTourNumberPerson;

	// constructor
	public Tour() {
		this(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE, PRODUCT_TOTAL, TOUR_TIME, TOUR_TRAVEL, TOUR_NUMBERPERSON);
	}

	public Tour(String product_id, String product_name, double product_price, int product_total, String tour_time,
			String tour_travel, int tour_numberPerson) {

		super(product_id, product_name, product_price, product_total);

		this.tour_time = tour_time;
		this.tour_travel = tour_travel;
		this.tour_numberPerson = tour_numberPerson;
	}

	public Tour(JTextField txtTourId, JTextField txtTourName, JTextField txtTourPrice, JTextField txtTourTotal,
			JTextField txtTourTime, JTextField txtTourTravel, JTextField txtTourNumberPerson) {
		this.txtTourId = txtTourId;
		this.txtTourName = txtTourName;
		this.txtTourPrice = txtTourPrice;
		this.txtTourTotal = txtTourTotal;
		this.txtTourTime = txtTourTime;
		this.txtTourTravel = txtTourTravel;
		this.txtTourNumberPerson = txtTourNumberPerson;
	}

	// getter
	public String getTour_time() {
		return tour_time;
	}

	public String getTour_travel() {
		return tour_travel;
	}

	public int getTour_numberPerson() {
		return tour_numberPerson;
	}

	// setter

	public void setTour_time(String tour_time) {
		this.tour_time = tour_time;
	}

	public void setTour_travel(String tour_travel) {
		this.tour_travel = tour_travel;
	}

	public void setTour_numberPerson(int tour_numberPerson) {
		this.tour_numberPerson = tour_numberPerson;
	}

	// other method

	@Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	Tour p = (Tour) obj;
        return this.getProduct_id().equalsIgnoreCase(p.getProduct_id());
    }
	
	// ovrride
	@Override
	public boolean addTour(Tour t) {
		// TODO Auto-generated method stub
		t.setProduct_id(t.txtTourId.getText());
		t.setProduct_name(t.txtTourName.getText());
		t.setProduct_price(Double.parseDouble(t.txtTourPrice.getText()));
		t.setProduct_total(Integer.parseInt(t.txtTourTotal.getText()));
		t.setTour_time(t.txtTourTime.getText());
		t.setTour_travel(t.txtTourTravel.getText());
		t.setTour_numberPerson(Integer.parseInt(t.txtTourNumberPerson.getText()));
		// thêm hành trình
		if (!list.contains(t)) {
			list.add(t);
			return true;
		}
		return false;
	}

	@Override
	public boolean editTour(Tour t) {

		// sửa hành trình
		t.setProduct_id(t.txtTourId.getText());
		if (list.contains(t)) {
			int vitri = list.indexOf(t);
			list.get(vitri).setProduct_name(t.txtTourName.getText());
			list.get(vitri).setProduct_price(Double.parseDouble(t.txtTourPrice.getText()));
			list.get(vitri).setProduct_total(Integer.parseInt(t.txtTourTotal.getText()));
			list.get(vitri).setTour_time(t.txtTourTime.getText());
			list.get(vitri).setTour_travel(t.txtTourTravel.getText());
			list.get(vitri).setTour_numberPerson(Integer.parseInt(t.txtTourNumberPerson.getText()));
			return true;
		}
		return false;
	}

	@Override
	public boolean delTour(Tour t) {
		// xóa hành trình
		t.setProduct_id(t.txtTourId.getText());
		if (list.contains(t)) {
			list.remove(list.indexOf(t));
			return true;
		}
		return false;
	}

	@Override
	public List<Tour> searchTour(String name) {

		// tìm kiếm theo tên hành trình
		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getProduct_name().equalsIgnoreCase(name)) {
				tmp.add(p);
			}
		});

		return tmp;
	}

	@Override
	public List<Tour> searchTour(double price) {
		// tìm kiếm theo chi phí hành trình
		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getProduct_price() == price) {
				tmp.add(p);
			}
		});
		return tmp;
	}

	@Override
	public List<Tour> searchTour(int numberPerson) {

		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getTour_numberPerson() == numberPerson) {
				tmp.add(p);
			}
		});
		return tmp;
	}

	@Override
	public List<Tour> searchTourByTime(String time) {

		// tìm kiếm theo thời gian hành trình
		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getTour_time().equalsIgnoreCase(time)) {
				tmp.add(p);
			}
		});

		return tmp;
	}

	@Override
	public List<Tour> searchTourByTravel(String travel) {

		// tìm kiếm theo điểm đến hành trình
		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getTour_travel().equalsIgnoreCase(travel)) {
				tmp.add(p);
			}
		});

		return tmp;
	}

	@Override
	public List<Tour> sortedTour(double price) {

		// sẵps xếp theo chi phí hành trinnhf
		List<Tour> tmp = new ArrayList<>();
		list.forEach((p) -> {
			if (p.getProduct_price() >= price) {
				tmp.add(p);
			}
		});
		Collections.sort(tmp, new SortedTourByPrice());
		return tmp;
	}

	@Override
	public List<Tour> sortedTour(String time) {

		// sắp xếp theo thời gian hành trình
		List<Tour> tmpn = new ArrayList<>();

		// dữ liệu đầu vào thời gian hành trình
		// ví dụ 7 ngày 6 đêm hoặc 7 ngày 7 đêm
		// => 7.6 hoặc 7.7
		double t = transferTime(time);
		list.forEach((p) -> {
			if (transferTime(p.getTour_time()) <= t) {
				tmpn.add(p);
			}
		});

		Collections.sort(tmpn, new SortedTourByTime());
		return tmpn;
	}

	@Override
	public String toString() {
		return String.format("%-10s , %-30s , %-10f , %-5d ,%-15s , %-10s , %5d", this.getProduct_id(),
				this.getProduct_name(), this.getProduct_price(), this.getProduct_total(), this.tour_time,
				this.tour_travel, this.tour_numberPerson);

	}

	public String getInfor() {
		return String.format("%-10s  %-30s  %-10f  %-5d ,%-15s  %-10s  %5d\n", this.getProduct_id(),
				this.getProduct_name(), this.getProduct_price(), this.getProduct_total(), this.tour_time,
				this.tour_travel, this.tour_numberPerson);
	}

	public double transferTime(String time) {

		// chuyển đổi thời gian hành trình thành double
		time = time.trim();
		String[] str = time.split(" ");
		double t;
		if (str.length >= 3) {
			t = Double.parseDouble(str[0] + "." + str[str.length - 2]);
			return t;
		} else {
			// 7 ngay
			t = Double.parseDouble(str[0] + ".0");
			return t;
		}
	}
}

// sắp xếp theo chi phí hành trình
class SortedTourByPrice implements Comparator<Tour> {

	@Override
	public int compare(Tour o1, Tour o2) {
		// TODO Auto-generated method stub

		return (int) (o1.getProduct_price() - o2.getProduct_price());
	}

}

// sắp xếp theo thời gian hành trình
class SortedTourByTime implements Comparator<Tour> {

	@Override
	public int compare(Tour o1, Tour o2) {
		// TODO Auto-generated method stub
		double time1 = o2.transferTime(o2.getTour_time());
		double time2 = o1.transferTime(o1.getTour_time());
		return (int) (time2 * 100 - time1 * 100);
	}

}
