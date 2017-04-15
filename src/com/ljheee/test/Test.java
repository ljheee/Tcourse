package com.ljheee.test;

public class Test {
	

	
	public static void main(String[] args) {
		String str="6-18";
		String ss[] = str.split(",");

		System.out.println(ss.length);
		for (String string : ss) {
			System.out.println(string);
		}
	}

}
