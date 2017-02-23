package com.ljheee.main;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ljheee.bean.Major;
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
	}
}
