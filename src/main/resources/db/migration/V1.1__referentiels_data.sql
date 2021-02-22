
 INSERT  INTO tva (id,taux) VALUES (1,0),(2,18),(3,9);
INSERT INTO categorie_produit(id,code,libelle) VALUES (1,'01','Grand Public'), (2,'02','Dietetique'),
 (3,'10','Specialité'), (4,'30','Accessoires'), (5,'40','Veterinaires'),
(6,'20','Parfumerie'); 

 INSERT INTO type_etiquette(id,libelle) VALUES (1,'CIP'), (2,'CIP_PRIX'),
 (3,'CIP_DESIGNATION'), (4,'CIP_PRIX_DESIGNATION'),(5,'POSITION'); 

 INSERT INTO type_risque(id,libelle) VALUES (1,'Travail'), (2,'Loisir');
 INSERT INTO risque(id,code,libelle,typerisque_id) VALUES (1,'04','ALD',1), (2,'02','Risque Invalide',1), (3,'01','Risque Normal',1),(4,'03','Exoneration Victime de guerre',1);

 INSERT INTO form_produit(id,code,libelle) VALUES(1,'01','Comprimés'),(2,'02','Sachets');
  INSERT INTO motif(id,libelle) VALUES(1,'Avarié'),(2,'Colis ouvert')
  ,(3,'Colis endommagé'),(4,'Livré non facturé'),(5,'Erreur de saisie'),(6,'Retour bon etat')
  ,(7,'Péremption proche')  ,(8,'Erreur livraison')
   ,(9,'Erreur commande')
    ,(10,'Prix eleve')
	  ,(11,'Retrait de lots')
  ;


