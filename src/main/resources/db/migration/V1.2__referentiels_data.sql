
INSERT INTO parametre(
	id, dtype, value, pkey, libelle)
	VALUES (1, 'Boolean', false, 'KEY_GESTION_MULTI_SITE', 'GESTION MULTI SITE DU STOCK');
	INSERT INTO parametre(
	id, dtype, value, pkey, libelle)
	VALUES (2, 'Multiple', false, 'KEY_MENU', 'Positionnement du menu');
	INSERT INTO parametre(
	id, dtype, value, pkey, libelle)
	VALUES (3, 'Multiple', false, 'KEY_THEMES', 'Theme du site');
	INSERT INTO magasin(id,  nom_court, nom_long, status, type_magasin) VALUES (1,'Nom abrégé du magasin', 'Nom du magasin', 'ACTIVE', 'PRINCIPAL');
	
INSERT INTO magasin(id,  nom_court, nom_long, status, type_magasin,magasin_id)	VALUES (2,'Point de vente', 'Point de vente', 'ACTIVE', 'POINT_DE_VENTE',1),
 (3,'Magasin de stockage reserve', 'Magasin de stockage reserve', 'ACTIVE', 'SAFETY_STOCK',1);
	INSERT INTO rayon(id, code,  libelle, status,  magasin_id) VALUES (1, 'SANS', 'SANS EMPLACEMENT', 'DESACTIVE', 1);
	 
	
	

  


