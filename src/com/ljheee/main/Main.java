package com.ljheee.main;

import java.util.Iterator;
import java.util.List;

import com.ljheee.bean.Teacher;
import com.ljheee.read.ReadXls;

public class Main {


	public static void main(String[] args) {
		
		
		ReadXls readXls = new ReadXls("abc.xls");
		List<Teacher> set = readXls.readXls();
		Iterator<Teacher> it = set.iterator();

		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		
		
		
		readXls.close();
	}
}
