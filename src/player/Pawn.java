package player;
import javax.swing.*;
import java.awt.*;
import monopolyGame.*;


public class Pawn extends JLabel
{
    private static int[][] pz = new int[36][4];
    JLabel pawn= new JLabel();
    private int typ;// pionek moze miec trzy cztery typy ideksowane od 0 do 3
    private int position;// aktualna pozycja pionka
    public Pawn(int typ)
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
        Image tmp = pict1.getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon pict2 = new ImageIcon(tmp);
        this.pawn.setIcon(pict2);
        this.position=0;
        pawn.setBounds(0, 0, 20,20);
        this.typ=typ;
    }
    public JLabel getPawn()
    {
        return pawn;
    }
    public int getTyp()
    {
        return typ;
    }
    public int getPosition()
    {
        return position;
    }
    public void setPosition(int n)
    {
        position=n;
    }
    public void placePawnOn(int nr)
    {
        int poz[]= new int[4];
        pz=GamePanel.getPoz();
        position=nr;
        for(int i=0; i<4; i++)
        {
            poz[i]=pz[nr][i];
        }
        if((nr>0 & nr<9) | (nr>18 & nr<27))
        {
            pawn.setLocation((poz[0]+typ*((Math.abs(poz[0]-poz[2]))/4)),(poz[1]));

        }
        if((nr>9 & nr<18) | (nr>27 & nr<36))
        {
            pawn.setLocation((poz[0]),(poz[1]+typ*((Math.abs(poz[1]-poz[3]))/4)));
        }
        if(nr==0 | nr==9 | nr==18 | nr==27)
        {
            if(typ==0)
            {
                pawn.setLocation((poz[0]),(poz[1]));
            }
            if(typ==1)
            {
                pawn.setLocation((poz[0]+((Math.abs(poz[0]-poz[2])/2))),(poz[1]));
            }
            if(typ==2)
            {
                pawn.setLocation((poz[0]),(poz[1]+((Math.abs(poz[1]-poz[3]))/2)));
            }
            if(typ==3)
            {
                pawn.setLocation((poz[0]+((Math.abs(poz[0]-poz[2])/2))),(poz[1]+((Math.abs(poz[1]-poz[3]))/2)));
            }
        }
    }
}
