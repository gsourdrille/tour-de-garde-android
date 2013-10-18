package fr.edenyorke.tourdegarde.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private final static String FORMAT_DATE="dd/MM/yyyy";
	
	public final static Date parseDate(String date){
		
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		Date dateFormate = null;
		try {
			dateFormate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateFormate;
		
	}

}
