import java.io.Serializable;

public class PU implements Serializable {
    String nom;
    String spec;
    int moyenne;
    PU(String nom, String spec, int moyenne) {
	this.nom = nom;
	this.spec = spec;
	this.moyenne = moyenne;   
    }
    String getNom() { return nom; }
    public String toString() {
	return "PU : " + nom + "  " + "spec : " + spec + " " + "moyenne :" + moyenne;
    }
}
