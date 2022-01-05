package sk.uniza.fri;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
//import java.util.Scanner;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
//import javax.swing.JComponent;
//import java.util.Timer;
//import java.util.TimerTask;


import static java.awt.BorderLayout.NORTH;

/**
 * 24.11.2021 - 9:23
 *
 * @author Andrej
 */
public class Frame extends JFrame implements ActionListener {

    private Random random = new Random();
    private JFrame frame = new JFrame();

    private JPanel title = new JPanel();
    private JPanel tlacitkovy = new JPanel();
    private JPanel footer = new JPanel();

    private JLabel textfield = new JLabel();

    private JButton[] tlacitka = new JButton[9];
    private JButton reset = new JButton();
    //private Timer timer = new Timer();
    private boolean hrac1Pohyb;

    private int vyhral = 0;

    private ZapisSuboru zapis = new ZapisSuboru();


    // private Stav prazdne = Stav.PRAZDNE;
    // private Stav xove = Stav.XOVE;
    // private Stav ockove = Stav.OCKOVE;

    Frame() {

        this.frame.setTitle("PiskvorkyGrafika");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(500, 500);
        this.frame.setLayout(new BorderLayout());
        this.frame.setVisible(true);

        this.title.setBackground(Color.GRAY);
        this.title.setPreferredSize(new Dimension(500, 100));
        this.title.setLayout(new BorderLayout());

        this.footer.setBackground(Color.green);
        this.footer.setPreferredSize(new Dimension(500, 50));

        this.textfield.setBackground(Color.blue);
        this.textfield.setForeground(Color.white);
        this.textfield.setHorizontalAlignment(JLabel.CENTER);
        this.textfield.setText("Hru spustíš kliknutím na políčko");
        this.textfield.setOpaque(true);

        this.tlacitkovy.setLayout(new GridLayout(3, 3));
        this.tlacitkovy.setBackground(new Color(150, 150, 25));

        this.reset.setText("RESETOVANIE");
        this.reset.addActionListener(this);

        for (int i = 0; i < 9; i++) {
            this.tlacitka[i] = new JButton();
            this.tlacitkovy.add(this.tlacitka[i]);
            this.tlacitka[i].setFocusable(false);
            this.tlacitka[i].addActionListener(this);
        }

        this.title.add(this.textfield);

        this.frame.add(this.title, NORTH);
        this.frame.add(this.tlacitkovy);
        this.frame.add(this.footer, BorderLayout.SOUTH);

        this.footer.add(this.reset, BorderLayout.CENTER);

        this.prvyTah();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.reset) {

            this.reset();
        }

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == this.tlacitka[i]) {
                if (this.hrac1Pohyb) {
                    if (this.tlacitka[i].getText().equals("")) {
                        this.tlacitka[i].setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
                        this.tlacitka[i].setForeground(new Color(255, 0, 0));
                        this.tlacitka[i].setText("X");
                        this.hrac1Pohyb = false;//
                        this.textfield.setText("O tah");
                        if (!this.kontrola()) {
                            this.kontrolaRemizy();
                        }
                    }
                } else {
                    if (this.tlacitka[i].getText().equals("")) {
                        this.tlacitka[i].setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
                        this.tlacitka[i].setForeground(new Color(0, 0, 255));
                        this.tlacitka[i].setText("O");
                        this.hrac1Pohyb = true;
                        this.textfield.setText("X tah");//
                        if (!this.kontrola()) {
                            this.kontrolaRemizy();
                        }
                    }
                }
            }
        }

    }

    public void prvyTah() {
        if (this.random.nextInt(2) == 0) {
            this.hrac1Pohyb = true;
            this.textfield.setText("X na ťahu");
        } else {
            this.hrac1Pohyb = false;
            this.textfield.setText("O na ťahu");
        }
    }

    public boolean kontrola() {
        //X
        if (
                (this.tlacitka[0].getText().equals("X")) &&
                        (this.tlacitka[1].getText().equals(("X"))) &&
                        (this.tlacitka[2].getText().equals("X"))
        ) {
            this.vyhraX(0, 1, 2);
            return true;
        }

        if (
                (this.tlacitka[3].getText().equals("X")) &&
                        (this.tlacitka[4].getText().equals("X")) &&
                        (this.tlacitka[5].getText().equals("X"))
        ) {
            this.vyhraX(3, 4, 5);
            return true;
        }

        if (
                (this.tlacitka[6].getText().equals("X")) &&
                        (this.tlacitka[7].getText().equals("X")) &&
                        (this.tlacitka[8].getText().equals("X"))
        ) {
            this.vyhraX(6, 7, 8);
            return true;
        }

        if (
                (this.tlacitka[0].getText().equals("X")) &&
                        (this.tlacitka[3].getText().equals("X")) &&
                        (this.tlacitka[6].getText().equals("X"))
        ) {
            this.vyhraX(0, 3, 6);
            return true;
        }

        if (
                (this.tlacitka[1].getText().equals("X")) &&
                        (this.tlacitka[4].getText().equals("X")) &&
                        (this.tlacitka[7].getText().equals("X"))
        ) {
            this.vyhraX(1, 4, 7);
            return true;
        }

        if (
                (this.tlacitka[2].getText().equals("X")) &&
                        (this.tlacitka[5].getText().equals("X")) &&
                        (this.tlacitka[8].getText().equals("X"))
        ) {
            this.vyhraX(2, 5, 8);
            return true;
        }

        if (
                (this.tlacitka[0].getText().equals("X")) &&
                        (this.tlacitka[4].getText().equals("X")) &&
                        (this.tlacitka[8].getText().equals("X"))
        ) {
            this.vyhraX(0, 4, 8);
            return true;
        }

        if (
                (this.tlacitka[2].getText().equals("X")) &&
                        (this.tlacitka[4].getText().equals("X")) &&
                        (this.tlacitka[6].getText().equals("X"))
        ) {
            this.vyhraX(2, 4, 6);
            return true;

        }

        //O
        if (
                (this.tlacitka[0].getText().equals("O")) &&
                        (this.tlacitka[1].getText().equals("O")) &&
                        (this.tlacitka[2].getText().equals("O"))
        ) {
            this.vyhraO(0, 1, 2);
            return true;
        }

        if (
                (this.tlacitka[3].getText().equals("O")) &&
                        (this.tlacitka[4].getText().equals("O")) &&
                        (this.tlacitka[5].getText().equals("O"))
        ) {
            this.vyhraO(3, 4, 5);
            return true;
        }

        if (
                (this.tlacitka[6].getText().equals("O")) &&
                        (this.tlacitka[7].getText().equals("O")) &&
                        (this.tlacitka[8].getText().equals("O"))
        ) {
            this.vyhraO(6, 7, 8);
            return true;
        }

        if (
                (this.tlacitka[0].getText().equals("O")) &&
                        (this.tlacitka[3].getText().equals("O")) &&
                        (this.tlacitka[6].getText().equals("O"))
        ) {
            this.vyhraO(0, 3, 6);
            return true;
        }

        if (
                (this.tlacitka[1].getText().equals("O")) &&
                        (this.tlacitka[4].getText().equals("O")) &&
                        (this.tlacitka[7].getText().equals("O"))
        ) {
            this.vyhraO(1, 4, 7);
            return true;
        }

        if (
                (this.tlacitka[2].getText().equals("O")) &&
                        (this.tlacitka[5].getText().equals("O")) &&
                        (this.tlacitka[8].getText().equals("O"))
        ) {
            this.vyhraO(2, 5, 8);
            return true;
        }

        if (
                (this.tlacitka[0].getText().equals("O")) &&
                        (this.tlacitka[4].getText().equals("O")) &&
                        (this.tlacitka[8].getText().equals("O"))
        ) {
            this.vyhraO(0, 4, 8);
            return true;
        }

        if (
                (this.tlacitka[2].getText().equals("O")) &&
                        (this.tlacitka[4].getText().equals("O")) &&
                        (this.tlacitka[6].getText().equals("O"))
        ) {
            this.vyhraO(2, 4, 6);
            return true;
        }
        return false;
    }

    public void kontrolaRemizy() {
        int plnePolicko = 0;
        for (int x = 0; x < 9; x++) {
            if (!this.tlacitka[x].getText().equals("")) {
                plnePolicko++;
            }
        }
        if (plnePolicko == 9) {
            this.remiza();
        }
    }

    public void vyhraX(int a, int b, int c) {
        this.vyhral = 5;
        this.tlacitka[a].setBackground(Color.green);
        this.tlacitka[b].setBackground(Color.green);
        this.tlacitka[c].setBackground(Color.green);
        for (int i = 0; i < 9; i++) {
            this.tlacitka[i].setEnabled(false);
        }
        this.textfield.setText("X vyhralo");
        this.zapis.zapisStatistiku(this.vyhral);
    }

    public void vyhraO(int a, int b, int c) {
        this.vyhral = 10;
        this.tlacitka[a].setBackground(Color.red);
        this.tlacitka[b].setBackground(Color.red);
        this.tlacitka[c].setBackground(Color.red);
        for (int i = 0; i < 9; i++) {
            this.tlacitka[i].setEnabled(false);
        }
        this.textfield.setText("O vyhralo");
        this.zapis.zapisStatistiku(this.vyhral);
    }

    public void remiza() {
        this.vyhral = 15;
        for (int x = 0; x < 9; x++) {
            this.tlacitka[x].setBackground(Color.gray);
            this.tlacitka[x].setEnabled(false);
        }
        this.textfield.setText("Remíza");
        this.zapis.zapisStatistiku(this.vyhral);
    }

    public void reset() {
        for (int x = 0; x < 9; x++) {
            this.tlacitka[x].setEnabled(true);
            this.tlacitka[x].setText("");
            this.tlacitka[x].setBackground(Color.white);
        }
        this.textfield.setText("");
    }
}


