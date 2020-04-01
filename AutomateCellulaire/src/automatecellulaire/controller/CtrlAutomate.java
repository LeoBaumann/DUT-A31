package automatecellulaire.controller;

import automatecellulaire.model.Automate;

import automatecellulaire.model.Cellule;
import automatecellulaire.model.Etat;

import automatecellulaire.model.TypeRegleEvolution;

import java.util.ArrayList;
import java.util.Random;

public class CtrlAutomate {

    /**
     * @associates <{automatecellulaire.controller.CtrlAutomate}>
     */
    private static CtrlAutomate ctrlAutomate;

    /**
     * @associates <{automatecellulaire.model.Automate}>
     */
    private ArrayList<Automate> automates = new ArrayList<Automate>();

    /**
     * Contructeur de Ctrl Automate
     */
    private CtrlAutomate() {
    }

    /**
     * Accesseur de automate par rapport � l'identifiant
     * @param id Identifiant de l'automate
     * @return Automate
     */
    public Automate getAutomate(Integer id) {
        return automates.get(id - 1);
    }

    /**
     * M�thode de cr�ation d'un automate
     * @param id L'identifiant affect� � l'automate lors de sa cr�ation
     * @param regleEvolution La r�gle d'�volution affect� � l'automate lors de sa cr�ation
     */
    public void creerAutomate(Integer id, Object regleEvolution) {
        automates.add(new Automate(id, (TypeRegleEvolution) regleEvolution));
    }

    /**
     * Accesseur de l'instance de CtrlAutomate
     * @return L'instance de CtrlAutomate
     */
    public static CtrlAutomate getInstance() {
        if (ctrlAutomate == null)
            ctrlAutomate = new CtrlAutomate();
        return ctrlAutomate;
    }

    /**
     * M�thode de cr�ation d'une cellule
     * @param x Position x de la cellule
     * @param y Position y de la cellule
     * @param etat Etat de la cellule
     * @param id Identifiant de l'automate qui va cr�e la cellule
     */
    public void creerCellule(Integer x, Integer y, Etat etat, Integer id) {
        getAutomate(id).creerCellule(x, y, etat);
    }

    /**
     * M�thode qui permet de faire evoluer un automate (evoluer toutes ses cellules)
     * @param id L'identifiant de l'automate
     */
    public void evoluerAutomate(Integer id) {
        Automate automate = getAutomate(id);
        ArrayList<Cellule> listeCelluleSuivant = new ArrayList<Cellule>();
        Etat etat;
        Integer nb = 0;
        for (Cellule cellule : automate.getListeCellule()) {
            nb++;
            etat = automate.evoluerCellule(cellule);
            Cellule nCellule = new Cellule(cellule.getX(), cellule.getY(), etat);
            listeCelluleSuivant.add(nCellule);
        }
        automate.setListeCellule(listeCelluleSuivant);
    }

    /**
     * M�thode qui fait combattre 2 cellules
     * @param cellule1 La premi�re cellule
     * @param cellule2 La deuxi�me cellule
     * @return La cellule qui a gagn� le combat
     */
    public Cellule combatCellule(Cellule cellule1, Cellule cellule2) {
        Cellule res;
        Random rand = new Random();
        Integer nombreAleatoire = rand.nextInt(2);
        if (nombreAleatoire == 0) {
            res = cellule1;
        } else {
            res = cellule2;
        }
        return res;
    }
}
