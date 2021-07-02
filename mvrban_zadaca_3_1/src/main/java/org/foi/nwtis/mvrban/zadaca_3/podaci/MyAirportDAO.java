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
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Korisnik;

/**
 * Klasa za dohvaćanje podataka o praćenim aerodromima
 * @author NWTiS_2
 */
public class MyAirportDAO {

    /**
     * Funkcija zapisuje u bazu podataka aerodrom koji će pratiti određeni korisnik 
     * @param aerodrom zaprima se icao podatak aerodroma u obliku string
     * @param korisnik zaprima se korisnicko ime u obliku string
     * @param con zaprima se konekcija u funkciju 
     * @return vraća se podataka true ili false ovisno o tome ako je izvšen unos ili nije
     */
    public boolean dodajAerodromKojiPratiKorisnik(Connection con ,String aerodrom, String korisnik) {
        String upit = "INSERT INTO myairports(ident, username,stored) "
                + "VALUES ( ? , ? , CURRENT_TIMESTAMP)";

        try (
                PreparedStatement s = con.prepareStatement(upit);
                ) {
            
            s.setString(1, aerodrom);
            s.setString(2, korisnik);
            int broj = s.executeUpdate();
            
            con.close();
            return broj==1;
            
        } catch (SQLException ex) {
            System.out.println("Vec postoji taj zapis u bazi podataka pa ga nije potrebno dodavati");
        }
        return false;
    }

}
