/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HraciPole;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author novak jan
 */
public class Hra extends javax.swing.JFrame {

    
    ctverec[][] a = new ctverec[17][17];
    int pom;
    public Hra() {
        initComponents();
        
        int y,z,g;
        
        Random rand = new Random();
        /****************Naplneni minami*******************/
        
        g= Integer.valueOf(JOptionPane.showInputDialog("Počet min? (menší než 255)", "25"));
        int[][] pole = new int[17][17];
        for (int l = 1; l < 17; l++) 
        {
            for (int m = 1; m < 17; m++) 
            {
                pole[l][m] = 0;
            }
        }
        for (int k = 0; k < g; k++) 
        {
            do
            {                
            y = rand.nextInt(15)+1;
            z = rand.nextInt(15)+1;
            }while(pole[y][z] == 9);
            pole[y][z] = 9;
        }
        /**************Doplneni cisel k minami****************/
        int poc;
        for (int l = 1; l < 16; l++) 
        {
            for (int m = 1; m < 16; m++) 
            {
                if (pole[l][m]!=9) 
                {
                
                if (pole[l-1][m-1]==9) {pole[l][m]++;}
                if (pole[l-1][m]==9) {pole[l][m]++;}
                if (pole[l-1][m+1]==9) {pole[l][m]++;}
                if (pole[l][m-1]==9) {pole[l][m]++;}
                if (pole[l][m+1]==9) {pole[l][m]++;}
                if (pole[l+1][m-1]==9) {pole[l][m]++;}
                if (pole[l+1][m]==9) {pole[l][m]++;}
                if (pole[l+1][m+1]==9) {pole[l][m]++;}
            }
                
            }
        }
        
        String[][] list = new String[15][15];
        for ( int l = 1; l <16; l++)
        {
            for (int m = 1; m < 16; m++) 
            {
                list[l-1][m-1] = Integer.toString(pole[l][m]);
            }
        }
        /***************Tvorba hraciho pole**************************/
        for (int r = 0; r < 15; r++) 
        {
            for (int s = 0; s < 15; s++) 
            {
                ctverec policko = new ctverec();
                policko.pole = new JButton();
                Font pismo=new Font(policko.pole.getFont().getName(),policko.pole.getFont().getStyle(),7);
                Font tucne=new Font(policko.pole.getFont().getName(),Font.BOLD,policko.pole.getFont().getSize());
                policko.pole.setBounds(r*40+5, s*40+5, 40, 40);
                policko.pole.setFont(pismo);
                policko.pole.setFont(tucne);
                if(list[r][s].equals("9")){policko.pole.setText("@");}
                if(list[r][s].equals("0")){policko.pole.setText(" ");}
                else {policko.pole.setText(list[r][s]);}
                policko.pole.setForeground(Color.white);
                policko.pole.setBackground(Color.white);
                policko.r = r;
                policko.s = s;
                policko.pole.setEnabled(true);
                policko.pole.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        
                        
                        switch(list[policko.r][policko.s])
                        {
                            case "0":odkryti(policko.r+1,policko.s+1);break;
                            case "9":nastaveniBarev(policko.pole, policko.pole.getText());prohra();break;
                            default: nastaveniBarev(policko.pole, policko.pole.getText());policko.pole.setEnabled(false);break;
                        }
                        if((kontrola())<=g)
                        {
                            pom = kontrola();
                             vyhra();
                        }
                        
                        
                        
                        nastaveniBarev(policko.pole, policko.pole.getText());
                    }
                        
                    
                    
                
                });
                
                
                a[r+1][s+1] = policko;
                this.add(policko.pole);
                }            

        }
        this.revalidate();
        this.repaint();
    }
    public void nastaveniBarev(JButton a, String x)
    {
        a.setBackground(Color.lightGray);
        switch(x)
        {
            case " " :a.setForeground(Color.gray);break;
            case "1" :a.setForeground(Color.blue);break;
            case "2" :a.setForeground(Color.green);break;
            case "3" :a.setForeground(Color.red);break;
            case "4" :a.setForeground(Color.orange);break;
            case "5" :a.setForeground(Color.magenta);break;
            case "6" :a.setForeground(Color.darkGray);break;
            case "7" :a.setForeground(Color.lightGray);break;
            case "8" :a.setForeground(Color.white);break;
            case "9" :a.setForeground(Color.black);break;
        } 
        
    }
    /////////////////Odkryvani policek, kdyz je pole prazdne/////////////////
    public void odkryti(int r,int s)
    {
                
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                
                if (((r+i <= 0) || (r+i >= 16)) || ((s+j <= 0) || (s+j >= 16)) || ((r+i == r) && (s+j == s))){}
                else{   
                
                
                
                if ((a[r+i][s+j].pole.getText()).equals(" ")&&(a[r+i][s+j].pole.isEnabled() == true))
                {
                        nastaveniBarev(a[r+i][s+j].pole, a[r+i][s+j].pole.getText());
                        a[r+i][s+j].pole.setEnabled(false);
                       
                        odkryti(r+i, s+j);
                }
                else{nastaveniBarev(a[r+i][s+j].pole, a[r+i][s+j].pole.getText());
                }a[r+i][s+j].pole.setEnabled(false);
                }

                
       a[r][s].pole.setEnabled(false);
    }
        }}
    
    
    public void prohra()
    {
        
        
        JOptionPane.showMessageDialog(null, "Prohrál jsi");
        System.exit(0);
        
        
    }
    
    public void vyhra()
    {
        
        JOptionPane.showMessageDialog(null, "Vyhrál jsi");
        System.exit(0);
        
        
    }
    
    public int kontrola()
    {     
        int p = 0;
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 16; j++) {
                if(a[i][j].pole.isEnabled())
                {p++;}   
            }
        }
        return p;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
