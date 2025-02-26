package it.cs.unicam.ids.filiera.domainModel.products;

import it.cs.unicam.ids.filiera.util.Coordinate;
import jakarta.persistence.Embeddable;

@Embeddable
public class SupplyChainPoint {

	private Long id;
	private String name;
	private String descr;
	private Coordinate coords;

	public SupplyChainPoint(Long id, String name, String descr, Coordinate coords) {
		this.id = id;
		this.name = name;
		this.descr = descr;
		this.coords = coords;
	}

	public SupplyChainPoint() {

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescr() {
		return descr;
	}

	public Coordinate getCoords() {
		return coords;
	}
}