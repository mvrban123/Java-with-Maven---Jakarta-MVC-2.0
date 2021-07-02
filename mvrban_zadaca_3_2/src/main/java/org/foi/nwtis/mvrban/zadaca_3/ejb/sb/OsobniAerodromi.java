package org.foi.nwtis.mvrban.zadaca_3.ejb.sb;

import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateful;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.mvrban.zadaca_3.podaci.AirportDAO;
import org.foi.nwtis.mvrban.zadaca_3.podaci.KorisnikDAO;
import org.foi.nwtis.mvrban.zadaca_3.podaci.MyAirportDAO;
import org.foi.nwtis.podaci.Aerodrom;
import org.foi.nwtis.podaci.Airport;
import org.foi.nwtis.podaci.Korisnik;

@Stateful
public class OsobniAerodromi {

    @EJB
    BankaAerodroma bankaAerodroma;

    @Resource(lookup = "jdbc/NWTiS_DZ3")
    javax.sql.DataSource nwtisBP;
    
    /**
     * Funckija koja poziva funckiju iz banke aerodroma kako bi se dodao aerodrom u aplikacijsku evidenciju
     * @param aerodrom klase Aerodrom
     */
    public void dodajAerodrom(Aerodrom aerodrom){
        bankaAerodroma.preuzmiAerodrom(aerodrom);
    }
    
    /**
     * Funckija koja pozika funkciju iz banke aerodroma koja vraća listu svih evidantiranih aerodroma iz aplikacijske evidencije
     * @return List Aerodrom
     */
    public List<Aerodrom> dajEvidentiraneAerodrome(){
        return bankaAerodroma.getSviAerodromi();
    }
    
    /**
     * Funckija koja poziva funkciju iz baze aerodroma koja briše sve aerodrome iz aplikacijske evidencije
     */
    public void obrisiAerodrome(){
        bankaAerodroma.obrisiAerodrome();
    }
    
    /**
     * Funckija koja poziva funkciju iz baze aerodroma koja vraća broj aerodroma koji se nalaze u aplikacijskoj domeni
     * @return integer broj aerodroma
     */
    public int brojAerodroma(){
        return bankaAerodroma.brojAerodroma();
    }
    
    /**
     * Funkcija koja dohvaća sve aerodrome iz baze podataka 
     * Funkcija ujedno služi i za dohvaćanje filtriranih podataka iz baze podataka
     * @param nazivAerodroma klase String
     * @return List Aerodrom
     */
    public List<Aerodrom> filtrirajAerodrome(String nazivAerodroma){
        AirportDAO airportDAO = new AirportDAO();
        try {
            List<Aerodrom> aerodromi = airportDAO.dajSveAerodromeNaziv(nwtisBP.getConnection(),nazivAerodroma);
            return aerodromi;
        } catch (SQLException ex) {
            Logger.getLogger(OsobniAerodromi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    
    /**
     * Funckija koja upisuje novi aerodrom u bazu podataka u tablicu myairports
     * @param nazivAerodroma klase String
     * @param korisnik klase String 
     */
    public void upisiAerodromUMYAIRPORTSDAO(String nazivAerodroma, String korisnik){
        try {
            MyAirportDAO myAirportDAO = new MyAirportDAO();
            
            myAirportDAO.dodajAerodromKojiPratiKorisnik(nwtisBP.getConnection(), nazivAerodroma, korisnik);
        } catch (SQLException ex) {
            Logger.getLogger(OsobniAerodromi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Funckija koja poziva potrebne funkcije kako bi se aerodrom dodao u bazu podataka u tablicu myairports i u aplikacijsku evidenciju
     * @param nazivAerodroma klase Aerodrom
     * @param korisnik klase String 
     */
    public void dodajAerodromUMyAirportsIBankuAerodroma(Aerodrom nazivAerodroma, String korisnik){
        KorisnikDAO korisnikDAO = new KorisnikDAO();
        try {
            Korisnik kor = korisnikDAO.dohvatiKorisnika(nwtisBP.getConnection(), korisnik);
            if(kor != null){
                upisiAerodromUMYAIRPORTSDAO(nazivAerodroma.getIcao(), korisnik);
                dodajAerodrom(nazivAerodroma);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OsobniAerodromi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Funkcija koja dohvaća jedan aerodrom iz baze podataka
     * @param nazivAerodroma klase String
     * @return Aerodrom
     */
    public Aerodrom dohvatiAerodrom(String nazivAerodroma){
        AirportDAO airportDAO = new AirportDAO();
        Aerodrom a;
        try {
            a = airportDAO.dohvatiAerodrom(nazivAerodroma, nwtisBP.getConnection());
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(OsobniAerodromi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Funckija koja dohvaća grad u kojem se nalazi određeni aerodrom
     * @param nazivAerodroma klase String
     * @return String naziv aerodroma
     */
    public String dohvatiNazivAerodroma(String nazivAerodroma) {
        AirportDAO airportDAO = new AirportDAO();
        try {
            return airportDAO.dohvatiGradAerodroma(nazivAerodroma, nwtisBP.getConnection());
        } catch (SQLException ex) {
            Logger.getLogger(OsobniAerodromi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

