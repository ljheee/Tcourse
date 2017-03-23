package com.ljheee.read;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.TheoryTeacher;
import com.ljheee.util.StringUtil;

public class Big2SmallTable {
	
	Workbook wb = null;
	Sheet sheet = null;
	File planFile;//教学计划-文件
	int rows = 0;//教学计划-总行数
	int cols = 0;//总列数
	
	int rowOfTitle;//标题行-索引
	int colOfTeacher;//教师姓名-列索引
	
	int colOfWeekday;//星期几
	int colOfClassci;//节次
	int colOfIsSingle;//单周
	int colOfBeginEndWeek;//起始周
	
	int colOfMajor;//上课专业

	public Big2SmallTable(File f) {
		this.planFile = f;
		try {
			wb = Workbook.getWorkbook(planFile);
			sheet = wb.getSheet(0);
			rows = sheet.getRows();
			cols = sheet.getColumns();
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取[教学计划表]教师名列index
	 */
	public void init() {
		
		Cell cell = sheet.findCell("教师姓名");
		rowOfTitle = cell.getRow();
		colOfTeacher = cell.getColumn();
		
		colOfWeekday = sheet.findCell("星期几").getColumn();
		colOfClassci = sheet.findCell("节次").getColumn();
		colOfIsSingle = sheet.findCell("单双周").getColumn();
		colOfBeginEndWeek = sheet.findCell("起止周").getColumn();
		colOfMajor = sheet.findCell("上课专业").getColumn();
	}
	
	/**
	 * 获取指定教师TheoryTeacher，所有理论课表
	 * @param teacherName
	 * @return
	 */
	public TheoryTeacher getTheoryTeacher(String teacherName){
		
		TheoryTeacher tt = new TheoryTeacher(teacherName);
		
		for (int i = 0; i < rows; i++) {
			//sheet.getCell(col,row)
			if(teacherName.equals(sheet.getCell(colOfTeacher,i).getContents().trim())){
				Cell[] oneRow = sheet.getRow(i);
				String[] strs = oneRow[colOfBeginEndWeek].getContents().trim().split("-");
				int begin = Integer.parseInt(strs[0]);
				int end = Integer.parseInt(strs[1]);
				
				int singleDouble = StringUtil.getSingleDouble(oneRow[colOfIsSingle].getContents().trim());
				int dayOfWeek = StringUtil.getDayOfWeek(oneRow[colOfWeekday].getContents().trim());
				int jieCi = StringUtil.getJieCi(oneRow[colOfClassci].getContents().trim());
				
				
				for (int j = begin; j <= end; j++) {
					
					switch (singleDouble) {
					case 0:
						//Todo;list索引0--19
						tt.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						break;
					case 1:
						if(j%2!=0){
							tt.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						}
						break;
					case 2:
						if(j%2==0){
							tt.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						}
						break;
					}
				}
			}
		}
		
		return tt;
	}


	/**
	 * 获取指定专业TheoryMajor，1-20周理论课表
	 * @param levelAndName，例如2014级软件工程
	 * @return
	 */
	public TheoryMajor getTheoryMajor(String levelAndName){
		
		TheoryMajor tm = new TheoryMajor(levelAndName);
		
		for (int i = 0; i < rows; i++) {
			
			if(levelAndName.equals(sheet.getCell(colOfMajor,i).getContents().trim())){
				Cell[] oneRow = sheet.getRow(i);
				String[] strs = oneRow[colOfBeginEndWeek].getContents().trim().split("-");
				int begin = Integer.parseInt(strs[0]);
				int end = Integer.parseInt(strs[1]);
				
				int singleDouble = StringUtil.getSingleDouble(oneRow[colOfIsSingle].getContents().trim());
				int dayOfWeek = StringUtil.getDayOfWeek(oneRow[colOfWeekday].getContents().trim());
				int jieCi = StringUtil.getJieCi(oneRow[colOfClassci].getContents().trim());
				
				
				for (int j = begin; j <= end; j++) {
					
					switch (singleDouble) {
					case 0:
						//Todo;list索引0--19
						tm.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						break;
					case 1:
						if(j%2!=0){
							tm.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						}
						break;
					case 2:
						if(j%2==0){
							tm.list.get(j-1).week[jieCi][dayOfWeek] = 1;//标记为有理论课
						}
						break;
					}
				}
			}
				
		}
		return tm;
	}
	
	
	

}
