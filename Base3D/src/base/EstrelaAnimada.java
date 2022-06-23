/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author mathe
 */
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class EstrelaAnimada extends JPanel implements Runnable {
    
    private final double RAIO = 0.4; 
    
    private double ainicial = -Math.PI/2.0d;
    
    public EstrelaAnimada() {
        super.setBackground( Color.BLACK );
    }
    
    @Override
    public void run() {
        while( true ) {
            super.repaint();
            try {
                Thread.sleep( 30 ); 
            } catch ( InterruptedException e ) {
                
            }
            ainicial += Math.PI / 96.0d;            
        }
    }
    
    @Override
    public void paintComponent( Graphics g ) {
        super.paintComponent( g );
        
        int w = super.getWidth();
        int h = super.getHeight();
                        
        int r1 = (int)( RAIO * h );
        int r2 = r1/2;
        
        int x = w / 2;
        int y = h / 2;
        
        double ainc = ( 2.0d * Math.PI ) / 5.0d;
        
        int[] xvet = new int[ 10 ];
        int[] yvet = new int[ 10 ];
                
        for( int i = 0; i < 5; i++ ) {
            double a = ainicial + ( i * ainc );
            double a2 = a + ( ainc / 2.0d );
            xvet[i*2] = (int)( x + r1 * Math.cos( a ) );
            yvet[i*2] = (int)( y + r1 * Math.sin( a ) );

            xvet[i*2+1] = (int)( x + r2 * Math.cos( a2 ) );
            yvet[i*2+1] = (int)( y + r2 * Math.sin( a2 ) );            
        }
        
        g.setColor( Color.BLACK );
        g.fillPolygon( xvet, yvet, 10 );
        g.setColor( Color.WHITE );
        g.drawPolygon( xvet, yvet, 10 );                
    }
    
    public static void main(String[] args) {
        EstrelaAnimada estrela = new EstrelaAnimada();
        
        JFrame f = new JFrame();
        f.setTitle( "Desenho de estrela" );
        f.setContentPane( estrela );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f.setSize( 500, 500 );
        f.setLocationRelativeTo( f );
        
        SwingUtilities.invokeLater( () -> {
            f.setVisible( true );
            Thread t = new Thread( estrela );
            t.start();
        } );
    }
    
}

