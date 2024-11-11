/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Admin;
import model.Poruka;
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
                a.setEmail(admin.getEmail());
                a.setPass(admin.getPass());
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
                Konekcija.getInstance().getKonek().commit();
                System.out.println("uspesno dodat korisnik");
            } else {
                System.out.println("neuspesno dodavanje");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User loginUser(User user) {
        User u = null;

        try {
            String upit = "SELECT * FROM useri WHERE user=? AND sifra=?";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setString(1, user.getUser());
            ps.setString(2, user.getSifra());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new User();
                u.setId(rs.getInt("userId"));
                u.setUser(rs.getString("user"));
                u.setSifra(rs.getString("sifra"));
            }
            return u;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void posaljiSvima(Poruka poruka, User u) {
        try {
            String upit = "INSERT INTO poruke(posiljalacId,primalacId,tekst,vreme) VALUES (?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setInt(1, poruka.getPosiljalac().getId());
            ps.setInt(2, u.getId());
            ps.setString(3, poruka.getText());
            Timestamp t = new Timestamp(poruka.getVreme().getTime());
            ps.setTimestamp(4, t);
            int red = ps.executeUpdate();
            if (red > 0) {
                System.out.println("USPESNO POSLATA PORUKA");
            }
            Konekcija.getInstance().getKonek().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void posalji(Poruka poruka) {
        try {
            String upit = "INSERT INTO poruke(posiljalacId,primalacId,tekst,vreme) VALUES (?,?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getKonek().prepareStatement(upit);
            ps.setInt(1, poruka.getPosiljalac().getId());
            ps.setInt(2, poruka.getPrimalac().getId());
            ps.setString(3, poruka.getText());
            Timestamp t = new Timestamp(poruka.getVreme().getTime());
            ps.setTimestamp(4, t);
            int red = ps.executeUpdate();
            if (red > 0) {
                System.out.println("USPESNO POSLATA PORUKA");
            }
            Konekcija.getInstance().getKonek().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
