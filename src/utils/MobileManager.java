package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import interfaces.IMobileManager;
import objects.Mobile;

public class MobileManager implements IMobileManager {

	@Override
	public boolean addMobile(Mobile newMobile) {
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Kiểm tra sản phẩm đã có trong danh sách chưa?
			if (mobileList.indexOf(newMobile) == -1) {
				// Thêm sản phẩm vào danh sách
				mobileList.add(newMobile);
				// lưu danh sách vào file
				MyFiles.mobileOutputStream(mobileList, "Mobile.bin");
				// Trả về true sau khi thêm sản phẩm
				return true;
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Đã có sẵn sản phẩm, trả về false;
		return false;
	}

	@Override
	public boolean editMobile(Mobile m) {
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm vị trí phần tử cần sửa trong danh sách
			for (int index = 0; index < mobileList.size(); index++) {
				if (mobileList.get(index).getProduct_id().equals(m.getProduct_id())) {
					// Nếu thì thấy, gán giá trị mới
					mobileList.set(index, m);
					// lưu danh sách vào file
					MyFiles.mobileOutputStream(mobileList, "Mobile.bin");
					// Trả về kết quả true;
					return true;
				}
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Nếu không tìm thấy, trả về false;
		return false;
	}

	@Override
	public boolean delMobile(Mobile m) {
		// biến lưu kết quả
		boolean result = false;
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Xóa phẩn tử trong mảng có cùng id với sản phẩm
			result = mobileList.removeIf(e -> e.getProduct_id().equals(m.getProduct_id()));
			// Nếu xóa thành công, lưu lại danh sách
			if (result) {
				// lưu danh sách vào file
				MyFiles.mobileOutputStream(mobileList, "Mobile.bin");
			}
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Mobile> searchMobile(String name) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm kiếm các phần tử trong mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getProduct_name().contains(name)) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Mobile> searchMobile(double price) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm kiếm các phần tử trong mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getProduct_price() == price) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Mobile> searchMobile(int storageCapacity) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm kiếm các phần tử trong mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getStorageCapacity() == storageCapacity) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Mobile> searchMobileByBattery(int batteryCapacity) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm kiếm các phần tử trong mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getBatteryCapacity() == batteryCapacity) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Mobile> searchMobileByRelease(int releaseTime) {
		// Tạo mảng mới lưu kết quả tìm kiếm
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm kiếm các phần tử trong mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getReleaseTime() == releaseTime) {
					// Thêm sản phẩm vào mảng kết quả nếu tìm thấy
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Trả về mảng kết quả
		return results;
	}

	@Override
	public List<Mobile> sortedMobile(double price) {
		// Tạo mảng lưu kết quả
		List<Mobile> results = new ArrayList<Mobile>();
		try {
			// đọc danh sách sản phẩm từ file
			List<Mobile> mobileList = MyFiles.mobileInputStream();
			// Tìm các sản phẩm có giá nhỏ hơn price và lưu vào mảng
			mobileList.forEach((mobile) -> {
				if (mobile.getProduct_price() <= price) {
					results.add(mobile);
				}
			});
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// khai báo đối tượng thực hiện sắp xếp
		Comparator<Mobile> comparator = Comparator.comparing(Mobile::getProduct_price);

		// Sắp xếp mảng kết quả
		results.sort(comparator);

		return results;
	}

	

	public static void main(String[] args) {
		MobileManager manager = new MobileManager();

		// Mảng lưu trữ kết quả
		List<Mobile> results = new ArrayList<Mobile>();
		System.out.printf("%-10s%-25s%8s%10s%10s%10s%16s\n", "ID", "Tên sản phẩm", "Giá sản phẩm", "Số lượng", "Bộ nhớ", "Pin", "Năm sản xuất");

		// Danh sách sinh ngẫu nhiên sản phẩm
		// Thêm dữ liệu vào kho
		MyFiles.generateMobiles(10).forEach((p) -> manager.addMobile(p));

		// Xóa một sản phẩm đầu tiên trong kho
//		Mobile firstMobile;
//		try {
//			firstMobile = manager.mobileInputStream().get(0);
//			if (manager.delMobile(firstMobile)) {
//				System.out.println("Đã xóa sản phẩm " + firstMobile.getProduct_id());
//			} else {
//				System.out.println("Không có sản phẩm " + firstMobile.getProduct_id());
//			}
//		} catch (ClassNotFoundException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		// Sửa một sản phẩm
//		Mobile m6 = new Mobile("5881", "Pixel 4XL", 500, 10, 64, 3200, 2019);
//		if (manager.editMobile(m6)) {
//			System.out.println("Sản phẩm đã được sửa.");
//		} else {
//			System.out.println("Không thể sửa sản phẩm");
//		}

		// In ra danh sách sản phẩm
		try {
			MyFiles.mobileInputStream().forEach((pr) -> System.out.println(pr));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();

		// Tìm kiếm sản phẩm
		// Tìm kiếm theo tên sản phẩm
//		results = manager.searchMobile("IPhone 14");

		// Tìm kiếm theo giá sản phẩm
//		results = manager.searchMobile(2000.0);

		// Tìm kiếm theo dung lượng bộ nhớ
//		results = manager.searchMobile(128);

		// Tìm kiếm theo dung lượng pin
//		results = manager.searchMobileByBattery(3100);

		// Tìm kiếm theo năm sản xuất
//		results = manager.searchMobileByRelease(2022);

		// In ra kết quả
//		if (results.size() == 0) {
//			System.out.println("Không tìm thấy sản phẩm nào.");
//		}

		// Sắp xếp sản phẩm theo giá
//		results = manager.sortedMobile(2100);

		// In ra danh sách sản phẩm
//		System.out.println("Danh sách sản phẩm sau khi sắp xếp:");
//		results.forEach((pr) -> System.out.println(pr.toString()));

	}

}
