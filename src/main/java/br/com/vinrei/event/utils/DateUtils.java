package br.com.vinrei.event.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtils {


	public static Date formatDate(Date date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String formattedDate = formatter.format(date);		
		return formatter.parse(formattedDate);
	}
	
	public static Date formatStringToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.parse(date);
	}

	
	
}
