package com.ljheee.read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ljheee.bean.Course;
import com.ljheee.bean.Major;
import com.ljheee.bean.Teacher;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
/**
 *读取xls文件
 */
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
	List<Major> teachMajors = null;
    Course course = null;
    Major major = null;
    
	public List<Teacher> readXls(){
		List<Teacher> tSet = new ArrayList<>();
		
		for (int i = 1; i < rows; i++) {//从第一行
	        
			Cell[] rowCells = sheet.getRow(i);
			
	        teachMajors = new ArrayList<>();
			course = new Course();
			major = new Major();
			
			course.name = rowCells[0].getContents().trim();
			major.level = rowCells[1].getContents().trim();
			major.name = rowCells[2].getContents().trim();
			major.numStudent = rowCells[3].getContents().trim();
			major.group = rowCells[4].getContents().trim();
			course.courseHour = rowCells[5].getContents().trim();
			String tName = rowCells[6].getContents().trim();
			major.course = course;
			
			Teacher t = null ;
			if(isContain(tSet ,tName ,t)){//如果已包含
				t.getTeachMajors().add(major);
			}else{
				teachMajors.add(major);
				teacher = new Teacher(tName, teachMajors);
				tSet.add(teacher);
			}
		}
		
		return tSet;
	}
	
	
	/**
	 * 获取教师集合
	 * @return
	 */
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
	
	public boolean isContain(List<Teacher> list,String name,Teacher t){
		
		Iterator<Teacher> it = list.iterator();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)){
				t = list.get(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 关闭工作簿
	 */
	public void close(){
		wb.close();
	}


}
