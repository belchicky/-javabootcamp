package jtm.extra02;

/**
 * 
 * This class represents a bus driving in route, stopping at bus stops and
 * adding passengers.
 */
public class LetsRide {
	int busStopCount;// bus stop count
	int passengersAtStart;// passengers at the start in bus
	int passengersCount;// overall passengers count in bus
	int seatsCount;// bus seats count

	public LetsRide(int busStopCount, int passengersInStop, int seatsCount) {
		// #1: Set passed values to LetsRide object
		this.busStopCount = busStopCount;
		this.seatsCount = seatsCount;
		this.passengersAtStart = passengersInStop;
	}

	public int freeSeats() {
		int freeSeats = 0;
		// #3: Calculate how much seats are free in bus
		freeSeats = seatsCount - passengersCount;
		return freeSeats;
	}

	public int getBusStopCount() {
		return busStopCount;
	}

	public int getPassengersAtStart() {
		return passengersAtStart;
	}

	public int getPassengersCount() {
		return passengersCount;
	}

	public int getSeatsCount() {
		return seatsCount;
	}

	public boolean isFull() {
		boolean status = false;
		// #4: Check if bus is full.
		if (passengersCount == seatsCount)
			status = true;
		return status;
	}

	public int passengersAtRouteEnd() {
		// #2: Calculate how many passengers will be in bus at the end of
		// route. Overall passenger count
		// is incremented by bus stop number. Example: In bus stop No.1
		// passenger count will be increased by 1, in stop No.2 it
		// will be increased by 2 and so on until bus reaches route end.
		// Note: Overall passenger count can't exceed seat count
		passengersCount = passengersAtStart;
		for (int i = 1; i <= busStopCount; i++) {
			passengersCount += i;
			if (passengersCount > seatsCount) {
				passengersCount = seatsCount;
			}
		}
		return passengersCount;
	}

	public void setBusStopCount(int busStopCount) {
		this.busStopCount = busStopCount;
	}

	public void setPassengersAtStart(int passengersAtStart) {
		this.passengersAtStart = passengersAtStart;
	}

	public void setPassengersCount(int passengersCount) {
		this.passengersCount = passengersCount;
	}

	public void setSeatsCount(int seatsCount) {
		this.seatsCount = seatsCount;
	}

}
