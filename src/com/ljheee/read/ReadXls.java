package com.ljheee.read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
    
    /**
     * 读取所有教师教学信息
     * @return
     */
	public List<Teacher> readXls(){
		List<Teacher> tSet = new ArrayList<>();
		
		for (int i = 1; i < rows; i++) {//从第一行
	        
			Cell[] rowCells = sheet.getRow(i);
			
	        teachMajors = new ArrayList<>();
	        teacher = new Teacher();
			course = new Course();
			major = new Major();
			
			course.name = rowCells[0].getContents().trim();
			major.level = rowCells[1].getContents().trim();
			major.name = rowCells[2].getContents().trim();
			major.numStudent = rowCells[3].getContents().trim();
			major.group = rowCells[4].getContents().trim();
			course.courseHour = rowCells[5].getContents().trim();
			teacher.name = rowCells[6].getContents().trim();
			major.course = course;
			
			teacher.teachMajor = major;
			if(teacher.name!=null&& !teacher.name.equals("")){
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
	
	/**
	 * 获取教师实验课程
	 * @param name
	 * @return
	 */
	public List<Major> getTeacherTeachesByName(String name){
		List<Major> list = new ArrayList<>();
		
		List<Teacher> tList = readXls();
		for (int i = 0; i < tList.size(); i++) {
			if(tList.get(i).name.equals(name)){
				list.add(tList.get(i).teachMajor);
			}
		}
		return list;
	}
	
	/**
	 * 读取指定实验老师-实验课程信息
	 * @param majors
	 * @return
	 */
	public Set<String> getMajorsInfo(List<Major> majors){
		Set<String> result = new HashSet<>();
		
		for (int i = 0; i < majors.size(); i++) {
			Major major = majors.get(i);
			result.add(major.level+major.name+major.numStudent);
		}
		return result;
	}
	
	
	public List<String> getGroup(String teacherName, String majorName){
		List<String> list = new ArrayList<>();
		
		List<Major> majors = getTeacherTeachesByName(teacherName);
		for (int i = 0; i < majors.size(); i++) {
			if(majors.get(i).name.equals(majorName)){
				list.add(majors.get(i).group);
			}
		}
		
		
		return list;
		
	}
	
	/**
	 * 关闭工作簿
	 */
	public void close(){
		wb.close();
	}


}
