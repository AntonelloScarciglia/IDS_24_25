package it.cs.unicam.ids.filiera.util;

import jakarta.persistence.Embeddable;

@Embeddable
public record Coordinate(long latitude, long longitude) {
}
