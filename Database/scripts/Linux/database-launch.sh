cd ../../target/
read -p 'add or delete or update or show?: ' c

if [ "$c" = "add" ]
then
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t
  read -p 'Entrez ce que vous voulez ajouter : ' nom
  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -maxConnection="$b" -timeOut=$t -create=$nom
elif [ "$c" = "show" ]
then
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t

  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -maxConnection=$b -timeOut=$t -show=1


elif [ "$c" = "update" ]; then
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t
  read -p 'Entrez la table à modifier :' table
  read -p 'Entrez le nom de la colonne à modifier :' colonne
  read -p 'Entrez l id à modifier : ' id
  read -p 'Entrez ce que vous voulez ajouter : ' nom
  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -tableName="$table" -idcolomn=$id -maxConnection=$b -timeOut=$t -create=$nom -namecolumn=$colonne

else
  read -p 'Entrez maxConnection: '  b
  read -p 'Entrez timeOut : ' t
  read -p 'Entrez ce que vous voulez effacer : ' nom
  java -jar database-1.0-SNAPSHOT-jar-with-dependencies.jar -maxConnection=$b -timeOut=$t -delete=$nom
fi
cd ..