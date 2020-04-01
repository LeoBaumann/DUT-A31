package automatecellulaire.model;

import java.util.ArrayList;

public interface RegleVoisinage {

    /**
     * M�thode qui permet de r�cuperer l'�tat des voisins de la cellule aux positions x et y
     * @param x Position x de la cellule dont on cherche les voisins
     * @param y Position y de la cellule dont on cherche les voisins
     * @param automate Automate qui poss�de la cellule � �voluer
     * @return La liste des �tats des voisins
     */
    ArrayList<Etat> trouverEtatVoisin(Integer x, Integer y, Automate automate);
}
