# updOtpMapping
Programme Java permettant de mettre à jour dans une base de données de destination les raisons d'appel (Open Ticket Purpose) à partir de celles déclarées dans une base de données source.

## Utilisation:
```
java UpdOtpMapping [-source db] [-destinatin db] -u unum|-clientCompanyUuid uuid [-d] [-t] 
```
où :
* ```-source prod|pre-prod|dev``` est la référence à la base de données MongoDb contenant les données à copier, par défaut désigne la base de données de pré-production. Voir fichier *updOtpMapping.prop* (optionnel).
* ```-destination prod|pre-prod|dev``` est la référence à la base de données MongoDb qui recevra les données à copier, par défaut désigne la base de données de pré-production. Voir fichier *updOtpMapping.prop* (optionnel).
* ```-u unum``` est l'identifiant interne du client concerné (paramètre obligatoire).
* ```-clientCompanyUuid uuid``` est l'identifiant universel unique du client concerné (paramètre obligatoire).
* ```-d``` le programme s'exécute en mode débug, il est beaucoup plus verbeux. Désactivé par défaut (paramètre optionnel).
* ```-t``` le programme s'exécute en mode test, les transactions en base de données ne sont pas faites. Désactivé par défaut (paramètre optionnel).

## Pré-requis :
- Java 6 ou supérieur.
- JDBC Informix
- Driver MongoDB
- [xmlbeans-2.6.0.jar](https://xmlbeans.apache.org/)
- [commons-collections4-4.1.jar](https://commons.apache.org/proper/commons-collections/download_collections.cgi)
- [junit-4.12.jar](https://github.com/junit-team/junit4/releases/tag/r4.12)
- [hamcrest-2.1.jar](https://search.maven.org/search?q=g:org.hamcrest)

## Fichier des paramètres : 

Ce fichier permet de spécifier les paramètres d'accès aux différentes bases de données.

A adapter selon les implémentations locales.

Ce fichier est nommé : *updOtpMapping.prop*.

Le fichier *updOtpMapping_Example.prop* est fourni à titre d'exemple.

## Références:
