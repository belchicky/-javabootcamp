package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Amphibia extends Transport {
	private Ship ship;
	private Vehicle vehicle;

	public Amphibia(String id, float consumption, int tankSize, byte sails, int wheels) {
		super(id, consumption, tankSize);
		ship = new Ship(id, sails);
		vehicle = new Vehicle(id, consumption, tankSize, wheels);
	}

	@Override
	public String move(Road road) {
		if (road instanceof WaterRoad)
			return ship.move(road).replace("Ship", "Amphibia");
		String status = vehicle.move(road).replace("Vehicle", "Amphibia");
		setFuelInTank(vehicle.getFuelInTank());
		return status;
	}

	public static void main(String[] args) {
		Road road = new Road("from", "to", 100);
		Amphibia amphibia = new Amphibia("id", 10, 100, (byte) 3, 4);
		System.out.println(amphibia.move(road));
	}
}