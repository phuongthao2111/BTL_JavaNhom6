package objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import interfaces.HousingManager;


public class Housing extends Product implements Comparable<Housing>, HousingManager, Serializable {

	/// Constants

	public static final float DIENTICH = 0f;
	public static final byte SOPHONG = 0;
	public static final byte SOTANG = 0;
	public static final boolean MATDUONG = false; /// có ở mặt đường hay không
	public static final Address DIACHI = new Address();
	/// Object's properties

	private float dienTich;
	private byte soPhong;
	private byte soTang;
	private boolean matDuong;
	private Address diaChi;

	private String FileName = "Housing.bin";
	private int n = 0;
	/// Constructor Methods
	
	public Housing() {
		this(Product.PRODUCT_ID, Product.PRODUCT_NAME, Product.PRODUCT_PRICE, Product.PRODUCT_TOTAL, Housing.DIENTICH,
				(byte) Housing.SOPHONG, (byte) Housing.SOTANG, Housing.DIACHI, Housing.MATDUONG);
	}
	public Housing(String ID) {
		this(ID, Product.PRODUCT_NAME, Product.PRODUCT_PRICE, Product.PRODUCT_TOTAL, Housing.DIENTICH,
				(byte) Housing.SOPHONG, (byte) Housing.SOTANG, Housing.DIACHI, Housing.MATDUONG);
		
	}

	public Housing(String product_id, String product_name, double product_price, int product_total, float dienTich,
			byte soPhong, byte soTang, Address diaChi, boolean matDuong) {
		super(product_id, product_name, product_price, product_total);
		this.dienTich = dienTich;
		this.soPhong = soPhong;
		this.soTang = soTang;
		this.diaChi = diaChi;
		this.matDuong = matDuong;
	}

	/// Other Methods
	@Override
	public String toString() {
		return String.format("%-6s %-15s %-7s %-15s %-15s %-10s %-10s %-40s %-10s",this.getProduct_id(),
				this.getProduct_name(),getProduct_price(),this.getProduct_total(),this.getDienTich(),
				this.getSoPhong(),this.getSoTang(),this.getDiaChi(),this.isMatDuong()
				);
	}

	@Override
	/// nếu ở mặt đường thì tăng 10% giá trị
	public double getProduct_price() {
		if (!matDuong) {
			return super.getProduct_price();
		} else {
			return 110 * super.getProduct_price() / 100;
		}
	}

	/// getter and setter methods

	public float getDienTich() {
		return dienTich;
	}

	public byte getSoPhong() {
		return soPhong;
	}

	public byte getSoTang() {
		return soTang;
	}

	public boolean isMatDuong() {
		return matDuong;
	}

	public Address getDiaChi() {
		return diaChi;
	}
	@Override
	public int getNumber() {
		return n;
	}

	public void setDienTich(float dienTich) {
		this.dienTich = dienTich;
	}

	public void setSoPhong(byte soPhong) {
		this.soPhong = soPhong;
	}

	public void setSoTang(byte soTang) {
		this.soTang = soTang;
	}

	public void setMatDuong(boolean matDuong) {
		this.matDuong = matDuong;
	}

