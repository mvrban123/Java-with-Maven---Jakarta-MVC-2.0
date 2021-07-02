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
import org.foi.nwtis.rest.podaci.Lokacija;


/**
 * Klasa za dohvaćanje podataka o aerodromima
 * @author NWTiS_2
 */
public class AirportDAO {

    
    /**
     * Funkcija koja dohvaća jedan aerodrom iz baze podataka
     * @param icao prima se u oblika stringa
     * @param con prima se konekcija u funkciju
     * @return vraća se tip podataka Aerodrom
     * @throws SQLException izbacuje grešku
     */
    public Aerodrom dohvatiAerodrom(String icao, Connection con) throws SQLException {

        String upit = "SELECT * "
                + "FROM airports WHERE ident = ?";

        try (
                PreparedStatement s = con.prepareStatement(upit)) {

            s.setString(1, icao);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                //String icao = rs.getString("ident");
                String drzava = rs.getString("iso_country");
                String naziv = rs.getString("name");
                String coordinates=  rs.getString("coordinates");
                             
                Lokacija l = new Lokacija();
                if(coordinates != null && !coordinates.trim().isEmpty() && coordinates.contains(",")){
                    String[] k = coordinates.split(",");
                    l = new Lokacija(k[1],k[0]);
                }
                Aerodrom a = new Aerodrom(icao, naziv, drzava, l);
                con.close();
                return a;
            }
            
        }catch(Exception ex){}
                
        return null;
    }
    
    /**
     * Funkcija koja dohvaća grad u kojem se nalazi određeni aerodrom na temelju icao podatka i vraća ga u obliku stringa
     * @param icao prima se u oblika stringa
     * @param con prima se konekcija u funkciju
     * @return vraća se podataka o nazivu grada u obliku stringa
     * @throws SQLException izbacuje grešku
     */
    public String dohvatiGradAerodroma(String icao, Connection con) throws SQLException {

        String upit = "SELECT * "
                + "FROM airports WHERE ident = ?";

        try (
                PreparedStatement s = con.prepareStatement(upit)) {

            s.setString(1, icao);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                return rs.getString("municipality");
            }
            
        }catch(Exception ex){}
                
        return null;
    }

    /**
     * Funckija koja dohvaća sve aerodrome iz baze podataka i vraća listu aerodroma
     * Funckija ujedno služi i za filtriranje ako se prosljedi naziv aerodroma kao argument
     * @param con prima se konekcija u funkciju
     * @param nazivAerodroma prima se u obliku strinka za potrebe filtriranja
     * @return vraća se lista tipa Aerodrom
     */
    public List<Aerodrom> dajSveAerodromeNaziv(Connection con, String nazivAerodroma) {;
        String upit = "SELECT * FROM airports";

        if(nazivAerodroma != null && !nazivAerodroma.trim().isEmpty()){
            if(nazivAerodroma.compareTo("*") == 0){
                
            } else {
                upit += " where name like '"+nazivAerodroma+"'";
            }
        }
        System.out.println(upit);
        List<Aerodrom> aerodromi = new ArrayList<>();
        try (   Statement s = con.createStatement();
                ResultSet rs = s.executeQuery(upit)) {
            
            while (rs.next()) {
                String icao = rs.getString("ident");
                String drzava = rs.getString("iso_country");
                String naziv = rs.getString("name");
                String coordinates=  rs.getString("coordinates");
                             
                Lokacija l = new Lokacija();
                if(coordinates != null && !coordinates.trim().isEmpty() && coordinates.contains(",")){
                    String[] k = coordinates.split(",");
                    l = new Lokacija(k[1],k[0]);
                }
                
                Aerodrom a = new Aerodrom(icao, naziv, drzava, l);
                
                aerodromi.add(a);
            }
            con.close();
            return aerodromi;
            
        } catch (Exception ex) {
            Logger.getLogger(AirportDAO.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return null;
    }
}