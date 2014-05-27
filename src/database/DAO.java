/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palmyman
 */

/**
 * @Class DAO - Data access object
 * @brief This object is used to initialize and work with the database
 */
public class DAO {

    /**
     * Connection to database
     */
    protected Connection conn;

    /**
     * Create prepared statement
     */
    protected PreparedStatement psCreate;

    /**
     * Update prepared statement
     */
    protected PreparedStatement psUpdate;

    /**
     * Delete prepared statement
     */
    protected PreparedStatement psDelete;

    /**
     * Delete children prepared statement
     */
    protected PreparedStatement psDeleteChilds;

    /**
     * Get all prepared statement
     */
    protected PreparedStatement psGetAll;

    /**
     * Get one prepared statement
     */
    protected PreparedStatement psGetOne;

    /**
     *  DAO constructor
     *  @brief Establishes connection to database. If the database is empty, 
     *  will initialize tables with sample data.
     */
    protected DAO() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:/tmp/sprinkler-controler-db;  create=true");
            DatabaseMetaData md = conn.getMetaData();
            ResultSet programs = md.getTables(null, null, "PROGRAM", null);
            if (!programs.next()) {
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE PROGRAM"
                        + "	(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "	NAME VARCHAR(100),"
                        + "     DATE DATE,"
                        + "     TIME TIME)");
                s.execute("INSERT INTO PROGRAM VALUES(DEFAULT, 'First', '1989-07-08', '16:00:00')");
            }

            ResultSet sprinklers = md.getTables(null, null, "SPRINKLER", null);
            if (!sprinklers.next()) {
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE SPRINKLER"
                        + "	(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "     PROGRAM_ID INT NOT NULL,"
                        + "     PANEL_ID INT NOT NULL,"
                        + "     INDEX INT NOT NULL,"
                        + "     DURATION INT NOT NULL)");
                s.execute("INSERT INTO SPRINKLER VALUES("
                        + "DEFAULT, 1, 1, 1, 20)");
                s.execute("INSERT INTO SPRINKLER VALUES("
                        + "DEFAULT, 1, 1, 2, 35)");
                s.execute("INSERT INTO SPRINKLER VALUES("
                        + "DEFAULT, 1, 2, 1, 15)");
            }

            ResultSet panels = md.getTables(null, null, "CONTROL_PANEL", null);
            if (!panels.next()) {
                Statement s = conn.createStatement();
                s.executeUpdate("CREATE TABLE CONTROL_PANEL"
                        + "	(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "	NAME VARCHAR(100),"
                        + "     IP VARCHAR(20),"
                        + "	SPRINKLER_COUNT INT)");
                s.execute("INSERT INTO CONTROL_PANEL VALUES("
                        + "DEFAULT, 'Main', '127.0.0.1', 2)");
                s.execute("INSERT INTO CONTROL_PANEL VALUES("
                        + "DEFAULT, 'Old Panel', '127.0.0.1', 1)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlPanelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
