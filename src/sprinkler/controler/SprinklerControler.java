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
        ControlPanel panelOne = new ControlPanel("panelOne", null);
        ControlPanel panelTwo = new ControlPanel("panelAaa", null);
        
        Sprinkler ba1 = new Sprinkler(1, panelOne);
        Sprinkler ba2 = new Sprinkler(2, panelOne);
        Sprinkler ba3 = new Sprinkler(3, panelOne);
        Sprinkler ba4 = new Sprinkler(4, panelOne);
        Sprinkler ba5 = new Sprinkler(5, panelOne);
        Sprinkler so1 = new Sprinkler(2, panelTwo);
        
        Program firstProgram = new Program(1, "base1");
        
        TimedSprinkler abs1 = new TimedSprinkler(ba1, 10, firstProgram);
        TimedSprinkler abs2 = new TimedSprinkler(ba2, 10, firstProgram);
        TimedSprinkler abs3 = new TimedSprinkler(ba3, 10, firstProgram);
        TimedSprinkler abs4 = new TimedSprinkler(ba4, 10, firstProgram);
        TimedSprinkler abs5 = new TimedSprinkler(ba5, 10, firstProgram);
        TimedSprinkler abs6 = new TimedSprinkler(so1, 7, firstProgram);
        
        firstProgram.runProgram();
    }
}
