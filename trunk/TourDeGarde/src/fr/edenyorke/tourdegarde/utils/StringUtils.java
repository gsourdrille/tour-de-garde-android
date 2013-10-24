package fr.edenyorke.tourdegarde.utils;

public class StringUtils {
	
	public final static boolean isEmpty(String value){
		return value == null || value.equals("");
	}
	
	public final static boolean isBlank(String value){
		return isEmpty(value) || value.equals(" ");
	}

}
