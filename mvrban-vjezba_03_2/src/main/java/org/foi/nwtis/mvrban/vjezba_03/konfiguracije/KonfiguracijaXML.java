/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.nwtis.mvrban.vjezba_03.konfiguracije;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author NWTiS_2
 */
public class KonfiguracijaXML extends KonfiguracijaApstraktna{

    /**
     * Konstruktor klase KonfiguracijaXML
     * @param nazivDatoteka 
     */
    public KonfiguracijaXML(String nazivDatoteka) {
        super(nazivDatoteka);
    }
    
    /**
     * unckija koja služi za učitavanje konfiguracije iz .xml datoteke
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void ucitajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        File f = new File(nazivDatoteke);
        if(f.exists() && f.isFile()) {
            try {
                this.postavke.loadFromXML(new FileInputStream(f));
            } catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod uÄŤitavanja datoteke: '" + nazivDatoteke + "'.");
            }
        }
    }

    /**
     * unckija koja služi za spremanje konfiguracije u .txt datoteku
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void spremiKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        File f = new File(nazivDatoteke);
        if((f.exists() && f.isFile()) || ! f.exists()) {
            try {
                this.postavke.storeToXML(new FileOutputStream(f), "NWTiS 2021.");
            } catch (IOException ex) {
                throw new NeispravnaKonfiguracija("Problem kod spremanja u datoteku: '" + nazivDatoteke + "'.");
            }
        }
    }
}
