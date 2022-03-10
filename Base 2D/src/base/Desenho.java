/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

/**
 *
 * @author 2info2021
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

public class Desenho {

    private double xwmax, xwmin, ywmax, ywmin, xvmax, xvmin, yvmax, yvmin;
    private double xmax, ymax, xcoord, ycoord, xd, yd, mx, my;
    Vector desenho = new Vector();

    public void setMundo(double x1, double y1, double x2, double y2) {
        xwmax = x2;
        ywmax = y2;
        xwmin = x1;
        ywmin = y1;
    }

    public void setDimension(Dimension d) {
        xmax = d.width;
        ymax = d.height;
    }

    public double getMouseX() {
        return mx;
    }

    public double getMouseY() {
        return my;
    }

    public void setMouse(int x, int y) {
        mx = (((x - xvmin) / ((xvmax - xvmin) / (xwmax - xwmin))) + xwmin) / xmax;
        my = (((y - yvmin) / ((yvmax - yvmin) / (ywmax - ywmin))) + ywmin) / ymax;
    }

    public void setViewPort(double x1, double y1, double x2, double y2) {
        xvmax = x2;
        yvmax = y2;
        xvmin = x1;
        yvmin = y1;
    }

    private void mapCoord(double x, double y) {
        double xv = (((x - xvmin) * ((xvmax - xvmin) / (xwmax - xwmin))) + xwmin) + xwmin / xmax;
        double yv = (((y - yvmin) * ((yvmax - yvmin) / (ywmax - ywmin))) + ywmin) + ywmin / ymax;
        xd = xv * xmax;
        yd = yv * ymax;
    }

    private void linhaAte(Graphics g, double x, double y) {
        double xi, yi;
        mapCoord(xcoord, ycoord);
        xi = xd;
        yi = yd;
        mapCoord(x, y);
        g.drawLine((int) xi, (int) yi, (int) xd, (int) yd);
        xcoord = x;
        ycoord = y;
    }

    private void movaPara(Graphics g, double x, double y, boolean pu) {
        if (pu) {
            xcoord = x;
            ycoord = y;
        } else {
            linhaAte(g, x, y);
        }
    }

    public void arco(double centroX, double centroY, double raio, double inicio, double fim) {
        desenho.add(new Ponto(centroX + raio * Math.cos(inicio), centroY + raio * Math.sin(inicio), true));
        for (double z = inicio; z <= fim; z += .1) {
            desenho.add(new Ponto(centroX + raio * Math.cos(z), centroY + raio * Math.sin(z), false));
        }
    }

    public void desenha(Graphics g) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            movaPara(g, p.getX(), p.getY(), p.isPu());
        }
    }

    public void triangulo() {
        desenho.addElement(new Ponto(10d, 9d, true));
        desenho.addElement(new Ponto(9d, 11d, false));
        desenho.addElement(new Ponto(11d, 11d, false));
        desenho.addElement(new Ponto(10d, 9d, false));
    }

    public void quadrado() {
        desenho.addElement(new Ponto(11, 9, true));
        desenho.addElement(new Ponto(9, 9, false));
        desenho.addElement(new Ponto(9, 11, false));
        desenho.addElement(new Ponto(11, 11, false));
        desenho.addElement(new Ponto(11, 9, false));
    }

    public void relacoesmetricas() {
        //Triangulo
        desenho.addElement(new Ponto(8, 5, true));
        desenho.addElement(new Ponto(6, 9, false));
        desenho.addElement(new Ponto(8, 9, false));
        desenho.addElement(new Ponto(8, 5, true));
        desenho.addElement(new Ponto(8, 9, false));
        desenho.addElement(new Ponto(8, 8.65, true));
        desenho.addElement(new Ponto(7.8, 8.65, false));
        desenho.addElement(new Ponto(7.8, 9, false));
        desenho.addElement(new Ponto(8, 9, true));
        desenho.addElement(new Ponto(11, 9, false));
        desenho.addElement(new Ponto(8, 5, true));
        desenho.addElement(new Ponto(11, 9, false));

        // Incógnitas
        
        //linha de A
        desenho.addElement(new Ponto(11, 9.5, true));
        desenho.addElement(new Ponto(6, 9.5, false));
        desenho.addElement(new Ponto(11, 9.3, true));
        desenho.addElement(new Ponto(11, 9.7, false));
        desenho.addElement(new Ponto(6, 9.3, true));
        desenho.addElement(new Ponto(6, 9.7, false));
        // A
        desenho.addElement(new Ponto(8.4, 10, true));
        desenho.addElement(new Ponto(8.25, 10.5, false));
        desenho.addElement(new Ponto(8.4, 10, true));
        desenho.addElement(new Ponto(8.55, 10.5, false));
        desenho.addElement(new Ponto(8.31, 10.3, true));
        desenho.addElement(new Ponto(8.49, 10.3, false));
        // B
        desenho.addElement(new Ponto(9.5, 6, true));
        desenho.addElement(new Ponto(9.5, 6.48, false));
        arco(9.5, 6.1, 0.1, 4.72, 7.9);
        arco(9.5, 6.34, 0.14, 4.72, 7.918); 
        // C
        arco(7, 6.2, 0.2, 1.6, 4.6);
        // M
        desenho.addElement(new Ponto(7, 8.9, true));
        desenho.addElement(new Ponto(7.08, 8.5, false));
        desenho.addElement(new Ponto(7.13, 8.8, false));
        desenho.addElement(new Ponto(7.18, 8.5, false));
        desenho.addElement(new Ponto(7.25, 8.9, false));
        // N
        desenho.addElement(new Ponto(9, 8.9, true));
        desenho.addElement(new Ponto(9, 8.5, false));
        desenho.addElement(new Ponto(9.15, 8.9, false));
        desenho.addElement(new Ponto(9.15, 8.5, false));
        // H
        desenho.addElement(new Ponto(8.1, 7, true));
        desenho.addElement(new Ponto(8.1, 7.5, false));
        arco(8.1, 7.4, 0.13, 4.72, 6.5);
        desenho.addElement(new Ponto(8.23, 7.4, true));
        desenho.addElement(new Ponto(8.23, 7.5, false));
        // 2
        arco(13, 7, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(13.05, 7.05, true));
        desenho.addElement(new Ponto(13, 7.1, false));
    }

    public void estrela() {
        desenho.addElement(new Ponto(10, 3.3, true));
        desenho.addElement(new Ponto(10.7, 7, false));
        desenho.addElement(new Ponto(13, 8, false));
        desenho.addElement(new Ponto(13, 8, false));
        desenho.addElement(new Ponto(11, 9, false));
        desenho.addElement(new Ponto(11.9, 13, false));
        desenho.addElement(new Ponto(10, 9.5, false));
        desenho.addElement(new Ponto(8.3, 13.2, false));
        desenho.addElement(new Ponto(9, 9.15, false));
        desenho.addElement(new Ponto(9, 9.15, false));
        desenho.addElement(new Ponto(7, 8, false));
        desenho.addElement(new Ponto(9.3, 7, false));
        desenho.addElement(new Ponto(10, 3.3, false));
    }

    public void arte() {
        //primeira base direita
        desenho.addElement(new Ponto(10.8, 0, true));
        desenho.addElement(new Ponto(12.3, 20, false));
        //fechando base direito
        desenho.addElement(new Ponto(10.98, 0, true));
        desenho.addElement(new Ponto(12.48, 20, false));
        //primeira base esquerda
        desenho.addElement(new Ponto(9.3, 0, true));
        desenho.addElement(new Ponto(7.8, 20, false));
        //fechando base esquerdo
        desenho.addElement(new Ponto(9.48, 0, true));
        desenho.addElement(new Ponto(7.98, 20, false));

        //base trilho
        desenho.addElement(new Ponto(8.8, 0.2, true));
        desenho.addElement(new Ponto(9.27, 0.2, false));
        //
        desenho.addElement(new Ponto(9.48, 0.2, true));
        desenho.addElement(new Ponto(10.8, 0.2, false));
        //
        desenho.addElement(new Ponto(11, 0.2, true));
        desenho.addElement(new Ponto(11.4, 0.2, false));
        //
        desenho.addElement(new Ponto(8.8, 0.2, true));
        desenho.addElement(new Ponto(8.8, -0.1, false));
        //
        desenho.addElement(new Ponto(11.4, 0.2, true));
        desenho.addElement(new Ponto(11.4, -0.1, false));

        // primeiro trilho
        desenho.addElement(new Ponto(8.6, 2, true));
        desenho.addElement(new Ponto(9.147, 2, false));
        //
        desenho.addElement(new Ponto(9.33, 2, true));
        desenho.addElement(new Ponto(10.95, 2, false));
        //
        desenho.addElement(new Ponto(11.13, 2, true));
        desenho.addElement(new Ponto(11.6, 2, false));
        //
        desenho.addElement(new Ponto(8.6, 2, true));
        desenho.addElement(new Ponto(8.6, 2.5, false));
        //parte de baixo
        desenho.addElement(new Ponto(8.6, 2.5, true));
        desenho.addElement(new Ponto(9.11, 2.5, false));
        //
        desenho.addElement(new Ponto(9.295, 2.5, true));
        desenho.addElement(new Ponto(10.982, 2.5, false));
        //
        desenho.addElement(new Ponto(11.17, 2.5, true));
        desenho.addElement(new Ponto(11.6, 2.5, false));
        //
        desenho.addElement(new Ponto(11.6, 2, true));
        desenho.addElement(new Ponto(11.6, 2.5, false));

        //segundo trilho
        desenho.addElement(new Ponto(8.4, 4.5, true));
        desenho.addElement(new Ponto(8.958, 4.5, false));
        //
        desenho.addElement(new Ponto(9.15, 4.5, true));
        desenho.addElement(new Ponto(11.13, 4.5, false));
        //
        desenho.addElement(new Ponto(11.32, 4.5, true));
        desenho.addElement(new Ponto(11.8, 4.5, false));
        //parte de baixo
        desenho.addElement(new Ponto(8.4, 5, true));
        desenho.addElement(new Ponto(8.92, 5, false));
        //
        desenho.addElement(new Ponto(9.11, 5, true));
        desenho.addElement(new Ponto(11.163, 5, false));
        //
        desenho.addElement(new Ponto(11.364, 5, true));
        desenho.addElement(new Ponto(11.8, 5, false));
        //      
        desenho.addElement(new Ponto(8.4, 4.5, true));
        desenho.addElement(new Ponto(8.4, 5, false));
        //
        desenho.addElement(new Ponto(11.8, 4.5, true));
        desenho.addElement(new Ponto(11.8, 5, false));

        //terceiro trilho
        desenho.addElement(new Ponto(8.2, 7, true));
        desenho.addElement(new Ponto(8.77, 7, false));
        //
        desenho.addElement(new Ponto(8.95, 7, true));
        desenho.addElement(new Ponto(11.32, 7, false));
        //
        desenho.addElement(new Ponto(11.52, 7, true));
        desenho.addElement(new Ponto(12, 7, false));
        //parte de baixo
        desenho.addElement(new Ponto(8.2, 7.5, true));
        desenho.addElement(new Ponto(8.74, 7.5, false));
        //
        desenho.addElement(new Ponto(8.92, 7.5, true));
        desenho.addElement(new Ponto(11.355, 7.5, false));
        //
        desenho.addElement(new Ponto(11.55, 7.5, true));
        desenho.addElement(new Ponto(12, 7.5, false));
        //
        desenho.addElement(new Ponto(8.2, 7, true));
        desenho.addElement(new Ponto(8.2, 7.5, false));
        //
        desenho.addElement(new Ponto(12, 7, true));
        desenho.addElement(new Ponto(12, 7.5, false));

        //quarto trilho
        desenho.addElement(new Ponto(8, 9.5, true));
        desenho.addElement(new Ponto(8.588, 9.5, false));
        //
        desenho.addElement(new Ponto(8.78, 9.5, true));
        desenho.addElement(new Ponto(11.5, 9.5, false));
        //
        desenho.addElement(new Ponto(11.7, 9.5, true));
        desenho.addElement(new Ponto(12.2, 9.5, false));
        //parte de baixo
        desenho.addElement(new Ponto(8, 10, true));
        desenho.addElement(new Ponto(8.55, 10, false));
        //
        desenho.addElement(new Ponto(8.74, 10, true));
        desenho.addElement(new Ponto(11.55, 10, false));
        //
        desenho.addElement(new Ponto(11.74, 10, true));
        desenho.addElement(new Ponto(12.2, 10, false));
        //
        desenho.addElement(new Ponto(8, 9.5, true));
        desenho.addElement(new Ponto(8, 10, false));
        //
        desenho.addElement(new Ponto(12.2, 9.5, true));
        desenho.addElement(new Ponto(12.2, 10, false));

        //quinto trilho
        desenho.addElement(new Ponto(7.8, 12, true));
        desenho.addElement(new Ponto(8.4, 12, false));
        //
        desenho.addElement(new Ponto(8.6, 12, true));
        desenho.addElement(new Ponto(11.7, 12, false));
        //
        desenho.addElement(new Ponto(11.9, 12, true));
        desenho.addElement(new Ponto(12.4, 12, false));
        //parte de baixo
        desenho.addElement(new Ponto(7.8, 12.5, true));
        desenho.addElement(new Ponto(8.35, 12.5, false));
        //
        desenho.addElement(new Ponto(8.55, 12.5, true));
        desenho.addElement(new Ponto(11.73, 12.5, false));
        //
        desenho.addElement(new Ponto(11.92, 12.5, true));
        desenho.addElement(new Ponto(12.4, 12.5, false));
        //
        desenho.addElement(new Ponto(7.8, 12, true));
        desenho.addElement(new Ponto(7.8, 12.5, false));
        //
        desenho.addElement(new Ponto(12.4, 12, true));
        desenho.addElement(new Ponto(12.4, 12.5, false));

        //sexto trilho
        desenho.addElement(new Ponto(7.6, 14.5, true));
        desenho.addElement(new Ponto(8.2, 14.5, false));
        //
        desenho.addElement(new Ponto(8.4, 14.5, true));
        desenho.addElement(new Ponto(11.88, 14.5, false));
        //
        desenho.addElement(new Ponto(12.08, 14.5, true));
        desenho.addElement(new Ponto(12.6, 14.5, false));
        //parte de baixo
        desenho.addElement(new Ponto(7.6, 15, true));
        desenho.addElement(new Ponto(8.17, 15, false));
        //
        desenho.addElement(new Ponto(8.37, 15, true));
        desenho.addElement(new Ponto(11.92, 15, false));
        //
        desenho.addElement(new Ponto(12.11, 15, true));
        desenho.addElement(new Ponto(12.6, 15, false));
        //
        desenho.addElement(new Ponto(7.6, 14.5, true));
        desenho.addElement(new Ponto(7.6, 15, false));
        //
        desenho.addElement(new Ponto(12.6, 14.5, true));
        desenho.addElement(new Ponto(12.6, 15, false));

        //setimo trilho
        desenho.addElement(new Ponto(7.4, 17, true));
        desenho.addElement(new Ponto(8, 17, false));
        //
        desenho.addElement(new Ponto(8.2, 17, true));
        desenho.addElement(new Ponto(12.08, 17, false));
        //
        desenho.addElement(new Ponto(12.26, 17, true));
        desenho.addElement(new Ponto(12.8, 17, false));
        //parte de baixo
        desenho.addElement(new Ponto(7.4, 17.5, true));
        desenho.addElement(new Ponto(7.98, 17.5, false));
        //
        desenho.addElement(new Ponto(8.17, 17.5, true));
        desenho.addElement(new Ponto(12.1, 17.5, false));
        //
        desenho.addElement(new Ponto(12.3, 17.5, true));
        desenho.addElement(new Ponto(12.8, 17.5, false));
        //
        desenho.addElement(new Ponto(7.4, 17, true));
        desenho.addElement(new Ponto(7.4, 17.5, false));
        //
        desenho.addElement(new Ponto(12.8, 17, true));
        desenho.addElement(new Ponto(12.8, 17.5, false));

        //ultimo trilho
        desenho.addElement(new Ponto(7.2, 19.5, true));
        desenho.addElement(new Ponto(7.82, 19.5, false));
        //
        desenho.addElement(new Ponto(8.02, 19.5, true));
        desenho.addElement(new Ponto(12.25, 19.5, false));
        //
        desenho.addElement(new Ponto(12.45, 19.5, true));
        desenho.addElement(new Ponto(13, 19.5, false));
        //parte de baixo
        desenho.addElement(new Ponto(7.2, 20, true));
        desenho.addElement(new Ponto(7.98, 20, false));
        //
        desenho.addElement(new Ponto(8.17, 20, true));
        desenho.addElement(new Ponto(12.1, 20, false));
        //
        desenho.addElement(new Ponto(12.5, 20, true));
        desenho.addElement(new Ponto(13, 20, false));
        //
        desenho.addElement(new Ponto(7.2, 19.5, true));
        desenho.addElement(new Ponto(7.2, 20, false));
        //
        desenho.addElement(new Ponto(13, 19.5, true));
        desenho.addElement(new Ponto(13, 20, false));

        //cenário
        //arvore 1
        desenho.addElement(new Ponto(15, 6, true));
        desenho.addElement(new Ponto(15.3, 6, false));
        desenho.addElement(new Ponto(15.3, 3.5, false));
        desenho.addElement(new Ponto(15, 6, true));
        desenho.addElement(new Ponto(15, 3.5, false));
        //
        desenho.addElement(new Ponto(15, 3.5, true));
        desenho.addElement(new Ponto(14.2, 3.5, false));
        desenho.addElement(new Ponto(14.2, 1.7, false));
        desenho.addElement(new Ponto(15.3, 3.5, true));
        desenho.addElement(new Ponto(16.1, 3.5, false));
        desenho.addElement(new Ponto(16.1, 1.7, false));
        desenho.addElement(new Ponto(14.2, 1.7, false));
        //
        desenho.addElement(new Ponto(14.6, 3.5, true));
        desenho.addElement(new Ponto(14.6, 3.7, false));
        desenho.addElement(new Ponto(14.7, 3.7, false));
        desenho.addElement(new Ponto(14.7, 4, false));
        desenho.addElement(new Ponto(14.5, 4, false));
        desenho.addElement(new Ponto(14.5, 3.7, false));
        desenho.addElement(new Ponto(14.6, 3.7, false));
        //
        desenho.addElement(new Ponto(15.5, 3.5, true));
        desenho.addElement(new Ponto(15.5, 3.7, false));
        desenho.addElement(new Ponto(15.6, 3.7, false));
        desenho.addElement(new Ponto(15.6, 4, false));
        desenho.addElement(new Ponto(15.4, 4, false));
        desenho.addElement(new Ponto(15.4, 3.7, false));
        desenho.addElement(new Ponto(15.5, 3.7, false));
        //
        desenho.addElement(new Ponto(15.9, 3.5, true));
        desenho.addElement(new Ponto(15.9, 3.7, false));
        desenho.addElement(new Ponto(16, 3.7, false));
        desenho.addElement(new Ponto(16, 4, false));
        desenho.addElement(new Ponto(15.8, 4, false));
        desenho.addElement(new Ponto(15.8, 3.7, false));
        desenho.addElement(new Ponto(15.9, 3.7, false));

        //arvore 2
        desenho.addElement(new Ponto(3, 18, true));
        desenho.addElement(new Ponto(3.6, 18, false));
        desenho.addElement(new Ponto(3.6, 14.5, false));
        desenho.addElement(new Ponto(3, 18, true));
        desenho.addElement(new Ponto(3, 14.5, false));
        //
        desenho.addElement(new Ponto(3, 14.5, true));
        desenho.addElement(new Ponto(1.8, 14.5, false));
        desenho.addElement(new Ponto(1.8, 11.5, false));
        desenho.addElement(new Ponto(4.8, 11.5, false));
        desenho.addElement(new Ponto(4.8, 14.5, false));
        desenho.addElement(new Ponto(3.6, 14.5, false));
        //
        desenho.addElement(new Ponto(2.4, 14.5, true));
        desenho.addElement(new Ponto(2.4, 14.9, false));
        desenho.addElement(new Ponto(2.55, 14.9, false));
        desenho.addElement(new Ponto(2.55, 15.35, false));
        desenho.addElement(new Ponto(2.25, 15.35, false));
        desenho.addElement(new Ponto(2.25, 14.9, false));
        desenho.addElement(new Ponto(2.4, 14.9, false));
        //
        desenho.addElement(new Ponto(3.9, 14.5, true));
        desenho.addElement(new Ponto(3.9, 14.9, false));
        desenho.addElement(new Ponto(4.05, 14.9, false));
        desenho.addElement(new Ponto(4.05, 15.35, false));
        desenho.addElement(new Ponto(3.75, 15.35, false));
        desenho.addElement(new Ponto(3.75, 14.9, false));
        desenho.addElement(new Ponto(3.9, 14.9, false));
        //
        desenho.addElement(new Ponto(4.5, 14.5, true));
        desenho.addElement(new Ponto(4.5, 14.9, false));
        desenho.addElement(new Ponto(4.65, 14.9, false));
        desenho.addElement(new Ponto(4.65, 15.35, false));
        desenho.addElement(new Ponto(4.35, 15.35, false));
        desenho.addElement(new Ponto(4.35, 14.9, false));
        desenho.addElement(new Ponto(4.5, 14.9, false));
    }

    public void translada(double dx, double dy) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            p.setX(p.getX() + dx);
            p.setY(p.getY() + dy);
            desenho.set(i, p);
        }
    }

    public void rotaciona(double ang) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            double xx = p.getX();
            double yy = p.getY();
            p.setX(xx * Math.cos(ang) + yy * (-Math.sin(ang)));
            p.setY(xx * Math.sin(ang) + yy * Math.cos(ang));
            desenho.set(i, p);
        }
    }

    public void deforma(boolean maior) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            if (maior) {
                p = (Ponto) desenho.get(i);
                p.setX(((p.getX() - mx) * 0.1) + p.getX());
                p.setY(((p.getY() - my) * 0.1) + p.getY());
                desenho.set(i, p);
            } else {
                p = (Ponto) desenho.get(i);
                p.setX(p.getX() - ((p.getX() - mx) * 0.1));
                p.setY(p.getY() - ((p.getY() - my) * 0.1));
                desenho.set(i, p);
            }
        }
    }
}
