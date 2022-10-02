package com.masai.dao;



//import com.masai.bean.Crime;
import com.masai.bean.CrimeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.masai.bean.Criminal;
import com.masai.bean.CriminalDTO;
import com.masai.bean.Police_Station;
import com.masai.bean.SuspectsDTO;
import com.masai.bean.VictimsDTO;
import com.masai.exceptions.Police_StationException;
import com.masai.exceptions.CriminalException;
import com.masai.Utility.DBUtil;

public class CriminalDaoImpl implements CriminalDao {

	@Override
	public String registerCriminal(Criminal criminal) {

		String message = "Not Inserted..";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"insert into Criminal(Criminal_Name, Age, Gender, Arrest_Date, Criminal_Address, Place_Crime, IdentifyingMark, CrimeType, CrimeDetails, Crime_Status) values(?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, criminal.getCriminal_Name());
			
			ps.setInt(2, criminal.getAge());

			ps.setString(3, criminal.getGender());

			ps.setString(4, criminal.getArrest_Date());

			ps.setString(5, criminal.getCriminal_Address());

			ps.setString(6, criminal.getPlace_Crime());


			ps.setString(7, criminal.getIdentifyingMark());

			ps.setString(8, criminal.getCrimeType());

			ps.setString(9, criminal.getCrimeDetails());

			ps.setString(10, criminal.getCrime_Status());

			int x = ps.executeUpdate();

			if (x > 0)
				message = "Criminal Registered Sucessfully !";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;

	}

	@Override
	public String registerPoliceStation(Police_Station police) {

		String message = "Not Inserted..";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into Police_Station(PoliceStation_Name, PoliceStation_Area) values(?,?)");

			ps.setString(1, police.getPoliceStation_Name());

			ps.setString(2, police.getPoliceStation_Area());

			int x = ps.executeUpdate();

			if (x > 0)
				message = "Registered Sucessfully !";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;
	}

	@Override
	public Criminal getCriminalByID(int Criminal_Id) throws CriminalException {

		Criminal criminal = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Criminal where Criminal_Id=?");

			ps.setInt(1, Criminal_Id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("Criminal_Id");
				String na = rs.getString("Criminal_Name");
				int age = rs.getInt("Age");
				String p1 = rs.getString("Gender");
				String p2 = rs.getString("Arrest_Date");
				String p3 = rs.getString("Criminal_Address");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("IdentifyingMark");
				String p6 = rs.getString("crimeType");
				String p7 = rs.getString("crimeDetails");
				String p8 = rs.getString("crime_Status");

				criminal = new Criminal(id, na, age, p1, p2, p3, p4, p5, p6,p7,p8);

			} else
				throw new CriminalException("Criminal does not exist with Criminal Id " + Criminal_Id);

		} catch (SQLException e) {

			e.printStackTrace();

			throw new CriminalException(e.getMessage());

		}

