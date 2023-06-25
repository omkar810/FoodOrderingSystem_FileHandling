package com.hotel;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ZomatoApplication {

	public static void main(String[] args) throws IOException {
		Zomato zomato=new Zomato();
		Hotel hotel=new Hotel();
		User  user=new User();
		Scanner scanner=new Scanner(System.in);
		String mainpath = "C:\\Users\\ADMIN\\Desktop\\Zomato";
		File mainFolder = new File(mainpath);
		mainFolder.mkdir();
		boolean b=true;
        do {
        	System.out.println("1.Admin 2.Customer");
        	switch(scanner.nextInt()) {
        	case 1:{
        		System.out.println("1.Add_hotel 2.Add_food 3.Show_Billing_Book");
        		switch(scanner.nextInt()) {
        		case 1:{
        			zomato.addHotel();
        			break;
        		}
        		case 2:{
        			hotel.addFood();
        			break;
        		}
        		case 3:{
        			hotel.showBillBook();
        			break;
        		}
        		default:{
                	System.err.println("Choose correct option");
                }
        		}
        		break;
        	}
        	case 2:{
        		user.addUser();
        		break;
        	}
            default:{
            	System.err.println("Choose correct option");
            }
        	}
        }while(b);
      scanner.close();
	}

}
