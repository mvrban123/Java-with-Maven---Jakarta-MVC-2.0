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
public class KonfiguracijaTxt extends KonfiguracijaApstraktna{

    /**
     * Kontruktor klase KonfiguracijaTxt
     * @param nazivDatoteka 
     */
    public KonfiguracijaTxt(String nazivDatoteka) {
        super(nazivDatoteka);
    }
    
    /**
     * Funckija koja služi za učitavanje konfiguracije iz .txt datoteke
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void ucitajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        File f = new File(nazivDatoteke);
        if(f.exists() && f.isFile()){
            try{
                this.postavke.load(new FileInputStream(f));
            }
            catch(IOException ex){
                throw new NeispravnaKonfiguracija("Problem kod učitavanja datoteke: " + nazivDatoteke);
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
        if((f.exists() && f.isFile()) || !f.exists()){
            try{
                this.postavke.store(new FileOutputStream(f), "NWTiS 2021");
            }
            catch(IOException ex){
                throw new NeispravnaKonfiguracija("Problem kod spremanja datoteke: " + nazivDatoteke);
            }
        }
    }
}
