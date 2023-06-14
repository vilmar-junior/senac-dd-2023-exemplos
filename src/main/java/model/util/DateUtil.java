package model.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static String formatoDataBrasil = "dd/MM/yyyy";
	
	public static String formatarDataPadraoBrasil(LocalDate data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoDataBrasil);
	    return formatter.format(data); 
	}
}
