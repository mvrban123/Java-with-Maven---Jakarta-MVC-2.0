package org.foi.nwtis.mvrban.zadaca_3.ejb.sb;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.podaci.Aerodrom;

@Startup
@Singleton
public class BankaAerodroma {
    
    @Inject
    PosiljateljPoruka posiljateljPoruka;
    
    @Getter
    @Setter
    private List<Aerodrom> sviAerodromi = new ArrayList<>(); 
    
    /**
     * Funkcija koja prima listu aerodroma i sprema ih u aplikacijsku evidenciju
     *@param aerodromi lista Klase Aerodrom
     */
    public void preuzmiAerodrome(List<Aerodrom> aerodromi){
        this.sviAerodromi.addAll(aerodromi);
        posiljateljPoruka.saljiPoruku("Broj aerodroma: " + this.brojAerodroma());
    }
    
    /**
     * Funckija koja prima jedan aerodrom i sprema ga u aplikacijsku evidenciju
     * @param aerodrom klase Aerodrom
     */
    public void preuzmiAerodrom(Aerodrom aerodrom){
        System.out.println(aerodrom.getIcao());
        this.sviAerodromi.add(aerodrom);
        posiljateljPoruka.saljiPoruku("Broj aerodroma: " + this.brojAerodroma());
    }
    
    /**
     * Funckija koja briše sve aerodrome iz aplikacijske evidencije 
     */
    public void obrisiAerodrome(){
        this.sviAerodromi.clear();
        posiljateljPoruka.saljiPoruku("Broj aerodroma: " + this.brojAerodroma());
    }
    
    /**
     * Funckija koja vraća broj aerodroma koji se nalaze u aplikacijskoj evidenciji
     * @return integer broj aerodroma
     */
    public int brojAerodroma() {
        return this.sviAerodromi.size();
    }
}
