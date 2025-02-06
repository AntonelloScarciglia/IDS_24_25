package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class AgriculturalPhase extends Phase {
    public AgriculturalPhase(Date start, Date end, Location loc) {
        super(start, end, loc);
    }

    @Override
    public String getPhaseInfo() {
        return null;
    }
}