package com.masai;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import com.masai.bean.Criminal;
import com.masai.dao.CriminalDao;
import com.masai.dao.CriminalDaoImpl;
import com.masai.exceptions.CriminalException;
import com.masai.exceptions.Police_StationException;
import com.masai.usecases.AddPoliceStation;
import com.masai.usecases.DeleteCriminalFromRegister;
import com.masai.usecases.GetAllCriminalUseCase;
import com.masai.usecases.GetCriminalbyCrimeType;
import com.masai.usecases.GetCriminalbyID;
import com.masai.usecases.GetCriminalsFromPolice_StationUseCase;
import com.masai.usecases.GetNumberOfCases_Month;
import com.masai.usecases.GetNumberOfCases_solved;
import com.masai.usecases.GetSuspectsfromCrimeRegister;
import com.masai.usecases.GetVictimsfromCrimeRegister;
import com.masai.usecases.RegisterCriminal;

public class Criminalrecords {

	public static void main(String[] args) throws SQLException, CriminalException, Police_StationException {

		try {
			System.out.println("                   Welcome to Crime Information Management System Application                   ");
			System.out.println(
					"==================================================================================================================");
			System.out.println("                               Please Login as Police Inspector  ");
			System.out.println("                   --------------------------------------------------------");
			Scanner in = new Scanner(System.in);

			System.out.println("Enter UserName");
			String username = in.next();

			System.out.println("Enter Password");

			String password = in.next();
			CriminalDao dao = new CriminalDaoImpl();

			if (dao.PoliceOfficer(username, password)) {
				System.out.println("   Sucessfully Logged-In  : ");
				System.out.println("  Find criminals using following tabs  ");

			} else {

				System.out.println("Re-login");
			}

			while (true) {

				System.out.println(" \n   1. All Criminals Data  : "
						+ " \n   2. Please enter Criminal Id to search to Criminal data : "
						+ " \n   3. Add Criminal data  : "
						+ " \n   4. Criminals Details from Crime Types OR Crime Date : "
						+ " \n   5. Please enter Criminal Id to Delete  : "
						+ " \n   6. Information of all Criminals get from Police station Area : "
						+ " \n   7. Information from Victims : " + " \n   8. Information from Suspects : "
						+ " \n   9. All crime data : "
						+ " \n   10. Solved and Unsolved crime   : "
						+ " \n  11. Crimes recorded in the Current Month." + " \n  12. END!!!");

				int choice = in.nextInt();
				switch (choice) {

				case 1:
					GetAllCriminalUseCase.main(args);
					break;

				case 2:
					GetCriminalbyID.main(args);

					break;

				case 3:
					RegisterCriminal.main(args);
					break;

				case 4:
					GetCriminalbyCrimeType.main(args);
					break;
					
					
				case 5:
					DeleteCriminalFromRegister.main(args);
					break;


				case 6:
					GetCriminalsFromPolice_StationUseCase.main(args);
					break;

				
				case 7:
					GetVictimsfromCrimeRegister.main(args);

					break;
				case 8:
					GetSuspectsfromCrimeRegister.main(args);
					break;
				case 9:
					GetallCrimeusecase.main(args);
					break;
					//all crime data

				case 10:
					GetNumberOfCases_solved.main(args);
					break;

				case 11:
					GetNumberOfCases_Month.main(args);
					break;
				case 12:
					Criminalrecords.exitApp();
					break;

				default:

					System.out.println("Wrong Credentials..!!");
					break;

				}
			}
		} catch (Exception e) {
			Criminalrecords.error(e.getMessage());

		}

	}

	public static void error(String message) {
		System.out.println("Try again!!");

	}

	public static void exitApp() {

		System.out.println("Thank you !");
		System.exit(0);
	}

}