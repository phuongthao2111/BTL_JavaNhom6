/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.*;
import objects.Tour;

/**
 *
 * @author Doan Van Quan
 */
public interface TourManager {

    List<Tour> list = new ArrayList<>();
    
    long pos = 0;

//    short n=70;
//    
//    String partFile = "TourManager.bin";
    
    public boolean addTour(Tour t); // thêm hành trình

    public boolean editTour(Tour t); //sửa hành trình

    public boolean delTour(Tour t); // xóa hành trình

    public List<Tour> searchTour(String name); // tìm kiếm theo tên hành trình

    public List<Tour> searchTour(int numberPerson); //tìm kiếm theo số người tham gia hành trình

    public List<Tour> searchTour(double price); // tìm kiếm theo chi phí hành trình

    public List<Tour> searchTourByTime(String time); // tìm kiếm theo thời gian hành trình

    public List<Tour> searchTourByTravel(String travel); // tìm kiếm theo diểm đến hành trình

    public List<Tour> sortedTour(double price); // sắp xếp theo chi phí hành trình

    public List<Tour> sortedTour(String time); // sắp xếp theo thời gian hành trình
}
