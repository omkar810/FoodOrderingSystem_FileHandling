package com.hotel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hotel {
	Zomato zomato = new Zomato();
	Scanner scanner = new Scanner(System.in);

	public void addFood() throws IOException {
		System.out.println("Enter hotel name:");
		String hotelName = scanner.next().toUpperCase();
		String path = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel\\" + hotelName;
		String menuRead=path+"\\"+hotelName+"_Menu.txt";
		File exist=new File(path);
		File hotelFolder = new File(menuRead);
		if (exist.isDirectory()) {
			FileWriter fWriter=new FileWriter(hotelFolder,true);
			PrintWriter pw=new PrintWriter(fWriter);
			BufferedWriter bw=new BufferedWriter(pw);
			File upd=new File(path+"\\"+hotelName+"_MenuManager.txt");
			boolean b=true;
			Scanner sc=new Scanner(upd);
			StringBuffer sBuffer=new StringBuffer();
			if(sc.hasNextLine()) {
				sBuffer.append(sc.nextLine());
			}
			String fetch=sBuffer.toString();
			int idFetch=Integer.parseInt(fetch);
			sc.close();
			FileWriter ffw=new FileWriter(upd,false);
			PrintWriter pw1=new PrintWriter(ffw);
			BufferedWriter updateId=new BufferedWriter(pw1);
			int id=1;
			do {
				System.out.println("1.Add_food 2.Complete");
				switch (scanner.nextInt()) {
				case 1: {
					id=idFetch++;
					System.out.println("Enter food name:");
					scanner.nextLine();
					String food=scanner.nextLine().toUpperCase();
					System.out.println("Enter food price:");
					double price=scanner.nextDouble();
					String sp=space(food);
					String menu=id+" "+food+sp+price;
				    bw.write(menu);
				    bw.newLine();
				    bw.flush();
					break;
				}
				case 2:{
					b=false;
					break;
				}
				default:{
					System.out.println("Choose correct option");
				}
				}
				
			} while (b);
			updateId.write(++id +"");
			updateId.flush();
			updateId.close();
			bw.close();
		} else {
            System.out.println("Hotel not existed");
		}
	}
	public void showBillBook() throws FileNotFoundException {
		System.out.println("Enter hotel name:");
		String hotelName = scanner.next().toUpperCase();
		String path = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel\\" + hotelName;
		File file=new File(path);
		if(file.exists()) {
			path+="\\"+hotelName+"_ReceiptManager.txt";
			file=new File(path);
			Scanner sc=new Scanner(file);
			while(sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
		}
		else {
			System.out.println("Hotel not found..");
		}
	}

	public String space(String food) {
		String sp="";
		for(int i=1;i<15-food.length();i++) {
			sp+=" ";
		}
		return sp;
	}
}