package automatecellulaire.model;

import java.util.ArrayList;

public interface RegleVoisinage {

    /**
     * Méthode qui permet de récuperer l'état des voisins de la cellule aux positions x et y
     * @param x Position x de la cellule dont on cherche les voisins
     * @param y Position y de la cellule dont on cherche les voisins
     * @param automate Automate qui possède la cellule à évoluer
     * @return La liste des états des voisins
     */
    ArrayList<Etat> trouverEtatVoisin(Integer x, Integer y, Automate automate);
}
