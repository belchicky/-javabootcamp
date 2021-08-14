package jtm.activity05;

import jtm.activity04.Road;
import jtm.activity04.Transport;

public class Ship extends Transport {
	protected byte sails;

	public Ship(String id, byte sails) {
		super(id, 0, 0);
		this.sails = sails;
	}

	@Override
	public String move(Road road) {
		if (road instanceof WaterRoad)
			return (getType() + " is sailing on " + road + " with " + sails + " sails");
		return ("Cannot sail on " + road);

	}

}
