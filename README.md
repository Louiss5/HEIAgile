PROJET AgileHEI -- H5 ITI 2014-2015 
====================================


Général
-------

Il est conseillé d'utiliser une interface graphique pour faciliter l'utilisation de git.

Grâce à celle-ci vous pourrez facilement cloner votre projet puis faire vos commit/push/pull...

Par exemple : [SourceTree](http://www.sourcetreeapp.com)



Outils
------
  
  > Canal chat : [Slack](http://agilehei.slack.com)
  
  > Orga projet : [Trello](https://trello.com/b/tY8G4BAj/scrum-agilehei)


Récupérer le projet (au choix):
-------------------------------

- Directement depuis Eclipse:

  > **File > Import >** rechercher “git”  Clone URI
  
  > **URI:** https://github.com/MValan/HEIAgile.git
  
  > **Host:** github.com
  
  > **Repository Path:** /MValan/HEIAgile.git




- Ligne de commande:

  > Ouvrir un terminal/invite de commande
  
  > Se placer dans le dossier dans lequel on veut cloner le projet
  
  > 	$ git clone https://github.com/MValan/HEIAgile.git
  
  > Dans **Eclipse > Import > Maven Project >** ...


- Via [SourceTree](http://www.sourcetreeapp.com):
  > Cliquer sur "Cloner / Nouveau"
  
  > **Source / URL:** git@github.com:MValan/HEIAgile.git
  
  > **Destination:** Le chemin où vous souhaitez stocker le projet sur votre PC, puis "Cloner".
  
  > Dans **Eclipse > Import > Maven Project >**  le chemin où est stocké le projet sur votre ordinateur...


Installer la base de données
----------------------------

Il ne faut pas oublier de créer la base de données du projet.
Le fichier `db_import.sql` permet d'initialiser la base.
Il ne faut pas oublier de modifier le `server.xml` de tomcat et ajouter la ligne suivante dans le Context :

  >`<Resource auth="Container" driverClassName="com.mysql.jdbc.Driver" maxActive="100" maxIdle="30" maxWait="10000" name="jdbc/hei_agile" password="****" type="javax.sql.DataSource" url="jdbc:mysql://localhost:3306/hei_agile" username="hei"/>`

Connection base de données commune MySQL:
------------------------------------------
  >**host:** guillaumehertault.fr
  
  >**user:** hei
  
  >**pass:**   ~  _(demander)_
  
  >**port:** 3306
