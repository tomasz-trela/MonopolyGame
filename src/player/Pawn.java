package player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import monopolyGame.*;


public class Pawn 
{
    JLabel pawn= new JLabel();
    private int typ;// pionek moze miec trzy cztery typy ideksowane od 0 do 3
    private int position;// aktualna pozycja pionka
    private Timer timer;
    private int nr;
    public Pawn(int typ)
    {
        this.position=0;
        pawn.setBounds(0, 0, 0,0);
        this.typ=typ;
        nr=0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                move();
            }
        });
    }

    private void move() {

        GamePanel.getStrategyPanel().setVisible(false);


        GamePanel.getFieldArray()[position].remove(pawn);
        GamePanel.getFieldArray()[position].revalidate();
        GamePanel.getFieldArray()[position].repaint();

        if (position==GamePanel.getFieldArray().length-1){
            position=-1;
        }

        if(position==nr){
            GamePanel.getFieldArray()[position].add(pawn);
            timer.stop();

            return;
        }

        this.position++;

            GamePanel.getFieldArray()[position].add(pawn);
            GamePanel.getFieldArray()[position].revalidate();
            GamePanel.getFieldArray()[position].repaint();

        if (position == nr) {
            // Pauzuje timer przy ostatniej pozycji (chyba że na jest na starcie bo wtedy nie powinien)
            timer.stop();
            if (nr!=0) GamePanel.getStrategyPanel().setVisible(true);
        }
    }
    public JLabel getPawn()
    {
        return pawn;
    }
    public void hidePawn(){
        pawn.setVisible(false);
    }
    public int getTyp()
    {
        return typ;
    }
    public int getPosition()
    {
        return nr;
    }
    public void setPosition(int n)
    {
        position=n;
    }
    public void placePawnOn(int nr){
        GamePanel.getStrategyPanel().setVisible(false);
        this.nr=nr;
        timer.setInitialDelay(0);
        timer.setDelay(300);
        timer.setRepeats(true);
        timer.start();
    }
    public void SizeSet(int x, int y)
    {
        Image pict1 = null;
        if(typ==0)
        {
            pict1 = new ImageIcon("src/images/pionekplaceholder.png").getImage();
        }
        if(typ==1)
        {
            pict1 = new ImageIcon("src/images/pionekplaceholder2.png").getImage();
        }
        if(typ==2)
        {
            pict1 = new ImageIcon("src/images/pionekplaceholder3.png").getImage();
        }
        if(typ==3)
        {
            pict1 = new ImageIcon("src/images/pionekplaceholder4.png").getImage();
        }
        Image tmp = pict1.getScaledInstance(x,y,Image.SCALE_DEFAULT);
        ImageIcon pict2 = new ImageIcon(tmp);
        this.pawn.setIcon(pict2);
    }

}
