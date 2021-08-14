package jtm.activity07;

public class Dog extends Mammal {

	private String name;

	public Dog() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (Character.isUpperCase(name.charAt(0))) {
			this.name = name;
		} else {
			this.name = "";
		}
	}

}
