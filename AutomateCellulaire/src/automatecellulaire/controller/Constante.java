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
     * Méthode de création d'une cellule hors de la grille
     * @param x Position en x de la cellule à créer
     * @param y Position en x de la cellule à créer
     * @param grille La grille de jeu
     * @param id L'identifiant de l'automate
     * @return La cellule crée hors de la grille
     */
    @Override
    public Cellule creerCelluleHorsGrille(Integer x, Integer y, Grille grille, Integer id) {
        return new Cellule(x, y, constante);
    }
}
