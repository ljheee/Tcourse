package com.ljheee.read;

import java.io.File;
import java.io.IOException;

import com.ljheee.bean.TheoryTeacher;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
				
				int singleDouble = getSingleDouble(oneRow[colOfIsSingle].getContents().trim());
				int dayOfWeek = getDayOfWeek(oneRow[colOfWeekday].getContents().trim());
				int jieCi = getJieCi(oneRow[colOfClassci].getContents().trim());
				
				
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
	 * 根据单双周，返回int
	 * @param arg
	 * @return
	 */
	private int getSingleDouble(String arg) {
		int result = 0;
		switch (arg) {
		case "":
			result = 0;//不分单双周
			break;
		case "单":
			result = 1;//单周
			break;
		case "双":
			result = 2;//双周
			break;
		default:
			System.out.println("getSingleDouble=default");
			break;
		}
		return result;
	}


	/**
	 * 根据节次，转化为上课时间段0-4
	 * @param arg
	 * @return
	 */
	private int getJieCi(String arg) {
		int result = 0;
		switch (arg) {
		case "第1,2节":
			result = 0;
			break;
		case "第3,4节":
			result = 1;
			break;
		case "第5,6节":
			result = 2;
			break;
		case "第7,8节":
			result = 3;
			break;
		case "第9,10节":
			result = 4;
			break;
		default:
			System.out.println("getJieCi=default");
			break;
		}
		return result;
	}

	/**
	 * 星期一 至星期日=0-6
	 * @param arg
	 * @return
	 */
	private int getDayOfWeek(String arg) {
		int result = 0;
		switch (arg) {
		case "星期一":
			result = 0;
			break;
		case "星期二":
			result = 1;
			break;
		case "星期三":
			result = 2;
			break;
		case "星期四":
			result = 3;
			break;
		case "星期五":
			result = 4;
			break;
		case "星期六":
			result = 5;
			break;
		case "星期七":
			result = 6;
			break;

		default:
			break;
		}
		return result;
	}


	
	
	
	
	
	
	
	
	
	
	

}
