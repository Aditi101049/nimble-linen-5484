package com.masai.usecases;




import java.util.List;

import com.masai.bean.Crime;
import com.masai.bean.Criminal;
import com.masai.dao.CriminalDao;
import com.masai.dao.CriminalDaoImpl;
import com.masai.exceptions.CrimeException;
import com.masai.exceptions.CriminalException;

public class GetallCrimeusecase {

	public static void main(String[] args) {

		CriminalDao dao = new CriminalDaoImpl();

		try {
			List<Crime> criminal = dao.getAllCrimesDetails();

			criminal.forEach(Crime -> {
				
				System.out.println("Criminal Name :" + Crime.getCriminal_Name());
				System.out.println("CrimeDetails : " + Crime.getCrimeDetails());
				System.out.println("DateOfCrime: " + Crime.getDateOfCrime());
				System.out.println("Victims_Name: " + Crime.getVictims_Name());
				System.out.println("Suspects_Name: " + Crime.getSuspects_Name());
				System.out.println("Crime Place: " + Crime.getPlace_Crime());
				
				System.out.println("Criminal Crime Type: " + Crime.getCrimeType());
				System.out.println("Criminal Crime Status: " + Crime.getCrime_status());

				System.out.println("---------------------------------------------------------");
			});

		} catch (CrimeException se) {
			System.out.println(se.getMessage());
		}

	}

}
