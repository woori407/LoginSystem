package com.aa.fileio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIO {
	
	String filePath = ".\\resources\\index.txt";
	
	File f= new File(filePath);
	
	public String getInitStr(){
		
		return ReadFileText(f);
		
	}
	
	public void writeLastStr(String fileStr){
		UpdateFile(filePath , fileStr );
	}
	
	private void CreateFile(String FilePath)  
	{  
	    try  
	    {  
	        System.out.println(FilePath);  
	          
	        int nLast = FilePath.lastIndexOf("\\");  
	        String strDir = FilePath.substring(0, nLast);  
	        String strFile = FilePath.substring(nLast+1, FilePath.length());  
	          
	        File dirFolder = new File(strDir);  
	        dirFolder.mkdirs();  
	        File f = new File(dirFolder, strFile);  
	        f.createNewFile();  
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    }  
	}  

	
	public String ReadFileText(File file){
		
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
	
	private void UpdateFile(String FilePath, String fileStr)  
	{  
	    try   
	    {  
	        if (f.exists() == false)  
	        {  
	            // 파일이 존재하지 않는 경우 파일을 만들ㄴ다.  
	            CreateFile(FilePath);  
	        }  
	          
	        // 파일 읽기  
//	        String fileText = ReadFileText(f);  
	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f));  
//	        fileStr = fileText + "\r\n" + fileStr;  
	        // 파일 쓰기  
	        buffWrite.write(fileStr, 0, fileStr.length());  
	        // 파일 닫기  
	        buffWrite.flush();  
	        buffWrite.close();  
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    }  
	}  


}
