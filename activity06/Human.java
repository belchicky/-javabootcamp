package jtm.activity06;

public class Human implements Humanoid {
	boolean alive;
	int stomach;

	public Human() {
		alive = true;
	}

	@Override
	public void eat(Integer food) {
		if (stomach == 0)
			stomach = food;

	}

	@Override
	public Integer vomit() {
		Integer tmp = stomach;
		stomach = 0;
		return tmp;
	}

	@Override
	public String isAlive() {
		if (alive)
			return "Alive";
		return "Dead";
	}

	@Override
	public String killHimself() {
		alive = false;
		return isAlive();
	}

	@Override
	public int getWeight() {
		return BirthWeight + stomach;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getWeight() + " [" + stomach + "]";
	}
}
