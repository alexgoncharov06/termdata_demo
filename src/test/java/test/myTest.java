package test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;

public class myTest {

	public static void main(String[] args) throws SQLException {
		String baseid = "860719029990557";
		String dateFrom = "25.10.2015 00:00";
		
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date from = new Date(), to = new Date();
		try {
			from = format.parse(dateFrom);
			to = new Date(from.getTime());
			to.setDate(1);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		String dateTo = "25.10.2015 04:30";
		try {
			to = format.parse(dateTo);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		ArrayList<ThermData> archiveData = (ArrayList<ThermData>) Factory
				.getInstance().getTermDAO()
				.getAllByBaseFromToDate(baseid, from, to);
		int num = 1;
//		for (ThermData one : archiveData){
//		System.out.println(num + ". ");
//			System.out.print(one.toString());
//			num++;
//		}
		Gson jsonArc = new GsonBuilder().setDateFormat(
				"HH:mm:ss dd.MM.yyyy").create();
		System.out.println(jsonArc.toJson(archiveData));
	}
	
	

}
