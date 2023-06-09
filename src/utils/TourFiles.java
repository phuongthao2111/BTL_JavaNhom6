/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.*;
import objects.Tour;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Doan Van Quan
 */
public class TourFiles {
	// du lieu trong file co dinh dang

	// lay vi tri con tro chuot
	public long getPos(String pathFile) {
		try {
			RandomAccessFile raf = new RandomAccessFile(pathFile, "rw");
			long length = raf.length();
			raf.close();
			return length;
		} catch (IOException ex) {
			Logger.getLogger(TourFiles.class.getName()).log(Level.SEVERE, null, ex);
		}
		return 0;
	}

	// sinh ngau nhien danh sach tour
	public static Tour[] generateProduct(int n) {
		Tour[] list = new Tour[n];
		String[] productID = { "TOUR" };
		String[] productName = { "Du lich " };
		String[] tourTime = { "7 ngay 6 dem", "7 ngay 7 dem", "6 ngay 6 dem", "6 ngay 5 dem", "8 ngay 8 dem",
				"8 ngay 7 dem", "2 ngay 1 dem", "2 ngay 2 dem", "3 ngay 3 dem", "3 ngay 2 dem", "10 ngay 10 dem",
				"10 ngay 9 dem" };
		String[] tourTravel = { "Singapore", "Viet Nam", "Thuy Dien", "Thai Lan", "Trung Quoc", "Han Quoc", "Nga",
				"Nauy", "Lao", "Campuchia" };
		double[] productPrice = { 1000000.0, 1200000.0, 1300000.0, 1400000.0, 2000000.0, 2200000.0, 2500000.0,
				3200000.0, 3000000.0, 3600000.0, 9000000.0, 1200000.0, 3300000.0, 6700000.0, 8000000.0, 9200000.0,
				9000000.0, 8200000.0, 7000000.0, 1100000.0, 1300000.0, 1700000.0, 1000000.0, 1200000.0, 1000000.0,
				1200000.0, 1000000.0, 1260000.0, 1070000.0, 1280000.0, 4000000.0, 4500000.0, 5000000.0, 7800000.0,
				8800000.0, 9520000.0 };

		// thuc hien sinh ngau nhien danh sach
		int index;
		for (int i = 0; i < n; i++) {
			list[i] = new Tour();
			list[i].setProduct_id(productID[0] + (i + 1));
			list[i].setProduct_name(productName[0] + (i + 1));
			index = (int) (Math.random() * productPrice.length);
			list[i].setProduct_price(productPrice[index]);

			list[i].setProduct_total((int) (Math.random() * 100));
			index = (int) (Math.random() * tourTime.length);
			list[i].setTour_time(tourTime[index]);
			index = (int) (Math.random() * tourTravel.length);
			list[i].setTour_travel(tourTravel[index]);
			index = 2 + (int) (Math.random() * 10);
			list[i].setTour_numberPerson(index);
		}
		return list;
	}

	// ghi vao file
	public static void binaryOutFile(String pathFile, short n) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(pathFile, "rw");
		Tour[] list = generateProduct(n);
		for (int i = 0; i < n; i++) {
			raf.writeUTF(list[i].toString());
		}

		raf.close();
	}

	// cap nhat lai file sau moi lan them,sua, xoa, update
	public static void updateOutFile(String pathFile, ArrayList<Tour> list) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(pathFile, "rw");
		raf.seek(raf.length());
		for (int i = 0; i < list.size(); i++) {
			raf.writeUTF(list.get(i).toString());
		}
		raf.close();
	}

	public static ArrayList<Tour> binaryInputFile(String pathFile, short n) throws IOException, ClassNotFoundException {
		RandomAccessFile in = new RandomAccessFile(pathFile, "rw");
		ArrayList<Tour> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String[] str = in.readUTF().split(",");
			list.add(new Tour(str[0].trim(), str[1].trim(), Double.parseDouble(str[2].trim()),
					Integer.parseInt(str[3].trim()), str[4].trim(), str[5].trim(), Integer.parseInt(str[6].trim())));
		}
		in.close();
		System.out.println("---------------------------------------------------");
		return list;
	}

	// doc du lieu tu file sau khi them, sua, xoa, sap xep
	public static ArrayList<Tour> listAfterUpdateFile(String pathFile, int n, long pos)
			throws IOException, ClassNotFoundException {
		RandomAccessFile in = new RandomAccessFile(pathFile, "rw");
		ArrayList<Tour> list = new ArrayList<>();
		in.seek(pos);
		for (int i = 0; i < n; i++) {
			String[] str = in.readUTF().split(",");
			list.add(new Tour(str[0].trim(), str[1].trim(), Double.parseDouble(str[2].trim()),
					Integer.parseInt(str[3].trim()), str[4].trim(), str[5].trim(), Integer.parseInt(str[6].trim())));
		}
		in.close();
		return list;
	}

	public static void reportFiles(String pathFile, int n, ArrayList<Tour> list) throws IOException {
		FileOutputStream out = new FileOutputStream(pathFile);
		BufferedOutputStream outFile = new BufferedOutputStream(out);
		String str = "Tổng số hành trình là: " + list.size() + "\n";
		byte b[] = str.getBytes();
		outFile.write(b);
		for (int i = 0; i < list.size(); i++) {
			b = list.get(i).getInfor().getBytes();
			outFile.write(b);
		}
		outFile.close();
		out.close();

	}
}
