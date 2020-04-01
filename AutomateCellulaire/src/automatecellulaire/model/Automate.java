package automatecellulaire.model;

import automatecellulaire.controller.CtrlGrille;

import automatecellulaire.controller.Fabrique;

import java.util.ArrayList;

public class Automate {

    /**
     * @associates <{automatecellulaire.model.Cellule}>
     */
    private ArrayList<Cellule> listeCellule = new ArrayList<Cellule>();

    /**
     * @associates <{automatecellulaire.model.Grille}>
     */
    private Grille grille;

    /**
     * @attribute
     */
    private Integer id;

    /**
     * @associates <{automatecellulaire.model.RegleEvolution}>
     */
    private RegleEvolution regleEvolution;

    /**
     * Constructeur de Automate
     * @param id Identifiant de l'automate
     * @param regleEvolution La règle d'évolution de l'automate
     */
    public Automate(Integer id, TypeRegleEvolution regleEvolution) {
        this.id = id;
        this.grille = CtrlGrille.getInstance().getGrille();
        this.regleEvolution = Fabrique.creerRegleEvolution(regleEvolution);
        for (int i = 0; i < grille.getX(); i++) {
            for (int j = 0; j < grille.getY(); j++) {
                creerCellule(i, j, Etat.MORT);
            }
        }
    }

    /**
     * Accesseur liste cellule
     * @return La liste des cellules de l'automate
     */
    public ArrayList<Cellule> getListeCellule() {
        return new ArrayList<Cellule>(this.listeCellule);
    }

    /**
     * Mutateur liste cellule
     * @param listeCellule La nouvelle liste de cellules de l'automate
     */
    public void setListeCellule(ArrayList<Cellule> listeCellule) {
        this.listeCellule = listeCellule;
    }

    /**
     * Méthode de création d'une cellule
     * @param x coordonnée x de la cellule
     * @param y coordonnée y de la cellule
     * @param etat Etat de la cellule
     */
    public void creerCellule(Integer x, Integer y, Etat etat) {
        Cellule cellule = getCellule(x, y);
        if (cellule == null) {
            cellule = new Cellule(x, y, etat);
            listeCellule.add(cellule);
        } else {
            cellule.setEtat(etat);
        }
        if (etat == Etat.VIVANT) {
            grille.ajoutCellule(cellule, this.id);
        } else {
            grille.ajoutCellule(cellule, 0);
        }
    }

    /**
     * Accesseur de id
     * @return L'id de l'automate
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Méthode qui compte le nombre de cellule vivante
     * @return Le nombre de cellule vivante
     */
    public Integer getNbCelluleVivant() {
        int nbCelluleVivant = 0;
        for (Cellule cellule : this.listeCellule) {
            if (cellule.getEtat() == Etat.VIVANT) {
                nbCelluleVivant++;
            }
        }
        return nbCelluleVivant;
    }

    /**
     * Accesseur d'une cellule
     * @param x coordonnée x de la cellule
     * @param y coordonnée y de la cellule
     * @return La cellule correspondant aux coordonnées
     */
    public Cellule getCellule(Integer x, Integer y) {
        Cellule celluleCherche = null;
        if (x < 0 || x == grille.getX() || y < 0 || y == grille.getY()) {
            celluleCherche = CtrlGrille.getInstance().creerCelluleHorsGrille(x, y, getId());
        } else {
            for (Cellule cellule : listeCellule) {
                if (cellule.getX() == x && cellule.getY() == y) {
                    celluleCherche = cellule;
                }
            }
        }
        return celluleCherche;
    }

    /**
     * Méthode qui fait évoluer une cellule
     * @param cellule La cellule qui va evoluer
     * @return La cellule evolué
     */
    @SuppressWarnings("unchecked")
    public Etat evoluerCellule(Cellule cellule) {

        Integer x = cellule.getX();
        Integer y = cellule.getY();
        ArrayList<Etat> listeVoisins = regleEvolution.getRegleVoisinage().trouverEtatVoisin(x, y, this);

        return (Etat) regleEvolution.evoluer(cellule.getEtat(), listeVoisins);
    }

}
