package com.flp.fms.util;

import java.util.Map;
import java.util.Set;

import com.flp.fms.exceptions.FieldEmptyException;
import com.flp.fms.exceptions.NegativeInputException;

public class InputDataValidation {
	public void inputDataValidation(Map data) throws FieldEmptyException, NegativeInputException
	{
		Set<String> keys=data.keySet(); 
        
        for(String key:keys) 
        { 
                if((((data.get(key)).getClass()).equals(Integer.class))  && (((Integer)data.get(key)) < 0))

                { 
                        throw new NegativeInputException(); 
                } 
                else if((((data.get(key)).getClass()).equals(String.class))  && (((String)data.get(key)).equals("") || (((String)data.get(key)).equals("null"))))
                { 
                        throw new FieldEmptyException(); 
                } 
        }
	}
}
