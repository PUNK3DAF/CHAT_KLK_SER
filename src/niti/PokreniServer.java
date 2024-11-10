/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vldmrk
 */
public class PokreniServer extends Thread {

    private boolean kraj = false;
    private ServerSocket ss;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(9000);
            while (!kraj) {
                Socket s = ss.accept();
                System.out.println("KLIJENT POVEZAN");
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s);
                okz.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        try {
            ss.close();
            System.out.println("ZAUSTAVLJEN SERVER");
            kraj = true;
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
