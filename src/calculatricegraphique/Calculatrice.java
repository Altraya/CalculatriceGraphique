/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Karakayn
 */
public class Calculatrice extends JFrame implements KeyListener {

    private JPanel container = new JPanel();
    //Tableau stockant les éléments à afficher dans la calculatrice
    String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/","cos(","sin(","tan(","exp(","log(","hihi"};
    // Tableau avec les éléments permis par une écriture sur clavier
    String[] allowed_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "+", "-", "*", "/","cos(","sin(","tan(","exp(","log(","(",")","hihi"};
    // Signes
    String[] signs_string = {"+", "-", "*", "/", "."};
    //Un bouton par élément à afficher
    JButton[] tab_button = new JButton[tab_string.length];
    private JLabel ecran = new JLabel();
    private Dimension dim = new Dimension(50, 40);
    private Dimension dim2 = new Dimension(50, 31);
    private Dimension dim3 = new Dimension(60, 40);
    private double chiffre1;
    private double resultPrec = 0; //Sert quand on a deja fait un calcul et qu'on remet un operateur derrière pour continuer
    private boolean clicOperateur = false, update = false;
    private String operateur = "";
    private String expression = ""; //continent l'expression entiere entrée par l'utilisateur

    public Calculatrice() {
        this.setSize(300, 450);
        this.setTitle("Calculatrice graphique");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //On initialise le conteneur avec tous les composants
        initComposant();
        //On ajoute le conteneur
        this.setContentPane(container);
        this.setVisible(true);
        
        // On met le container comme listener de clavier
        container.addKeyListener(this);
        container.setFocusable(true);

    }
    private void initComposant() {
        
        
        //On définit la police d'écriture à utiliser
        Font police = new Font("Arial", Font.BOLD, 20);
        ecran = new JLabel("0");
        ecran.setFont(police);
        //On aligne les informations à droite dans le JLabel
        ecran.setHorizontalAlignment(JLabel.RIGHT);
        ecran.setPreferredSize(new Dimension(220, 20));
        JPanel operateur = new JPanel();
        operateur.setPreferredSize(new Dimension(55, 225));
        JPanel chiffre = new JPanel();
        chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel jEcr = new JPanel();
        jEcr.setPreferredSize(new Dimension(220, 30));
        JPanel trigo = new JPanel();
        trigo.setPreferredSize(new Dimension(165,225));

        //On parcourt le tableau initialisé
        //afin de créer nos boutons
        for (int i = 0; i < tab_string.length; i++) {
            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);
             // On veut que le focus soit sur la Window et pas sur le bouton
            tab_button[i].setFocusable(false);
            
            switch (i) {
                //Pour chaque élément situé à la fin du tableau
                //et qui n'est pas un chiffre
                //on définit le comportement à avoir grâce à un listener
                case 11:
                    tab_button[i].addActionListener(new EgalListener());
                    //tab_button[i].addActionListener(new ExprListener());
                    chiffre.add(tab_button[i]);
                    break;
                case 12:
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    operateur.add(tab_button[i]);
                    break;
                case 13:
                    //tab_button[i].addActionListener(new PlusListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 14:
                    //tab_button[i].addActionListener(new MoinsListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 15:
                    //tab_button[i].addActionListener(new MultiListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 16:
                    //tab_button[i].addActionListener(new DivListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 17:
                    //tab_button[i].addActionListener(new CosListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim3);
                    trigo.add(tab_button[i]);
                    break;
                case 18:
                    //tab_button[i].addActionListener(new SinListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim3);
                    trigo.add(tab_button[i]);
                    break;
                case 19:
                    //tab_button[i].addActionListener(new TanListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim3);
                    trigo.add(tab_button[i]);
                    break;
                case 20:
                    //tab_button[i].addActionListener(new ExpListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim3);
                    trigo.add(tab_button[i]);
                    break;
                case 21:
                    //tab_button[i].addActionListener(new LogListener());
                    tab_button[i].addActionListener(new ExprListener());
                    tab_button[i].setPreferredSize(dim3);
                    trigo.add(tab_button[i]);
                    break;
                case 22:
                    //tab_button[i].addActionListener(new PiListener());
                    tab_button[i].addActionListener(new HihiListener());
                    tab_button[i].setPreferredSize(dim3);
                    operateur.add(tab_button[i]);
                    break;
                default:
                    //Par défaut, ce sont les premiers éléments du tableau
                    //donc des chiffres, on affecte alors le bon listener
                    chiffre.add(tab_button[i]);
                    //tab_button[i].addActionListener(new ChiffreListener());
                    tab_button[i].addActionListener(new ExprListener());
                    break;
            }
        }
        jEcr.add(ecran);
        jEcr.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(jEcr, BorderLayout.NORTH);
        container.add(chiffre, BorderLayout.CENTER);
        container.add(operateur, BorderLayout.EAST);
        container.add(trigo,BorderLayout.WEST);
    }
    
    public String handleStringAdd(String in)
    {
        String previousText = ecran.getText();
        if(!previousText.isEmpty())
        {
            char lastChar = previousText.charAt(previousText.length()-1);
            String lastCharString = Character.toString(lastChar);
            // Avoid twice a sign
            if (Arrays.asList(signs_string).contains(lastCharString) && Arrays.asList(signs_string).contains(in))
                previousText = previousText.substring(0, previousText.length() - 1);
        }
        return previousText + in;
    }
    
    // Listener attribute for key press
    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyPressed(KeyEvent e) {
        String typedString = String.valueOf(e.getKeyChar());
        if (Arrays.asList(allowed_string).contains(typedString))
        {
            String str = handleStringAdd(typedString);
            if (update) {
                update = false;
                System.out.println("Passe update a false");
            } else if (ecran.getText().equals("0")) {
                str = String.valueOf(e.getKeyChar());
            }            
            System.out.println(String.valueOf(e.getKeyChar()));
            expression = str;
            ecran.setText(str);
        }
        else if (e.getKeyCode()==KeyEvent.VK_ENTER){
            clicOperateur = false;
            EXPR exp;
            System.out.println("Expression : " + expression);
            exp = Parser.on(expression);
            System.out.println("Result = " + exp.eval());
            resultPrec = exp.eval();
            ecran.setText("" + exp.eval());             
        } 
        else if (e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
            String prev = ecran.getText();
            prev = prev.substring(0, prev.length() - 1);
            expression = prev;
            ecran.setText(prev);      
        }        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    
    //Listener utilisé pour recevoir l'expression
    class ExprListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = ((JButton) e.getSource()).getText();
            if (update) {
                update = false;
                System.out.println("Passe update a false");
            } else if (!ecran.getText().equals("0")) {
                System.out.println("Set le texte ? +" + ecran.getText());
                str = handleStringAdd(str);
            }

            System.out.println("Dans expression listener");
            expression = str;
            System.out.println("expr : " + expression);
            ecran.setText(str);
        }
    }

    //Listener affecté au bouton =
    class EgalListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            clicOperateur = false;
            EXPR e;
            System.out.println("Expression : " + expression);
            e = Parser.on(expression);
            System.out.println("Result = " + e.eval());
            resultPrec = e.eval();
            ecran.setText("" + e.eval());
        }
    }
    
        //Listener affecté au bouton Hihi
    class HihiListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            playSound("hihi.wav");
        }
    }

    //Listener affecté au bouton de remise à zéro
    class ResetListener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            ecran.setText("");
        }
    }
    
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
        // The wrapper thread is unnecessary, unless it blocks on the
        // Clip finishing; see comments.
          public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                Main.class.getResourceAsStream("music/" + url));
                clip.open(inputStream);
                clip.start(); 
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
          }
        }).start();
    }
}
