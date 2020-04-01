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
     * Accesseur de l'�tat de la cellule
     * @return L'�tat de la cellule
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
     * Accesseur de la position en ordonn�e de la cellule
     * @return La position en ordonn�e de la cellule
     */
    public Integer getY() {
        return this.y;
    }

    /**
     * Mutateur de l'�tat de la cellule
     * @param etat Etat de la cellule
     */
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
}
