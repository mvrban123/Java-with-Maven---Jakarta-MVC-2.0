package org.foi.nwtis.mvrban.zadaca_3.podaci;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.mvrban.konfiguracije.bazaPodataka.PostavkeBazaPodataka;
import org.foi.nwtis.podaci.Korisnik;

/**
 * Klasa za dohvaćanje podataka o korisnicima
 * @author NWTiS_2
 */
public class KorisnikDAO {

    /**
     * Funkcija koja dohvaća specificnog korisnika iz baze podataka i vraća objekt tipa Korisnik
     * Kao ulazni argumenti prima se objekt Connection i string koji je naziv korisnika
     * @param con prima se konekcija u funkciju
     * @param korisnik prima se u obliku stringa
     * @return vraća se podataka u obliku Korisnik
     */
    public Korisnik dohvatiKorisnika(Connection con, String korisnik) {
        String upit = "SELECT ime, prezime, korisnik, lozinka, emailAdresa, vrijemeKreiranja, vrijemePromjene "
                + "FROM korisnici WHERE korisnik = ?";

        try (
                PreparedStatement s = con.prepareStatement(upit)) {

            s.setString(1, korisnik);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                String korisnik1 = rs.getString("korisnik");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String email = rs.getString("emailAdresa");
                Timestamp kreiran = rs.getTimestamp("vrijemeKreiranja");
                Timestamp promjena = rs.getTimestamp("vrijemePromjene");

                Korisnik k = new Korisnik(korisnik1, "******", prezime, ime, email, kreiran, promjena, 0);
                return k;
            }

        } catch (SQLException ex) {
            System.out.println("Ne postoji korisnik sa navedenim korisničkim imenom u bazi podataka.");
        }
        return null;
    }

}
