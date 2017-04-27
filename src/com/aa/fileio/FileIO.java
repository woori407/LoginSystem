package com.aa.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileIO {
	
	String filePath = ".\\resources\\index.txt";
	
	File fp= new File(filePath);
	
	public String getInitStr(){
		
		File fp= new File(filePath);
		
		return getFileContents(fp);
		
	}
	
	public String getFileContents(File file){
		
		String strText = "";  
	    int nBuffer;  
	    try   
	    {  
	        BufferedReader buffRead = new BufferedReader(new FileReader(file));  
	        while ((nBuffer = buffRead.read()) != -1)  
	        {  
	            strText += (char)nBuffer;  
	        }  
	        buffRead.close();  
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    }  
	      
	    return strText;  

	}

}
