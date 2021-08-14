package jtm.activity06;

public class Martian implements Humanoid, Alien, Cloneable {

	Object stomach;

	@Override
	public void eat(Object item) {
		if (stomach == null) {
			stomach = item;
			if (item instanceof Human)
				((Human) item).killHimself();
		}
	}

	@Override
	public void eat(Integer food) {
		eat((Object) food);
	}

	@Override
	public Object vomit() {
		Object tmp = stomach;
		stomach = null;
		return tmp;
	}

	@Override
	public String isAlive() {
		return "I AM IMMORTAL!";
	}

	@Override
	public String killHimself() {
		return isAlive();
	}

	@Override
	public int getWeight() {
		int stomachWeight = 0;
		if (stomach instanceof Integer)
			stomachWeight = (Integer) stomach;
		if (stomach instanceof Humanoid) // common interface
			stomachWeight = ((Humanoid) stomach).getWeight();
		return Alien.BirthWeight + stomachWeight;
	}
	/*
	 * @Override public Object clone() throws CloneNotSupportedException { return
	 * super.clone(); }
	 */

	@Override
	public Object clone() throws CloneNotSupportedException {
		return clone(this);
	}

	private Object clone(Object current) {
		if (current instanceof Integer)
			return Integer.valueOf((Integer) current);
		if (current instanceof Human) {
			Human src = (Human) current;
			Human trg = new Human();
			trg.stomach = (Integer) clone(src.stomach);
			return trg;
		}
		if (current instanceof Martian) {
			Martian src = (Martian) current;
			Martian trg = new Martian();
			trg.stomach = clone(src.stomach);
			return trg;
		}

		return null;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getWeight() + " [" + stomach + "]";
	}

}
