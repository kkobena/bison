# nucleus

This application was generated using JHipster 6.9.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.9.0](https://www.jhipster.tech/documentation-archive/v6.9.0).

## Development

To start your application in the dev profile, run:

    ./mvnw

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the nucleus application for production, run:

    ./mvnw -Pprod clean verify

To ensure everything worked, run:

    java -jar target/*.jar

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    ./mvnw -Pprod,war clean verify

## Testing

To launch your application's tests, run:

    ./mvnw verify

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

or

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/postgresql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/postgresql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw -Pprod verify jib:dockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.9.0 archive]: https://www.jhipster.tech/documentation-archive/v6.9.0
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.9.0/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.9.0/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.9.0/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.9.0/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.9.0/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.9.0/setting-up-ci/

## requete pour le fichier csv d'importation de la table tiers-payant de prestige 2

SELECT p.str_FULLNAME,p.str_NAME,p.str_TELEPHONE,p.str_MOBILE,p.str_ADRESSE,
p.str_REGISTRE_COMMERCE,p.int_NBRE_EXEMPLAIRE_BORD,p.b_IsAbsolute,
p.dbl_PLAFOND_CREDIT,p.b_CANBEUSE,p.int_MONTANTFAC,p.dbl_REMISE_FORFETAIRE,r.str_CODE_RISQUE,t.str_CODE_TYPE_TIERS_PAYANT
FROM t_tiers_payant p , t_risque r,t_type_tiers_payant t WHERE p.lg_RISQUE_ID=r.lg_RISQUE_ID
AND t.lg_TYPE_TIERS_PAYANT_ID=p.lg_TYPE_TIERS_PAYANT_ID

# @Transactional(timeout=3600)

#Coût moyen achat
Average Cost = Total Cost of Goods(Achats) ÷ Total Quantity of Goods
Available for Sale Available for Sale

Current Ratio = Current Assets(Actifs) ÷ Current Liabilities(Passifs)
#Taux de rotation des stock
Inventory Turnover Ratio = Cost of Goods Sold ÷ Average Inventory(moyen stock pour une période donnée

# inventory balance (solde de stock calculé à la fin du ternier jour iuvrable du mois)

#Cost of goods sold (Coût des marchandises vendues)
Beginning inventory(date fin dernier invententaire) + Purchases during the period - Ending inventory = Cost of goods sold

#Inventory Turnover (Rotation des stocks)
Annual cost of goods sold(montant des vente annuelle) ÷ Inventory(valorisation en vente du stock actuel) = Inventory turnover

#exemple de calcul Inventory Turnover
$8,150,000 Cost of Goods Sold
--------------------------------------------   =   5 Turns Per Year===>> 365/5 (nbre stock en main journalier)
$1,630,000 Inventory

#Average inventory calculation
(Beginning inventory + Ending inventory) / 2
#exemple

#January ending inventory $185,000 
#February ending inventory	$213,000
#March ending inventory $142,000
#Total	$540,000
#Average inventory = Total / 3 \$180,000

#Days of Inventory
365 ÷ (Annualized cost of goods sold ÷ Inventory)

365 ÷ ($1,000,000 ÷ $200,000) = 73 Days of inventory

#stock replenishment (réapprovisionnement de stock)
#ROP(reorder point) FORMULE
(Usage x Lead Time) + Safety Stock = ROP

usage=consommation

Lead time =delais de livraison

Safety stock =stock de secutité

#Lead time

1 week = 0.25 = 25%

4 weeks = 1.00 = 100%

2 weeks = 0.50 = 50%

5 weeks = 1.25 = 125%

3 weeks = 0.75 = 75%

6 weeks = 1.50 = 150%

#Example 1:
Assume:

• Usage rate of 1,200 items per month

• Lead time of 3 weeks

Step-by-Step Calculation:

• Calculate weekly usage. Assume a 4-week month. 1,200

items ÷ 4 weeks = 300 items per week ➜ therefore Bin 1
or working stock should contain at least 300 items

• Calculate working reserve: Given 3 weeks of lead time,
working reserve should be 1,200 items x 0.75 = 900 items

• Calculate safety stock, use 50 percent of working reserve
as a guideline (900 items x 50% = 450 items)

• Calculate ROP: (1,200 items x 0.75) + 450 items = ROP
1,350 items

#Example 2:
Assume:

• Usage rate of 1,200 items per month

• Lead time of 1 week

Step-by-Step Calculation:

• Calculate weekly usage. Assume a 4-week month. 1,200
items ÷ 4 weeks = 300 items per week ➜ therefore Bin 1

or working stock should contain at least 300 items
• Calculate working reserve: Given 1 week of lead time,
working reserve should be 1,200 items x 0.25 = 300 items

• Calculate safety stock, use 50 percent of working reserve
as a guideline (300 items x 50% = 150 items)

• Calculate ROP: (1,200 items x 0.25) + 150 items = ROP
450 items

#Economic Order Quantity (EOQ) Formula

# REQUETE POUR EXPORTER LES FOURNISSEURS EN CSV

SELECT g.str_LIBELLE AS libelle,g.str_CODE AS code,g.str_CODE_POSTAL AS addresse_postal,g.str_MOBILE AS mobile, g.str_TELEPHONE AS phone
,g.str_URL_EXTRANET AS site, g.idrepartiteur AS identifiant_repartiteur, f.libelle AS groupeFournisseur_libelle
FROM t_grossiste g LEFT JOIN groupefournisseur f ON g.groupeId=f.id;

#REQUETE FAMILLE PRODUIT
SELECT g.str_LIBELLE AS libelle,g.str_CODE AS code,g.str_CODE_POSTAL AS addresse_postal,g.str_MOBILE AS mobile, g.str_TELEPHONE AS phone
,g.str_URL_EXTRANET AS site, g.idrepartiteur AS identifiant_repartiteur, f.libelle AS groupeFournisseur_libelle
FROM t_grossiste g LEFT JOIN groupefournisseur f ON g.groupeId=f.id;
