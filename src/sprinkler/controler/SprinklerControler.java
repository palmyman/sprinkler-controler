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
    public static void main(String[] args) {
        ControlPanel panelOne = new ControlPanel(1, "panelOne", null);
        
        Sprinkler ba1 = new Sprinkler(1, panelOne, 360);
        Sprinkler ba2 = new Sprinkler(2, panelOne, 360);
        Sprinkler ba3 = new Sprinkler(3, panelOne, 360);
        Sprinkler ba4 = new Sprinkler(4, panelOne, 360);
        Sprinkler ba5 = new Sprinkler(5, panelOne, 360);        
        
        Program firstProgram = new Program(1, "base1", 30);
        
        firstProgram.add(ba1);
        firstProgram.add(ba2);
        firstProgram.add(ba3);
        firstProgram.add(ba4);
        
        firstProgram.runProgram();
    }
}
