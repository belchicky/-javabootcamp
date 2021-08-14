package jtm.activity07;

public class Animal {

	private int age;

	public Animal() {

	}

	public int getAge() {
		if (age > 0) {
			return this.age;
		} else {
			return 0;
		}
	}

	public void setAge(int age) {
		this.age = age;
	}

}