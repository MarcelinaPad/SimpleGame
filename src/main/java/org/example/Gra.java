package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gra extends JPanel implements KeyListener, ActionListener {
    private int x = 300;
    private int punkty = 0;
    private Timer time;
    private int speed = 20;
    private int ex = 10;
    private int ey = 10;
    private  Random liczba = new Random();
    private int WrogX1 = liczba.nextInt(6)*100;
    private int WrogX2 = liczba.nextInt(6)*100;

    private Image graczImg;
    private Image graczHappyImage;
    private Image wrog1Img;
    private Image wrog2Img;

    private boolean czyPokazacGraczaHappy = false;
    private boolean czyPokazaćTekst = false;
    private boolean czyPrzegrana = false;





    public Gra(){
        time = new Timer(10, this);
        time.start();

        graczImg = new ImageIcon("Gracz_new.png").getImage();
        graczHappyImage = new ImageIcon("gracz_happy.png").getImage();
        wrog1Img = new ImageIcon("wrog1.png").getImage();
        wrog2Img = new ImageIcon("wrog2.png").getImage();

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addKeyListener(this);

    }
    public void paint(Graphics g) {

        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0, 700, 600);




        // wróg
        g.drawImage(wrog1Img,WrogX1, ey, 100, 100, this);
        g.drawImage(wrog2Img,WrogX2, ey, 100, 100, this);
        //punkty
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Punkty: " + punkty, 570,20);

        //napis pozmywaj
        if (czyPrzegrana) {
            g.setColor(Color.RED);
            g.setFont(new Font("Rick and Morty Font", Font.CENTER_BASELINE, 32));
            g.drawString("POZMYWAJ", 250, 300);
        }else {

            //gracz

            if (czyPokazacGraczaHappy) {
                g.setColor(Color.MAGENTA);
                g.setFont(new Font("Rick and Morty Font", Font.BOLD, 32));
                g.drawString("JE JE JE", 300, 300);
                g.drawImage(graczHappyImage, 250, 320, 200, 200, this);

            } else {
                g.drawImage(graczImg, x, 475, 75, 75, this);
            }


        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        ey+=2;
        if (ey == 580){
            punkty++;
            ey = 10;
            WrogX1 = liczba.nextInt(6) * 100;
            WrogX2 = liczba.nextInt(6) * 100;
            czyPokazacGraczaHappy = true;
            Timer timer = new Timer(750, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    czyPokazacGraczaHappy = false;

                }
            });
            timer.setRepeats(false);
            timer.start();

//            czyPokazaćTekst = true;
//            time.stop();
//            Timer timerPozmywaj = new Timer(3000, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    czyPokazaćTekst = false;
//                    time.start();
//                }
//            });
//            timerPozmywaj.setRepeats(false);
//            timerPozmywaj.start();
//
//            czyPokazaćTekst = true;
//            timerTekst= new Timer (1000, new ActionListener() {
//                        @Override
//                        public void actionPerformed(ActionEvent e) {
//
//                            czyPokazaćTekst = false;
//                            timerTekst.start();
//
//                        }
//                    });
//            timerTekst.setRepeats(false);
//            timerTekst.start();
        }

        Rectangle gracz = new Rectangle(x,475, 75,75);
        Rectangle wrog1 = new Rectangle(WrogX1,ey, 100,100);
        Rectangle wrog2 = new Rectangle(WrogX2,ey, 100,100);

        if (gracz.intersects(wrog1) || gracz.intersects(wrog2)) {
            time.stop();
            czyPrzegrana = true;
        }




    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x == 0) {
                x =0;
            } else {
                x -= speed;
            }


        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x == 600) {
                x = 600;
            }else {

                x += speed;

            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            reset();
        }

    }

    private void reset() {
        ey = 110;
        WrogX1 = liczba.nextInt(6) * 100;
        WrogX2 = liczba.nextInt(6) * 100;
        x = 300;
        time.start();
        czyPrzegrana = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
