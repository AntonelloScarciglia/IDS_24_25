package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public abstract class PhaseFactory {

    /**
     * @param start
     * @param end
     * @param loc
     */
    public abstract Phase create(Date start, Date end, Location loc);
}