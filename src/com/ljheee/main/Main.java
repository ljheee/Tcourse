package com.ljheee.main;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ljheee.bean.Major;
import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.TheoryTeacher;
import com.ljheee.bean.WeekClass;
import com.ljheee.read.Big2SmallTable;
import com.ljheee.read.ReadXls;
import com.ljheee.util.MatrixUtil;
import com.ljheee.util.StringUtil;

public class Main {


	public static void main(String[] args) {
		
		
		ReadXls readXls = new ReadXls(new File("17春专业实验实践课.xls"));
		readXls.init();
		
//		List<Teacher> set = readXls.readXls();
//		Iterator<Teacher> it = set.iterator();
//
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
		
		List<Major> majors = readXls.getTeacherTeachesByName("辛动军");
		for (int i = 0; i < majors.size(); i++) {
			System.out.println(majors.get(i));
		}
		
		
		Set<String> set = readXls.getMajorsInfo(majors);
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		List<String> groups = readXls.getGroups("辛动军", majors.get(0).name);
		for (int i = 0; i < groups.size(); i++) {
			System.out.println(groups.get(i));
		}
		
		readXls.close();
		
		
		

		/*
		Big2SmallTable big2SmallTable = new Big2SmallTable(new File("D:\\用户目录\\我的文档\\▲实验课排课系统\\new.xls"));
		big2SmallTable.init();
		TheoryTeacher tt = big2SmallTable.getTheoryTeacher("黄华军");
		TheoryMajor tm = big2SmallTable.getTheoryMajor("2014级软件工程");
		MatrixUtil.firstCalculate(tt, tm);
		int[][] free = MatrixUtil.getResult(1, 20);
		
		
		for (int i = 0; i < free.length; i++) {
			for (int j = 0; j < free[0].length; j++) {
				if(free[i][j]==0){
					System.out.println(StringUtil.array2String(StringUtil.getWeekAndJieCi(i, j)));
				}
				
			}
		}
		
		*/
		
		
	}

	
}
