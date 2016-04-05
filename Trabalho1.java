
import java.awt.*;
import java.awt.geom.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Trabalho1 extends Frame {

    public class MyFinishWindow extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    // Codigo copiado do exemplo - serve para poder fechar a janela
    Trabalho1() {
        //Enables the closing of the window
        addWindowListener(new MyFinishWindow());
    }

    private static int repeticoes, raio, x, y, segmentos;

    public ArrayList<Point> a = new ArrayList<Point>(); // array com os pontos do primeiro octante
    public ArrayList<Point> b = new ArrayList<Point>(); // array com os pontos do segundo octante
    public ArrayList<Point> c = new ArrayList<Point>(); // array com os pontos do quarto octante
    public ArrayList<Point> d = new ArrayList<Point>(); // array com os pontos do terceiro octante
    public ArrayList<Point> e = new ArrayList<Point>(); // array com os pontos ordenados no sentido horario de todos os octantes
    public ArrayList<Point> arrayFinal = new ArrayList<Point>(); // array com os pontos de interesse
    public ArrayList<Point> vetorLinhas = new ArrayList<Point>(); // array com os pontos de interesse

    public void setXPos(int n) {
        x = n;
    }

    public static int getXPos() {
        return x;
    }

    public void setYPos(int n) {
        y = n;
    }

    public static int getYPos() {
        return y;
    }

    public void setRaio(int n) {
        raio = n;
    }

    public static int getRaio() {
        return raio;
    }

    public void setSegmentos(int n) {
        segmentos = n;
    }

    public static int getSegmentos() {
        return segmentos;
    }

    public void setRepeticoes(int n) {
        repeticoes = n;
    }

    public static int getRepeticoes() {
        return repeticoes;
    }

    @SuppressWarnings("empty-statement")
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke bsThick = new BasicStroke(2.0f); // grossura da linha
        g2d.setStroke(bsThick);
        Shape shape = new Rectangle(getXPos()-getRaio()-5, getYPos()-5, 10, 10);
        Shape shape2 = new Rectangle(70,70, 10, 10);
        g.setColor(Color.black); // seta a cor da linha como preta
        //Faz os calculos dos pontos por Bresenham
        DrawCircle(getXPos(), getYPos(), getRaio(), g2d);
        e.addAll(a);
        for (int i = b.size() - 1; i >= 0; i--) {
            e.add(b.get(i));
        }
        e.addAll(d);
        for (int i = c.size() - 1; i >= 0; i--) {
            e.add(c.get(i));
        }

        for (int k = 0; k < getRepeticoes(); k++) {
            for (int i = 0; i <= getSegmentos(); i++) {
                if (i > 0) {
                    arrayFinal.add(
                    new Point(
                    ((e.get(((e.size() / getSegmentos()) * i) - 1 + (e.size() % getSegmentos())).x) + getRaio() * 2 * k),
                            e.get(((e.size() / getSegmentos()) * i) - 1 + (e.size() % getSegmentos())).y));
                } else {
                    arrayFinal.add(new Point ((e.get((e.size() / getSegmentos()) * i).x + getRaio() * 2 * k), 
												e.get(((e.size() / getSegmentos()) * i) + (e.size() % getSegmentos())).y));
                }
            }
        }    
        for (int i = 0; i < arrayFinal.size()-1; i++) {
            drawLine(arrayFinal.get(i).x, arrayFinal.get(i).y, arrayFinal.get(i+1).x, arrayFinal.get(i+1).y);           
        }

        /*AffineTransform singleRotation = new AffineTransform();
	singleRotation.setToRotation(-Math.PI/180);
        AffineTransform accumulatedRotation = new AffineTransform();

        AffineTransform singleScale = new AffineTransform();
        singleScale.setToScale(5, 5);
        AffineTransform accumulatedScale = new AffineTransform();
                
	AffineTransform singleTranslation = new AffineTransform();
	AffineTransform accumulatedTranslation = new AffineTransform();
	accumulatedTranslation.setToTranslation(150,150);
        
        AffineTransform Translation = new AffineTransform();
        
        AffineTransform volta = new AffineTransform();
        
        AffineTransform gira = new AffineTransform();

        AffineTransform handTransform = new AffineTransform();*/ 

        for(int k=0; k < vetorLinhas.size()-1;k++){
            
            /*singleRotation.setToRotation(-Math.PI/185);
            Translation.setToTranslation(-vetorLinhas.get(k).x, -vetorLinhas.get(k).y);
            volta.setToTranslation(vetorLinhas.get(k).x, vetorLinhas.get(k).y);
            gira.setTransform(Translation);
            gira.preConcatenate(singleRotation);
            gira.preConcatenate(volta);
            clearWindow(g2d);
            g2d.draw(gira.createTransformedShape(shape));*/
            
            
            /*singleTranslation.setToTranslation(vetorLinhas.get(k+1).x-vetorLinhas.get(k).x, vetorLinhas.get(k+1).y-vetorLinhas.get(k).y);
            volta.setToTranslation(vetorLinhas.get(k).x, vetorLinhas.get(k).y);
	    handTransform.setTransform(accumulatedTranslation);
            handTransform.preConcatenate(accumulatedRotation);
            clearWindow(g2d);
            g2d.draw(accumulatedTranslation.createTransformedShape(shape));
            g2d.fill(accumulatedRotation.createTransformedShape(shape2));
            accumulatedTranslation.preConcatenate(singleTranslation);*/
   	    //accumulatedRotation.preConcatenate(singleRotation);
            try{
                sustain();
            }
            catch(Exception e){};
            }
    }

    
    private void drawLine(int x1, int y1, int x2, int y2) {
        // delta of exact value and rounded value of the dependant variable
        int d = 0;
 
        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);
 
        int dy2 = (dy << 1); // slope scaling factors to avoid floating
        int dx2 = (dx << 1); // point
 
        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;
 
        if (dy <= dx) {
            for (;;) {
                vetorLinhas.add(new Point(x1, y1));
                if (x1 == x2)
                    break;
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                vetorLinhas.add(new Point(x1, y1));
                if (y1 == y2)
                    break;
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }
    
    public void DrawCircle(int x0, int y0, int radius, Graphics2D g2) {
        int x = radius;
        int y = 0;
        int radiusError = 1 - x;

        while (x >= y) {
            //g2.drawLine(-x + x0, -y + y0, -x + x0, -y + y0);
            a.add(new Point(-x + x0, -y + y0));
            //g2.drawLine(-y + x0, -x + y0, -y + x0, -x + y0);
            b.add(new Point(-y + x0, -x + y0));
            //g2.drawLine( x + x0, -y + y0,  x + x0, -y + y0);
            c.add(new Point(x + x0, -y + y0));
            //g2.drawLine( y + x0, -x + y0,  y + x0, -x + y0);
            d.add(new Point(y + x0, -x + y0));

            y++;
            if (radiusError < 0) {
                radiusError += 2 * y + 1;
            } else {
                x--;
                radiusError += 2 * (y - x) + 1;
            }
        }
    }
    
		public void clearWindow(Graphics2D g)
	{
		g.setPaint(Color.white);
		g.fill(new Rectangle(0,0,800,600));
		g.setPaint(Color.black);
	}
	public void sustain() throws InterruptedException
	{
			Thread.sleep(100);
	}

	// A inclinacao em que a reta a ser percorrida ira ficar eh de 90-(90/segmentos)
	// args[0]=x
    // args[1]=y
    // args[2]=raio
    // args[3]=segmentos
    // args[4]=repeticoes
    public static void main(String[] args) {
        Trabalho1 f = new Trabalho1();
        f.setXPos(Integer.parseInt(args[0]));
        f.setYPos(Integer.parseInt(args[1]));
        f.setRaio(Integer.parseInt(args[2]));
        f.setSegmentos(Integer.parseInt(args[3]));
        f.setRepeticoes(Integer.parseInt(args[4]));
        f.setTitle("Trabalho1"); // titulo da janela
        f.setSize(800, 600); // vai fazer com que a cena fique no meio da janela
        f.setVisible(true); // torna a janela visivel
    }
}
