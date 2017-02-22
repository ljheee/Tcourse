package com.ljheee.main;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ljheee.bean.Major;
import com.ljheee.bean.Teacher;
import com.ljheee.read.ReadXls;

public class Main {


	public static void main(String[] args) {
		
		
		ReadXls readXls = new ReadXls("abc.xls");
//		List<Teacher> set = readXls.readXls();
//		Iterator<Teacher> it = set.iterator();
//
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
		
		
		List<Major> majors = readXls.getTeacherTeachesByName("辛动军");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
		
		
		Set<String> set = readXls.getMajorsInfo(majors);
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		readXls.close();
	}
}
