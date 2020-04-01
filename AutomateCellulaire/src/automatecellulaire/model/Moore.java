package automatecellulaire.model;

import java.util.ArrayList;

public class Moore implements RegleVoisinage {

    /**
     * Méthode qui permet de récuperer l'état des voisins de la cellule aux positions x et y
     * @param x Position x de la cellule dont on cherche les voisins
     * @param y Position y de la cellule dont on cherche les voisins
     * @param automate Automate qui possède la cellule à évoluer
     * @return La liste des états des voisins
     */
    @Override
    public ArrayList<Etat> trouverEtatVoisin(Integer x, Integer y, Automate automate) {
        ArrayList<Etat> listeEtatVoisins = new ArrayList<Etat>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Cellule celluleVoisine = automate.getCellule(x + i, y + j);
                if ((i != 0 || j != 0)) {
                    listeEtatVoisins.add(celluleVoisine.getEtat());
                }
            }
        }
        return listeEtatVoisins;
    }
}
