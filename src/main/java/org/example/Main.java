package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Gra gra = new Gra();

        JFrame jf = new JFrame ( "Prosta Gra");

        jf.setBounds(200, 200, 700, 600 );
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.add(gra);

    }
}