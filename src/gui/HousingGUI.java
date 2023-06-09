package gui;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import interfaces.HousingManager;
import objects.Address;
import objects.Housing;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;

public class HousingGUI extends JPanel {

	private HousingManager QuanLy = new Housing();
	private List<Housing> list = new ArrayList<>();
	DefaultTableModel tableModel1 = new DefaultTableModel();
	JTable table = new JTable(tableModel1);

	public void Design(JPanel Frame) {
		setLayout(null);	
		JLabel Id = new JLabel("ID :");
		Id.setBounds(59, 20, 40, 40);
		Id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Frame.add(Id);
		JTextField text_Id = new JTextField();
		text_Id.setFont(new Font("Tahoma", Font.PLAIN, 20));
		text_Id.setBounds(93, 20, 170, 40);
		Frame.add(text_Id);

		JLabel Name = new JLabel("Name: ");
		Name.setBounds(313, 20, 101, 40);
		Name.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Name = new JTextField();
		text_Name.setBounds(383, 20, 170, 40);
		Frame.add(text_Name);
		Frame.add(Name);

		JLabel Price = new JLabel("Price: ");
		Price.setBounds(563, 20, 98, 40);
		Price.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Price = new JTextField();
		text_Price.setBounds(616, 20, 170, 40);
		Frame.add(Price);
		Frame.add(text_Price);

		JLabel Total = new JLabel("Total: ");
		Total.setBounds(809, 20, 103, 40);
		Total.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Total = new JTextField();
		text_Total.setBounds(874, 20, 170, 40);
		Frame.add(text_Total);
		Frame.add(Total);

		JLabel Area = new JLabel("Area:");
		Area.setBounds(39, 80, 50, 40);
		Area.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Area = new JTextField();
		text_Area.setBounds(93, 80, 170, 40);
		Frame.add(text_Area);
		Frame.add(Area);

		JLabel Room = new JLabel("Room: ");
		Room.setBounds(315, 80, 80, 40);
		Room.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Room = new JTextField();
		text_Room.setBounds(383, 80, 170, 40);
		Frame.add(text_Room);
		Frame.add(Room);

		JLabel Floor = new JLabel("Floor: ");
		Floor.setBounds(563, 80, 80, 40);
		Floor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Floor = new JTextField();
		text_Floor.setBounds(616, 80, 170, 40);
		Frame.add(Floor);
		Frame.add(text_Floor);

		JLabel Address = new JLabel("Address: ");
		Address.setBounds(26, 140, 106, 40);
		Address.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel Street = new JLabel("Street,");
		Street.setBounds(325, 140, 61, 40);
		Street.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel District = new JLabel("District,");
		District.setBounds(600, 140, 80, 40);
		District.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel City = new JLabel("City.");
		City.setBounds(874, 140, 50, 40);
		City.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JTextField text_Street = new JTextField();
		text_Street.setBounds(114, 140, 200, 40);
		JTextField text_District = new JTextField();
		text_District.setBounds(390, 140, 200, 40);
		JTextField text_City = new JTextField();
		text_City.setBounds(670, 140, 200, 40);
		Frame.add(Address);
		Frame.add(Street);
		Frame.add(District);
		Frame.add(City);
		Frame.add(text_Street);
		Frame.add(text_District);
		Frame.add(text_City);

		JCheckBox NearStreet = new JCheckBox("Near Street");
		NearStreet.setBounds(926, 140, 170, 40);
		NearStreet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Frame.add(NearStreet);

		JButton button_Add = new JButton("Add");
		button_Add.setBounds(230, 190, 100, 60);
		button_Add.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Housing item1 = new Housing();
				
				item1.setProduct_id(text_Id.getText());
				item1.setProduct_name(text_Name.getText());
			
				try {
				item1.setProduct_price(Double.parseDouble(text_Price.getText()));
				item1.setProduct_total(Integer.parseInt(text_Total.getText()));
				item1.setDienTich(Float.parseFloat(text_Area.getText()));
				item1.setSoPhong(Byte.parseByte(text_Room.getText()));
				item1.setSoTang(Byte.parseByte(text_Floor.getText()));
				}
				catch(Exception e1 ){
					JOptionPane.showMessageDialog(Frame,"Nhập đầy đủ giá trị vào ");
					return;
				}
				item1.setMatDuong(NearStreet.isSelected());
				
				Address address = new Address(text_City.getText(), text_District.getText(), text_Street.getText());
				item1.setDiaChi(address);
		
				QuanLy.addHousing(item1);
				list.clear();
				list.addAll(QuanLy.getList());
				ShowResult(list);
			}

		});
		Frame.add(button_Add);

		JButton button_Reset = new JButton("Reset");
		button_Reset.setBounds(570, 190, 100, 60);
		button_Reset.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text_Id.setText("");
				text_Name.setText("");
				text_Price.setText("");
				text_Area.setText("");
				text_Total.setText("");
				text_Room.setText("");
				text_Floor.setText("");
				NearStreet.setSelected(false);
				text_Street.setText("");
				text_District.setText("");
				text_City.setText("");

			}

		});
		Frame.add(button_Reset);
		
		
		JButton button_Edit = new JButton("Edit");
		button_Edit.setBounds(400, 190, 100, 60);
		button_Edit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Housing item1 = new Housing();
				item1.setProduct_id(text_Id.getText());
				item1.setProduct_name(text_Name.getText());
				try {
				item1.setProduct_price(Double.parseDouble(text_Price.getText()));
				item1.setProduct_total(Integer.parseInt(text_Total.getText()));
				item1.setDienTich(Float.parseFloat(text_Area.getText()));
				item1.setSoPhong(Byte.parseByte(text_Room.getText()));
				item1.setSoTang(Byte.parseByte(text_Floor.getText()));
				}
				catch(Exception e1 ){
					JOptionPane.showMessageDialog(Frame,"Nhập giá trị hợp lệ.");
					return;
				}
				item1.setMatDuong(NearStreet.isSelected());
				Address address = new Address(text_City.getText(), text_District.getText(), text_Street.getText());
				item1.setDiaChi(address);
				JOptionPane.showMessageDialog(Frame,"Đã Sửa thành công");
				QuanLy.editHousing(item1);
				list.clear();
				list.addAll(QuanLy.getList());
				ShowResult(list);
			}
			
			
		});
		Frame.add(button_Edit);
		
		
		JButton button_Del = new JButton("Del");
		button_Del.setBounds(740, 190, 100, 60);
		button_Del.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID = JOptionPane.showInputDialog(Frame,"Nhập ID (xóa) : ");
				Housing temp = new Housing(ID);
				if(QuanLy.delHousing(temp)) {
					JOptionPane.showMessageDialog(Frame,"Xóa Thành công");
					list.clear();
					list.addAll(QuanLy.getList());
					ShowResult(list);
				}
				else {
					JOptionPane.showMessageDialog(Frame,"Xóa Không Thành Công");
					
				}
				
			}
			
		});
		
		Frame.add(button_Del);
		
		JButton btnSearchName = new JButton("Search Name\r\n");
		btnSearchName.setBounds(619, 270, 200, 60);
		btnSearchName.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSearchName.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String Name= JOptionPane.showInputDialog(Frame,"Nhập Tên(Cần tìm) : ");
				list.clear();
				list.addAll(QuanLy.searchHousing(Name));
				ShowResult(list);
			}
		
		});
		Frame.add(btnSearchName);
		
		
		
		JButton btnSearchPrice = new JButton("Search Price\r\n");
		btnSearchPrice.setBounds(399, 270, 200, 60);
		btnSearchPrice.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnSearchPrice.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String Gia= JOptionPane.showInputDialog(Frame,"Nhập Giá(Đúng giá) : ");
				int price = Integer.parseInt(Gia);
				list.clear();
				list.addAll(QuanLy.searchHousing(price));
				ShowResult(list);
			}
		
		});
		Frame.add(btnSearchPrice);
		
		JButton button_SearchViTri = new JButton("Search NearStreet");
		button_SearchViTri.setBounds(839, 270, 250, 60);
		button_SearchViTri.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_SearchViTri.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int a = JOptionPane.showConfirmDialog(Frame,"Gần đường hay không ?");
				if (a==0) {
					list.clear();
					list.addAll(QuanLy.searchHousing(true));
					ShowResult(list);
				}
				else if (a==1){
					list.clear();
					list.addAll(QuanLy.searchHousing(false));
					ShowResult(list);
				}
				else {
					list.clear();
					list.addAll(QuanLy.getList());
					ShowResult(list);
				}
			
			}
		
		});
		Frame.add(button_SearchViTri);
		
		JButton btnThngTinTt = new JButton("All Housing");
		btnThngTinTt.setBounds(926, 386, 155, 40);
		btnThngTinTt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnThngTinTt.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				list.clear();
				list.addAll(QuanLy.getList());
				ShowResult(list);
			}
		});
		
		Frame.add(btnThngTinTt);
		
		
		

		JButton button_SortPrice = new JButton("SortPrice");
		button_SortPrice.setBounds(59, 270, 150, 60);
		button_SortPrice.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_SortPrice.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String Gia= JOptionPane.showInputDialog(Frame,"Nhập Tiền bạn có : ");
				double price = Double.parseDouble(Gia);
				list.clear();
				list.addAll(QuanLy.sortedHousing(price));
				ShowResult(list);
			}
		
		});
		Frame.add(button_SortPrice);
		
		
		
		JButton button_SortArea = new JButton("SortArea");
		button_SortArea.setBounds(229, 270, 150, 60);
		button_SortArea.setFont(new Font("Tahoma", Font.PLAIN, 25));
		button_SortArea.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Frame,"Đã Sắp xếp");
				list.clear();
				list.addAll(QuanLy.sortedHousing(true));
				ShowResult(list);
			}
		
		});
		Frame.add(button_SortArea);
		
	};



	public void ShowResult(List<Housing> list) {
		tableModel1.setRowCount(0);
		PrintInf(list);
		for (int i = 0; i < list.size(); i++) {
			String[] data = new String[9];

			data[0] = list.get(i).getProduct_id();
			data[1] = list.get(i).getProduct_name();
			data[2] = list.get(i).getProduct_price()+"";
			data[3] = list.get(i).getProduct_total()+"";
			data[4] = list.get(i).getDienTich()+"";
			data[5] = list.get(i).getSoPhong()+"";
			data[6] = list.get(i).getSoTang()+"";
			data[7] = list.get(i).getDiaChi()+"";
			if(list.get(i).isMatDuong()) {
				data[8] = "Có";
			}
			else data[8] ="Không";
	
			tableModel1.addRow(data);
		}
		table.setModel(tableModel1);

	}

	public HousingGUI() {
		setBounds(0, 0, 1138,870);		
		table.setBounds(10, 245, 596, 243);
		tableModel1.addColumn("ID");
		tableModel1.addColumn("Name");
		tableModel1.addColumn("Price($)");
		tableModel1.addColumn("Total");
		tableModel1.addColumn("Area(m2)");
		tableModel1.addColumn("Room");
		tableModel1.addColumn("Floor");
		tableModel1.addColumn("Address");
		tableModel1.addColumn("NearStreet");
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(22, 436, 1070, 316);
		add(scrollPane);
		AutoHousing(25); 
	}
	
	protected void PrintInf(List<Housing> a) {
		try {
			int n = a.size();
			/// Khởi tạo đối tượng file để ghi dữ liệu
			FileWriter outFileWriter = new FileWriter("report.bin");
			/// KHởi tạo đối tượng thực thi ghi vào file
			PrintWriter out = new PrintWriter(outFileWriter);
			out.println("Tổng số sản phẩm : "+ n);
			out.printf("%-6s %-15s %-7s %-15s %-15s %-10s %-10s %-40s %-10s\n","ID","Tên","Giá","Số Lượng","Diện Tích","Số Phòng","Số Tầng","Địa Chỉ","Gần Đường");

			int Stt = 1;
			for (Housing HS : a) {
				out.println(HS);
				Stt++;
			}
			out.close();/// đóng file lại
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected void AutoHousing(int n) {
		Address add1 = new Address("Hà Nội", "Bắc Từ Liêm", "Đống Đa");
		Address add2 = new Address("Hà Nội", "Bắc Từ Liêm", "Xuân Tảo");
		Address add3 = new Address("Hồ Chí Minh", "Quận 1", "Bùi Thị Xuân");
		Address add4 = new Address("Hồ Chí Minh", "Quận 3", "Cao Thắng");
		Address add5 = new Address("Hà Nội","Ba Đình","Đê La Thành");
		Address add6 = new Address("Hà Nội","Ba Đình","Hoàng Ha Thám");
		Address add7 = new Address("Hà Nội","Ba Đình","Hoàng Diệu");
		
		
		List<Address> Ad = new  ArrayList<>();
		Ad.add(add1);
		Ad.add(add2);
		Ad.add(add3);
		Ad.add(add4);
		Ad.add(add5);
		Ad.add(add6);
		Ad.add(add7);
		
	
		
		String[] NameHousing = {"Biệt thự","Chung cư","Nhà Đất"};
		int[] Value = {5000,7000,9000,10000,12000};
		for (int i = 0;i<n;i++) {
			String ID = "H"+(i+1);
			String Name = NameHousing[(i%3)];
			int value = Value[i%5];
			int SL = (i%3)+2;
			int area = (i%5)*20+100;
			int SoPhong = (i%6)+2;
			int SoTang = (i%3)+1;
			boolean a = false ;
			if (Math.random()*10>5) {
				a = true;
			}
			int index = i%7;
			Address address =Ad.get(index);
			Housing a2 = new Housing(ID,Name,value,SL,(float)area,(byte)SoPhong,(byte)SoTang,address,a); 
			QuanLy.addHousing(a2);
		}
		list = QuanLy.getList();
		ShowResult(list);
		Design(this);
		
	
		
		
	}
}
