/*
 * Classe DialegFinal del joc mastermind
 * 
 * ATRIBUTS
 * Una etiqueta amb el missatge i un boto per tornar al joc
 * 
 * CONSTRUCTOR
 * Crea l'etiqueta amb l'string que passa per parametre,
 * el boto simplement amaga el dialeg
 */
package batallaNaval;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author albertgelabert
 */
class DialegFinal extends JDialog {

    private final JLabel jlbGuanyador;
    private final JButton jbtOk;

    public DialegFinal(String s) {
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setResizable(false);
        jlbGuanyador = new JLabel(s,SwingConstants.CENTER);//mitjançant la llibreria SwingConstants centram el text
        jlbGuanyador.setFont(new Font("arial", 1, 20));
        jbtOk = new JButton("Acceptar");
        this.add(jlbGuanyador);
        this.add(jbtOk);
        jbtOk.setBounds(100, 50, 100, 50);
        this.add(jlbGuanyador);
        jlbGuanyador.setBounds(0, 0, 300, 50);
        jbtOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
