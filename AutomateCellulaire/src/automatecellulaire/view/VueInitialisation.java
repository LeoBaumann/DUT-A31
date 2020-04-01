package automatecellulaire.view;

import automatecellulaire.model.TypeRegleEvolution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueInitialisation extends JFrame {

    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de VueGrille
     * @param x Taille en abscisse de la grille
     * @param y Taille en ordonnée de la grille
     * @param methodeExpension La méthode d'expension
     * @param nbCelluleInitial Le nombre de cellule initial pour chaque joueur
     * @param nbIteration Le nombre d'itération maximal
     * @param randIdJoueur L'identifiant du joueur qui va commencer à placer ses cellules
     */
    @SuppressWarnings("unchecked")
    public VueInitialisation(Integer x, Integer y, Object methodeExpension, Integer nbCelluleInitial,
                             Integer nbIteration, Integer randIdJoueur) {

        this.setSize(400, 130);
        this.setTitle("Phase d'initialisation");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelEst = new JPanel(new GridLayout(3, 2));
        JPanel panelOuest = new JPanel(new GridLayout(3, 2));
        JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Panel Est
        JLabel label1 = new JLabel("Joueur 1");
        panelOuest.add(label1);
        JButton button1 = new JButton();
        button1.setBackground(Color.blue);
        button1.setEnabled(false);
        panelOuest.add(button1);

        JLabel label2 = new JLabel("Type d'automate");
        panelOuest.add(label2);
        JComboBox combo1 = new JComboBox();
        for (TypeRegleEvolution t : TypeRegleEvolution.values()) {
            combo1.addItem(t);
        }
        panelOuest.add(combo1, new FlowLayout(FlowLayout.CENTER));

        //Panel Ouest
        JLabel label5 = new JLabel("Joueur 2");
        panelEst.add(label5);
        JButton button2 = new JButton();
        button2.setBackground(Color.red);
        button2.setEnabled(false);
        panelEst.add(button2);

        JLabel label6 = new JLabel("Type d'automate");
        panelEst.add(label6);
        JComboBox combo2 = new JComboBox();
        for (TypeRegleEvolution t : TypeRegleEvolution.values()) {
            combo2.addItem(t);
        }
        panelEst.add(combo2, new FlowLayout(FlowLayout.CENTER));

        //Panel Sud
        JButton button3 = new JButton("Valider");
        panelSud.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actualiserComboBox(combo1, combo2, randIdJoueur)) {
                    lancerGrille(x, y, methodeExpension, nbCelluleInitial, nbIteration, combo1.getSelectedItem(),
                                 combo2.getSelectedItem(), randIdJoueur);
                }
            }
        });


        if (randIdJoueur == 1) {
            combo2.setEnabled(false);
        } else {
            combo1.setEnabled(false);
        }

        this.add(panelEst, BorderLayout.EAST);
        this.add(panelOuest, BorderLayout.WEST);
        this.add(panelSud, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    /**
     * Méthode qui permet d'actualiser les ComboBox
     * @param combo1
     * @param combo2
     */
    @SuppressWarnings("unchecked")
    public boolean actualiserComboBox(JComboBox combo1, JComboBox combo2, Integer id) {
        boolean res = false;
        if (id == 1) {
            if (combo2.isEnabled()) {
                res = true;
            } else {
                combo1.setEnabled(false);
                combo2.setEnabled(true);
                combo2.removeAllItems();
                for (TypeRegleEvolution t : TypeRegleEvolution.values()) {
                    if (combo1.getSelectedItem() != t) {
                        combo2.addItem(t);
                    }
                }
            }
        } else {
            if (combo1.isEnabled()) {
                res = true;
            } else {
                combo2.setEnabled(false);
                combo1.setEnabled(true);
                combo1.removeAllItems();
                for (TypeRegleEvolution t : TypeRegleEvolution.values()) {
                    if (combo2.getSelectedItem() != t) {
                        combo1.addItem(t);
                    }
                }
            }
        }
        return res;
    }

    /**
     * Méthode qui permet de lancer VueGrille
     * @param x Taille en abscisse de la grille
     * @param y Taille en ordonnée de la grille
     * @param methodeExpension La méthode d'expension
     * @param nbCelluleInitial Le nombre de cellule initial pour chaque joueur
     * @param nbIteration Le nombre d'itération maximal
     * @param automateJr1 Le type d'automate du joueur 1
     * @param automateJr2 Le type d'automate du joueur 2
     * @param nombreAleatoire L'identifiant du premier joueur à choisir ces cellules
     */
    public void lancerGrille(Integer x, Integer y, Object methodeExpension, Integer nbCelluleInitial,
                             Integer nbIteration, Object automateJr1, Object automateJr2, Integer nombreAleatoire) {
        this.setVisible(false);
        if (nombreAleatoire == 1) {
            nombreAleatoire = 2;
        } else {
            nombreAleatoire = 1;
        }
        new VueGrille(x, y, methodeExpension, nbCelluleInitial, nbIteration, automateJr1, automateJr2, nombreAleatoire);
    }
}
