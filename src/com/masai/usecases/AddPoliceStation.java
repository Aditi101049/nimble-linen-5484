package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.CriminalDao;
import com.masai.dao.CriminalDaoImpl;

public class AddPoliceStation {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the name of police station :-");
		String stationName = sc.next();

		CriminalDao dao = new CriminalDaoImpl();
		String result = dao.addPoliceStation(stationName);
		
		System.out.println(result);
		
	}
	
}