/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

public class Desenho3D {

    double xwmax, xwmin, ywmax, ywmin, zwmax, zwmin, xvmax, xvmin, yvmax, yvmin, zvmax, zvmin;
    double xmax, ymax, xcoord, ycoord, zcoord;
    private double centrox;
    private double centroy;
    double xd, yd;
    Vector desenho = new Vector();

    public void setMundo(double x1, double y1, double z1, double x2, double y2, double z2) {
        xwmax = x2;
        ywmax = y2;
        zwmax = z2;
        xwmin = x1;
        ywmin = y1;
        zwmin = z1;
        centrox = (x1 + x2) / 2;
        centroy = (y1 + y2) / 2;
    }

    public void setViewPort(double x1, double y1, double x2, double y2) {
        xvmax = x2;
        yvmax = y2;
        xvmin = x1;
        yvmin = y1;
    }

    public void setDimension(Dimension dim) {
        xmax = dim.width;
        ymax = dim.height;
    }

    private void mapCoord(double x, double y, double z) {
        double zv;
        if (z < 0) {
            zv = 1 / (1 + Math.abs(z) / (zwmax - zwmin));
        } else {
            zv = (1 + Math.abs(z) / (zwmax - zwmin));
        }
        x = x / zv;
        y = y / zv;
        double xv = (((x - xwmin) * ((xvmax - xvmin) / (xwmax - xwmin))) + xvmin);
        double yv = (((y - ywmin) * ((yvmax - yvmin) / (ywmax - ywmin))) + yvmin);
        xd = xv * xmax;
        yd = yv * ymax;
    }

    private void linhaAte(Graphics g, double x, double y, double z) {
        double xi, yi;
        mapCoord(xcoord, ycoord, zcoord);
        xi = xd;
        yi = yd;
        mapCoord(x, y, z);
        g.drawLine((int) xi, (int) yi, (int) xd, (int) yd);
        xcoord = x;
        ycoord = y;
        zcoord = z;
    }

    private void movaPara(Graphics g, double x, double y, double z, boolean pu) {
        if (pu) {
            xcoord = x;
            ycoord = y;
            zcoord = z;
        } else {
            linhaAte(g, x, y, z);
        }
    }

