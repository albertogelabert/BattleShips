/*
 * Classe casella
 *
 * El constructor defineix una casella com un rectangle d'un color i si estÃ 
 * ocupada o no
 *
 * El metode paintComponent pinta el rectangle de la casella
 */

package batallaNaval;

/**
 *
 * @author albertgelabert
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casella {
    
    private Rectangle2D.Float rec;
    private Color col;  //color de la cassella
    private Boolean colocada; //si conté un vaixell o no
    private Boolean disparada;  //si hi hem disparat o no
    private int mida; //mida del vaixell que conté
    private int direccio;//horitzontal-0  vertical-1

    public Casella(Rectangle2D.Float r, Color c, Boolean col,int mida,int direccio, Boolean disp){
        this.rec = r;
        this.col = c;
        this.colocada = col;
        this.mida=0;
        this.direccio=-1;
        this.disparada=disp;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.col);
        g2d.fill(this.rec);
        g2d.setColor(Color.lightGray);
        g2d.draw(rec);
    }


    /**
     * @return the rec
     */
    public Rectangle2D.Float getRec() {
        return rec;
    }

    /**
     * @param rec the rec to set
     */
    public void setRec(Rectangle2D.Float rec) {
        this.rec = rec;
    }

    /**
     * @return the col
     */
    public Color getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(Color col) {
        this.col = col;
    }

    /**
     * @return the colocada
     */
    public Boolean getColocada() {
        return colocada;
    }

    /**
     * @param colocada the colocada to set
     */
    public void setColocada(Boolean colocada) {
        this.colocada = colocada;
    }


    /**
     * @return the disparada
     */
    public Boolean getDisparada() {
        return disparada;
    }

    /**
     * @param disparada the disparada to set
     */
    public void setDisparada(Boolean disparada) {
        this.disparada = disparada;
    }

    /**
     * @return the mida
     */
    public int getTamany() {
        return mida;
    }

    /**
     * @param mida the mida to set
     */
    public void setMida(int mida) {
        this.mida = mida;
    }

    /**
     * @return the direccio
     */
    public int getDireccio() {
        return direccio;
    }

    /**
     * @param direccio the direccio to set
     */
    public void setDireccio(int direccio) {
        this.direccio = direccio;
    }


     

}
