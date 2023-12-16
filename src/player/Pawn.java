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
        ImageIcon pict1= new ImageIcon("pionekplaceholder.png");
        Image test = pict1.getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT);
        ImageIcon pict2 = new ImageIcon(test);
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
    public void placePawnOn(int nr)
    {
        int poz[]= new int[4];
        pz=GamePanel.getPoz();
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
        else
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
