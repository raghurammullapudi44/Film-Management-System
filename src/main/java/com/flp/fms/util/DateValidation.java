package com.flp.fms.util;

public class DateValidation {
	public boolean isValidFormat(String inputDate)
	{
		if(inputDate.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"))
		{
			return true;
		}
		return false;
	}
}
