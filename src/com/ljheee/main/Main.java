package com.ljheee.main;

import java.io.File;

import com.ljheee.bean.TheoryMajor;
import com.ljheee.bean.TheoryTeacher;
import com.ljheee.bean.WeekClass;
import com.ljheee.read.Big2SmallTable;
import com.ljheee.util.MatrixUtil;

public class Main {


	public static void main(String[] args) {
		
		
//		ReadXls readXls = new ReadXls(new File("17春专业实验实践课.xls"));
		
//		List<Teacher> set = readXls.readXls();
//		Iterator<Teacher> it = set.iterator();
//
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
/*		
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
*/		
		Big2SmallTable big2SmallTable = new Big2SmallTable(new File("D:\\用户目录\\我的文档\\▲实验课排课系统\\new.xls"));
		big2SmallTable.init();
		TheoryTeacher tt = big2SmallTable.getTheoryTeacher("黄辉");
		TheoryMajor tm = big2SmallTable.getTheoryMajor("2014级软件工程");
		MatrixUtil.calculate(1, 20, tt, tm);
		
//		WeekClass wc1 = tt.getTheoryTable(11);
		WeekClass wc1 = tm.getTheoryTable(4);
		for (int i = 0; i < wc1.week.length; i++) {
			for (int j = 0; j < wc1.week[0].length; j++) {
				System.out.print(wc1.week[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}
}
