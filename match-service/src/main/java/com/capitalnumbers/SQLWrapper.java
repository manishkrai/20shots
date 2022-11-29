package com.capitalnumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.springframework.stereotype.Component;

@Component
public class SQLWrapper{ 
    
	private final String baseDir = "db/queries";
	
	public String getScript(String name) throws CustomException {
		String fileName = "{0}/{1}.sql";
		String blank = " ";
		StringBuffer query = new StringBuffer();
		InputStream ioStream = this.getClass()
	            .getClassLoader()
	            .getResourceAsStream(MessageFormat.format(fileName,baseDir,name));
	        
        if (ioStream == null) {
            throw new CustomException("Error reading sql script: " + name);
        }
        
        try (InputStreamReader isr = new InputStreamReader(ioStream); 
                BufferedReader br = new BufferedReader(isr);) 
        {
            String line;
            while ((line = br.readLine()) != null) {
            	query.append(blank + line);
            }
            isr.close();
        } catch (IOException e) {
        	throw new CustomException("Error reading sql script: " + name);
		}
        
        return query.toString();
	}
}