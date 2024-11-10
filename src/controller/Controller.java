/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.User;

/**
 *
 * @author vldmrk
 */
public class Controller {

    private static Controller instance;
    private DBBroker dbb;
    private Admin ulogovan;

    public Controller() {
        dbb = new DBBroker();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Admin loginAdmin(Admin a) {
        ulogovan = dbb.loginAdmin(a);
        return ulogovan;
    }

    public Admin getUlogovan() {
        return ulogovan;
    }

    public void setUlogovan(Admin ulogovan) {
        this.ulogovan = ulogovan;
    }

    public boolean jedinstven(User u) {
        return dbb.jedinstven(u);
    }

    public void dodaj(User u) {
        dbb.dodaj(u);
    }

}
