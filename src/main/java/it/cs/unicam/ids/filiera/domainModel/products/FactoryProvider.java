package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.util.PhaseType;

public class FactoryProvider {

	private PhaseFactory phaseFactory;

	/**
	 * Method to retrieve the appropriate phase
	 * @param type
	 * @return PhaseFactory instance of the phase
	 */
	public PhaseFactory getFactory(PhaseType type) {
		switch (type) {
			case AGRICULTURAL:
				return new AgriculturalPhaseFactory();
			case DISTRIBUTION:
				return new DistributionPhaseFactory();
			case TRANSFORMATION:
				return new TransformationPhaseFactory();
			default:
				throw new IllegalArgumentException("Invalid phase type: " + type);
		}
	}
}