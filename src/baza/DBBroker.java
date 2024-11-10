/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.User;

/**
 *
 * @author vldmrk
 */
public class DBBroker {

    public DBBroker() {
    }

    public Admin loginAdmin(Admin admin) {
        Admin a = null;

        try {
            String upit = "SELECT * FROM admini WHERE email=? AND pass=?";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setString(1, admin.getEmail());
            ps.setString(2, admin.getPass());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Admin();
                a.setAdminId(rs.getInt("adminId"));
                a.setEmail(rs.getString("email"));
                a.setPass(rs.getString("pass"));
                a.setIme(rs.getString("ime"));
                a.setPrezime(rs.getString("prezime"));
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;

    }

    public boolean jedinstven(User u) {
        boolean jeste = true;
        try {
            String upit = "SELECT * FROM useri WHERE user=" + "'" + u.getUser() + "'";
            Statement st = Konekcija.getInstance().getKonek().createStatement();
            ResultSet rs = st.executeQuery(upit);
            if (rs.next()) {
                jeste = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jeste;

    }

    public void dodaj(User u) {
        try {
            String upit = "INSERT INTO useri(user,sifra) VALUES (?,?)";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setString(1, u.getUser());
            ps.setString(2, u.getSifra());
            int red = ps.executeUpdate();
            if (red > 0) {
                System.out.println("uspesno dodat korisnik");
            } else {
                System.out.println("neuspesno dodavanje");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
