package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class AgriculturalPhaseFactory extends PhaseFactory {
	/**
	 * Method to create a new AgriculturalPhase
	 * @param start
	 * @param end
	 * @param location
	 * @return Phase AgriculturalPhase
	 */
	@Override
	public Phase createPhase(Date start, Date end, SupplyChainPoint location) {
		return new AgriculturalPhase(start, end, location);
	}
}