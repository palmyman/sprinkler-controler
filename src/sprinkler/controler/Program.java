/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprinkler.controler;

import java.sql.Time;
import java.util.Set;

/**
 *
 * @author palmyman
 */
public class Program {
    private int id;
    private String name;
    private Set<Sprinkler> sprinklers;
    private Time oneSpriklerTime;

    public Program(int id, String name, Time oneSpriklerTime) {
        this.id = id;
        this.name = name;
        this.oneSpriklerTime = oneSpriklerTime;
    }
}
