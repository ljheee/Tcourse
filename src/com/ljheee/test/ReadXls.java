package com.ljheee.test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.ljheee.bean.Course;
import com.ljheee.bean.Major;
import com.ljheee.bean.Teacher;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadXls {
	
	static int colOfTeacher = 0;
	
	static Set<Teacher> tSet = new HashSet<>();
	
	
	public static Set<Teacher> read(String fileName) throws IOException, BiffException {
		
		File xlsFile = new File(fileName);
		
		Workbook wb = Workbook.getWorkbook(xlsFile);
		Sheet sheet = wb.getSheet(0);
		
        // 获得列数
        int cols = sheet.getColumns();
        
        
        Cell[] tableTitles = sheet.getRow(0);
        
        for (int i = 0; i < tableTitles.length; i++) {
        	if(tableTitles[i].getContents().equals("实验老师")){
        		colOfTeacher = i;
        	}
		}
        
        Cell[] teacherCells = sheet.getColumn(colOfTeacher);
        
        Teacher teacher = null;
        Set<Major> teachMajors = null;
        Course course = null;
        for (int i = 1; i < teacherCells.length; i++) {//从第一行开始
//			System.out.println(teacherCells[i].getContents());
			
			teachMajors = new HashSet<>();
			course = new Course();
			Major major = null;
			major = new Major();
			
			int col = 0;
			course.name = sheet.getCell(i, col).getContents();
			major.level = Integer.parseInt(sheet.getCell(i, ++col).getContents());
			major.name = sheet.getCell(i, ++col).getContents();
			major.numStudent = Integer.parseInt(sheet.getCell(i, ++col).getContents());
			major.group = sheet.getCell(i, ++col).getContents();
			course.courseHour = Integer.parseInt(sheet.getCell(i, ++col).getContents());
			
			teachMajors.add(major);
			teacher = new Teacher(teacherCells[i].getContents(), teachMajors);
			tSet.add(teacher);
		}
        
        
        
        wb.close();
        return tSet;
	}
	
	public static void main(String[] args) {
		try {
			Set<Teacher> set = read("abc.xls");
			Iterator<Teacher> it = set.iterator();
				
			while(it.hasNext()){
				System.out.println(it.next());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

}
