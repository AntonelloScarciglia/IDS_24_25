package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class TransformationPhase extends Phase {
    public TransformationPhase(Date start, Date end, SupplyChainPoint location) {
        super(start, end, location);
    }

    /**
     * Method to get the information of the phase
     * @return String
     */
    @Override
    public String getPhaseInfo() {
        return "transformation phase";
    }
}