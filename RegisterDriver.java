package PricelyRegister;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import PricelyInventory.Item;
import PricelyInventory.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class RegisterDriver {
	
	public static void main(String[] args) throws IOException {
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		CSVReader reader = new CSVReader(new FileReader("src/inventory.csv"), ',', '"', 1);
		
		List<String[]> allItems = reader.readAll();
		
		for (String[] item: allItems) {
			String name = item[0];
			String UPC = item[1];
			String company = item[2];
			float price = Float.parseFloat(item[3]);
			int count = Integer.parseInt(item[4]);
			
			items.add(new OrderItem(name, UPC, company, price, count));
		}
		
		for (OrderItem item: items) {
			System.out.println(item);
		}
		
		
	}

}
