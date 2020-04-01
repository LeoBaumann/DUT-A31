package automatecellulaire.controller;

import automatecellulaire.model.Cellule;
import automatecellulaire.model.Grille;

public interface MethodeExtension {

    /**
     * Méthode de création d'une cellule hors de la grille
     * @param x Position en x de la cellule à créer
     * @param y Position en x de la cellule à créer
     * @param grille La grille de jeu
     * @param id L'identifiant de l'automate
     * @return La cellule crée hors de la grille
     */
    public Cellule creerCelluleHorsGrille(Integer x, Integer y, Grille grille, Integer id);
}
