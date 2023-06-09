package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import objects.Account;
import objects.Mobile;
import utils.MobileManager;
import utils.MyFiles;

public class MobileGUI extends JPanel {

	private JList<Mobile> mobileList;
	private MobileManager manager;
	private DefaultListModel<Mobile> mobileListModel;
	private JTextField txtID, txtName, txtPrice, txtTotal, txtStorage, txtPin;
	private JComboBox<Integer> cbYear;

	/**
	 * Create the frame.
	 */
	public MobileGUI() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(true);
		// setBounds(100, 100, 1280, 800);
		// setPreferredSize(new Dimension(1280, 800));
		setLayout(new BorderLayout());
		// setContentPane(contentPane);
		// setTitle("Quan ly dien thoai");
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(new BorderLayout());
		JPanel menuPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new FlowLayout());
		JButton btnAdd = new JButton("Thêm");
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnEdit = new JButton("Sửa");
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnRemove = new JButton("Xóa");
		btnRemove.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnSort = new JButton("Sắp xếp");
		btnSort.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnShowAll = new JButton("Hiển thị tất cả");
		btnShowAll.setFont(new Font("Tahoma", Font.BOLD, 14));

		topPanel.add(btnAdd);
		topPanel.add(btnEdit);
		topPanel.add(btnRemove);
		topPanel.add(btnSearch);
		topPanel.add(btnSort);
		topPanel.add(btnShowAll);

		JPanel bottomPanel = new JPanel(new FlowLayout());
		inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		JButton btnPriceSearch = new JButton("Tìm kiếm theo giá");
		btnPriceSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnStorageSearch = new JButton("Tìm kiếm theo bộ nhớ");
		btnStorageSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnBatterySearch = new JButton("Tìm kiếm theo pin");
		btnBatterySearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		JButton btnReleaseSearch = new JButton("Tìm kiếm theo năm sản xuất");
		btnReleaseSearch.setFont(new Font("Tahoma", Font.BOLD, 14));

		bottomPanel.add(btnPriceSearch);
		bottomPanel.add(btnStorageSearch);
		bottomPanel.add(btnBatterySearch);
		bottomPanel.add(btnReleaseSearch);
		menuPanel.add(topPanel, BorderLayout.NORTH);
		menuPanel.add(bottomPanel, BorderLayout.SOUTH);
		inputPanel.add(new InputDataPanel(), BorderLayout.NORTH);
		inputPanel.add(menuPanel, BorderLayout.SOUTH);
		add(inputPanel, BorderLayout.NORTH);

		// Khởi tạo biến quản lý sản phẩm
		manager = new MobileManager();
		//
		mobileListModel = new DefaultListModel<>();
		mobileListModel.addElement(new Mobile());

		// Thêm dữ liệu từ file vào danh sách
		try {
			MyFiles.mobileInputStream().forEach((mobile) -> mobileListModel.addElement(mobile));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Tạo và cài đặt danh sách
		mobileList = new JList<>(mobileListModel);
		mobileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mobileList.setSelectedIndex(0);
		mobileList.setVisibleRowCount(3);
		mobileList.setFixedCellHeight(32);
		mobileList.setCellRenderer(new ItemRenderer());
		JScrollPane mobileScrollPane = new JScrollPane(mobileList);
		add(mobileScrollPane, BorderLayout.CENTER);

		// Hành động nút thêm
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (checkDataInput()) {
					// Sinh ngẫu nhiên một sản phẩm
					// Mobile mobile = MyFiles.generateMobile();
					Mobile mobile = new Mobile();
					mobile.setProduct_id(txtID.getText());
					mobile.setProduct_name(txtName.getText());
					mobile.setProduct_price(Double.parseDouble(txtPrice.getText()));
					mobile.setProduct_total(Integer.parseInt(txtTotal.getText()));
					mobile.setStorageCapacity(Integer.parseInt(txtStorage.getText()));
					mobile.setBatteryCapacity(Integer.parseInt(txtPin.getText()));
					mobile.setReleaseTime((int) cbYear.getSelectedItem());
					manager.addMobile(mobile);
					// Thêm một sản phẩm vào cuối danh sách;
					mobileListModel.addElement(mobile);
					// Xóa dữ liệu ô nhập dữ liệu.
					resetTextField();
					// Xuất dữ liệu
					exportData();
					// Chọn sản phẩm cuối cùng vừa thêm
					mobileList.setSelectedIndex(mobileListModel.size() - 1);
					// Cuộn đến vị trí cuối cùng
					mobileList.ensureIndexIsVisible(mobileListModel.size() - 1);
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Thêm một sản phẩm điện thoại xuống cuối.");
				}
			}
		});

		// Hành động nút chỉnh sửa
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí sản phẩm được chọn để sửa
				int index = getItemSelected();
				if (index != -1) {
					// Lấy thông tin sản phẩm được chọn
					Mobile selectedMobile = mobileListModel.get(mobileList.getSelectedIndex());
					// Sinh một sản phẩm mới
					Mobile mobile = MyFiles.generateMobile();
					// Gán id sản phẩm cũ vào sản phẩm mới
					mobile.setProduct_id(selectedMobile.getProduct_id());
					// Sửa thông tin sản phẩm được chọn bằng sản phẩm mới
					manager.editMobile(mobile);
					// gán thông tin mới cho sản phẩm trong danh sách
					mobileListModel.set(mobileList.getSelectedIndex(), mobile);

					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã sửa sản phẩm điện thoại được chọn.");
				}

			}
		});

		// Hành động nút xóa
		btnRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Lấy vị trí sản phẩm được chọn
				int index = getItemSelected();
				// Nếu có một sản phẩm được chọn
				if (index != -1) {
					// Lấy thông tin sản phẩm được chọn
					Mobile selectedMobile = mobileListModel.get(index);
					// Xóa sản phẩm được chọn khỏi danh sách sản phẩm trong file
					if (manager.delMobile(selectedMobile)) {
						// Xóa sản phầm được chọn khỏi danh sách
						mobileListModel.remove(mobileList.getSelectedIndex());
						// Xuất dữ liệu
						exportData();
						// cập nhật trạng thái
						MenuGUI.statusInfoList.addElement("Đã xóa một sản phẩm điện thoại được chọn.");
					}
				}
			}
		});

		// hành động nút sắp xếp
		btnSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Hiển thị hộp thoại nhập giá sản phẩm đầu vào
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập giá sản phẩm", 1000);

				// Nếu người dùng hủy hộp thoại thì không thực hiện sắp xếp
				if (value == null) {
					return;
				}

				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());

				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau sắp xếp
				mobileListModel.addAll(manager.sortedMobile(Double.parseDouble(value)));

				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement("Dữ liệu sản phẩm điện thoại đã được sắp.");
			}
		});

		// Thêm lắng nghe hành động nút hiển thị tất cả dữ liệu trong file
		btnShowAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent er) {
				// TODO Auto-generated method stub
				// Xóa dữ liệu trong file cho danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Thêm dữ liệu từ file vào danh sách
				try {
					MyFiles.mobileInputStream().forEach((mobile) -> mobileListModel.addElement(mobile));
					// Xuất dữ liệu
					exportData();
					// cập nhật trạng thái
					MenuGUI.statusInfoList.addElement("Đã hiển thị tất cả sản phẩm điện thoại.");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo tên sản phẩm
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập tên sản phẩm", "");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Gán danh sách sản phẩm sau khi tìm kiếm theo tên
				mobileListModel.addAll(manager.searchMobile(value));
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (mobileListModel.size() - 1) + " điện thoại theo tên.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo giá sản phẩm
		btnPriceSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập giá sản phẩm", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo giá sản phẩm
				mobileListModel.addAll(manager.searchMobile(Double.valueOf(value)));
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (mobileListModel.size() - 1) + " điện thoại theo giá.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo dung lượng bộ nhớ
		btnStorageSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập dung lượng bộ nhớ", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo dung lượng bộ nhớ
				mobileListModel.addAll(manager.searchMobile(Integer.valueOf(value)));
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (mobileListModel.size() - 1) + " điện thoại theo dung lượng bộ nhớ.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo dung lượng pin
		btnBatterySearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập dung lượng pin", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo dung lượng pin
				mobileListModel.addAll(manager.searchMobileByBattery(Integer.valueOf(value)));
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList
						.addElement("Đã tìm thất tất cả " + (mobileListModel.size() - 1) + " điện thoại theo pin.");
			}
		});

		// Thêm lắng nghe hành động nút tìm kiếm theo năm sản xuất
		btnReleaseSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Hiển thị hộp thoại nhập tên sản phẩm tìm kiếm
				String value = JOptionPane.showInputDialog(MobileGUI.this, "Nhập năm sản xuất", "0");
				// Kiểm tra nếu hủy hộp thoại thì không làm gì.
				if (value == null || value.isEmpty()) {
					return;
				}
				// Xóa danh sách sản phẩm cũ trong danh sách
				mobileListModel.clear();
				mobileListModel.addElement(new Mobile());
				// Sắp xếp danh sách sản phẩm
				// Gán danh sách sản phẩm sau khi tìm kiếm theo năm sản xuất
				mobileListModel.addAll(manager.searchMobileByRelease(Integer.valueOf(value)));
				// Xuất dữ liệu
				exportData();
				// cập nhật trạng thái
				MenuGUI.statusInfoList.addElement(
						"Đã tìm thất tất cả " + (mobileListModel.size() - 1) + " điện thoại theo năm sản xuất.");
			}
		});
	}

	public class InputDataPanel extends JPanel {
		public InputDataPanel() {
			setBorder(BorderFactory.createEmptyBorder(20, 50, 0, 50));
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.insets = new Insets(5, 5, 5, 5);
			gbc.anchor = GridBagConstraints.EAST;
			add(new JLabel("ID:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Tên sản phẩm:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Giá sản phẩm:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Số lượng:"), gbc);
			gbc.gridy++;
			gbc.gridx = 0;
			add(new JLabel("Bộ nhớ:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Dung lượng Pin:"), gbc);
			gbc.gridx += 2;
			add(new JLabel("Năm sản xuất:"), gbc);
			//
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.weightx = 0.5;
			txtID = new JTextField(10);
			txtID.setPreferredSize(new Dimension(100, 30));
			add(txtID, gbc);
			gbc.gridx += 2;
			txtName = new JTextField(10);
			txtName.setPreferredSize(new Dimension(100, 30));
			add(txtName, gbc);
			gbc.gridx += 2;
			txtPrice = new JTextField(10);
			txtPrice.setPreferredSize(new Dimension(100, 30));
			add(txtPrice, gbc);
			gbc.gridx += 2;
			txtTotal = new JTextField(10);
			txtTotal.setPreferredSize(new Dimension(100, 30));
			add(txtTotal, gbc);
			//
			gbc.gridy++;
			gbc.gridx = 1;
			txtStorage = new JTextField(10);
			txtStorage.setPreferredSize(new Dimension(100, 30));
			add(txtStorage, gbc);
			gbc.gridx += 2;
			txtPin = new JTextField(10);
			txtPin.setPreferredSize(new Dimension(100, 30));
			add(txtPin, gbc);
			gbc.gridx += 2;
			cbYear = new JComboBox<Integer>(new Integer[] { 2019, 2020, 2021, 2022 });
			cbYear.setPreferredSize(new Dimension(100, 30));
			add(cbYear, gbc);
		}
	}

	private boolean checkDataInput() {
		String id = txtID.getText();
		String name = txtName.getText();
		String price = txtPrice.getText();
		String total = txtTotal.getText();
		String storage = txtStorage.getText();
		String pin = txtPin.getText();
		if (id.isEmpty()) {
			showMessage("Nhập id sản phẩm");
			return false;
		}
		if (isIDExists(id)) {
			showMessage("ID sản phẩm đã tồn tại.");
			return false;
		}
		if (name.isEmpty()) {
			showMessage("Nhập tên điện thoại.");
			return false;
		}
		if (price.isEmpty()) {
			showMessage("Nhập giá sản phẩm.");
			return false;
		}
		try {
			Double.parseDouble(price);
		} catch (NumberFormatException e) {
			showMessage("Nhập giá sản phẩm là số.");
			return false;
		}
		if (total.isEmpty()) {
			showMessage("Nhập số lượng sản phẩm.");
			return false;
		}
		try {
			Integer.parseInt(total);
		} catch (NumberFormatException e) {
			showMessage("Nhập số lượng sản phẩm là số nguyên.");
			return false;
		}
		if (storage.isEmpty()) {
			showMessage("Nhập bộ nhớ sản phẩm.");
			return false;
		}
		try {
			Double.parseDouble(storage);
		} catch (NumberFormatException e) {
			showMessage("Nhập bộ nhớ sản phẩm là số nguyên.");
			return false;
		}
		if (pin.isEmpty()) {
			showMessage("Nhập dung lượng pin.");
			return false;
		}
		try {
			Double.parseDouble(pin);
		} catch (NumberFormatException e) {
			showMessage("Nhập dung lượng pin là số nguyên.");
			return false;
		}
		return true;
	}

	// Lấy và kiểm tra sản phẩm được chọn
	private int getItemSelected() {
		int index = mobileList.getSelectedIndex();
		// Nếu chưa có sản phẩm nào được chọn thì hiển thị thông báo
		if (index <= 0) {
			JOptionPane.showMessageDialog(MobileGUI.this, "Vui lòng chọn một dòng sản phẩm.", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		return index;
	}

	// xuất báo cáo ra file report.bin
	private void exportData() {
		List<Mobile> exportList = new ArrayList<Mobile>();
		for (int index = 1; index < mobileListModel.size(); index++) {
			exportList.add(mobileListModel.getElementAt(index));
		}
		try {
			MyFiles.exportStream(exportList, "Report.bin");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isIDExists(String id) {
		for (int index = 0; index < mobileListModel.size(); index++) {
			if (mobileListModel.get(index).getProduct_id().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private void resetTextField() {
		txtID.setText("");
		txtName.setText("");
		txtPrice.setText("");
		txtTotal.setText("");
		txtStorage.setText("");
		txtPin.setText("");
	}

	private void showMessage(String message) {
		JOptionPane.showMessageDialog(MobileGUI.this, message, "Thông báo", JOptionPane.WARNING_MESSAGE);
	}
}
