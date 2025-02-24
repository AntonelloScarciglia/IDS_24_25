package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class TransformationPhaseFactory extends PhaseFactory {
	/**
	 * Method to create a new TransformationPhase
	 * @param start
	 * @param end
	 * @param location
	 * @return Phase TransformationPhase
	 */
	@Override
	public Phase createPhase(Date start, Date end, SupplyChainPoint location) {
		return new TransformationPhase(start, end, location);
	}
}