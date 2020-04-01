package automatecellulaire.model;

import java.util.ArrayList;

public interface RegleEvolution<Etat> {

    /**
     * Méthode qui permet de récuperer l'état suivant d'une cellule
     * @param etat Etat de la cellule à évoluer
     * @param etats Etat des cellules voisines à la cellule à évoluer
     * @return Etat de la cellule à t+1
     */
    Etat evoluer(Etat etat, ArrayList<Etat> etats);

    /**
     * Accesseur de la regle de voisinage
     * @return La regle de voisinage
     */
    RegleVoisinage getRegleVoisinage();
}
