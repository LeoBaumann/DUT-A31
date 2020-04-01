package automatecellulaire.model;

import java.util.ArrayList;

public class JeuDeLaVie implements RegleEvolution {

    /**
     * @associates <{automatecellulaire.model.RegleVoisinage}>
     */
    private RegleVoisinage regleVoisinage = new Moore();

    /**
     * Méthode qui permet de récuperer l'état suivant d'une cellule
     * @param etatCellule Etat de la cellule à évoluer
     * @param etats Etat des cellules voisines à la cellule à évoluer
     * @return Etat de la cellule à t+1
     */
    @Override
    public Object evoluer(Object etatCellule, ArrayList etats) {

        Integer nbVoisinVivant = 0;
        Etat etatSuivant = null;

        for (Object etat : etats) {
            if (etat != Etat.MORT) {
                nbVoisinVivant++;
            }
        }
        if (nbVoisinVivant == 3) {
            etatSuivant = Etat.VIVANT;
        } else if (nbVoisinVivant == 2) {
            etatSuivant = (Etat) etatCellule;
        } else {
            etatSuivant = Etat.MORT;
        }
        return etatSuivant;
    }

    /**
     * Accesseur de la regle de voisinage
     * @return La regle de voisinage
     */
    public RegleVoisinage getRegleVoisinage() {
        return this.regleVoisinage;
    }
}
