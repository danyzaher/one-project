cd ../../target/
read -p 'add or delete?: ' c

if [ "$c" = "add" ]
then
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t
  read -p 'Entrez ce que vous voulez ajouter : ' nom
  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -maxConnection=$b -timeOut=$t -create=$nom

else
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t
  read -p 'Entrez ce que vous voulez effacer : ' nom
  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -maxConnection=$b -timeOut=$t -delete=$nom
fi
cd ..