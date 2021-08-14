package jtm.activity09;

/*-#1
 * Implement Comparable interface with Order class
 * Hint! Use generic type of comparable items in form: Comparable<Order>
 * 
 * #2 Override/implement necessary methods for Order class:
 * - public Order(String orderer, String itemName, Integer count) — constructor of the Order
 * - public int compareTo(Order order) — comparison implementation according to logic described below
 * - public boolean equals(Object object) — check equality of orders
 * - public int hashCode() — to be able to handle it in some hash... collection 
 * - public String toString() — string in following form: "ItemName: OrdererName: Count"
 * 
 * Hints:
 * 1. When comparing orders, compare their values in following order:
 *    - Item name
 *    - Customer name
 *    - Count of items
 * If item or customer is closer to start of alphabet, it is considered "smaller"
 * 
 * 2. When implementing .equals() method, rely on compareTo() method, as for sane design
 * .equals() == true, if compareTo() == 0 (and vice versa).
 * 
 * 3. Also Ensure that .hashCode() is the same, if .equals() == true for two orders.
 * 
 */

public class Order implements Comparable<Order> {
	String customer; // Name of the customer
	String name; // Name of the requested item
	int count; // Count of the requested items

	public Order(String customer, String name, Integer count) {
		this.customer = customer;
		this.name = name;
		this.count = count;
	}

	public int compareTo(Order other) {
		int status = name.compareTo(other.name);
		if (status == 0) {
			status = customer.compareTo(other.customer);
			if (status == 0) {
				status = count - other.count;
			}
		}
		return status;
	}

	public boolean equals(Object object) {
		if (object instanceof Order) {
			return compareTo((Order) object) == 0;
		}
		return false;
	}

	public int hashCode() {
		return toString().hashCode();
	}

	public String toString() {
		return name + ": " + customer + ": " + count;
	}

}