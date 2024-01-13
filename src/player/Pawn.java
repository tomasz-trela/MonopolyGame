package player;
import javax.swing.*;
import java.awt.*;
import monopolyGame.*;


public class Pawn 
{
    JLabel pawn= new JLabel();
    private int typ;// pionek moze miec trzy cztery typy ideksowane od 0 do 3
    private int position;// aktualna pozycja pionka
    public Pawn(int typ)
    {
        this.position=0;
        pawn.setBounds(0, 0, 0,0);
        this.typ=typ;
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
        return position;
    }
    public void setPosition(int n)
    {
        position=n;
    }
    public void placePawnOn(int nr){
        GamePanel.getFieldArray()[position].remove(pawn); // Usunięcie etykiety z poprzedniego panelu
        GamePanel.getFieldArray()[position].revalidate();
        GamePanel.getFieldArray()[position].repaint();
        GamePanel.getFieldArray()[nr].add(pawn);
        this.position=nr;
        GamePanel.getFieldArray()[position].revalidate();
        GamePanel.getFieldArray()[position].repaint();
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
