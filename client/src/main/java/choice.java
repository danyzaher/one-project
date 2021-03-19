import java.util.Scanner;

public class choice {
    private final Model modeltest;

    public choice() {
        modeltest = new Model();
    }
    public void menu(){
        System.out.println("tape --read : ");
        Scanner scanner= new Scanner(System.in);
        
    }
    
    public String choixUtilisateur(Scanner scanner){
        
        String choix = scanner.nextLine();
        if(choix == "--read"){
            showElement(scanner);
        }
    }

    private void showElement(Scanner scanner) {
    }
}

