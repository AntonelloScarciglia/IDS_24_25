package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class DistributionPhaseFactory extends PhaseFactory {

	/**
	 * Method to create a new DistributionPhase
	 * @param start
	 * @param end
	 * @param location
	 * @return Phase DistributionPhase
	 */
	@Override
	public Phase createPhase(Date start, Date end, SupplyChainPoint location) {
		return new DistributionPhase(start, end, location);
	}
}