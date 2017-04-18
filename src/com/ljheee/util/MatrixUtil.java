package com.ljheee.util;

import java.util.List;

import com.ljheee.bean.BothSpare;
import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.TheoryTeacher;
import com.ljheee.bean.WeekClass;

public class MatrixUtil {
	
	static BothSpare bothSpare = new BothSpare();
	
	/**
	 * 获取从begin-end周，实验课可选时间段
	 * @param begin
	 * @param end
	 * @return
	 */
	public static  int[][] getResult(int begin, int end){
		
		List<WeekClass> llist = bothSpare.list;
		int[][] result = new int[5][7];
		
		for (int i = begin; i <= end; i++) {//将begin-end多张二维表叠加覆盖，结果为0的时最终可选的[实验课安排段]
			int[][] week = llist.get(i).week;
			
			for (int j = 0; j < 5; j++) {
				for (int j2 = 0; j2 < 7; j2++) {
					if(week[j][j2]==1){
						result[j][j2] = 1;
					}
				}
			}
		}
		return result;
	}

	/**
	 * 初始化bothSpare
	 * @param tt
	 * @param tm
	 */
	public static  void firstCalculate(TheoryTeacher tt,TheoryMajor tm){
		
		for (int i = 1; i <= 20; i++) {//1-20周
			int[][] arr1 = tt.getTheoryTable(i).week;
			int[][] arr2 = tm.getTheoryTable(i).week;
			bothSpare.list.add(getResultMatrix(arr1,arr2));
		}
	}


	/**
	 * 计算同一周 [教师-学生除去理论课]空闲时间
	 * @param wk1
	 * @param wk2
	 * @return WeekClass
	 */
	public static WeekClass getResultMatrix(int[][] wk1,int[][] wk2){
		WeekClass wk = new WeekClass();
		
		for (int j = 0; j < wk1.length; j++) {
			for (int j2 = 0; j2 < wk1[0].length; j2++) {
				
				if(wk1[j][j2]==1||wk2[j][j2]==1){//教师和学生，此时间段任一方有理论课，则不能安排实验课
					wk.week[j][j2] = 1;
				}
			}
		}
		return wk;
	}
	
	
	
}
