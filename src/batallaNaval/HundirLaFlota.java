/*
El constructor HundirLaFlota crea tota la finestra de joc amb els
seus corresponents ActionListeners, on anirem controlant el joc.

Cream un action listener per a cada component que ens interessa 
controlar

La finestra es forma per: 
-Una barra d'eines on tenim les seguents opcions:
    ·Sortir. Ens finalitzara l'execucio
    ·Solucio. Ens mostrara els vaixells de la maquina
    ·AmagarSolucio. Amagara els vaixells de la maquina que no haguem disparat
    ·Reiniciar. Reiniciara tot el joc
-Dos taulers, un per la maquina i l'altre per al jugador
-Boto de jugar, que comprova si estan ben posicionats el vixells per començar el joc
-Quatre botons, per indicar la mida del vaixell que volem posicionar
-Text Inferior. Hem possat un JTextPane a la part inferior de la finestra per a 
mostrar missatges informatius al jugador. 
-Un fons, dibuixat mitjançant un JLabel
*/
package batallaNaval;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class HundirLaFlota{
    private static final JFrame finestra = new JFrame();; // Es la finestra que contindra  tots els components del nostre programa.
    Random rdm=new Random();  
    final Tauler tauler1 = new Tauler();
    final Tauler tauler2 = new Tauler();
    JTextPane textInferior = new JTextPane();
    JButton botoInferior = new JButton("Jugar");
    JMenuBar jmbarEditor;//barra d'eines
    JMenu jmnuJoc;//menu de barra d'eines
    JMenuItem jmItemSortir;//items que afegirem al menu
    JMenuItem jmItemSolucio;
    JMenuItem jmItemReiniciar;
    JMenuItem jmItemAmagaSolucio;
    JLabel label = new JLabel();//etiqueta per al fons
    ImageIcon fondo = new ImageIcon("batallanaval.jpg");
    DialegFinal victoria=new DialegFinal("ENHORABONA HAS GUANYAT!");
    DialegFinal derrota;
    JButton vaixell1 = new JButton("1");//botons per indicar la mida del vaixell
    JButton vaixell2 = new JButton("2");
    JButton vaixell3 = new JButton("3");
    JButton vaixell4 = new JButton("4");
    boolean interruptorTauler1=true;
    boolean interruptorTauler2=false;
    private int mida;
    private int direccio;
    boolean tornJugador=false;
    String dialeginicial;
    
    public static void main(String[] args) {
        HundirLaFlota hundirLaFlota = new HundirLaFlota();
    }

    
    public HundirLaFlota() {
        this.dialeginicial = "Bones Jugador! Introdueix 1 vaixell de 4 posicions, 2 de 3 posicions, 3 de 2 posicions i 4 de 1 posicio.";
                
        //dialeg de boto SALIR
        final JDialog Confirmacion= new JDialog(finestra, "Salir", true);      
        Confirmacion.setSize(300,200);
        Confirmacion.getContentPane().setLayout(null);
        JButton BSi = new JButton("Si"); //Boton Si Salir
        BSi.setBounds(75, 100, 50, 35);
        JButton BNo = new JButton("No"); //Boton No Salir
        BNo.setBounds(140, 100, 50, 35);
        JLabel EtiqMsgSalir = new JLabel("¿Desea salir de la partida?");
        EtiqMsgSalir.setFont(new Font("Arial",Font.PLAIN,20));
        EtiqMsgSalir.setBounds(25, 25, 250,50);
        Confirmacion.add(BSi);
        Confirmacion.add(BNo);
        Confirmacion.add(EtiqMsgSalir);
        Confirmacion.setLocationRelativeTo(null);
        

        // Menu 
        jmbarEditor = new JMenuBar();
        jmnuJoc = new JMenu("Joc");
        jmnuJoc.setMnemonic(KeyEvent.VK_J);//assignam un mnemonic (subratlla la lletra)
        jmItemSolucio = new JMenuItem("Solucio"); 
        jmItemSortir = new JMenuItem("Sortir"); 
        jmItemReiniciar = new JMenuItem("Reiniciar");
        jmItemAmagaSolucio= new JMenuItem("Amagar solucio");
        //Afegim les opcions al menu
        jmnuJoc.add(jmItemSolucio);
        jmnuJoc.add(jmItemReiniciar);
        jmnuJoc.add(jmItemAmagaSolucio);
        jmnuJoc.add(new JSeparator());//LINEA SEPARATORIA
        jmnuJoc.add(jmItemSortir);
        jmbarEditor.add(jmnuJoc);
        finestra.setJMenuBar(jmbarEditor);
        
        finestra.setLayout(null);//per poder col·locar les components a la finestra amb pixels
        finestra.setSize(635, 440);

        
        finestra.add(textInferior);
        textInferior.setBounds(0, 370, 635, 20);
        textInferior.setText(dialeginicial);
        textInferior.setEditable(false);
        
        finestra.add(tauler1);
        tauler2.setBounds(0, 0, Tauler.getMAXIM()+1, Tauler.getMAXIM()+1);
        finestra.add(tauler2);
        tauler1.setBounds(328,0,Tauler.getMAXIM()+1,Tauler.getMAXIM()+1);
        
        finestra.add(vaixell1);
        vaixell1.setBounds(370, 327, 50, 35);
        finestra.add(vaixell2);
        vaixell2.setBounds(430, 327, 50, 35);
        finestra.add(vaixell3);
        vaixell3.setBounds(490, 327, 50, 35);
        finestra.add(vaixell4);
        vaixell4.setBounds(550, 327, 50, 35);
        
        botoInferior.setAlignmentX(0.5f); // Centram el boto.
        finestra.add(botoInferior);
        botoInferior.setBounds(270,327,80,35);
        
        
        finestra.setVisible(true);
        finestra.setLocationRelativeTo(null);
        finestra.getRootPane().setDefaultButton(botoInferior);//botÃ³ per omisiÃ³ intro
        finestra.setResizable(false);
        finestra.setTitle("Battle Ships");
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label.setIcon(fondo);
        finestra.add(label);
        label.setBounds(0, 0, finestra.getWidth(), finestra.getHeight());
        
        /*********************** COMPORTAMENT REINICIAR *********************************************/
        jmItemReiniciar.setMnemonic(KeyEvent.VK_R);
        jmItemReiniciar.setAccelerator(KeyStroke.getKeyStroke('R'));
        jmItemReiniciar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            tornJugador=false;
            interruptorTauler1=true;
            botoInferior.setVisible(true);
            vaixell1.setVisible(true);
            vaixell2.setVisible(true);
            vaixell3.setVisible(true);
            vaixell4.setVisible(true);
            tauler1.reiniciar();
            tauler2.reiniciar();
            finestra.repaint();
            tauler2.setContador(999);
            textInferior.setText(dialeginicial);
        }});
        /*********************** COMPORTAMENT SOLUCIO *********************************************/
            jmItemSolucio.setMnemonic(KeyEvent.VK_O);
            jmItemSolucio.setAccelerator(KeyStroke.getKeyStroke('O'));//asignam un 'shortcut' al jmItem
        jmItemSolucio.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            tauler2.ferVaixellsVisibles();
            finestra.repaint();
        }});
        /*********************** COMPORTAMENT AMAGA SOLUCIO****************************************/
        jmItemAmagaSolucio.setMnemonic(KeyEvent.VK_A);
        jmItemAmagaSolucio.setAccelerator(KeyStroke.getKeyStroke('A'));
        jmItemAmagaSolucio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                tauler2.ferVaixellsInvisibles();
                finestra.repaint();
            }
        });
        /*********************** COMPORTAMENT SORTIR *********************************************/
        jmItemSortir.setMnemonic(KeyEvent.VK_S);
        jmItemSortir.setAccelerator(KeyStroke.getKeyStroke('S'));
        jmItemSortir.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Confirmacion.setVisible(true);
        }        
        });
         /**************************** ACTION LISTENER SORTIR ***************************************/
       BSi.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                System.exit(0);
            }
        });
        BNo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Confirmacion.setVisible(false);
            }
        });
        Confirmacion.getRootPane().setDefaultButton(BSi);   //voto per defecte quan apretam intro/espai

        
        
        
        /****************************** ACTION LISTENER BOTO INFERIOR JUGAR *************************/
        
        botoInferior.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {  
                
                if (tauler1.correcte()){//si hi ha els vaixells que toca haver
                    tauler2.ColocaAleatoriament();
                    tauler2.ferVaixellsInvisibles();
                    tornJugador=true;
                    interruptorTauler1=false;
                    interruptorTauler2=true;
                    tauler1.setContador(20);//posam manualment el comptador a 20
                    textInferior.setText("Configuració correcte. Endavant a jugar. ");
                    botoInferior.setVisible(false);//amagam el que no necessitem
                    vaixell1.setVisible(false);
                    vaixell2.setVisible(false);
                    vaixell3.setVisible(false);
                    vaixell4.setVisible(false);
                }else {//vaixells incorrecte, imprimim el nombre de vaixells per a que l'usuari ho modifiqui
                    textInferior.setText("Torna a colocar els vaixells. Vaixells de 1: "+tauler1.getVaixellsDe1()+" | Vaixells de 2: "+
                            tauler1.getVaixellsDe2()+" | vaixells de 3: "+tauler1.getVaixellsDe3()+" | vaixells de 4: "+tauler1.getVaixellsDe4());
                }
                
            }
            
        });
        
        vaixell1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {  
                mida=1;                
            }
            
        });
        vaixell2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {  
                mida=2;                
            }
            
        });
        vaixell3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {  
                mida=3;                
            }
            
        });
        vaixell4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {  
                mida=4;                
            }
            
        });
        
        
        /****************************** ACTION LISTENER TAULER 1 *************************/

        tauler1.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (interruptorTauler1&&mida!=0){
                    int x=evt.getX()/Tauler.getCOSTAT();
                    int i=evt.getY()/Tauler.getCOSTAT();
                    direccio=evt.getButton();
                    switch(direccio){//direccio. Clic esquerr=1, clic dret=3
                        case 1://horitzontal
                            if(x<Tauler.getDIMENSIO()-mida+1){//hi cap horitzontalment
                                System.out.println("miram veins posicio ("+x+" "+i+"), direccio: "+direccio+" mida: "+mida);
                                if(tauler1.pucColocarVaixell(x,i,direccio,mida)){
                                    for (int idx=0;idx<mida;idx++){
                                            tauler1.Posa(Color.black, x+idx, i, mida, direccio);
                                        }incrementarComptadorsVaixells(mida);
                                }
                            }break;
                            
                        case 3://vertical
                                if (i<Tauler.getDIMENSIO()-mida+1){//hi cap verticalment
                                    System.out.println("miram posicio ("+x+" "+i+"), direccio: "+direccio+" mida: "+mida);
                                    if(tauler1.pucColocarVaixell(x,i,direccio,mida)){
                                        for (int idx=0;idx<mida;idx++){
                                                tauler1.Posa(Color.BLACK, x, i+idx, mida, direccio);
                                            }incrementarComptadorsVaixells(mida);
                                    }
                                }break;                        
                    }
                    finestra.repaint();
                }
            }
            
            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                
            }
            
            
            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

            private void incrementarComptadorsVaixells(int mida) {
                switch(mida){
                    case 1:
                        tauler1.setVaixellsDe1(tauler1.getVaixellsDe1()+1);
                        break;
                    case 2:
                        tauler1.setVaixellsDe2(tauler1.getVaixellsDe2()+1);
                        break;
                    case 3:
                        tauler1.setVaixellsDe3(tauler1.getVaixellsDe3()+1);
                        break;
                    case 4:
                        tauler1.setVaixellsDe4(tauler1.getVaixellsDe4()+1);
                        break;
                    default: System.out.println("mida incorecte :S");break;
                }
            }
    

        });
        
        /****************************** ACTION LISTENER TAULER 2 *************************/

        tauler2.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent evt) {
            }   
            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent evt) {
                if(tornJugador&&tauler2.getContador()!=0&&interruptorTauler2){//torn&&no hem guanyat&&interruptor
                int x=evt.getX()/Tauler.getCOSTAT();
                int i=evt.getY()/Tauler.getCOSTAT();
                
                System.out.println("tauler 1  x: "+x+" y: "+i);
                if (!tauler2.t[x][i].getDisparada()){//si no hem disparat ja a la posicio
                    tauler2.Dispara(x, i);
                    if(!tauler2.t[x][i].getColocada()){//si no acertam algun vaixell
                        textInferior.setText("Aigua! Sort pel proxim dispar ;) ");
                        tornJugador=false;
                        disparMaquina();

                    }else{//si acertam un vaixell
                        textInferior.setText("Ferit!! Torna a disparar. ");
                    }finestra.repaint();
                    }
                } if (tauler2.getContador()==0){
                    textInferior.setText("Enhorabona! Has guanyat a la maquina. Ets un pro");   
                    interruptorTauler1=false;
                    interruptorTauler2=false;
                    victoria.setVisible(true);
                }if(tauler1.getContador()==0){
                    derrota.setVisible(true);
                }
                }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
            
            
            public void disparMaquina(){
            if (!tornJugador&&tauler1.getContador()>0){
                int x=rdm.nextInt(Tauler.getDIMENSIO());
                int i=rdm.nextInt(Tauler.getDIMENSIO());
                if(tauler1.t[x][i].getDisparada()){    //si ja ha disparat anteriorment
                    disparMaquina();//torna a disparar
                }
                tauler1.Dispara(x, i);
                if(!tauler1.hihavaixell(x, i)){ //si no hi havia vaixell 
                        tornJugador=true;       //canbi torn
                    }else{
                        disparMaquina();        //torn a disparar
                    }finestra.repaint();
                }
            if (!tornJugador&&tauler1.getContador()==0){
                tornJugador=true;
                interruptorTauler1=false;
                interruptorTauler2=false;
                derrota=new DialegFinal("LA MAQUINA GUANYA");
                derrota.setVisible(true);
                textInferior.setText("La maquina guanya");
              }
            }
       
        
        });
        
   
        
    }
   
    
    




}