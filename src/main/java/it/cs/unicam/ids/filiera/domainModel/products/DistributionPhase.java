package it.cs.unicam.ids.filiera.domainModel.products;

import java.util.Date;

public class DistributionPhase extends Phase {
    public DistributionPhase(Date start, Date end, Location loc) {
        super(start, end, loc);
    }

    @Override
    public String getPhaseInfo() {
        return null;
    }
}