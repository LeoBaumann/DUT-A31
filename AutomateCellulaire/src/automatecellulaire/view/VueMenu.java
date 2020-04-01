package automatecellulaire.view;

import java.awt.BorderLayout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

import automatecellulaire.controller.TypeMethodeExtension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VueMenu extends JFrame {

    /**
     * @attribute
     */
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de VueMenu
     */
    @SuppressWarnings("unchecked")
    public VueMenu() {

        this.setSize(350, 160);
        this.setTitle("Menu");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panelNord = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelCentre = new JPanel(new GridLayout(3, 2));
        JPanel panelSud = new JPanel(new FlowLayout(FlowLayout.CENTER));

        // Panel Nord
        // Dimensions de la grille
        JLabel label1 = new JLabel("Dimensions de la grille :");
        panelNord.add(label1);

        JSpinner spinnerX = new JSpinner(new SpinnerNumberModel(10, 2, 54, 1));
        ((JSpinner.DefaultEditor) spinnerX.getEditor()).getTextField().setEditable(false);
        panelNord.add(spinnerX);

        JSpinner spinnerY = new JSpinner(new SpinnerNumberModel(10, 2, 54, 1));
        ((JSpinner.DefaultEditor) spinnerY.getEditor()).getTextField().setEditable(false);
        panelNord.add(spinnerY);

        // Panel Centre
        // Methode d'extension
        JLabel label2 = new JLabel("Méthode d'extension :");
        panelCentre.add(label2);

        JComboBox combo = new JComboBox();
        for (TypeMethodeExtension t : TypeMethodeExtension.values()) {
            combo.addItem(t);
        }
        panelCentre.add(combo, new FlowLayout(FlowLayout.CENTER));

        // Nombre initial de cellules
        JLabel label3 = new JLabel("Nb initial de cellules :");
        panelCentre.add(label3, new FlowLayout(FlowLayout.CENTER));

        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(5, 0, 10000, 1));
        ((JSpinner.DefaultEditor) spinner1.getEditor()).getTextField().setEditable(false);
        panelCentre.add(spinner1, new FlowLayout(FlowLayout.CENTER));

        // Nombre d'itération
        JLabel label4 = new JLabel("Nb d'itération maximum :");
        panelCentre.add(label4, new FlowLayout(FlowLayout.CENTER));

        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(20, 0, 10000, 1));
        ((JSpinner.DefaultEditor) spinner2.getEditor()).getTextField().setEditable(false);
        panelCentre.add(spinner2, new FlowLayout(FlowLayout.CENTER));

        // Panel Sud
        // Bouton
        JButton buttonLancer = new JButton("Lancer");
        panelSud.add(buttonLancer);
        buttonLancer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lancerVueInitialisation((Integer) spinnerX.getValue(), (Integer) spinnerY.getValue(),
                                        combo.getSelectedItem(), (Integer) spinner1.getValue(),
                                        (Integer) spinner2.getValue());

            }
        });

        this.add(panelNord, BorderLayout.NORTH);
        this.add(panelCentre, BorderLayout.CENTER);
        this.add(panelSud, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    /**
     * Méthode qui lance VueInitialisation
     * @param x Taille en abscisse de la grille
     * @param y Taille en ordonnée de la grille
     * @param methodeExtension La méthode d'extension
     * @param nbCelluleInitial Le nombre de cellule initial pour chaque joueur
     * @param nbIteration Le nombre d'itération maximal
     */
    public void lancerVueInitialisation(Integer x, Integer y, Object methodeExtension, Integer nbCelluleInitial,
                                        Integer nbIteration) {
        this.setVisible(false);
        Random rand = new Random();
        Integer nombreAleatoire = rand.nextInt(2) + 1;
        new VueInitialisation(x, y, methodeExtension, nbCelluleInitial, nbIteration, nombreAleatoire);
    }
}
