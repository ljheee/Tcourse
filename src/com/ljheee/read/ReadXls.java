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
 * 读取（学院-实验课计划）xls文件
 */
public class ReadXls {

	File xlsFile;//学院-实验课计划

	Workbook wb = null;
	Sheet sheet = null;
	int rows = 0;
	int cols = 0;

	int rowOfTitle,colOfcourse, colOflevel, colOfmajorName, colOfnumStudent, colOfgroup, courseHour, colOfTeacher,colOfWeek;

	public ReadXls(File f) {
		this.xlsFile = f;
		try {
			wb = Workbook.getWorkbook(xlsFile);
			sheet = wb.getSheet(0);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}

	Teacher teacher = null;
	List<Major> teachMajors = null;
	Course course = null;
	Major major = null;

	/**
	 * 初始化[获取表头-索引列下表]
	 */
	public void init(){
		
		rows = sheet.getRows();
		cols = sheet.getColumns();
		
		Cell cell = sheet.findCell("实验老师");
		rowOfTitle = cell.getRow();
		colOfTeacher = cell.getColumn();
		
		Cell[] tableTitles = sheet.getRow(rowOfTitle);
		
		for (int i = 0; i < tableTitles.length; i++) {// 匹配表头
			switch (tableTitles[i].getContents().trim()) {
			case "课程名称":
				colOfcourse = i;
				break;
			case "年级":
				colOflevel = i;
				break;
			case "专业名称":
				colOfmajorName = i;
				break;
			case "人数":
				colOfnumStudent = i;
				break;
			case "分组":
				colOfgroup = i;
				break;
			case "实验学时":
				courseHour = i;
				break;
			case "实验周":
				colOfWeek = i;
				break;
			default:
				break;
			}
		}
	}
	
	/**
	 * 读取所有教师教学信息
	 * @return
	 */
	public List<Teacher> readXls() {
		List<Teacher> tSet = new ArrayList<>();
		
		for (int i = 1; i < rows; i++) {// 从第一行

			Cell[] rowCells = sheet.getRow(i);

			teachMajors = new ArrayList<>();
			teacher = new Teacher();
			course = new Course();
			major = new Major();

			course.name = rowCells[colOfcourse].getContents().trim();
			if("".equals(course.name)){
				continue;//rows > 实际内容行，最下面有空行
			}
			
			int[] beginEnd = getBeginEnd(rowCells[colOfWeek].getContents().trim());
			course.beginWeek = beginEnd[0];
			course.endWeek = beginEnd[1];
			String temp = rowCells[courseHour].getContents().trim();
			if(!"".equals(temp)){
				try {
					course.courseHour = Integer.parseInt(temp);
				} catch (NumberFormatException e) {
					course.courseHour = Integer.parseInt(temp.split("\\.")[0]);
				}
			}
			
			course.timeOfWeek = course.courseHour/((course.endWeek-course.beginWeek+1)*2);
					
			major.level = rowCells[colOflevel].getContents().trim();
			major.name = rowCells[colOfmajorName].getContents().trim();
			major.numStudent = rowCells[colOfnumStudent].getContents().trim();
			major.group = rowCells[colOfgroup].getContents().trim();
			
			teacher.name = rowCells[colOfTeacher].getContents().trim();
			major.course = course;

			teacher.teachMajor = major;
			if (teacher.name != null && !teacher.name.equals("")) {
				tSet.add(teacher);
			}
		}
		return tSet;
	}

	//Todo...........
	private int[] getBeginEnd(String arg) {
		int[] result = new int[2];
		
		String ss[] = arg.split(",");
		
		if(ss.length==1){
			String[] strs = arg.split("-");
			result[0] = Integer.parseInt(strs[0]);
			try {
				result[1] = Integer.parseInt(strs[strs.length-1]);
			} catch (NumberFormatException e) {
				result[1] = Integer.parseInt(strs[strs.length-1].substring(0, 2));
			}
		}else{
			result[0] = Integer.parseInt(ss[0].split("-")[0]);
			result[1] = Integer.parseInt(ss[1].split("-")[1].substring(0, 2));
		}
				
		return result;
	}

	
	/**
	 * 获取教师集合
	 * @return
	 */
	public Set<String> getTeachers() {
		Set<String> set = new HashSet<>();

		Cell[] teacherCells = sheet.getColumn(colOfTeacher);
		for (int i = 1; i < teacherCells.length; i++) {
			String name = teacherCells[i].getContents();
			if (!"".equals(name) && name != null) {
				set.add(name);
			}
		}

		return set;
	}

	/**
	 * 获取教师实验课程
	 * 
	 * @param name
	 * @return
	 */
	public List<Major> getTeacherTeachesByName(String name) {
		List<Major> list = new ArrayList<>();

		List<Teacher> tList = readXls();
		for (int i = 0; i < tList.size(); i++) {
			if (tList.get(i).name.equals(name)) {
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
	public Set<String> getMajorsInfo(List<Major> majors) {
		Set<String> result = new HashSet<>();

		for (int i = 0; i < majors.size(); i++) {
			Major major = majors.get(i);
			result.add(major.level + major.name + major.numStudent + "——" + major.group);
		}
		return result;
	}

	/**
	 * 根据teacherName、majorName获取专业分组消息
	 * @param teacherName
	 * @param majorName
	 * @return
	 */
	public Set<String> getGroups(String teacherName, String majorName) {
		Set<String> list = new HashSet<>();

		List<Major> majors = getTeacherTeachesByName(teacherName);
		for (int i = 0; i < majors.size(); i++) {
			if (majors.get(i).name.equals(majorName)) {
				list.add(majors.get(i).group);
			}
		}
		return list;
	}
	

	/**
	 * 关闭工作簿
	 */
	public void close() {
		if(wb != null){
			wb.close();
		}
	}

}
