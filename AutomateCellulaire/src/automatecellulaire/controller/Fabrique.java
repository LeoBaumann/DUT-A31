package automatecellulaire.controller;

import automatecellulaire.model.Fredkin;
import automatecellulaire.model.JeuDeLaVie;
import automatecellulaire.model.RegleEvolution;
import automatecellulaire.model.TypeRegleEvolution;

//DP Fabrique parametrée
public class Fabrique {
    
    /**
     * Methode qui creer une Methode d'extension
     * @param t le type de methode d'extension à crée
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
     * Methode qui creer une Regle d'évolution
     * @param t le type de règle d'évolution à crée
     * @return p : la règle d'évolution
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
