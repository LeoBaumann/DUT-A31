package automatecellulaire.controller;

import automatecellulaire.model.Cellule;
import automatecellulaire.model.Grille;
import automatecellulaire.model.Etat;

public class CtrlGrille {

    /**
     * @associates <{automatecellulaire.model.Cellule}>
     */
    private Grille grille;

    /**
     * @associates <{automatecellulaire.model.Cellule}>
     */
    private MethodeExtension methodeExtension;

    /**
     * @associates <{automatecellulaire.controller.Cellule}>
     */
    private static CtrlGrille ctrlGrille;

    /**
     * Constructeur de CtrlGrille
     */
    private CtrlGrille() {
    }

    /**
     * Méthode de construction de la grille
     * @param x Taille de l'abscisse de la grille
     * @param y Taille de l'ordonnée de la grille
     */
    public void creerGrille(Integer x, Integer y,TypeMethodeExtension methodeExtension) {
        grille = new Grille(x, y);
        this.methodeExtension = Fabrique.creerMethodeExtension(methodeExtension);
    }

    /**
     * Accesseur de l'instance de CtrlGrille
     * @return L'instance de CtrlGrille
     */
    public static CtrlGrille getInstance() {
        if (ctrlGrille == null)
            ctrlGrille = new CtrlGrille();
        return ctrlGrille;
    }

    /**
     * Accesseur de la grille
     * @return La grille
     */
    public Grille getGrille() {
        return grille;
    }

    /**
     * Methode qui test si il y a des combats de cellules
     */
    public void testCombat(){
        Integer x = grille.getX();
        Integer y = grille.getY();
        Cellule cellule1;
        Cellule cellule2;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cellule1 = CtrlAutomate.getInstance()
                                       .getAutomate(1)
                                       .getCellule(i, j);
                cellule2 = CtrlAutomate.getInstance()
                                       .getAutomate(2)
                                       .getCellule(i, j);
                if (cellule1.getEtat() != Etat.MORT && cellule2.getEtat() != Etat.MORT) {
                    Cellule res = CtrlAutomate.getInstance().combatCellule(cellule1, cellule2);
                    if (res == cellule2) {
                        cellule1.setEtat(Etat.MORT);
                    } else {
                        cellule2.setEtat(Etat.MORT);
                    }
                }
            }
        }
    }
    /**
     * Méthode qui actualise la grille par rapport aux automates
     */
    public void actualiserGrille() {
        testCombat();
        Integer x = grille.getX();
        Integer y = grille.getY();
        Cellule cellule1;
        Cellule cellule2;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                cellule1 = CtrlAutomate.getInstance()
                                       .getAutomate(1)
                                       .getCellule(i, j);
                cellule2 = CtrlAutomate.getInstance()
                                       .getAutomate(2)
                                       .getCellule(i, j);
                if (cellule1.getEtat() != Etat.MORT) {
                    grille.setTab(i, j, CtrlAutomate.getInstance()
                                                    .getAutomate(1)
                                                    .getId());
                } else if (cellule2.getEtat() != Etat.MORT) {
                    grille.setTab(i, j, CtrlAutomate.getInstance()
                                                    .getAutomate(2)
                                                    .getId());
                } else {
                    grille.setTab(i, j, 0);
                }
            }
        }
    }
    /**
     * Méthode qui crée une cellule hors grille par rapport à la méthode d'extension
     * @param x Coordonnée x de la cellule à crée
     * @param y Coordonnée y de la cellule à crée
     * @param id Identifiant de l'automate qui va crée la cellule
     * @return La cellule crée
     */
    public Cellule creerCelluleHorsGrille(Integer x, Integer y, Integer id) {
        return methodeExtension.creerCelluleHorsGrille(x, y, grille, id);
    }
}
