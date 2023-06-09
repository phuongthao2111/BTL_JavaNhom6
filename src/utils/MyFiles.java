package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import objects.Mobile;

public class MyFiles {

	public static Mobile generateMobile() {
		int index;
		Mobile mobile = new Mobile();
		// Tạo danh sách tên sản phẩm
		String[] productNames = { "IPhone 6", "IPhone 6s", "IPhone 7", "IPhone 7 Plus", "IPhone 8", "IPhone 8 Plus",
				"IPhone X", "IPhone XS", "IPhone XS Max", "IPhone 11", "IPhone 11 Pro", "IPhone 11 Pro Max",
				"IPhone 12 Mini", "IPhone 12 Pro", "IPhone 12 Pro Max", "IPhone 13 Mini", "IPhone 13 Pro",
				"IPhone 13 Pro Max", "SamSung Galaxy A33", "SamSung Galaxy A53", "SamSung Galay A13",
				"SamSung Galaxy A23", "SamSung Galaxy S22", "SamSung Galaxy S21", "SamSung Galaxy A03" };

		// random id sản phẩm
		mobile.setProduct_id(String.valueOf((int)(Math.random() * 1000)));

		// random tên sản phẩm
		index = (int) (Math.random() * productNames.length);
		mobile.setProduct_name(productNames[index]);

		// random giá sản phẩm
		index = (int) (Math.random() * 1000) + 800;
		mobile.setProduct_price(index);

		// random số lượng sản phẩm
		index = (int) (Math.random() * 100) + 5;
		mobile.setProduct_total(index);

		// random dung lượng bộ nhớ
		index = (int) ((Math.random() * 6) + 2) * 32;
		mobile.setStorageCapacity(index);

		// random dung lượng pin
		index = (int) (Math.random() * 4000) + 2000;
		mobile.setBatteryCapacity(index);

		// random năm sản xuất sản phẩm
		index = (int) (Math.random() * 3) + 2019;
		mobile.setReleaseTime(index);
		return mobile;
	}

	/**
	 * This method is used to generate Mobile list.<br>
	 * 
	 * @param count
	 * @return
	 */
	public static List<Mobile> generateMobiles(int count) {
		// Tạo mảng lưu dữ liệu
		List<Mobile> mobileList = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			// Sinh ngẫu nhiên một sản phẩm
			// Thêm sản phẩm vào danh sách
			mobileList.add(generateMobile());
		}
		// Trả về kết quả
		return mobileList;
	}

	/**
	 * This method is used to read mobile list from file.<br>
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Mobile> mobileInputStream() throws IOException, ClassNotFoundException {
		// Khai báo biến danh sách sản phẩm
		List<Mobile> mobileList = new ArrayList<Mobile>();
		// Khai báo đối tượng file
		File file = new File("Mobile.bin");
		// Kiểm tra file đã tồn tại
		if (file.exists()) {
			// Xác định đối tượng tập tin xuất dữ liệu
			FileInputStream inFile = new FileInputStream("Mobile.bin");
			// Khai báo đối tượng thực hiện xuất
			ObjectInputStream in = new ObjectInputStream(inFile);
			// Đọc kích thước danh sách
			int size = in.readInt();
			// Duyệt theo kích thước để đọc thông tin
			for (int index = 0; index < size; index++) {
				// Đọc đối tượng và lưu vào danh sách
				mobileList.add((Mobile) in.readObject());
			}
			// đóng file đang đọc
			in.close();
		} else {
			mobileList = generateMobiles(10);
			mobileOutputStream(mobileList, "Mobile.bin");
		}
		// Trả kết quả danh sách
		return mobileList;
	}

	/**
	 * This method is used to write mobile list to file.<br>
	 */
	public static void mobileOutputStream(List<Mobile> list, String fileName) throws IOException {
		// Xác định đối tượng tập tin xuất dữ liệu
		FileOutputStream outFile = new FileOutputStream(fileName);
		// Khai báo đối tượng xuất dữ liệu
		ObjectOutputStream out = new ObjectOutputStream(outFile);
		// In kích thước danh sách lên đầu
		out.writeInt(list.size());

		// Duyệt phần tử trong danh sách
		for (int index = 0; index < list.size(); index++) {
			// Xuất từng phần tử ra file
			out.writeObject(list.get(index));
		}
		// Đóng file sau khi xuất
		out.close();
	}
	
	public static void exportStream(List<Mobile> list, String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		PrintWriter writer = new PrintWriter(fw);
		writer.println("Tổng số sản phẩm: " + list.size());
		writer.printf("%-10s%-25s%8s%10s%10s%10s%16s\n", "ID", "Tên sản phẩm", "Giá sản phẩm", "Số lượng", "Bộ nhớ", "Pin", "Năm sản xuất");
		list.forEach(m -> {
			writer.println(m);
		});
		writer.close();
	}
}
