/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

/**
 *
 * @author palmyman
 */
public class SprinklerControler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ControlPanel panelOne = new ControlPanel("panelOne", null, 5);
        ControlPanel panelTwo = new ControlPanel("panelAaa", null, 3);
        
        Program firstProgram = new Program(1, "base1");
        
        firstProgram.add(panelOne, 1, 2);
        

        firstProgram.runProgram();
    }
}
