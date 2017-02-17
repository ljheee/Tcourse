package com.ljheee.main;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ljheee.read.ReadXls;

public class Main {


	public static void main(String[] args) {
		ReadXls readXls = new ReadXls("abc.xls");
		Set<String> set = readXls.getTeachers();
		Iterator<String> it = set.iterator();

		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
