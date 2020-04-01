package automatecellulaire.controller;

import automatecellulaire.model.Cellule;
import automatecellulaire.model.Etat;
import automatecellulaire.model.Grille;

public class Symetrie implements MethodeExtension {


    /**
     * Méthode de création d'une cellule hors de la grille
     * @param x Position en x de la cellule à créer
     * @param y Position en x de la cellule à créer
     * @param grille La grille de jeu
     * @param id L'identifiant de l'automate
     * @return La cellule crée hors de la grille
     */
    @Override
    public Cellule creerCelluleHorsGrille(Integer x, Integer y, Grille grille, Integer id) {

        Integer xMax = grille.getX();
        Integer yMax = grille.getY();
        Cellule res = null;
        Integer[][] tab = grille.getTab();

        if (x < 0 && y < 0) {
            if (tab[0][0] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (x >= xMax && y >= yMax) {
            if (tab[xMax - 1][yMax - 1] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (x >= xMax && y < 0) {
            if (tab[xMax - 1][0] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (x < 0 && y >= yMax) {
            if (tab[0][yMax - 1] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (x < 0) {
            if (tab[0][y] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (y < 0) {
            if (tab[x][0] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (x >= xMax) {
            if (tab[xMax - 1][y] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        } else if (y >= yMax) {
            if (tab[x][yMax - 1] == id) {
                res = new Cellule(x, y, Etat.VIVANT);
            }
        }
        if (res == null) {
            res = new Cellule(x, y, Etat.MORT);
        }
        return res;
    }
}
