/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.konfiguracije.bazaPodataka;

import java.util.Properties;
import org.foi.nwtis.mvrban.vjezba_03.konfiguracije.Konfiguracija;
import org.foi.nwtis.mvrban.vjezba_03.konfiguracije.KonfiguracijaApstraktna;
import org.foi.nwtis.mvrban.vjezba_03.konfiguracije.NeispravnaKonfiguracija;

/**
 *
 * @author NWTiS_2
 */
public class PostavkeBazaPodataka extends KonfiguracijaApstraktna implements KonfiguracijaBP {

    /**
     * Konstruktor klase PostavkeBazaPodataka
     * @param nazivDatoteke 
     */
    public PostavkeBazaPodataka(String nazivDatoteke) {
        super(nazivDatoteke);
    }
    
    /**
     * Funckija koja dohvaća konfiguraciju u aplikacijsku memoriju
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void ucitajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        Konfiguracija konfig = KonfiguracijaApstraktna.preuzmiKonfiguraciju(nazivDatoteke);
        this.postavke = konfig.dajSvePostavke();
    }

    /**
     * Funckija koja sprema konfiguraciju u datoteku na disku
     * @param datoteka
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija {
        Konfiguracija konfig = KonfiguracijaApstraktna.dajKonfiguraciju(nazivDatoteke);
        konfig.kopirajKonfiguraciju(this.dajSvePostavke());
        konfig.spremiKonfiguraciju();
    }

    /**
     * Funckija koja dohvaća postavku admin naziva baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getAdminDatabase() {
        return this.dajPostavku("admin.database");
    }

    /**
     * Funckija koja dohvaća postavku admin lozinke baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getAdminPassword() {
        return this.dajPostavku("admin.password");
    }

    /**
     * Funckija koja dohvaća postavku admin naziva korisnika baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getAdminUsername() {
        return this.dajPostavku("admin.username");
    }

    /**
     * Funckija koja poziva funckiju za dohvaćanje postavke drivera baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getDriverDatabase() {
        return this.getDriverDatabase(this.getServerDatabase());
    }

    /**
     * Funckija koja dohvaća postavku drivera baze podataka na koju se treba spojiti
     * @param urlBazePodataka
     * @return 
     */
    @Override
    public String getDriverDatabase(String urlBazePodataka) {
        String bp = urlBazePodataka;
        String protokol = bp.substring(0, bp.indexOf("//") - 1).replace(":", ".");
        return this.dajPostavku(protokol);
    }

    /**
     * Funckija koja dohvaća postavku drivera baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public Properties getDriversDatabase() {
        Properties p = new Properties();
        String protokol = "jdbc.";
        for (Object o : this.dajSvePostavke().keySet()) {
            String k = (String) o;
            if (k.startsWith(protokol)) {
                p.setProperty(k, this.dajPostavku(k));
            }
        }
        return p;
    }

    /**
     * Funckija koja dohvaća postavku servera baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getServerDatabase() {
        return this.dajPostavku("server.database");
    }

    /**
     * Funckija koja dohvaća postavku naziva korisničke baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getUserDatabase() {
        return this.dajPostavku("user.database");
    }

    /**
     * Funckija koja dohvaća postavku lozinke korisničke baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getUserPassword() {
        return this.dajPostavku("user.password");
    }

    /**
     * Funckija koja dohvaća postavku naziva korisnika korisničke baze podataka na koju se treba spojiti
     * @return 
     */
    @Override
    public String getUserUsername() {
        return this.dajPostavku("user.username");
    }

}
