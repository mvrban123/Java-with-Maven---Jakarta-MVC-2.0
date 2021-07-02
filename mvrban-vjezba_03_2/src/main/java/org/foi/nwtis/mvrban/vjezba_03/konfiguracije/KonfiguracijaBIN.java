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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
 *
 * @author NWTiS_2
 */
public class KonfiguracijaBIN extends KonfiguracijaApstraktna{

    /**
     * Konstruktor klase KonfiguracijaBIN
     * @param nazivDatoteke 
     */
    public KonfiguracijaBIN(String nazivDatoteke) {
        super(nazivDatoteke);
    }

    /**
     * Funkcija koja služi za učitavanje konfiguracije iz .bin datoteke
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void ucitajKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        File f = new File(nazivDatoteke);
        if (f.exists() && f.isFile()){
            Object o;
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));){
                o = ois.readObject();
                ois.close();
                if(o instanceof Properties){
                    this.postavke = (Properties) o;
                }
                else{
                    throw new NeispravnaKonfiguracija("Problem sadržaja kod učitavanja datoteke: '" + nazivDatoteke + "'.");
                }
            }
            catch(Exception ex){
                throw new NeispravnaKonfiguracija("Problem kod učitavanja datoteke: '" + nazivDatoteke + "'.");
            }
        }
    }

    /**
     * Funckija koja služi za spremanje konfiguracije u .bin datoteku
     * @param nazivDatoteke
     * @throws NeispravnaKonfiguracija 
     */
    @Override
    public void spremiKonfiguraciju(String nazivDatoteke) throws NeispravnaKonfiguracija {
        File f = new File(nazivDatoteke);
        if((f.exists() && f.isFile()) || !f.exists()){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));){
                oos.writeObject(this.postavke);
            }
            catch(IOException ex){
                throw new NeispravnaKonfiguracija("Problem kod spremanja datoteke: " + nazivDatoteke);
            }
        }
    }
}
