# updOtpMapping
Programme Java permettant de mettre � jour dans une base de donn�es de destination les raisons d'appel (Open Ticket Purpose) � partir de celles d�clar�es dans une base de donn�es source.

## Utilisation:
```
java UpdOtpMapping [-source db] [-destinatin db] -u unum|-clientCompanyUuid uuid [-d] [-t] 
```
o� :
* ```-source prod|pre-prod|dev``` est la r�f�rence � la base de donn�es MongoDb contenant les donn�es � copier, par d�faut d�signe la base de donn�es de pr�-production. Voir fichier *updOtpMapping.prop* (optionnel).
* ```-destination prod|pre-prod|dev``` est la r�f�rence � la base de donn�es MongoDb qui recevra les donn�es � copier, par d�faut d�signe la base de donn�es de pr�-production. Voir fichier *updOtpMapping.prop* (optionnel).
* ```-u unum``` est l'identifiant interne du client concern� (param�tre obligatoire).
* ```-clientCompanyUuid uuid``` est l'identifiant universel unique du client concern� (param�tre obligatoire).
* ```-d``` le programme s'ex�cute en mode d�bug, il est beaucoup plus verbeux. D�sactiv� par d�faut (param�tre optionnel).
* ```-t``` le programme s'ex�cute en mode test, les transactions en base de donn�es ne sont pas faites. D�sactiv� par d�faut (param�tre optionnel).

## Pr�-requis :
- Java 6 ou sup�rieur.
- JDBC Informix
- Driver MongoDB
- [xmlbeans-2.6.0.jar](https://xmlbeans.apache.org/)
- [commons-collections4-4.1.jar](https://commons.apache.org/proper/commons-collections/download_collections.cgi)
- [junit-4.12.jar](https://github.com/junit-team/junit4/releases/tag/r4.12)
- [hamcrest-2.1.jar](https://search.maven.org/search?q=g:org.hamcrest)

## Fichier des param�tres : 

Ce fichier permet de sp�cifier les param�tres d'acc�s aux diff�rentes bases de donn�es.

A adapter selon les impl�mentations locales.

Ce fichier est nomm� : *updOtpMapping.prop*.

Le fichier *updOtpMapping_Example.prop* est fourni � titre d'exemple.

## R�f�rences:
