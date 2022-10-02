package com.masai.bean;

public class SuspectsDTO {
    String Criminal_Name;
	String CrimeType;
	String CrimeDetails;
	String Suspects_Name;
	String Victims_Name;
	String DateOfCrime;
	String Place_Crime;
	String Crime_status;
	
	public String getCriminal_Name() {
		return Criminal_Name;
	}
	public void setCriminal_Name(String criminal_Name) {
		CrimeType = criminal_Name;
	}
	public String getCrimeType() {
		return CrimeType;
	}
	public void setCrimeType(String crimeType) {
		CrimeType = crimeType;
	}
	public String getCrimeDetails() {
		return CrimeDetails;
	}
	public void setCrimeDetails(String crimeDetails) {
		CrimeDetails = crimeDetails;
	}
	public String getSuspects_Name() {
		return Suspects_Name;
	}
	public void setSuspects_Name(String suspects_Name) {
		Suspects_Name = suspects_Name;
	}
	public String getVictims_Name() {
		return Victims_Name;
	}
	public void setVictims_Name(String victims_Name) {
		Victims_Name = victims_Name;
	}
	public String getDateOfCrime() {
		return DateOfCrime;
	}
	public void setDateOfCrime(String dateOfCrime) {
		DateOfCrime = dateOfCrime;
	}
	public String getPlace_Crime() {
		return Place_Crime;
	}
	public void setPlace_Crime(String place_Crime) {
		Place_Crime = place_Crime;
	}
	public String getCrime_status() {
		return Crime_status;
	}
	public void setCrime_status(String crime_status) {
		Crime_status = crime_status;
	}
	@Override
	public String toString() {
	
		return "            Suspects           "
				+"\n============================================================================" 
				+ "\n   Criminal_Name   =  " + Criminal_Name + "\n   CrimeType   =  " + CrimeType + ", \n   CrimeDetails    =  " + CrimeDetails + ",\n  Suspects_Name  = "
				+ Suspects_Name + ",\n   Victims_Name  = " + Victims_Name + ",\n  DateOfCrime  = " + DateOfCrime + ",\n  Place_Crime ="
				+ Place_Crime + ",\n  Crime_status  =" + Crime_status + 
				"\n=========================================================================";
	}
	public SuspectsDTO(String p6, String c1, String na, String p1, String p2, String p3, String p4, String p5) {
		super();
		// TODO Auto-generated constructor stub
	}
	public SuspectsDTO(String crimeType, String crimeDetails, String suspects_Name, String victims_Name,
			String dateOfCrime, String place_Crime, String crime_status) {
		super();
		Criminal_Name= Criminal_Name;
		CrimeType = crimeType;
		CrimeDetails = crimeDetails;
		Suspects_Name = suspects_Name;
		Victims_Name = victims_Name;
		DateOfCrime = dateOfCrime;
		Place_Crime = place_Crime;
		Crime_status = crime_status;
	}

}