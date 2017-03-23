package com.ljheee.util;

import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.TheoryTeacher;

public class MatrixUtil {

	public static void calculate(int begin, int end, TheoryTeacher tt,TheoryMajor tm){
		
		for (int i = begin; i <= end; i++) {
			int[][] arr1 = tt.getTheoryTable(i).week;
			int[][] arr2 = tm.getTheoryTable(i).week;
			
			for (int j = 0; j < arr1.length; j++) {
				for (int j2 = 0; j2 < arr1[0].length; j2++) {
					if(arr1[j][j2]==arr2[j][j2]&&arr1[j][j2]==0){//可选
						String[] strs = StringUtil.getWeekAndJieCi(j, j2);
						System.out.println(i+"周 "+strs[0]+"####"+strs[1]);
					}
				}
			}
			System.out.println();
		}
	}
}