		return criminal;

	}

	@Override
	public Criminal loginCriminal(String Criminal_Name, String CrimeType) throws CriminalException {

		Criminal criminal = null;

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("select * from Criminal where Criminal_Name = ? AND CrimeType = ?");

			ps.setString(1, Criminal_Name);
			ps.setString(2, CrimeType);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("Criminal_Id");
				String na = rs.getString("Criminal_Name");
				int age = rs.getInt("Age");
				String p1 = rs.getString("Gender");
				String p2 = rs.getString("Arrest_Date");
				String p3 = rs.getString("Criminal_Address");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("IdentifyingMark");
				String p6 = rs.getString("crimeType");
				String p7 = rs.getString("crimeDetails");
				String p8 = rs.getString("crime_Status");

				criminal = new Criminal(id, na,age, p1, p2, p3, p4, p5, p6,p7,p8);

			} else
				throw new CriminalException("Invalid Username ");

		} catch (SQLException e) {
			throw new CriminalException(e.getMessage());
		}

		return criminal;

	}

	@Override
	public List<Criminal> getAllCriminalDetails() throws CriminalException {

		List<Criminal> Criminals = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Criminal");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("Criminal_Id");
				String na = rs.getString("Criminal_Name");
				int age = rs.getInt("Age");
				String p1 = rs.getString("Gender");
				String p2 = rs.getString("Arrest_Date");
				String p3 = rs.getString("Criminal_Address");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("IdentifyingMark");
				String p6 = rs.getString("crimeType");
				String p7 = rs.getString("crimeDetails");
				String p8 = rs.getString("crime_Status");

				Criminal criminal = new Criminal(id, na,age, p1, p2, p3, p4, p5, p6,p7,p8);

				Criminals.add(criminal);

			}

		} catch (SQLException e) {
			throw new CriminalException(e.getMessage());
		}

		if (Criminals.size() == 0)
			throw new CriminalException("No Criminal found..");

		return Criminals;
	}

	@Override
	public String registerCriminalInsideAStation(int StationId, int Criminal_Id)
			throws CriminalException, Police_StationException {

		String message = "Not Resgistered";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Criminal where Criminal_Id =?");

			ps.setInt(1, Criminal_Id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				PreparedStatement ps2 = conn.prepareStatement("select * from Police_Station where StationId=?");

				ps2.setInt(1, StationId);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {

					PreparedStatement ps3 = conn.prepareStatement("insert into PoliceStation_Criminal values(?,?)");

					ps3.setInt(1, StationId);
					ps3.setInt(2, Criminal_Id);

					int x = ps3.executeUpdate();

					if (x > 0)
						message = "Criminal Registred/Arrested inside the Police_Station Sucessfully.. ";
					else
						throw new CriminalException("Techical error..");

				} else
					throw new Police_StationException("Invalid Police_Station...");

			} else
				throw new CriminalException("Invalid Criminal...");

		} catch (SQLException e) {
			throw new CriminalException(e.getMessage());
		}

		return message;
	}

	@Override
	public List<CriminalDTO> getAllCriminalsByPolice_Station_name(String PoliceStation_Area)
			throws Police_StationException {

		List<CriminalDTO> dtos = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"select Criminal_Id, Criminal_Name,Criminal_Address, PoliceStation_Name, PoliceStation_Area from  Criminal  INNER JOIN Police_Station  INNER JOIN PoliceStation_Criminal  ON Criminal_Id = CriminalId AND StationId = StationId AND PoliceStation_Area= ?");

			ps.setString(1, PoliceStation_Area);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("Criminal_Id");
				String na = rs.getString("Criminal_Name");
				String p1 = rs.getString("Criminal_Address");
				String p2 = rs.getString("policeStation_Name");
				String p3 = rs.getString("policeStation_Area");
				String p4 = rs.getString("Criminal_status");

				CriminalDTO dto = new CriminalDTO(id, na, p1, p2, p3,p4);

				dtos.add(dto);

			}

		} catch (SQLException e) {
			throw new Police_StationException(e.getMessage());
		}

		if (dtos.isEmpty())
			throw new Police_StationException("No Criminal found in that Police Staion ");

		return dtos;

	}

	@Override
	public List<Criminal> DeleteCriminalFromRegister(int Criminal_Id) throws CriminalException {
		List<Criminal> ccdd = new ArrayList<>();

		try (Connection conn = com.masai.Utility.DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("delete from Criminal where Criminal_Id = ?");

			ps.setInt(1, Criminal_Id);

			int x = ps.executeUpdate();

			if (x > 0)
				System.out.println(x + " Record deleted Sucessfully");
			else
				System.out.println("Record does not exist.");

		} catch (SQLException e) {
			throw new CriminalException(e.getMessage());
		}

		return ccdd;
	}

	@Override
	public boolean PoliceOfficer(String username, String password) {

		return true;
	}

	@Override
	public Criminal getCriminalByCrimeType(String CrimeType, String Arrest_Date) throws CriminalException {
		Criminal criminal = null;

		try (Connection conn = com.masai.Utility.DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Criminal where CrimeType=? OR Arrest_Date=?");

			ps.setString(1, CrimeType);
			ps.setString(2, Arrest_Date);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				int id = rs.getInt("Criminal_Id");
				String na = rs.getString("Criminal_Name");
				int age = rs.getInt("Age");
				String p1 = rs.getString("Gender");
				String p2 = rs.getString("Arrest_Date");
				String p3 = rs.getString("Criminal_Address");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("IdentifyingMark");
				String p6 = rs.getString("crimeType");
				String p7 = rs.getString("crimeDetails");
				String p8 = rs.getString("crime_Status");

				criminal = new Criminal(id, na,age, p1, p2, p3, p4, p5, p6, p7, p8);

			} else
				throw new CriminalException("Criminal does not exist with Criminal Id " + CrimeType);

		} catch (SQLException e) {

			e.printStackTrace();

			throw new CriminalException(e.getMessage());

		}

		return criminal;
	}

	@Override
	public List<SuspectsDTO> getSuspectsfromCrimeRegister(String Suspects_Name) throws Police_StationException {
		
		List<SuspectsDTO> dtos1 = new ArrayList<>();

		try (Connection conn = com.masai.Utility.DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Crime where Suspects_Name=?");

			ps.setString(1, Suspects_Name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
                String p6 = rs.getString("Criminal_Name");
				String c1 = rs.getString("CrimeType");
				String na = rs.getString("CrimeDetails");
				String p1 = rs.getString("Suspects_Name");
				String p2 = rs.getString("Victims_Name");
				String p3 = rs.getString("DateOfCrime");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("Crime_status");

				SuspectsDTO dto1 = new SuspectsDTO(p6,c1, na, p1, p2, p3, p4,p5);

				dtos1.add(dto1);

			}

		} catch (SQLException e) {
			throw new Police_StationException(e.getMessage());
		}

		if (dtos1.isEmpty())
			throw new Police_StationException("No Criminal found in that Police Staion ");

		return dtos1;
	}

	@Override
	public List<VictimsDTO> getVictimsfromCrimeRegister(String Victims_Name) throws Police_StationException {
		// TODO Auto-generated method stub
		List<VictimsDTO> dtos2 = new ArrayList<>();

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("select * from Crime where Victims_Name=?");

			ps.setString(1, Victims_Name);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				String c1 = rs.getString("CrimeType");
				String na = rs.getString("CrimeDetails");
				String p1 = rs.getString("Suspects_Name");
				String p2 = rs.getString("Victims_Name");
				String p3 = rs.getString("DateOfCrime");
				String p4 = rs.getString("Place_Crime");
				String p5 = rs.getString("Crime_status");

				VictimsDTO dto1 = new VictimsDTO(c1, na, p1, p2, p3, p4, p5);

				dtos2.add(dto1);

			}

		} catch (SQLException e) {
			throw new Police_StationException(e.getMessage());
		}

		if (dtos2.isEmpty())
			throw new Police_StationException("No Victims found in that Police Staion ");

		return dtos2;
	
	}

	@Override
	public String GetNumberOfCases_solved() throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs;
		List<String> dtos3 = new ArrayList<>();
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"SELECT Crime_status, COUNT(Crime_status) FROM Crime GROUP BY Crime_status HAVING COUNT('Solved');");

			rs = ps.executeQuery();

			while (rs.next()) {

				String na = rs.getString("Crime_status");
				String p1 = rs.getString("COUNT(Crime_status)");

				dtos3.add(na);
				dtos3.add(p1);

			}

		} catch (SQLException e) {
			throw new Police_StationException(e.getMessage());
		}

		return dtos3.toString();
	
	}

	@Override
	public String GetNumberOfCases_Month() throws CriminalException, Exception {
		// TODO Auto-generated method stub
		ResultSet rs;
		List<String> dtos3 = new ArrayList<>();
		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement(
					"SELECT DateOfCrime, COUNT(Crime_status)Total_Cases_In_Current_Month FROM Crime GROUP BY DateOfCrime HAVING DateOfCrime>='2022-08-31';");

			rs = ps.executeQuery();

			while (rs.next()) {

				String na = rs.getString("DateOfCrime");
				String p1 = rs.getString("Total_Cases_In_Current_Month");

				dtos3.add(na);
				dtos3.add(p1);

			}

		} catch (SQLException e) {
			throw new Police_StationException(e.getMessage());
		}

		return dtos3.toString();
	
	}

	
//	public String GetNumberOfCases_solved() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String GetNumberOfCases_Month() throws CriminalException, Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public String addPoliceStation(String stationName) {

		String message = "not inserted";

		try (Connection conn = DBUtil.provideConnection()) {

			PreparedStatement ps = conn.prepareStatement("insert into police_dept values(?)");

			ps.setString(1, stationName);

			int i = ps.executeUpdate();

			if (i > 0)
				message = "police station inserted..!";

		} catch (SQLException e) {
			message = e.getMessage();
		}

		return message;

	}


}
