/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personal_Schedule_Management;

/**
 *
 * @author admin
 */
public class Opening_Form_install {
    public static void display() {
       Opening_Form splash = new Opening_Form();
       splash.setVisible(true);
       try{
           for(int i = 0; i <= 100; i++){
               Thread.sleep(30);
               Opening_Form.jLabel_percent.setText(""+i+"%");
               Opening_Form.jProgressBar1.setValue(i);
               if(i==100){
                   Main_Form form = new Main_Form();
                   form.setVisible(true);
                   form.pack();
                   form.setLocationRelativeTo(null);
                   splash.dispose();
               }
          
           }
           
       }catch(Exception e){
           
       }
    }
}
