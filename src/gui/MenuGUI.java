package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MenuGUI extends JFrame {

	public static DefaultListModel<String> statusInfoList;

	private JPanel contentPane, mobilePanel, housingPanel, tourPanel;

	/**
	 * Create the frame.
	 */
	public MenuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Phần mềm quản lí");
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.black));
		contentPane.setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel lbTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		lbTitle.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
		lbTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		lbTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		titlePanel.add(lbTitle);

		JPanel containerPanel = new JPanel();
		containerPanel.setBorder(new LineBorder(Color.black));

		JPanel menuPanel = new JPanel();
		BoxLayout layout = new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS);
		menuPanel.setLayout(layout);
		menuPanel.setBorder(new LineBorder(Color.black));
		menuPanel.setPreferredSize(new Dimension(350, 0));

		JLabel lbStatus = new JLabel("Trạng Thái");
		lbStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbStatus.setBorder(new EmptyBorder(new Insets(0, 60, 10, 0)));
		lbStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		statusInfoList = new DefaultListModel<>();

		JList<String> statusList = new JList<String>(statusInfoList);
		statusList.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		statusList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		statusList.setVisibleRowCount(3);
		JScrollPane statusScrollPane = new JScrollPane(statusList);

		JButton btn1 = new JButton("Quản lý điện thoại");
		btn1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn1.setMaximumSize(new Dimension(300, btn1.getMinimumSize().height));
		btn1.setPreferredSize(new Dimension(0, 36));
		changeStyleButton(btn1, false);
		
		JButton btn2 = new JButton("Quản lý bất động sản");
		btn2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn2.setMaximumSize(new Dimension(300, btn2.getMinimumSize().height));
		btn2.setPreferredSize(new Dimension(0, 36));
		changeStyleButton(btn2, false);
		
		JButton btn3 = new JButton("Quản lý hành trình");
		btn3.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn3.setMaximumSize(new Dimension(300, btn3.getMinimumSize().height));
		btn3.setPreferredSize(new Dimension(0, 36));
		changeStyleButton(btn3, false);
		
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(btn1);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(btn2);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		menuPanel.add(btn3);
		menuPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		menuPanel.add(lbStatus);
		menuPanel.add(statusScrollPane);

		/*
		 * containerPanel.add(menuPanel, BorderLayout.WEST); containerPanel.add(new
		 * JButton("SDsd"), BorderLayout.CENTER);
		 */

		contentPane.add(titlePanel, BorderLayout.NORTH);
		contentPane.add(menuPanel, BorderLayout.WEST);

		JPanel managePanel = new JPanel();
		managePanel.setBorder(new LineBorder(Color.black));
		managePanel.setAlignmentX(CENTER_ALIGNMENT);
		managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));

		mobilePanel = new MobileGUI();
		housingPanel = new HousingGUI();
		tourPanel = new TourGUI();
		mobilePanel.setVisible(false);
		housingPanel.setVisible(false);
		tourPanel.setVisible(false);

		managePanel.add(mobilePanel, BorderLayout.CENTER);
		managePanel.add(housingPanel, BorderLayout.CENTER);
		managePanel.add(tourPanel, BorderLayout.CENTER);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusInfoList.addElement("Quản lý sản phẩm điện thoại");
				mobilePanel.setVisible(true);
				housingPanel.setVisible(false);
				tourPanel.setVisible(false);
				// Thay đổi kiểu nút
				changeStyleButton(btn1, true);
				changeStyleButton(btn2, false);
				changeStyleButton(btn3, false);
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusInfoList.addElement("Quản lý bất động sản");
				// Ẩn hiện JPanel của quản lý khách sạn 
				mobilePanel.setVisible(false);
				housingPanel.setVisible(true);
				tourPanel.setVisible(false);
				// Thay đổi kiểu nút
				changeStyleButton(btn1, false);
				changeStyleButton(btn2, true);
				changeStyleButton(btn3, false);
			}
		});

		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				statusInfoList.addElement("Quản lý hành trình");
				// Ẩn hiện JPanel tương ứng
				tourPanel.setVisible(true);
				mobilePanel.setVisible(false);
				housingPanel.setVisible(false);
				// Thay đổi kiểu nút
				changeStyleButton(btn1, false);
				changeStyleButton(btn2, false);
				changeStyleButton(btn3, true);
			}
		});

		contentPane.add(managePanel, BorderLayout.CENTER);
		setContentPane(contentPane);
	}
	
	private void changeStyleButton(JButton btn, boolean isSelected) {
		if (isSelected) {
			btn.setBackground(Color.gray);
			btn.setForeground(Color.white);
			btn.setBorder(null);
		} else {
			btn.setForeground(Color.black);
			btn.setBackground(Color.white);
			btn.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
	}

}
