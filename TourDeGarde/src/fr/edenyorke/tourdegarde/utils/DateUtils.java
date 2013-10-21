package fr.edenyorke.tourdegarde.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public final static String FORMAT_DATE_SHORT="dd/MM/yyyy a";
	public final static String FORMAT_DATE_LONG="EEEE, d MMM yyyy a";
	
	public final static Date parseDate(String date){
		
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_SHORT);
		Date dateFormate = null;
		try {
			dateFormate = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateFormate;
		
	}
	
public final static String formatDate(Date date, String format){
		String formatPattern = null;
		if(format != null && !format.isEmpty()){
			formatPattern = format;
		}else{
			formatPattern = FORMAT_DATE_SHORT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(formatPattern);
		String dateFormate = null;
		dateFormate = sdf.format(date);
	    return dateFormate.toLowerCase();
		
	}

public final static String formatDate(Date date){
	return formatDate(date, null);
}

}
