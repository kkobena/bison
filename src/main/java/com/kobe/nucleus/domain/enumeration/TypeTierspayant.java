package com.kobe.nucleus.domain.enumeration;

/**
 * The TypeTierspayant enumeration.
 */
public enum TypeTierspayant {
	ASSURANCE("01"), CARNET("02");

	private final String value;

	private TypeTierspayant(String value) {
		this.value = value;
	}

	public static TypeTierspayant valueOfLabel(String value) {
		for (TypeTierspayant e : values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
