package interfaces;
import java.io.*;
import java.util.*;

import objects.Housing;

public interface HousingManager {
	
	public boolean addHousing(Housing h);
	public boolean editHousing (Housing h);
	public boolean delHousing(Housing h);
	

	
	public List<Housing> searchHousing(String name);
	public List<Housing> searchHousing(double price);
	public List<Housing> searchHousing(boolean NearStreet);
	
	public boolean checkHousing(Housing h);
	
	
	public List<Housing> sortedHousing(double price);
	public List<Housing> sortedHousing(boolean Asc);
	public void inDanhSach();
	public int getNumber();
	public List<Housing> getList();

	
	default  List<Housing> InputFile(String FileName, int n){
		List<Housing> result = new ArrayList<>();
		try {
			FileInputStream fileStream = new FileInputStream(FileName);
			ObjectInputStream objStream = new ObjectInputStream(fileStream);
			
			for (int i =0 ;i<n;i++) {
				result.add((Housing)objStream.readObject());
			}
			objStream.close();
			fileStream.close();
		} catch (IOException e) {
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	};
	default void OutputFile(String FileName,List<Housing> Houses, int n) throws IOException {
		FileOutputStream outFile = new FileOutputStream(FileName);
		ObjectOutputStream out = new ObjectOutputStream(outFile);
		for (int i = 0; i < n; i++) {
			out.writeObject(Houses.get(i));
		}
		out.close();
		outFile.close();
		
		
	}
	
}
