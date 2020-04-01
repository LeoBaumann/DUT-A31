package automatecellulaire.model;

public class Cellule {
    /**
     * @attribute
     */
    private Integer x;

    /**
     * @attribute
     */
    private Integer y;

    /**
     * @associates <{automatecellulaire.model.Etat}>
     */
    private Etat etat;

    /**
     * Constructeur de Cellule
     * @param x Position x de la cellule
     * @param y Position y de la cellule
     * @param etat Etat de la cellule
     */
    public Cellule(Integer x, Integer y, Etat etat) {
        this.x = x;
        this.y = y;
        this.etat = etat;
    }

    /**
     * Accesseur de l'état de la cellule
     * @return L'état de la cellule
     */
    public Etat getEtat() {
        return this.etat;
    }

    /**
     * Accesseur de la position en abscisse de la cellule
     * @return La position en abscisse de la cellule
     */
    public Integer getX() {
        return this.x;
    }

    /**
     * Accesseur de la position en ordonnée de la cellule
     * @return La position en ordonnée de la cellule
     */
    public Integer getY() {
        return this.y;
    }

    /**
     * Mutateur de l'état de la cellule
     * @param etat Etat de la cellule
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
