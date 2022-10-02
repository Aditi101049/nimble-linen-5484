package com.masai.usecases;

import java.rmi.StubNotFoundException;
import java.util.Scanner;
import com.masai.bean.Criminal;
import com.masai.dao.CriminalDao;
import com.masai.dao.CriminalDaoImpl;

public class RegisterCriminal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter Criminal Name:");
		String Criminal_Name = sc.next();

		System.out.println("Enter Criminal Age:");
		int Age = sc.nextInt();

		System.out.println("Enter Criminal Gender:");
		String Gender = sc.next();
		
		System.out.println("Enter Criminal Arrest Date :");
		String Arrest_Date = sc.next();


		System.out.println("Enter Criminal Address:");
		String Criminal_Address = sc.next();

		System.out.println("Enter Place of Crime:");
		String Place_Crime = sc.next();

		

		
		System.out.println("Enter Criminal IdentifyingMark:");
		String IdentifyingMark = sc.next();

		System.out.println("Enter Criminal Crime Type:");
		String CrimeType = sc.next();

		System.out.println("Enter Criminal Crime Details:");
		String CrimeDetails = sc.next();
		
		System.out.println("Enter Criminal Crime Status:");
		String Crime_Status = sc.next();
		
		CriminalDao dao = new CriminalDaoImpl();

		Criminal criminal = new Criminal();
		
		criminal.setCriminal_Name(Criminal_Name);


		criminal.setArrest_Date(Arrest_Date);


		criminal.setCriminal_Address(Criminal_Address);

		criminal.setPlace_Crime(Place_Crime); 

		criminal.setAge(Age);

		criminal.setGender(Gender);

		
		criminal.setIdentifyingMark(IdentifyingMark);

		criminal.setCrimeType(CrimeType);
		
		criminal.setCrimeDetails(CrimeDetails);
		
		criminal.setCrimeDetails(Crime_Status); 

		String result = dao.registerCriminal(criminal);

		System.out.println(result);
	}

}