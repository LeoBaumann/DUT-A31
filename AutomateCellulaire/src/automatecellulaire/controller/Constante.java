package automatecellulaire.controller;

import automatecellulaire.model.Cellule;
import automatecellulaire.model.Etat;
import automatecellulaire.model.Grille;

public class Constante implements MethodeExtension {

    /**
     * @attribute
     */
    private Etat constante = Etat.MORT;

    /**
     * M�thode de cr�ation d'une cellule hors de la grille
     * @param x Position en x de la cellule � cr�er
     * @param y Position en x de la cellule � cr�er
     * @param grille La grille de jeu
     * @param id L'identifiant de l'automate
     * @return La cellule cr�e hors de la grille
     */
    @Override
    public Cellule creerCelluleHorsGrille(Integer x, Integer y, Grille grille, Integer id) {
        return new Cellule(x, y, constante);
    }
}