    public void desenha(Graphics g) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            movaPara(g, p.getX(), p.getY(), p.getZ(), p.isPu());
        }
    }

    public void cubo() {
        desenho.addElement(new Ponto(11, 10, 1, true));
        desenho.addElement(new Ponto(11, 11, 1, false));
        desenho.addElement(new Ponto(12, 11, 1, false));
        desenho.addElement(new Ponto(12, 10, 1, false));
        desenho.addElement(new Ponto(11, 10, 1, false));
        desenho.addElement(new Ponto(11, 10, 2, false));
        desenho.addElement(new Ponto(11, 11, 2, false));
        desenho.addElement(new Ponto(12, 11, 2, false));
        desenho.addElement(new Ponto(12, 10, 2, false));
        desenho.addElement(new Ponto(11, 10, 2, false));
        desenho.addElement(new Ponto(11, 11, 2, false));
        desenho.addElement(new Ponto(11, 11, 1, false));
        desenho.addElement(new Ponto(12, 11, 1, false));
        desenho.addElement(new Ponto(12, 11, 2, false));
        desenho.addElement(new Ponto(12, 10, 2, false));
        desenho.addElement(new Ponto(12, 10, 1, false));
    }

    public void pontos() {
        //base
        desenho.addElement(new Ponto(4, 12, 4, true));
        desenho.addElement(new Ponto(4, 12, 1, false));
        desenho.addElement(new Ponto(6, 12, 1, false));
        desenho.addElement(new Ponto(6, 12, 4, false));
        desenho.addElement(new Ponto(4, 12, 4, false));
        //laterais e conectores
        desenho.addElement(new Ponto(5, 7, 2, false));
        desenho.addElement(new Ponto(6, 12, 1, false));
        desenho.addElement(new Ponto(5, 7, 2, true));
        desenho.addElement(new Ponto(6, 12, 4, false));
        desenho.addElement(new Ponto(5, 7, 2, true));
        desenho.addElement(new Ponto(4, 12, 1, false));

        // estrela
        desenho.addElement(new Ponto(10, 9, 1, true));
        desenho.addElement(new Ponto(10.5, 12, 1, false));
        desenho.addElement(new Ponto(12.5, 12, 1, false));
        desenho.addElement(new Ponto(11, 14, 1, false));
        desenho.addElement(new Ponto(11.5, 17, 1, false));
        desenho.addElement(new Ponto(10, 15, 1, false));
        desenho.addElement(new Ponto(8.5, 17, 1, false));
        desenho.addElement(new Ponto(9, 14, 1, false));
        desenho.addElement(new Ponto(7.5, 12, 1, false));
        desenho.addElement(new Ponto(9.5, 12, 1, false));
        desenho.addElement(new Ponto(10, 9, 1, false));
        //estrela de trás
        desenho.addElement(new Ponto(10, 9, 2, true));
        desenho.addElement(new Ponto(10.5, 12, 2, false));
        desenho.addElement(new Ponto(12.5, 12, 2, false));
        desenho.addElement(new Ponto(11, 14, 2, false));
        desenho.addElement(new Ponto(11.5, 17, 2, false));
        desenho.addElement(new Ponto(10, 15, 2, false));
        desenho.addElement(new Ponto(8.5, 17, 2, false));
        desenho.addElement(new Ponto(9, 14, 2, false));
        desenho.addElement(new Ponto(7.5, 12, 2, false));
        desenho.addElement(new Ponto(9.5, 12, 2, false));
        desenho.addElement(new Ponto(10, 9, 2, false));
        //conectores
        desenho.addElement(new Ponto(10, 9, 1, true));
        desenho.addElement(new Ponto(10, 9, 2, false));
        desenho.addElement(new Ponto(10.5, 12, 1, true));
        desenho.addElement(new Ponto(10.5, 12, 2, false));
        desenho.addElement(new Ponto(12.5, 12, 1, true));
        desenho.addElement(new Ponto(12.5, 12, 2, false));
        desenho.addElement(new Ponto(11, 14, 1, true));
        desenho.addElement(new Ponto(11, 14, 2, false));
        desenho.addElement(new Ponto(11.5, 17, 1, true));
        desenho.addElement(new Ponto(11.5, 17, 2, false));
        desenho.addElement(new Ponto(10, 15, 1, true));
        desenho.addElement(new Ponto(10, 15, 2, false));
        desenho.addElement(new Ponto(8.5, 17, 1, true));
        desenho.addElement(new Ponto(8.5, 17, 2, false));
        desenho.addElement(new Ponto(9, 14, 1, true));
        desenho.addElement(new Ponto(9, 14, 2, false));
        desenho.addElement(new Ponto(7.5, 12, 1, true));
        desenho.addElement(new Ponto(7.5, 12, 2, false));
        desenho.addElement(new Ponto(9.5, 12, 1, true));
        desenho.addElement(new Ponto(9.5, 12, 2, false));

        // rampa
        desenho.addElement(new Ponto(15, 12, 1, true));
        desenho.addElement(new Ponto(15, 14, 1, false));
        desenho.addElement(new Ponto(17, 14, 1, false));
        desenho.addElement(new Ponto(15, 12, 1, false));
        //parte de trás
        desenho.addElement(new Ponto(15, 12, 3, true));
        desenho.addElement(new Ponto(15, 14, 3, false));
        desenho.addElement(new Ponto(17, 14, 3, false));
        desenho.addElement(new Ponto(15, 12, 3, false));
        //conectores
        desenho.addElement(new Ponto(15, 12, 1, true));
        desenho.addElement(new Ponto(15, 12, 3, false));
        desenho.addElement(new Ponto(15, 14, 1, true));
        desenho.addElement(new Ponto(15, 14, 3, false));
        desenho.addElement(new Ponto(17, 14, 1, true));
        desenho.addElement(new Ponto(17, 14, 3, false));
    }

    public void cenario() {
        // chão
        desenho.addElement(new Ponto(1, 14, 1, true));
        desenho.addElement(new Ponto(20, 14, 1, false));
        desenho.addElement(new Ponto(20, 15, 1, false));
        desenho.addElement(new Ponto(1, 15, 1, false));
        desenho.addElement(new Ponto(1, 14, 1, false));
        desenho.addElement(new Ponto(1, 14, 4, true));
        desenho.addElement(new Ponto(20, 14, 4, false));
        desenho.addElement(new Ponto(20, 15, 4, false));
        desenho.addElement(new Ponto(1, 15, 4, false));
        desenho.addElement(new Ponto(1, 14, 4, false));
        // conectores
        desenho.addElement(new Ponto(1, 14, 1, true));
        desenho.addElement(new Ponto(1, 14, 4, false));
        desenho.addElement(new Ponto(20, 14, 1, true));
        desenho.addElement(new Ponto(20, 14, 4, false));
        desenho.addElement(new Ponto(20, 15, 1, true));
        desenho.addElement(new Ponto(20, 15, 4, false));
        desenho.addElement(new Ponto(1, 15, 1, true));
        desenho.addElement(new Ponto(1, 15, 4, false));
        // área verde
        desenho.addElement(new Ponto(4, 14, 1, true));
        desenho.addElement(new Ponto(4, 14, 4, false));
        // arvore
        desenho.addElement(new Ponto(2.5, 14, 1.5, true));
        desenho.addElement(new Ponto(2, 14, 1.5, false));
        desenho.addElement(new Ponto(2, 12, 1.5, false));
        desenho.addElement(new Ponto(2.5, 12, 1.5, false));
        desenho.addElement(new Ponto(2.5, 14, 1.5, false));
        desenho.addElement(new Ponto(2.5, 14, 2, true));
        desenho.addElement(new Ponto(2, 14, 2, false));
        desenho.addElement(new Ponto(2, 12, 2, false));
        desenho.addElement(new Ponto(2.5, 12, 2, false));
        desenho.addElement(new Ponto(2.5, 14, 2, false));
        // conectores
        desenho.addElement(new Ponto(2.5, 14, 1.5, true));
        desenho.addElement(new Ponto(2.5, 14, 2, false));
        desenho.addElement(new Ponto(2, 14, 1.5, true));
        desenho.addElement(new Ponto(2, 14, 2, false));
        desenho.addElement(new Ponto(2, 12, 1.5, true));
        desenho.addElement(new Ponto(2, 12, 2, false));
        desenho.addElement(new Ponto(2.5, 12, 1.5, true));
        desenho.addElement(new Ponto(2.5, 12, 2, false));
        // parte de cima da árvore
        desenho.addElement(new Ponto(2, 12, 1.5, true));
        desenho.addElement(new Ponto(3, 12, 1.5, false));
        desenho.addElement(new Ponto(2.25, 8, 1.5, false));
        desenho.addElement(new Ponto(1.5, 12, 1.5, false));
        desenho.addElement(new Ponto(2, 12, 1.5, false));
        desenho.addElement(new Ponto(2, 12, 2, true));
        desenho.addElement(new Ponto(3, 12, 2, false));
        desenho.addElement(new Ponto(2.25, 8, 2, false));
        desenho.addElement(new Ponto(1.5, 12, 2, false));
        desenho.addElement(new Ponto(2, 12, 2, false));
        // conectores
        desenho.addElement(new Ponto(3, 12, 1.5, true));
        desenho.addElement(new Ponto(3, 12, 2, false));
        desenho.addElement(new Ponto(2.25, 8, 1.5, true));
        desenho.addElement(new Ponto(2.25, 8, 2, false));
        desenho.addElement(new Ponto(1.5, 12, 1.5, true));
        desenho.addElement(new Ponto(1.5, 12, 2, false));

        // primeiro prédio
        desenho.addElement(new Ponto(4, 14, 1, true));
        desenho.addElement(new Ponto(4, 4, 1, false));
        desenho.addElement(new Ponto(7, 4, 1, false));
        desenho.addElement(new Ponto(7, 14, 1, false));
        desenho.addElement(new Ponto(7, 14, 4, false));
        desenho.addElement(new Ponto(4, 14, 4, true));
        desenho.addElement(new Ponto(4, 4, 4, false));
        desenho.addElement(new Ponto(7, 4, 4, false));
        desenho.addElement(new Ponto(7, 14, 4, false));
        // conectores
        desenho.addElement(new Ponto(4, 4, 1, true));
        desenho.addElement(new Ponto(4, 4, 4, false));
        desenho.addElement(new Ponto(7, 4, 1, true));
        desenho.addElement(new Ponto(7, 4, 4, false));
        // portão do prédio
        desenho.addElement(new Ponto(4.5, 14, 1, true));
        desenho.addElement(new Ponto(4.5, 12, 1, false));
        desenho.addElement(new Ponto(6.5, 12, 1, false));
        desenho.addElement(new Ponto(6.5, 14, 1, false));
        desenho.addElement(new Ponto(4.5, 12, 1, true));
        desenho.addElement(new Ponto(4, 12, 1, false));
        desenho.addElement(new Ponto(6.5, 12, 1, true));
        desenho.addElement(new Ponto(7, 12, 1, false));

        // janelas
        desenho.addElement(new Ponto(4.5, 11, 1, true));
        desenho.addElement(new Ponto(6.5, 11, 1, false));
        desenho.addElement(new Ponto(6.5, 10, 1, false));
        desenho.addElement(new Ponto(4.5, 10, 1, false));
        desenho.addElement(new Ponto(4.5, 11, 1, false));
        desenho.addElement(new Ponto(5.5, 11, 1, true));
        desenho.addElement(new Ponto(5.5, 10, 1, false));
        // janela 2
        desenho.addElement(new Ponto(4.5, 9, 1, true));
        desenho.addElement(new Ponto(6.5, 9, 1, false));
        desenho.addElement(new Ponto(6.5, 8, 1, false));
        desenho.addElement(new Ponto(4.5, 8, 1, false));
        desenho.addElement(new Ponto(4.5, 9, 1, false));
        desenho.addElement(new Ponto(5.5, 9, 1, true));
        desenho.addElement(new Ponto(5.5, 8, 1, false));
        // janela 3
        desenho.addElement(new Ponto(4.5, 7, 1, true));
        desenho.addElement(new Ponto(6.5, 7, 1, false));
        desenho.addElement(new Ponto(6.5, 6, 1, false));
        desenho.addElement(new Ponto(4.5, 6, 1, false));
        desenho.addElement(new Ponto(4.5, 7, 1, false));
        desenho.addElement(new Ponto(5.5, 7, 1, true));
        desenho.addElement(new Ponto(5.5, 6, 1, false));

        // area verde 2
        desenho.addElement(new Ponto(12, 14, 4, true));
        desenho.addElement(new Ponto(12, 14, 1, false));
        // arvore 2
        desenho.addElement(new Ponto(9.5, 14, 1.5, true));
        desenho.addElement(new Ponto(9, 14, 1.5, false));
        desenho.addElement(new Ponto(9, 12, 1.5, false));
        desenho.addElement(new Ponto(9.5, 12, 1.5, false));
        desenho.addElement(new Ponto(9.5, 14, 1.5, false));
        desenho.addElement(new Ponto(9.5, 14, 2, true));
        desenho.addElement(new Ponto(9, 14, 2, false));
        desenho.addElement(new Ponto(9, 12, 2, false));
        desenho.addElement(new Ponto(9.5, 12, 2, false));
        desenho.addElement(new Ponto(9.5, 14, 2, false));
        // conectores
        desenho.addElement(new Ponto(9.5, 14, 1.5, true));
        desenho.addElement(new Ponto(9.5, 14, 2, false));
        desenho.addElement(new Ponto(9, 14, 1.5, true));
        desenho.addElement(new Ponto(9, 14, 2, false));
        desenho.addElement(new Ponto(9, 12, 1.5, true));
        desenho.addElement(new Ponto(9, 12, 2, false));
        desenho.addElement(new Ponto(9.5, 12, 1.5, true));
        desenho.addElement(new Ponto(9.5, 12, 2, false));
        // parte de cima da árvore 2
        desenho.addElement(new Ponto(9, 12, 1.5, true));
        desenho.addElement(new Ponto(10, 12, 1.5, false));
        desenho.addElement(new Ponto(9.25, 8, 1.5, false));
        desenho.addElement(new Ponto(8.5, 12, 1.5, false));
        desenho.addElement(new Ponto(9, 12, 1.5, false));
        desenho.addElement(new Ponto(9, 12, 2, true));
        desenho.addElement(new Ponto(10, 12, 2, false));
        desenho.addElement(new Ponto(9.25, 8, 2, false));
        desenho.addElement(new Ponto(8.5, 12, 2, false));
        desenho.addElement(new Ponto(9, 12, 2, false));
        // conectores
        desenho.addElement(new Ponto(10, 12, 1.5, true));
        desenho.addElement(new Ponto(10, 12, 2, false));
        desenho.addElement(new Ponto(9.25, 8, 1.5, true));
        desenho.addElement(new Ponto(9.25, 8, 2, false));
        desenho.addElement(new Ponto(8.5, 12, 1.5, true));
        desenho.addElement(new Ponto(8.5, 12, 2, false));

        // farmacia
        desenho.addElement(new Ponto(12, 14, 1, true));
        desenho.addElement(new Ponto(12, 8, 1, false));
        desenho.addElement(new Ponto(20, 8, 1, false));
        desenho.addElement(new Ponto(20, 14, 1, false));
        // parte de tras
        desenho.addElement(new Ponto(12, 14, 4, true));
        desenho.addElement(new Ponto(12, 8, 4, false));
        desenho.addElement(new Ponto(20, 8, 4, false));
        desenho.addElement(new Ponto(20, 14, 4, false));
        // conectores
        desenho.addElement(new Ponto(12, 8, 1, true));
        desenho.addElement(new Ponto(12, 8, 4, false));
        desenho.addElement(new Ponto(20, 8, 1, true));
        desenho.addElement(new Ponto(20, 8, 4, false));
        // logo
        desenho.addElement(new Ponto(12, 9, 1, true));
        desenho.addElement(new Ponto(20, 9, 1, false));
        desenho.addElement(new Ponto(15, 8.45, 1, true));
        desenho.addElement(new Ponto(15, 8.65, 1, false));
        desenho.addElement(new Ponto(15.1, 8.65, 1, false));
        desenho.addElement(new Ponto(15.1, 8.8, 1, false));
        desenho.addElement(new Ponto(15.2, 8.8, 1, false));
        desenho.addElement(new Ponto(15.2, 8.65, 1, false));
        desenho.addElement(new Ponto(15.3, 8.65, 1, false));
        desenho.addElement(new Ponto(15.3, 8.45, 1, false));
        desenho.addElement(new Ponto(15.2, 8.45, 1, false));
        desenho.addElement(new Ponto(15.2, 8.3, 1, false));
        desenho.addElement(new Ponto(15.1, 8.3, 1, false));
        desenho.addElement(new Ponto(15.1, 8.45, 1, false));
        desenho.addElement(new Ponto(15, 8.45, 1, false));
        // porta farmacia
        desenho.addElement(new Ponto(14, 12, 1, true));
        desenho.addElement(new Ponto(17, 12, 1, false));
        desenho.addElement(new Ponto(17, 14, 1, false));
        desenho.addElement(new Ponto(14, 12, 1, true));
        desenho.addElement(new Ponto(14, 14, 1, false));
        desenho.addElement(new Ponto(15.5, 12, 1, true));
        desenho.addElement(new Ponto(15.5, 14, 1, false));
    }

    public void translada(double dx, double dy, double dz) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            p.setX(p.getX() + dx);
            p.setY(p.getY() + dy);
            p.setZ(p.getZ() + dz);
            desenho.set(i, p);
        }
    }

    public void rotaciona(double h, double q, double b) {
        double m[][] = new double[4][4];
        double ch = Math.cos(h);
        double cp = Math.cos(q);
        double cb = Math.cos(b);
        double sh = Math.sin(h);
        double sp = Math.sin(q);
        double sb = Math.sin(b);
        m[1][1] = ch * cb - sh * sp * sb;
        m[1][2] = sb * ch + cb * sp * sh;
        m[1][3] = -cp * sh;
        m[2][1] = -sb * cp;
        m[2][2] = cb * cp;
        m[2][3] = sp;
        m[3][1] = cb * sh + sb * sp * ch;
        m[3][2] = sb * sh - cb * sp * ch;
        m[3][3] = ch * cp;
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            p = (Ponto) desenho.get(i);
            double x = p.getX();
            double y = p.getY();
            double z = p.getZ();
            double xx = (x * m[1][1] + y * m[1][2] + z * m[1][3]);
            double yy = (x * m[2][1] + y * m[2][2] + z * m[2][3]);
            double zz = (x * m[3][1] + y * m[3][2] + z * m[3][3]);
            p.setX(xx);
            p.setY(yy);
            p.setZ(zz);
            desenho.set(i, p);
        }
    }

    public void deforma(boolean maior) {
        Ponto p;
        for (int i = 0; i < desenho.size(); i++) {
            if (maior) {
                p = (Ponto) desenho.get(i);
                p.setX(((p.getX() - getCentrox()) * 0.1) + p.getX());
                p.setY(((p.getY() - getCentroy()) * 0.1) + p.getY());
                p.setZ((p.getZ() * 0.1) + p.getZ());
                desenho.set(i, p);
            } else {
                p = (Ponto) desenho.get(i);
                p.setX(p.getX() - ((p.getX() - getCentrox()) * 0.1));
                p.setY(p.getY() - ((p.getY() - getCentroy()) * 0.1));
                p.setZ(p.getZ() - (p.getZ() * 0.1));
                desenho.set(i, p);
            }
        }
    }

    public double getCentroy() {
        return centroy;
    }

    public void setCentroy(double centroy) {
        this.centroy = centroy;
    }

    public double getCentrox() {
        return centrox;
    }

    public void setCentrox(double centrox) {
        this.centrox = centrox;
    }
}
