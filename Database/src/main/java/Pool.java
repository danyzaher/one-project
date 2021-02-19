import java.sql.* ;
import java.io.*;
import ch.qos.logback.classic.Logger;
import org.apache.commons.cli.*;
import org.slf4j.LoggerFactory;

public  class Pool {
	private final static Logger logger = (Logger) LoggerFactory.getLogger(Pool.class.getName());

	public  static  void main (String[] args) throws ParseException {

		try {
			// chargement de la classe par son nom
			Class c = Class.forName("com.postgresql.jdbc.Driver") ;
			Driver pilote = (Driver)c.newInstance() ;
			// enregistrement du pilote auprès du DriverManager
			DriverManager.registerDriver(pilote);
			// Protocole de connexion
			String protocole =  "jdbc:postegresql:" ;
			// Adresse IP de l’hôte de la base et port
			String ip =  "127.0.0.1" ;  // dépend du contexte
			String port =  "53020" ;  // port MySQL par défaut
			// Nom de la base
			String nomBase =  "testone" ;  // dépend du contexte
			// Chaîne de connexion
			String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase ;
			// Identifiants de connexion et mot de passe
			String nomConnexion =  "postgres" ;  // dépend du contexte
			String motDePasse =  "123" ;  // dépend du contexte
			// Connexion
			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;

			// Envoi d’un requête générique
			String sql =  "select * from salles" ;
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			while (rs.next()) {
				System.out.println(rs.getString("numero_salle")) ;
			}
	}
}
