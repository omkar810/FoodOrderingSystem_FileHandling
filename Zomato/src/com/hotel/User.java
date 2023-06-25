package com.hotel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class User {
	Scanner scanner = new Scanner(System.in);
	String path = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel\\";

	public void addUser() throws IOException {
		String userPath = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Admin";
		System.out.println("Enter your name:");
		String name = scanner.next();
		System.out.println("Enter your Address:");
		String address = scanner.next();
		System.out.println("Enter your Contact number:");
		long contact = scanner.nextLong();
		File createUser = new File(userPath);
		createUser.mkdir();
		userPath += "\\" + name + "_" + contact + ".txt";
		File addUser = new File(userPath);
		FileWriter fWriter = new FileWriter(addUser);
		PrintWriter pw = new PrintWriter(fWriter);
		BufferedWriter bw = new BufferedWriter(pw);
		if (addUser.exists()) {
			String user = "Name: " + name + "  Address: " + address + "  Contact: " + contact;
			bw.write(user);
			bw.newLine();
			bw.write("_____________________________________________");
			bw.newLine();
			String sp = "  Menu" + spaceIndex("  Menu") + "  Qty" + space("  Qty") + "   Price"+space("   Price")+"   Total";
			bw.write(sp);
			bw.flush();
			bw.close();
			chooseHotel(name, contact);
		} else {
			addUser.createNewFile();
			String user = "Name: " + name + "  Address: " + address + "  Contact: " + contact;
			bw.write(user);
			bw.newLine();
			bw.write("_____________________________________________");
			bw.newLine();
			String sp = "  Menu" + spaceIndex("  Menu") + "  Qty" + space("  Qty") + "   Price"+space("   Price")+"   Total";
			bw.write(sp);
			bw.flush();
			bw.close();
			chooseHotel(name, contact);
		}
	}

	public void chooseHotel(String name, long contact) throws IOException {
		System.out.println("Enter hotel name:");
		String hName = scanner.next();
		File search = new File(path + hName);
		if (search.isDirectory()) {
			String hotelPath = path + hName + "\\" + hName + "_Menu.txt";
			File viewMenu = new File(hotelPath);
			Scanner sc = new Scanner(viewMenu);
			sc.close();
			chooseMenu(viewMenu, name, contact,hName);
		} else {
			System.out.println("Hotel not found..");
			chooseHotel(name,contact);
		}
	}

	public void chooseMenu(File viewMenu, String name, long contact,String hName) throws IOException {
		String userPath = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Admin\\" + name + "_" + contact + ".txt";
		File file = new File(userPath);
		Scanner sc1 = new Scanner(viewMenu);
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		BufferedWriter bw = new BufferedWriter(printWriter);
		int id = 1;
		boolean b = false;
		double total = 0;
		for (;;) {
			System.out.println("1.Order 2.Bill");
			switch (scanner.nextInt()) {
			case 1: {
				getMenu(new Scanner(viewMenu));
				System.out.println("Choose food no.");
				int foodNo = scanner.nextInt();
				System.out.println("Enter Quantity:");
				int quantity = scanner.nextInt();
				String number = foodNo + "";
				if (setFlag(new Scanner(viewMenu), number)) {
					b = true;
					String wr = getMenu_1(new Scanner(viewMenu), number, id++, quantity);
					bw.newLine();
					bw.write(wr);
					bw.flush();
				} else {
					System.out.println("Choose correct option for food no.");
				}
				break;
			}
			case 2: {
				if (b == true) {
					Scanner billScan = new Scanner(file);
					for (int i = 1; i <= 3; i++) {
						billScan.nextLine();
					}
					while (billScan.hasNextLine()) {
						billScan.next();
						billScan.next();
						billScan.next();
						billScan.next();
						double price = Double.parseDouble(billScan.next());
						total += price;
					}
					bw.newLine();
					bw.write("_____________________________________________");
					bw.newLine();
					bw.write("Total" + spaceIndex("Total") + spaceIndex("Total")+space("") + total);
					bw.flush();
					Scanner showBill = new Scanner(file);
					while (showBill.hasNextLine()) {
						System.out.println(showBill.nextLine());
					}
					showBill.close();
					while (payBill(total)) {
						
					}
					System.out.println("Payment successful.. Thank you!! ");
					String dateAndTime=getDate()+"            "+getTime();
					bw.newLine();
					bw.write(dateAndTime);
					bw.flush();
					recordUser(file,hName);
					bw.close();
					billScan.close();
					sc1.close();
					file.delete();
					return;
				} else {
					System.out.println("First order the food..");
				}
				break;
			}
			default: {
				System.out.println("Choose correct option..");
			}
			}
		}
	}

	public boolean payBill(double total) {
		boolean b = false;
		System.out.println("Enter amount to pay:");
		double pay = scanner.nextDouble();
		if (pay < total) {
			System.out.println("Please pay complete money..");
			b = true;
		}
		return b;
	}
	public void recordUser(File readUser,String hName) throws IOException {
		String receiptPath = "C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel\\"+hName+"\\"+hName+"_ReceiptManager.txt";
		File file = new File(receiptPath);
		File billPath=new File("C:\\Users\\ADMIN\\Desktop\\Zomato\\Hotel\\"+hName+"\\"+hName+"_BillManager.txt");
		Scanner billNo=new Scanner(billPath);
		FileWriter fileWriter = new FileWriter(file, true);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		BufferedWriter bw = new BufferedWriter(printWriter);

		StringBuffer sBuffer=new StringBuffer();
		if(billNo.hasNextLine()) {
			sBuffer.append(billNo.nextLine());
		}
		billNo.close();
		Scanner sc=new Scanner(readUser);
		String fetch=sBuffer.toString();
		int billNumber=Integer.parseInt(fetch);
		bw.newLine();
		bw.write("Bill No: "+billNumber);
		bw.newLine();
		while(sc.hasNextLine()) {
			bw.write(sc.nextLine());
			bw.newLine();
			bw.flush();
		}
		bw.write("=============================================");
		bw.flush();
		bw.close();
		sc.close();
		FileWriter fw1 = new FileWriter(billPath,false);
		PrintWriter pw1 = new PrintWriter(fw1);
		BufferedWriter bw1 = new BufferedWriter(pw1);
		bw1.write(++billNumber+"");
		bw1.flush();
		bw1.close();
	}

	public boolean setFlag(Scanner sc1, String number) {
		while (sc1.hasNext()) {
			String numString = sc1.next();
			if (number.equals(numString)) {
				return true;
			}
		}
		return false;
	}

	public void getMenu(Scanner sc1) {
		String sp = "  Menu" + spaceIndex("   Menu") + "Price";
		System.out.println(sp);
		while (sc1.hasNextLine()) {
			System.out.println(sc1.nextLine());
		}
	}

	public String getMenu_1(Scanner sc1, String number, int id, int quantity) {
		while (sc1.hasNext()) {
			String numString = sc1.next();
			if (number.equals(numString)) {
				String pr = sc1.next();
				double parse=Double.parseDouble(sc1.next());
				double price=quantity*parse;
				return id++ + " " + pr + spaceIndex(pr) + quantity + spaceIndex("quantity") + parse +space(parse+"")+price;
			}
		}
		return number;
	}

	public String space(String blank) {
		String sp = "";
		for (int i = 1; i < 10 - blank.length(); i++) {
			sp += " ";
		}
		return sp;
	}

	public String spaceIndex(String blank) {
		String sp = "";
		for (int i = 1; i < 18 - blank.length(); i++) {
			sp += " ";
		}
		return sp;
	}
	public String getDate()
	{
		LocalDate ld=LocalDate.now();
		return "Date:-"+ld.getDayOfMonth()+"-"+ld.getMonth()+"-"+ld.getYear();
	}
	public String getTime()
	{
		LocalTime lt=LocalTime.now();
		return "Time:-"+lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond();
	}
}
