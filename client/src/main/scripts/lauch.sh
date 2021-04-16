cd ../../target/

  read -p 'Entrez ce que vous voulez voir : ' nom
  java -jar client-1.0-SNAPSHOT-jar-with-dependencies.jar -variable=$nom -show=1
cd ..