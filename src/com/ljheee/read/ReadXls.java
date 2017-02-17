package com.ljheee.read;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.ljheee.bean.Course;
import com.ljheee.bean.Major;
import com.ljheee.bean.Teacher;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadXls {
	
	File xlsFile;

	Workbook wb = null;
	Sheet sheet = null;
	int rows = 0;
	int cols = 0;
	
	public ReadXls(String filename) {
		this.xlsFile = new File(filename);
		try {
			wb = Workbook.getWorkbook(xlsFile);
			sheet = wb.getSheet(0);
			rows = sheet.getRows();
			cols = sheet.getColumns();
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
	
	Teacher teacher = null;
    Set<Major> teachMajors = null;
    Course course = null;
    
	public Set<Teacher> readXls(){
		Set<Teacher> tSet = new HashSet<>();
		
		for (int i = 1; i < rows; i++) {
			
			
			
			
		}
		
		
		
		return tSet;
	}
	public Set<String> getTeachers(){
		Set<String> set = new HashSet<>();
		
		Cell[] tableTitles = sheet.getRow(0);
        int colOfTeacher = 0;
        for (int i = 0; i < tableTitles.length; i++) {
        	if(tableTitles[i].getContents().equals("实验老师")){
        		colOfTeacher = i;
        	}
		}
		
        Cell[] teacherCells = sheet.getColumn(colOfTeacher);
        for (int i = 1; i < teacherCells.length; i++) {
        	String name = teacherCells[i].getContents();
        	if(!"".equals(name)&&name!=null){
        		set.add(name);
        	}
		}
		
		return set;
	}
	
	
	
	
	
	/**
	 * 关闭工作簿
	 */
	public void close(){
		wb.close();
	}


}
