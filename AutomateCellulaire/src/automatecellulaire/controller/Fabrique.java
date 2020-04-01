package automatecellulaire.controller;

import automatecellulaire.model.Fredkin;
import automatecellulaire.model.JeuDeLaVie;
import automatecellulaire.model.RegleEvolution;
import automatecellulaire.model.TypeRegleEvolution;

//DP Fabrique parametr�e
public class Fabrique {
    
    /**
     * Methode qui creer une Methode d'extension
     * @param t le type de methode d'extension � cr�e
     * @return p : la methode d'extension
     */
    public static MethodeExtension creerMethodeExtension(TypeMethodeExtension t){
        MethodeExtension p=null;
        switch(t)
        {
        case CONSTANTE:
            p = new Constante();
            break;
        case PERIODICITE:
            p = new Periodicite();
            break;
        case SYMETRIE:
            p = new Symetrie();
            break;
        }
        return p;
    }
    
    /**
     * Methode qui creer une Regle d'�volution
     * @param t le type de r�gle d'�volution � cr�e
     * @return p : la r�gle d'�volution
     */
    public static RegleEvolution creerRegleEvolution(TypeRegleEvolution t){
        RegleEvolution p=null;
        switch(t)
        {
        case JEUDELAVIE:
            p = new JeuDeLaVie();
            break;
        case FREDKIN:
            p = new Fredkin();
            break;
        }
        return p;
    }
}
