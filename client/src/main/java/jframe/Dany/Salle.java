package jframe.Dany;

public class Salle {
    private boolean available;
    private int x,y;
    public Salle(int xx,int yy) {
        x = xx; y = yy;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
