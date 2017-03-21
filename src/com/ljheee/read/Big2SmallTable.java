package com.ljheee.read;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ljheee.bean.TheoryTeacher;
import com.ljheee.bean.WeekClass;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Big2SmallTable {
	
	File planFile;
	Workbook wb = null;
	Sheet sheet = null;
	int rows = 0;
	int cols = 0;
	
	int rowOfTitle;
	int colOfTeacher;
	
	int colOfWeekday;
	int colOfClassci;
	int colOfIsSingle;
	int colOfBeginEndWeek;

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
	 * 获取指定教师，所有所在行index
	 * @param teacherName
	 * @return
	 */
	public List<Integer> getRowIndexs(String teacherName){
		List<Integer> list = new ArrayList<>();
		getIndex();
		
		TheoryTeacher tt = new TheoryTeacher(teacherName);
		
		for (int i = 0; i < rows; i++) {
			//sheet.getCell(col,row)
			if(teacherName.equals(sheet.getCell(colOfTeacher,i).getContents().trim())){
				list.add(i);
				Cell[] oneRow = sheet.getRow(i);
				String[] strs = oneRow[colOfBeginEndWeek].getContents().trim().split("-");
				int begin = Integer.parseInt(strs[0]);
				int end = Integer.parseInt(strs[1]);
				boolean isSingle;
				
				if(oneRow[colOfIsSingle].getContents().trim().equals("")){
					isSingle = false;
				}
				
				int dayOfWeek = getDayOfWeek(oneRow[colOfIsSingle].getContents().trim());
				int jieCi = getJieCi(oneRow[colOfIsSingle].getContents().trim());
				
				
				for (int j = begin; j <= end; j++) {
					tt.list.get(j).week[jieCi][dayOfWeek] = 1;//标记为有理论课
				}
				
				
			}
		}
		
		System.out.println("over");
		return list;
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


	/**
	 * 获取[教学计划表]教师名列index
	 */
	private void getIndex() {
		
		Cell cell = sheet.findCell("教师姓名");
		rowOfTitle = cell.getRow();
		colOfTeacher = cell.getColumn();
		
		colOfWeekday = sheet.findCell("星期几").getColumn();
		colOfClassci = sheet.findCell("节次").getColumn();
		colOfIsSingle = sheet.findCell("单双周").getColumn();
		colOfBeginEndWeek = sheet.findCell("起止周").getColumn();
	}
	
	
	
	
	
	
	
	
	
	

}