	public void setDiaChi(Address diaChi) {
		this.diaChi = diaChi;
	}
	public void setDiaChi(String StreetName,String districtName,String cityName) {
		this.diaChi.setCityName(cityName);
		this.diaChi.setDistrictName(districtName);
		this.diaChi.setStreetName(StreetName);
		
	}
	/// Override interface
	@Override
	public boolean addHousing(Housing h) {

		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		if (editHousing(h)) {
			return false;
		} else {
			danhSach.add(h);
			n++;
			try {
				OutputFile(FileName, danhSach, n);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}

	@Override
	public boolean checkHousing(Housing h) {

		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		for (Housing house : danhSach) {
			if (house.getProduct_id().equals(h.getProduct_id()))
				return true;
		}
		return false;

	}

	@Override
	public boolean editHousing(Housing h) {
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		for (int i = 0; i < n; i++) {
			if (danhSach.get(i).getProduct_id().equals(h.getProduct_id())) {
				danhSach.set(i, h);
				try {
					OutputFile(FileName, danhSach, n);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delHousing(Housing h) {
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		if (checkHousing(h)) {
			for (int i = 0; i < n; i++) {
				if (danhSach.get(i).getProduct_id().equals(h.getProduct_id())) {
					danhSach.remove(danhSach.get(i));
					break;
				}
			}
			n--;
			try {
				OutputFile(FileName, danhSach, n);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Housing> searchHousing(String name) {
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		List<Housing> result = new ArrayList<>();
		for (Housing house : danhSach) {
			if (house.getProduct_name().equals(name)) {
				result.add(house);
			}
		}
		return result;
	}

	@Override
	public List<Housing> sortedHousing(double price) {
		/// price, theo hiểu là lấy tất cả sản phầm có giá trị < price rồi sắp xếp theo
		/// giảm dần
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		List<Housing> result = new ArrayList<>();
		for (Housing house : danhSach) {
			if (house.getProduct_price() < price) {
				result.add(house);
			} else
				continue;
		}
		Collections.sort(result);

		return result;
	}

	@Override
	public void inDanhSach() {
		System.out.println("===============================");
		System.out.println("ID|Name|Price|Total|Area|NumberOfRoom|NumberOfFloor|Address|NearStreet");
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		for (Housing house : danhSach) {
			System.out.println(house);
		}

	}

	public static void inDanhSach(List<Housing> a) {
		System.out.println("===============================");
		System.out.println("ID|Name|Price|Total|Area|NumberOfRoom|NumberOfFloor|Address|NearStreet");
		// TODO Auto-generated method stub
		for (Housing house : a) {
			System.out.println(house);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Address add1 = new Address("Hà Nội", "Bắc Từ Liêm", "Đống Đa");
		Address add2 = new Address("Hà Nội", "Bắc Từ Liêm", "Xuân Tảo");
		Address add3 = new Address("Hồ Chí Minh", "Quận 1", "Bùi Thị Xuân");
		Address add4 = new Address("Hồ Chí Minh", "Quận 3", "Cao Thắng");

		Housing a1 = new Housing("H01", "Biệt thự", 7000, 2, 300, (byte) 10, (byte) 3, add1, true);
		Housing a2 = new Housing("H02", "Chung cư", 2000, 7, 80, (byte) 4, (byte) 1, add2, false);
		Housing a3 = new Housing("H03", "Biệt thự", 8000, 1, 320, (byte) 8, (byte) 3, add3, true);
		Housing a4 = new Housing("H04", "Căn hộ", 3000, 2, 100, (byte) 5, (byte) 2, add4, false);

		HousingManager QuanLy = new Housing();
		QuanLy.inDanhSach();
		QuanLy.addHousing(a1);
		QuanLy.addHousing(a2);
		QuanLy.addHousing(a3);
		QuanLy.addHousing(a4);

		/// sau khi thêm các thông tin của Housing
		QuanLy.inDanhSach();

		/// edit sửa thông tin của a1 về tên "Biệt thự" -> "căn hộ" và giá từ 7000->8000
		a1.setProduct_name("căn hộ");
		a1.setProduct_price(8000);
		QuanLy.editHousing(a1);
		QuanLy.inDanhSach();

		/// xóa thông tin của a2 ra khỏi danh sách
		QuanLy.delHousing(a2);
		QuanLy.inDanhSach();

		/// tìm Kiếm theo tên
		List<Housing> tmp = new ArrayList<>();
		tmp = QuanLy.searchHousing("Biệt thự");
		Housing.inDanhSach(tmp);

		/// tìm kiếm theo giá
		tmp = QuanLy.searchHousing(8800);
		Housing.inDanhSach(tmp);

		/// tìm kiếm bắt động sản ở gần đường
		//// true là có ở gần đường
		tmp = QuanLy.searchHousing(true);
		Housing.inDanhSach(tmp);

		/// Sắp xếp theo giá
		tmp = QuanLy.sortedHousing(9000);
		Housing.inDanhSach(tmp);

		/// sắp xếp theo diện tích của bất động sản
		/// true là sắp xếp tăng dần theo diện tích

		tmp = QuanLy.sortedHousing(true);
		Housing.inDanhSach(tmp);
	}

	@Override
	public List<Housing> searchHousing(double price) {
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		List<Housing> result = new ArrayList<>();
		for (Housing house : danhSach) {
			if (house.getProduct_price() == price) {
				result.add(house);
			}
		}
		return result;
	}

	@Override
	public List<Housing> searchHousing(boolean NearStreet) {
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		List<Housing> result = new ArrayList<>();
		for (Housing house : danhSach) {
			if (house.isMatDuong() == NearStreet) {
				result.add(house);
			}
		}
		return result;
	}

	@Override
	public int compareTo(Housing o) {
		// TODO Auto-generated method stub
		if (this.getProduct_price() > o.getProduct_price()) {
			return -1;
		} else
			return 1;
	}

	@Override
	public List<Housing> sortedHousing(boolean Asc) {
		// TODO Auto-generated method stub
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);

		List<Housing> tmp = new ArrayList<>();

		tmp.addAll(danhSach);

		if (Asc) {
			Collections.sort(tmp, new SortedByArea());

		} else {
			//
			Collections.sort(tmp, new SortedByArea().reversed());
		}

		return tmp;
	}

	class SortedByArea implements Comparator<Housing> {

		@Override
		public int compare(Housing o1, Housing o2) {

			if (o1.getDienTich() > o2.getDienTich()) {
				return 1;
			} else
				return -1;
		}
	}

	@Override
	public List<Housing> getList() {
		List<Housing> danhSach = new ArrayList<>();
		danhSach = InputFile(FileName, n);
		
		return danhSach;
	}

	

}
