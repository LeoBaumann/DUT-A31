package automatecellulaire.model;

import java.util.ArrayList;

public interface RegleEvolution<Etat> {

    /**
     * M�thode qui permet de r�cuperer l'�tat suivant d'une cellule
     * @param etat Etat de la cellule � �voluer
     * @param etats Etat des cellules voisines � la cellule � �voluer
     * @return Etat de la cellule � t+1
     */
    Etat evoluer(Etat etat, ArrayList<Etat> etats);

    /**
     * Accesseur de la regle de voisinage
     * @return La regle de voisinage
     */
    RegleVoisinage getRegleVoisinage();
}
