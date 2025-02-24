package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public abstract class PhaseFactory {

    /**
     * Method to create a new phase
     * @param start Date
     * @param end Date
     * @param location SupplyChainPoint
     * @return Phase
     */
    public abstract Phase createPhase(Date start, Date end, SupplyChainPoint location);
}