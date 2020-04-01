package automatecellulaire.model;

import automatecellulaire.utils.Observable;

public class Grille extends Observable {

    /**
     * @attribute
     */
    private Integer[][] tab;

    /**
     * @attribute
     */
    private Integer y;

    /**
     * @attribute
     */
    private Integer x;

    /**
     * Constructeur de grille
     * @param x La taille de la grille en abscisse
     * @param y La taille de la grille en ordonn�e
     */
    public Grille(Integer x, Integer y) {
        tab = new Integer[x][y];
        this.x = x;
        this.y = y;
    }

    /**
     * M�thode d'ajout d'une cellule au tableau
     * @param cellule
     * @param id
     */
    public void ajoutCellule(Cellule cellule, Integer id) {
        tab[cellule.getX()][cellule.getY()] = id;
    }

    /**
     * Accesseur du tableau d'entier
     * @return Le tableau � double dimension d'entier
     */
    public Integer[][] getTab() {
        return this.tab;
    }
    
    /**
     * Mutateur du tableau d'entier
     * @param x Position x du tableau
     * @param y Position y du tableau
     * @param value La valeur � mettre dans le tableau
     */
    public void setTab(Integer x,Integer y,Integer value){
        tab[x][y]=value;
        this.notifierObs();
    }
    
    /**
     * Accesseur de la taille en abscisse de la grille
     * @return La taille en abscisse de la grille
     */
    public Integer getX() {
        return this.x;
    }

    /**
     * Accesseur de la taille en ordonn�e de la grille
     * @return La taille en ordonn�e de la grille
     */
    public Integer getY() {
        return this.y;
    }
}
