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
        //arco(13, 7, 0.08, 4.5, 7.1);
        // desenho.addElement(new Ponto(13.054, 7.05, true));
        //desenho.addElement(new Ponto(12.98, 7.15, false));
        //desenho.addElement(new Ponto(13.1, 7.15, false));

        // equações
        //pitagoras
        //A ao quadrado
        desenho.addElement(new Ponto(12.8, 6, true));
        desenho.addElement(new Ponto(12.65, 6.5, false));
        desenho.addElement(new Ponto(12.8, 6, true));
        desenho.addElement(new Ponto(12.95, 6.5, false));
        desenho.addElement(new Ponto(12.71, 6.3, true));
        desenho.addElement(new Ponto(12.89, 6.3, false));
        //
        arco(13, 6, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(13.054, 6.05, true));
        desenho.addElement(new Ponto(12.98, 6.15, false));
        desenho.addElement(new Ponto(13.1, 6.15, false));
        // =
        desenho.addElement(new Ponto(13.2, 6.3, true));
        desenho.addElement(new Ponto(13.4, 6.3, false));
        desenho.addElement(new Ponto(13.2, 6.4, true));
        desenho.addElement(new Ponto(13.4, 6.4, false));
        // B ao quadrado 
        desenho.addElement(new Ponto(13.6, 6, true));
        desenho.addElement(new Ponto(13.6, 6.48, false));
        arco(13.6, 6.1, 0.1, 4.72, 7.9);
        arco(13.6, 6.34, 0.14, 4.72, 7.918);
        //
        arco(13.8, 6, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(13.854, 6.05, true));
        desenho.addElement(new Ponto(13.78, 6.15, false));
        desenho.addElement(new Ponto(13.9, 6.15, false));
        // +
        desenho.addElement(new Ponto(14.05, 6.3, true));
        desenho.addElement(new Ponto(14.2, 6.3, false));
        desenho.addElement(new Ponto(14.127, 6.18, true));
        desenho.addElement(new Ponto(14.127, 6.42, false));
        // C ao quadrado
        arco(14.55, 6.25, 0.2, 1.6, 4.6);
        arco(14.64, 6, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(14.703, 6.05, true));
        desenho.addElement(new Ponto(14.62, 6.15, false));
        desenho.addElement(new Ponto(14.74, 6.15, false));

        // c2 = a.m
        // C ao quadrado
        arco(12.85, 7.4, 0.2, 1.6, 4.6);
        arco(12.94, 7.15, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(12.99, 7.21, true));
        desenho.addElement(new Ponto(12.9, 7.31, false));
        desenho.addElement(new Ponto(13.03, 7.31, false));
        // = 
        desenho.addElement(new Ponto(13.1, 7.4, true));
        desenho.addElement(new Ponto(13.3, 7.4, false));
        desenho.addElement(new Ponto(13.1, 7.5, true));
        desenho.addElement(new Ponto(13.3, 7.5, false));
        // A
        desenho.addElement(new Ponto(13.6, 7.1, true));
        desenho.addElement(new Ponto(13.45, 7.6, false));
        desenho.addElement(new Ponto(13.6, 7.1, true));
        desenho.addElement(new Ponto(13.75, 7.6, false));
        desenho.addElement(new Ponto(13.51, 7.4, true));
        desenho.addElement(new Ponto(13.69, 7.4, false));
        // .
        desenho.addElement(new Ponto(13.9, 7.45, true));
        desenho.addElement(new Ponto(13.95, 7.45, false));
        desenho.addElement(new Ponto(13.9, 7.55, true));
        desenho.addElement(new Ponto(13.95, 7.55, false));
        desenho.addElement(new Ponto(13.9, 7.45, true));
        desenho.addElement(new Ponto(13.9, 7.55, false));
        desenho.addElement(new Ponto(13.95, 7.45, true));
        desenho.addElement(new Ponto(13.95, 7.55, false));
        // M
        desenho.addElement(new Ponto(14.1, 7.58, true));
        desenho.addElement(new Ponto(14.18, 7.18, false));
        desenho.addElement(new Ponto(14.23, 7.48, false));
        desenho.addElement(new Ponto(14.28, 7.18, false));
        desenho.addElement(new Ponto(14.35, 7.58, false));

        // b2 = a.n
        // B ao quadrado
        desenho.addElement(new Ponto(12.7, 8.2, true));
        desenho.addElement(new Ponto(12.7, 8.68, false));
        arco(12.7, 8.3, 0.1, 4.72, 7.9);
        arco(12.7, 8.54, 0.14, 4.72, 7.918);
        // 2
        arco(12.9, 8.05, 0.08, 4.5, 7.1);
        desenho.addElement(new Ponto(12.954, 8.1, true));
        desenho.addElement(new Ponto(12.88, 8.2, false));
        desenho.addElement(new Ponto(13, 8.2, false));
        // =
        desenho.addElement(new Ponto(13.1, 8.5, true));
        desenho.addElement(new Ponto(13.3, 8.5, false));
        desenho.addElement(new Ponto(13.1, 8.6, true));
        desenho.addElement(new Ponto(13.3, 8.6, false));
        // A
        desenho.addElement(new Ponto(13.6, 8.2, true));
        desenho.addElement(new Ponto(13.45, 8.7, false));
        desenho.addElement(new Ponto(13.6, 8.2, true));
        desenho.addElement(new Ponto(13.75, 8.7, false));
        desenho.addElement(new Ponto(13.51, 8.5, true));
        desenho.addElement(new Ponto(13.69, 8.5, false));
        // .
        desenho.addElement(new Ponto(13.9, 8.55, true));
        desenho.addElement(new Ponto(13.95, 8.55, false));
        desenho.addElement(new Ponto(13.9, 8.65, true));
        desenho.addElement(new Ponto(13.95, 8.65, false));
        desenho.addElement(new Ponto(13.9, 8.55, true));
        desenho.addElement(new Ponto(13.9, 8.65, false));
        desenho.addElement(new Ponto(13.95, 8.55, true));
        desenho.addElement(new Ponto(13.95, 8.65, false));
        // N
        desenho.addElement(new Ponto(14.1, 8.65, true));
        desenho.addElement(new Ponto(14.1, 8.25, false));
        desenho.addElement(new Ponto(14.25, 8.65, false));
        desenho.addElement(new Ponto(14.25, 8.25, false));

        // h2 = m.n
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

    public void cenario() {
        //primeira base direita
        desenho.addElement(new Ponto(12.5, 0, true));
        desenho.addElement(new Ponto(12.5, 20, false));
        //fechando base direito
        desenho.addElement(new Ponto(12.7, 0, true));
        desenho.addElement(new Ponto(12.7, 20, false));
        //primeira base esquerda
        desenho.addElement(new Ponto(8.2, 0, true));
        desenho.addElement(new Ponto(8.2, 20, false));
        //fechando base esquerdo
        desenho.addElement(new Ponto(8.4, 0, true));
        desenho.addElement(new Ponto(8.4, 20, false));
        //traços no meio da pista
        desenho.addElement(new Ponto(10.25, 16.5, true));
        desenho.addElement(new Ponto(10.25, 19, false));
        desenho.addElement(new Ponto(10.65, 19, false));
        desenho.addElement(new Ponto(10.65, 16.5, false));
        desenho.addElement(new Ponto(10.25, 16.5, false));
        // traço 2
        desenho.addElement(new Ponto(10.25, 12.5, true));
        desenho.addElement(new Ponto(10.25, 15, false));
        desenho.addElement(new Ponto(10.65, 15, false));
        desenho.addElement(new Ponto(10.65, 12.5, false));
        desenho.addElement(new Ponto(10.25, 12.5, false));
        // traço 3
        desenho.addElement(new Ponto(10.25, 8.5, true));
        desenho.addElement(new Ponto(10.25, 11, false));
        desenho.addElement(new Ponto(10.65, 11, false));
        desenho.addElement(new Ponto(10.65, 8.5, false));
        desenho.addElement(new Ponto(10.25, 8.5, false));
        //traço 4
        desenho.addElement(new Ponto(10.25, 4.5, true));
        desenho.addElement(new Ponto(10.25, 7, false));
        desenho.addElement(new Ponto(10.65, 7, false));
        desenho.addElement(new Ponto(10.65, 4.5, false));
        desenho.addElement(new Ponto(10.25, 4.5, false));
        //traço 5
        desenho.addElement(new Ponto(10.25, 0.5, true));
        desenho.addElement(new Ponto(10.25, 3, false));
        desenho.addElement(new Ponto(10.65, 3, false));
        desenho.addElement(new Ponto(10.65, 0.5, false));
        desenho.addElement(new Ponto(10.25, 0.5, false));
        //sol
        arco(20, 1.2, 1.5, 0.5, 4.5);
        //raios de sol
        desenho.addElement(new Ponto(18.4, 2, true));
        desenho.addElement(new Ponto(17.4, 3, false));
        //
        desenho.addElement(new Ponto(19, 3, true));
        desenho.addElement(new Ponto(18.2, 4.5, false));
        //
        desenho.addElement(new Ponto(19.8, 3.5, true));
        desenho.addElement(new Ponto(19.5, 5, false));
        //
        desenho.addElement(new Ponto(18.2, 0.7, true));
        desenho.addElement(new Ponto(17.2, 0.9, false));
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
