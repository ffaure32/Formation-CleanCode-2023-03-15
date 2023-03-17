package zenika.solid.ocp;

public class Cercle implements Figure {
    public int rayon;

    public Cercle(int rayon) {
        this.rayon = rayon;
    }

    public double getAire() {
        return Math.PI * rayon * rayon;
    }
}
