package com.ljheee.test;

public class Test {
	
	public Long getLong(){
		return new Long(5);
	}

	class TestB extends Test{
		
		public Long getLong(){
			return new Long(9);
		}
	}
	//函数覆盖发生在父类与子类之间，其函数名、参数类型、返回值类型必须同父类中的相对应被覆盖的函数严格一致，覆盖函数和被覆盖函数只有函数体不同，当派生类对象调用子类中该同名函数时会自动调用子类中的覆盖版本，而不是父类中的被覆盖函数版本，这种机制就叫做函数覆盖。
	public static void main(String[] args) {

	}

}
