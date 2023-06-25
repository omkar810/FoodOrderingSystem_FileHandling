package com.hotel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Zomato {
	Scanner scanner = new Scanner(System.in);

	public void addHotel() {
		String hotelPath = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel";
		File hotelFolder = new File(hotelPath);
		hotelFolder.mkdir();
		System.out.println("Enter hotel name:");
		String hotelName = scanner.next().toUpperCase();
		String hotelNamePath = hotelPath + "\\" + hotelName;
		File createHotel = new File(hotelNamePath);
		if(createHotel.isDirectory()) {
			System.out.println("Hotel already Existed");
		}
		else {
			createHotel.mkdir();
			int billNo = 1;
			String menuPath = hotelNamePath + "\\" + hotelName + "_Menu.txt";
			String menuManagePath = hotelNamePath + "\\" + hotelName + "_MenuManager.txt";
			String billPath = hotelNamePath + "\\" + hotelName + "_BillManager.txt";
			String receiptPath = hotelNamePath + "\\" + hotelName + "_ReceiptManager.txt";
			File menuManager = new File(menuPath);
			File menuManageManger = new File(menuManagePath);
			File billManager = new File(billPath);
			File receiptManager = new File(receiptPath);
			try {
				menuManager.createNewFile();
				menuManageManger.createNewFile();
				billManager.createNewFile();
				receiptManager.createNewFile();
				FileWriter createBillNo = new FileWriter(billManager);
				createBillNo.write(billNo+"");
				createBillNo.close();
				FileWriter menuMangeWriter=new FileWriter(menuManageManger);
				menuMangeWriter.write(billNo+"");
				menuMangeWriter.close();
				System.out.println("Hotel added.");
				createBillNo.close();
			} catch (IOException e) {
				System.out.println("Something wrong..");
			}
		}
	}
}
