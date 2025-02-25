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
        return switch (type) {
            case AGRICULTURAL -> new AgriculturalPhaseFactory();
            case DISTRIBUTION -> new DistributionPhaseFactory();
            case TRANSFORMATION -> new TransformationPhaseFactory();
            default -> throw new IllegalArgumentException("Invalid phase type: " + type);
        };
	}
}