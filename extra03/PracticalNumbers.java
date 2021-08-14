package jtm.extra03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PracticalNumbers {

	// Read article https://en.wikipedia.org/wiki/Practical_number
	// Implement method, which returns practical numbers in given range
	// including
	public String getPracticalNumbers(int from, int to) {

		int firstNum = from;
		int lastNum = to;
		String message = "[";

		for (int num = firstNum; num <= lastNum; num++) {
			boolean status = false;
			int[] arrNum = new int[num - 1];
			boolean found = true;
			List<Integer> arr = new ArrayList<Integer>();
			for (int i = 1; i <= num; i++) {
				if (num % i == 0) {
					arr.add(i);
				}
			}
			int sum = arr.get(0);
			for (int i = 1; i < arr.size() - 1; i++) {
				if (sum < arr.get(i) - 1) {
					status = false;
					break;
				} else {
					status = true;
					sum += arr.get(i);
				}

			}
			if (status == true) {
				message += num;
				if (num == lastNum) {
					message += "]";
				} else {
					message += ", ";
				}
			}

		}
		if (message.endsWith(", ")) {

			message = message.substring(1, message.length() - 2);
			message += "]";
		}

		return "[" + message;
	}

}