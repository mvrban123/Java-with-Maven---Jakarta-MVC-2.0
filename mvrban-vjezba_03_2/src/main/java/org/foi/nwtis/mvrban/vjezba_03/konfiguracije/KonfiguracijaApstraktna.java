/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.vjezba_03.konfiguracije;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author NWTiS_2
 */
public abstract class KonfiguracijaApstraktna implements Konfiguracija{
    protected String nazivDatoteke;
    protected Properties postavke = new Properties();

    /**
     * Kontruktor klase KonfiguracijaApstraktna
     * @param nazivDatoteke 
     */
    public KonfiguracijaApstraktna(String nazivDatoteke) {
        this.nazivDatoteke = nazivDatoteke;
        this.postavke = new Properties();
    } 

    /**
     * Funckiaj koja poziva funkciju koja služi za učitavanje konfiguracije
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void ucitajKonfiguraciju() throws NeispravnaKonfiguracija {
        this.ucitajKonfiguraciju(this.nazivDatoteke);
    }

    /**
     * Funckija koja poziva funkciju za spremanje konfiguracije
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void spremiKonfiguraciju() throws NeispravnaKonfiguracija {
        this.spremiKonfiguraciju(this.nazivDatoteke);
    }

    /**
     * Funckija koja služi za ažuriranje konfiguracije
     * Nije implementirano
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void azurirajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    /**
     * Funckija koja služi za dodavanje nove konfiguracije
     * Nije impplementirano
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void dodajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Funckija koja služi za dodavanje nove konfiguracije u dodatetu konfiguracije ako isti takva ne postoji
     * @param postavke 
     */
    @Override
    public void dodajKonfiguraciju(Properties postavke) {
        for(Enumeration<Object> keys = postavke.keys(); keys.hasMoreElements();){
            String kljuc = (String)keys.nextElement();
            String vrijednost = postavke.getProperty(kljuc);
            if(this.postojiPostavka(kljuc)){
                this.azurirajPostavku(kljuc, vrijednost);
            }
            else{
                this.spremiPostavku(kljuc, vrijednost);
            }
        }
    }

    /**
     * Funckija koja služi za kopiranje nove konfiguracije u aplikacijsku memoriju
     * @param postavke 
     */
    @Override
    public void kopirajKonfiguraciju(Properties postavke) {
        this.postavke.clear();
        this.postavke = postavke;
    }

    /**
     * Funkcija koja vraća sve postavke iz aplikacijske evidencije
     * @return 
     */
    @Override
    public Properties dajSvePostavke() {
        return this.postavke;
    }

    /**
     * Funckija koja briše konfiguraciju iz aplikacijske evidencije
     * @return 
     */
    @Override
    public boolean obrisiSvePostavke() {
     if(this.postavke.isEmpty()) {
            return false;
        } else {
            this.postavke.clear();
            return true;
        }    
    }

    /**
     * Funckija koja vraća određenu postavku na temelju zaprimljenog ključa
     * @param kljuc
     * @return 
     */
    @Override
    public String dajPostavku(String kljuc) {
        return this.postavke.getProperty(kljuc);
    }

    /**
     * Funckija koja sprema novu postavku ako takva ne postoji
     * @param kljuc
     * @param vrijednost
     * @return 
     */
    @Override
    public boolean spremiPostavku(String kljuc, String vrijednost) {
        if(this.postavke.containsKey(kljuc)) {
            return false;
        } else {
            this.postavke.setProperty(kljuc, vrijednost);
            return true;
        }
    }

    /**
     * Funckija koja ažurira postojeću postavku novim podacima ako postavka postoji
     * @param kljuc
     * @param vrijednost
     * @return 
     */
    @Override
    public boolean azurirajPostavku(String kljuc, String vrijednost) {
        if(!this.postavke.containsKey(kljuc)){
            return false;
        }
        else{
            this.postavke.setProperty(kljuc, vrijednost);
            return true;
        }
    }

    /**
     * Funkcija koja provjerava ako postiji određena postavka 
     * @param kljuc
     * @return 
     */
    @Override
    public boolean postojiPostavka(String kljuc) {
        return this.postavke.containsKey((kljuc));
    }

    /**
     * Funkcija koja briše određenu postavku ako takva postoji
     * @param kljuc
     * @return 
     */
    @Override
    public boolean obrisiPostavku(String kljuc) {
        if(!this.postavke.containsKey(kljuc)){
            return false;
        }
        else{
            this.postavke.remove(kljuc);
            return true;
        }
    }
    
    /**
     * Funckija koja kreira novu datoteku konfiguracije
     * @param nazivDatoteke
     * @return
     * @throws NeispravnaKonfiguracija 
     */
    public static Konfiguracija kreirajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija{
        Konfiguracija konfig = dajKonfiguraciju(nazivDatoteke);
        konfig.spremiKonfiguraciju();
        return konfig;
    }
    
    /**
     * Funckija koja preuzima sve postavke iz konfiguracijske datoteke
     * @param nazivDatoteke
     * @return
     * @throws NeispravnaKonfiguracija 
     */
    public static Konfiguracija preuzmiKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija{
        Konfiguracija konfig =  dajKonfiguraciju(nazivDatoteke);
        konfig.ucitajKonfiguraciju();
        return konfig;
    }

    /**
     * Funckija koja poziva određenu za dohvaćanje konfiguracije na temelju nastavka datoteke
     * Mogući nastavci su: txt, bin, xml, json
     * @param nazivDatoteke
     * @return
     * @throws NeispravnaKonfiguracija 
     */
    public static Konfiguracija dajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        int poz = nazivDatoteke.lastIndexOf(".");
        if(poz == -1){
            throw new NeispravnaKonfiguracija("Datoteka: '" + nazivDatoteke + "' nema tip datoteke");
        }
        String ekst = nazivDatoteke.substring(poz +1).toLowerCase();
        switch (ekst) {
            case "txt":
                return new KonfiguracijaTxt(nazivDatoteke);
            case "bin":
                return new KonfiguracijaBIN(nazivDatoteke);
            case "xml":
                return new KonfiguracijaXML(nazivDatoteke);
            case "json":
                return new KonfiguracijaJSON(nazivDatoteke);
            default:
                throw new NeispravnaKonfiguracija();
        }
    }
}
