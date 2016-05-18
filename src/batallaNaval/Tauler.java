/*
 * Classe Tauler definida com un panell on es defineix una taula 8x8 de caselles
 *
 * El constructor defineix totes les caselles com a rectangles d'un color
 * determinat inicialitzant-les buides
 *
 * El mettode paintComponent recorr el tauler pintant les caselles
 *
 */
package batallaNaval;

/**
 *
 * @author albertgelabert
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Tauler extends JPanel {

    private static int DIMENSIO = 10;//dimensio, en caselles, del tauler
    private static int MAXIM = 300;//mida maxima del tauler
    private static int COSTAT = MAXIM / DIMENSIO;//mida del costat d'una casella
    public Casella t[][];   //array bidimensional de caselles 
    Random rdm=new Random();    //per generar nombres aleatoris. 
    private int contador=999;   //contador=0 salta la finestra de victoria. Primer val aixo perque no aparegui. 
    private int vaixellsDe1=0;  //comptadors del nombre de vaixells de cada mida
    private int vaixellsDe2=0;
    private int vaixellsDe3=0;
    private int vaixellsDe4=0;
    Color blautransparent= new Color(0, 0, 150, 50);//color del tauler
    Color aigua=new Color(0,0,250,150);//color quan feim aigua
    
    public Tauler() {//constructor
        t = new Casella[DIMENSIO][DIMENSIO];
        int x = 0;
        for (int i = 0; i < DIMENSIO; i++) {
            int y = 0;
            for (int j = 0; j < DIMENSIO; j++) {
                Rectangle2D.Float r = new Rectangle2D.Float(x, y, getCOSTAT(), getCOSTAT());
                t[i][j] = new Casella(r, blautransparent, false, 0,-1,false);
                y += COSTAT;
            }
            x += COSTAT;
        }
    }

    public void Dispara(int x, int i){
        if (!t[x][i].getDisparada()) {
            if(t[x][i].getColocada()){//primer dispar i tocat
                t[x][i].setCol(Color.red);
                contador--;
            }else if(!t[x][i].getColocada()){//primer dispar i aigua
                t[x][i].setCol(aigua);            
            }
        }t[x][i].setDisparada(true);
    }
    

    public void Posa(Color col, int x, int i, int mida, int direccio){//direccio l'obtenim amb evt.getButton()
        if(!t[x][i].getColocada()){
             if (direccio==3){//vertical
                direccio=1;
            }else{//horitzontal
                 direccio=0;
            }
            t[x][i].setColocada(true);
            t[x][i].setCol(col);
            t[x][i].setMida(mida);
            t[x][i].setDireccio(direccio);
            contador++;
        }
        
        
    }
    
    public boolean pucColocarVaixell(int x, int i, int direccio, int mida){
        boolean afirmatiu=true;
        int veins;
        switch(direccio){
            case 1://horitzontal
                veins=0;
                for (int idx=0;idx<mida;idx++){
                    veins+=veins(x+idx,i);
                }System.out.println("veins: "+veins);
                if (veins!=0){
                    afirmatiu=false;
                }  
                break;
            case 3://vertical
                veins=0;
                for (int idx=0;idx<mida;idx++){
                    veins+=veins(x,i+idx);
                }System.out.println("veins: "+veins);
                if (veins!=0){
                    afirmatiu=false;
                }break;
        }
        return afirmatiu;
        
    }
    
    
    public void ferVaixellsInvisibles(){
        for (int i=0;i<DIMENSIO;i++){
            for (int j = 0; j < DIMENSIO; j++) {
                if(t[i][j].getColocada()&&!t[i][j].getDisparada()){
                    t[i][j].setCol(new Color(0, 0, 155, 50));
                }
            }
        }
   }
    public void ferVaixellsVisibles(){
        for (int i=0;i<DIMENSIO;i++){
            for (int j = 0; j < DIMENSIO; j++) {
                if(t[i][j].getColocada()&&!t[i][j].getDisparada()){
                    t[i][j].setCol(Color.YELLOW);
                }
            }
        }
    }
    
    public void reiniciar(){
        for (int i=0;i<DIMENSIO;i++){
            for (int j=0;j<DIMENSIO;j++){
                t[i][j].setCol(new Color(0, 0, 155, 50));
                t[i][j].setColocada(false);
                t[i][j].setDireccio(0);
                t[i][j].setMida(0);
                t[i][j].setDisparada(false);
                vaixellsDe1=0;
                vaixellsDe2=0;
                vaixellsDe3=0;
                vaixellsDe4=0;
            }
        }
    }
 
    public boolean hihavaixell(int x, int i){
        return t[x][i].getColocada();
    }
    
    public void ColocaAleatoriament(){
        contador=0;
        ColocarVaixell(4);
        for (int i=0; i<2;i++){
            ColocarVaixell(3);
        }
        for (int i=0; i<3;i++){
            ColocarVaixell(2);
        }
        for (int i=0; i<4;i++){
            ColocarVaixell(1);
        }
        
        
    }
    
    private void ColocarVaixell(int size){
        int x=rdm.nextInt(DIMENSIO-1);
        int i=rdm.nextInt(DIMENSIO-1);
        System.out.println(x+" "+i+" mida: "+size);
        if(x<=t.length-size){//si hi cap segons la mida horitzontalment
            System.out.println("hi cap horitzontal");
            if(pucColocarVaixell(x,i,1,size)){//no interferesc amb altres vaixells
                for (int n=0;n<size;n++){
                Posa(null,x+n,i,size,0);//col·loc el vaixell
                }
                System.out.println("COLO·LOCAT! duim "+contador+" vaixells");
            }else {
                System.out.println("no es pot colo·locar, interferim amb un altre vaixell");
                ColocarVaixell(size);
            }
        }
        else if(i<=t.length-size){//si hi cap segons la mida verticalment
            System.out.println("hi cap vertical");
            if (pucColocarVaixell(x,i,3,size)){
                for (int n=0;n<size;n++){
                    Posa(null,x,i+n,size,1);
                }
                System.out.println("COLO·LOCAT!");
            }else {
                System.out.println("no es por col·locar, interferim amb un altre vaixell");
                ColocarVaixell(size);
            }
        }
        else {
            System.out.println("no hi cap, anem a probar un altra cop");
            ColocarVaixell(size);
        }
    }

 
    public boolean correcte(){
        return vaixellsDe1==4&&vaixellsDe2==3&&vaixellsDe3==2&&vaixellsDe4==1;
    }
    
    private int veins(int x, int i){//calcula el nombre de veins a la posicio x i
        int veins=0;
        if (x>0&&t[x-1][i].getColocada()) veins++;//esquerra
        if (i>0&&x>0&&t[x-1][i-1].getColocada()) veins++;//esquerra-adalt
        if (i<DIMENSIO-1&&x>0&&t[x-1][i+1].getColocada()) veins++;//esquerra-abaix
        if (i>0&&t[x][i-1].getColocada()) veins++;//adalt
        if (i<DIMENSIO-1&&t[x][i+1].getColocada()) veins++;//abaix
        if (x<DIMENSIO-1&&t[x+1][i].getColocada()) veins++;//dreta
        if (i<DIMENSIO-1&&x<DIMENSIO-1&&t[x+1][i+1].getColocada()) veins++;//dreta-abaix
        if (x<DIMENSIO-1&&i>0&&t[x+1][i-1].getColocada()) veins++;//dreta-adalt
        return veins;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i <DIMENSIO; i++) {
            for (int j = 0; j <DIMENSIO; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }
   



    /**
     * @return the t
     */
    public Casella[][] getT() {
        return t;
    }

    /**
     * @param t the t to set
     */
    public void setT(Casella[][] t) {
        this.t = t;
    }
    /**
     * @return the DIMENSIO
     */
    public static int getDIMENSIO() {
        return DIMENSIO;
    }

    /**
     * @param aDIMENSIO the DIMENSIO to set
     */
    public static void setDIMENSIO(int aDIMENSIO) {
        DIMENSIO = aDIMENSIO;
    }

    /**
     * @return the MAXIM
     */
    public static int getMAXIM() {
        return MAXIM;
    }

    /**
     * @param aMAXIM the MAXIM to set
     */
    public static void setMAXIM(int aMAXIM) {
        MAXIM = aMAXIM;
    }

    /**
     * @return the COSTAT
     */
    public static int getCOSTAT() {
        return COSTAT;
    }

    /**
     * @param aCOSTAT the COSTAT to set
     */
    public static void setCOSTAT(int aCOSTAT) {
        COSTAT = aCOSTAT;
    }

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }

    /**
     * @param contador the contador to set
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * @return the vaixellsDe1
     */
    public int getVaixellsDe1() {
        return vaixellsDe1;
    }

    /**
     * @param vaixellsDe1 the vaixellsDe1 to set
     */
    public void setVaixellsDe1(int vaixellsDe1) {
        this.vaixellsDe1 = vaixellsDe1;
    }

    /**
     * @return the vaixellsDe2
     */
    public int getVaixellsDe2() {
        return vaixellsDe2;
    }

    /**
     * @param vaixellsDe2 the vaixellsDe2 to set
     */
    public void setVaixellsDe2(int vaixellsDe2) {
        this.vaixellsDe2 = vaixellsDe2;
    }

    /**
     * @return the vaiexllsDe3
     */
    public int getVaixellsDe3() {
        return vaixellsDe3;
    }

    /**
     * @param vaiexllsDe3 the vaiexllsDe3 to set
     */
    public void setVaixellsDe3(int vaiexllsDe3) {
        this.vaixellsDe3 = vaiexllsDe3;
    }

    /**
     * @return the vaixellsDe4
     */
    public int getVaixellsDe4() {
        return vaixellsDe4;
    }

    /**
     * @param vaixellsDe4 the vaixellsDe4 to set
     */
    public void setVaixellsDe4(int vaixellsDe4) {
        this.vaixellsDe4 = vaixellsDe4;
    }

    
    
}
