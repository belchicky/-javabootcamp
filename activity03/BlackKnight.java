package jtm.activity03;

/**
 * Black Knight is brave soldier who fights till he is alive. He doesn't bother
 * if some of his arms or legs are cut off. You can kill him only when he lose
 * head. More info at:
 * https://en.wikipedia.org/wiki/Black_Knight_%28Monty_Python%29
 */
public class BlackKnight {
	// Class variables which are shared between all class members (objects)
	public static short totalKnights; // total number of knights at the start of
										// the battle
	public static short aliveKnights; // total number of alive knights
	public static short deadKnights; // total number of dead knights
	public static BlackKnight[] knights; // array of knights in the battle

	// instance variables which are separate for each class member (object)
	public String name; // knight name
	public byte arms, legs, head; // number of limbs
	public boolean alive; // is knight alive

	public static void setBattle(int initialNumber) {
		// initialize array of knights with the passed size.
		// Reset total numbers of total, alive and dead knights to zero
		knights = new BlackKnight[initialNumber];
		totalKnights = 0;
		aliveKnights = 0;
		deadKnights = 0;
	}

	public BlackKnight(String name) {
		// set name of newly created knight
		// 1. set proper count of his arms, legs and head,
		// 2. set his status to alive
		// 3. put reference of this knight into next free cell of knights static
		// array
		// 4. increase number of total and alive knights of static counters
		// HINT: use "this.name" to access name of knight which otherwise is shadowed
		// by parameter of constructor, which is also called "name"

		this.name = name;
		alive = true;
		arms = 2;
		legs = 2;
		head = 1;

		knights[totalKnights] = this;
		totalKnights++;
		aliveKnights++;
	}

	public String cutOffArm() {
		// handle cutting off knight's arms in following way:
		// If knight is dead, return "Only chicken beats dead!"
		if (!alive)
			return "Only chicken beats dead!";
		// If knight has some arms, cut one off and return "Tis but a scratch!"
		if (arms > 0) {
			arms--;
			return "Tis but a scratch!";
		}
		// Else return just "Haah!"
		return "Haah!";
	}

	public String cutOffLeg() {
		// handle cutting off legs knight's legs in following way:
		// If knight is dead, return "Only chicken beats dead!"
		if (!alive)
			return "Only chicken beats dead!";
		// If knight has some legs, cut one off and return "Had worse!"
		if (legs > 0) {
			legs--;
			return "Had worse!";
		}
		// Else return just "Haah!"
		return "Haah!";
	}

	public String cutOffHead() {
		// handle cutting off knight's head in following way:
		// If knight is dead, return "Only chicken beats dead!"
		if (!alive)
			return "Only chicken beats dead!";
		// If knight is alive and has head, cut it off and update
		// number of total alive and dead knights and then
		// If there are other knights alive return:
		// "You'll never win! Arthur, Cnut will still fight!"
		// Where "Arthur, Cnut" are names of still alive knights
		if (head > 0) {
			head--;
			alive = false;
			aliveKnights--;
			deadKnights++;
		}
		String list = "";
		for (BlackKnight knight : knights) {
			if (knight.alive)
				list = list + knight.name + ", ";
		}
		if (!"".equals(list)) {
			list = list.substring(0, list.length() - 2);
			return "You'l never win! " + list + " will still fight!";
		}

		// Else return "You'l burn in hell forever!"
		return "You'l burn in hell forever!";
	}
}