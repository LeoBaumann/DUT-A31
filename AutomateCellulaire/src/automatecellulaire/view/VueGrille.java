package automatecellulaire.view;

import automatecellulaire.controller.CtrlAutomate;
import automatecellulaire.controller.CtrlGrille;

import automatecellulaire.controller.TypeMethodeExtension;

import automatecellulaire.model.Etat;

import automatecellulaire.utils.IObservateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VueGrille extends JFrame implements IObservateur {

    /**
     * @attribute
     */
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = 1L;

    /**
     * @attribute
     */
    private Integer iteration = 0;

    /**
     * @attribute
     */
    private ArrayList<JButton> listeButton = new ArrayList<JButton>();

    /**
     * @attribute
     */
    private Integer idJoueur;

    /**
     * @attribute
     */
    private Integer cellulesRestantes1;

    /**
     * @attribute
     */
    private Integer cellulesRestantes2;

    /**
     * @attribute
     */
    private String vainqueur;

    /**
     * Constructeur de VueGrille
     * @param x Taille en abscisse de la grille
     * @param y Taille en ordonnée de la grille
     * @param methodeExtension La méthode d'extension
     * @param nbCelluleInitial Le nombre de cellule initial pour chaque joueur
     * @param nbIteration Le nombre d'itération maximal
     * @param automateJr1 Le type d'automate du joueur 1
     * @param automateJr2 Le type d'automate du joueur 2
     * @param randIdJoueur L'identifiant du joueur qui va commencer à placer ses cellules
     */
    public VueGrille(Integer x, Integer y, Object methodeExtension, Integer nbCelluleInitial, Integer nbIteration,
                     Object automateJr1, Object automateJr2, Integer randIdJoueur) {

        this.idJoueur = randIdJoueur;
        Integer iterationMax = nbIteration;

        cellulesRestantes1 = nbCelluleInitial;
        cellulesRestantes2 = nbCelluleInitial;

        this.setSize(x * 50, y * 30);
        this.setTitle("Automate Cellulaire");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelGrille = new JPanel(new GridLayout(y, x));
        JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Panel Sud
        JLabel label1 = new JLabel("Nombre d'itérations :");
        panelSud.add(label1);
        JLabel label2 = new JLabel("0");
        panelSud.add(label2);
        JButton button1 = new JButton("Lancer le jeu");
        button1.setVisible(false);
        panelSud.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                evenementValider(label2, button1, iterationMax);
            }
        });

        //Panel Nord
        JLabel label3 = new JLabel("Joueur ");
        panelNord.add(label3);
        JLabel label4 = new JLabel();
        if (randIdJoueur == 1) {
            label4.setText("1 :  " + cellulesRestantes1);
        } else {
            label4.setText("2 :  " + cellulesRestantes2);
        }
        panelNord.add(label4);
        JLabel label5 = new JLabel(" cellules restantes à placer");
        panelNord.add(label5);

        //Panel Grille
        for (int i = 0; i < x * y; i++) {
            JButton button = new JButton();
            button.setBackground(Color.white);
            panelGrille.add(button);
            listeButton.add(button);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cellulesRestantes1 + cellulesRestantes2 == 0) {
                        panelNord.setVisible(false);
                    } else {
                        changerCouleur(button, button1, idJoueur, label4, panelNord);
                    }
                }
            });
        }
        CtrlGrille.getInstance().creerGrille(x, y, (TypeMethodeExtension) methodeExtension);
        CtrlGrille.getInstance()
                  .getGrille()
                  .ajouterObs(this);
        CtrlAutomate.getInstance().creerAutomate(1, automateJr1);
        CtrlAutomate.getInstance().creerAutomate(2, automateJr2);


        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelGrille, BorderLayout.CENTER);
        this.add(panelSud, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Méthode qui permet de changer la couleur du bouton selectionné
     * @param button Button selectionner
     * @param button1 Button de validation
     * @param idJoueur L'identifiant du joueur qui a activé le bouton
     * @param label Label qui indique le nombre de cellule restante
     * @param panelNord La panel qui permet d'indique le nombre de cellule restante
     */
    public void changerCouleur(JButton button, JButton button1, Integer idJoueur, JLabel label, JPanel panelNord) {

        Integer x = button.getX();
        Integer y = button.getY();
        Double height = button.getSize().getHeight();
        Double width = button.getSize().getWidth();

        if (idJoueur == 1) {
            if (button.getBackground() == Color.white) {
                button.setBackground(Color.blue);
                this.idJoueur = 2;
                cellulesRestantes1--;
                label.setText("2 :  " + cellulesRestantes2);
            }
        } else {
            if (button.getBackground() == Color.white) {
                button.setBackground(Color.red);
                this.idJoueur = 1;
                cellulesRestantes2--;
                label.setText("1 :  " + cellulesRestantes1);
            }
        }
        CtrlAutomate.getInstance().creerCellule(x / width.intValue(), y / height.intValue(), Etat.VIVANT, idJoueur);

        if (cellulesRestantes1 + cellulesRestantes2 == 0) {
            panelNord.setVisible(false);
            button1.setVisible(true);
        }
    }

    /**
     * Méthode correspondante à l'évenement du bouton valider
     * @param label Label d'affichage de l'itération actuelle
     * @param button Bouton sur lequel l'utilisateur à appuyé
     * @param iterationMax Le nombre d'itération max de l'application
     */
    public void evenementValider(JLabel label, JButton button, Integer iterationMax) {
        new Thread(new Runnable() {
            public void run() {
                button.setVisible(false);
                while (!testFinJeu(iterationMax)) {
                    try {
                        Thread.sleep(1000);
                        CtrlAutomate.getInstance().evoluerAutomate(1);
                        CtrlAutomate.getInstance().evoluerAutomate(2);
                        CtrlGrille.getInstance().actualiserGrille();

                        iteration++;
                        label.setText(iteration.toString());
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
                
            }
        }).start();
    }

    /**
     * Méthode qui permet d'actualiser les couleurs de la grille (Boutons)
     */
    public void actualiserBouton() {
        Integer[][] tab = CtrlGrille.getInstance()
                                    .getGrille()
                                    .getTab();

        Integer i, j;
        Double height, width;

        for (JButton button : listeButton) {
            height = button.getSize().getHeight();
            width = button.getSize().getWidth();
            i = (Integer) button.getX() / width.intValue();
            j = (Integer) button.getY() / height.intValue();
            if (tab[i][j] == 0) {
                button.setBackground(Color.white);
            } else if (tab[i][j] == 1) {
                button.setBackground(Color.blue);
            } else if (tab[i][j] == 2) {
                button.setBackground(Color.red);
            }
        }

    }

    /**
     * Méthode qui permet de tester si la partie est finie
     * @param iterationMax Le nombre d'itération maximale de la partie
     * @return True si la partie est fini, False sinon
     */
    public boolean testFinJeu(Integer iterationMax) {

        boolean res = false;
        Integer nbCellulesJr1 = CtrlAutomate.getInstance()
                                            .getAutomate(1)
                                            .getNbCelluleVivant();
        Integer nbCellulesJr2 = CtrlAutomate.getInstance()
                                            .getAutomate(2)
                                            .getNbCelluleVivant();
        if (iteration >= iterationMax) {
            res = true;
            if (nbCellulesJr1 < nbCellulesJr2) {
                vainqueur = "Joueur 2 vainqueur";
            } else if (nbCellulesJr1 > nbCellulesJr2) {
                vainqueur = "Joueur 1 vainqueur";
            } else {
                vainqueur = "Joueur 1 et 2 vainqueur";
            }
        } else {
            if (nbCellulesJr1 == 0 && nbCellulesJr2 == 0) {
                res = true;
                vainqueur = "Joueur 1 et 2 vainqueur";
            } else if (nbCellulesJr1 == 0) {
                res = true;
                vainqueur = "Joueur 2 vainqueur";
            } else if (nbCellulesJr2 == 0) {
                res = true;
                vainqueur = "Joueur 1 vainqueur";
            }
        }
        if(vainqueur != null){
            JOptionPane.showMessageDialog(this,vainqueur, "Fin de la partie !", JOptionPane.INFORMATION_MESSAGE);
        }
        return res;
    }

    @Override
    public void miseAJour() {
        actualiserBouton();
    }
}
