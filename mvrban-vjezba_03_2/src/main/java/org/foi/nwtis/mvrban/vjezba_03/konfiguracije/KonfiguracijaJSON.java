/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.vjezba_03.konfiguracije;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

/**
 *
 * @author NWTiS_2
 */
public class KonfiguracijaJSON extends KonfiguracijaApstraktna{

    private String datoteka;

    /**
     * Konstruktor klase KonfiguracijaJSON
     * @param datoteka 
     */
    public KonfiguracijaJSON(final String datoteka) {
        super(datoteka);
        this.datoteka = datoteka;
    }
    
    /**
     * Funckija koja poziva funckiju za učitavanje konfiguracije iz .json datoteke
     * @throws NeispravnaKonfiguracija 
     */
    public void ucitajKonfiguraciju() throws NeispravnaKonfiguracija {
        this.ucitajKonfiguraciju(this.datoteka);
    }
    
    /**
     * Funckija koja služi za učitavanje konfiguracije iz .json datoteke
     * @param datoteka
     * @throws NeispravnaKonfiguracija 
     */
    public void ucitajKonfiguraciju(final String datoteka) throws NeispravnaKonfiguracija {
        this.obrisiSvePostavke();
        if (datoteka == null || datoteka.length() == 0) {
            throw new NeispravnaKonfiguracija("Neispravni naziv datoteke!");
        }
        final File f = new File(datoteka);
        if (f.exists() && f.isFile()) {
            try {
                final Gson gson = new Gson();
                final BufferedReader br = new BufferedReader(new FileReader(f));
                this.postavke = (Properties)gson.fromJson((Reader)br, (Class)Properties.class);
                br.close();
                return;
            }
            catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod u\u010ditavanja datoteke!");
            }
        }
        throw new NeispravnaKonfiguracija("Datoteka ne postoji!");
    }
    
    /**
     * Funckija koja poziva funckiju za spremanje konfiguracije u .json datoteku
     * @throws NeispravnaKonfiguracija 
     */
    public void spremiKonfiguraciju() throws NeispravnaKonfiguracija {
        this.spremiKonfiguraciju(this.datoteka);
    }
    
    /**
     * Funckija kojasluži za spremanje konfiguracije u .json datoteku
     * @param datoteka
     * @throws NeispravnaKonfiguracija 
     */
    public void spremiKonfiguraciju(final String datoteka) throws NeispravnaKonfiguracija {
        if (datoteka == null || datoteka.length() == 0) {
            throw new NeispravnaKonfiguracija("Neispravni naziv datoteke!");
        }
        final File f = new File(datoteka);
        if (!f.exists() || !f.isFile()) {
            if (f.exists()) {
                throw new NeispravnaKonfiguracija("Datoteka ne postoji!");
            }
        }
        try {
            final Gson gson = new Gson();
            final String json = gson.toJson((Object)this.postavke);
            final FileWriter file = new FileWriter(f);
            try {
                file.write(json);
                System.out.println("Datoteka uspje\u0161no spremljena!");
                file.close();
            }
            catch (Throwable t) {
                try {
                    file.close();
                }
                catch (Throwable exception) {
                    t.addSuppressed(exception);
                }
                throw t;
            }
            this.postavke.store(new FileOutputStream(f), "lgaric");
            return;
        }
        catch (IOException ex) {
            throw new NeispravnaKonfiguracija("Problem kod spremanja datoteke!");
        }
    }
}
