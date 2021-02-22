package com.kobe.nucleus.domain.enumeration;

/**
 * The TypeMagasin enumeration.
 */
public enum TypeMagasin {
	PRINCIPAL("Stockage principal"), SAFETY_STOCK("Magasin de reserve"), DEPOT("Dépôt"),
	POINT_DE_VENTE("Point de vente"), DEPOT_AGREE("Dépôt agréé");

	public final String value;

	public String getValue() {
		return value;
	}

	private TypeMagasin(String value) {
		this.value = value;
	}

	public static TypeMagasin valueOfLabel(String value) {
		for (TypeMagasin e : values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		return null;
	}
}
