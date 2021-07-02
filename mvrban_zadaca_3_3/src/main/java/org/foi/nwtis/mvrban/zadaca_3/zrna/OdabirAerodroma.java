package org.foi.nwtis.mvrban.zadaca_3.zrna;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.foi.nwtis.mvrban.zadaca_3.ejb.sb.OsobniAerodromi;
import org.foi.nwtis.podaci.Aerodrom;

@Named(value = "odabirAerodroma")
@RequestScoped
public class OdabirAerodroma {

    @EJB
    OsobniAerodromi osobniAerodromi;

    @Getter
    @Setter
    private String nazivAerodroma;
    
    @Getter
    @Setter
    private String korisnik;
    
    @Getter
    @Setter
    private String odabraniAerodrom;
    
    private List<Aerodrom> sviAerodromi = new ArrayList<>();
    
    /**
     * Konstruktor klase OdabirAerodroma
     */
    public OdabirAerodroma() {
    }

    /**
     * Funckija koja dohvaća sve aerodrome iz klase osobnih aerodroma i vraća iz u obliku liste
     * @return List Aerodrom 
     */
    public List<Aerodrom> getSviAerodromi() {
        
        sviAerodromi = osobniAerodromi.filtrirajAerodrome(nazivAerodroma);

        List<Aerodrom> aer = osobniAerodromi.dajEvidentiraneAerodrome();

        for (Iterator<Aerodrom> iterator = sviAerodromi.iterator(); iterator.hasNext();) {
            Aerodrom value = iterator.next();
            for (Aerodrom ae : aer) {
                if (value.getIcao().compareTo(ae.getIcao()) == 0) {
                    iterator.remove();
                }
            }
        } 

        return sviAerodromi;
    }
    
    /**
     * Funkcija koja služi za filtriranje svih aerodroma i vraća ih u obliku liste aerodroma
     * @return string
     */
    public String filtrirajAerodrome(){
        
        getSviAerodromi();
            
        return "";
    }
    
    /**
     * Funkcija koja služi za dodavanje novog aerodroma u aplikacijsku evidenciju aerodroma
     * @return String
     */
    public String dodajAerodrom(){
        try {
            if (korisnik.length() < 1) {
                System.out.println(korisnik);
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
      
        for(Aerodrom a : sviAerodromi) {
            if(a.getIcao().compareTo(odabraniAerodrom) == 0){
                System.out.println(odabraniAerodrom);
                osobniAerodromi.dodajAerodromUMyAirportsIBankuAerodroma(a, korisnik);
                filtrirajAerodrome();
                break;
            }
        }
        
        return "";
    }
    
    /**
     * Funkcija koja služi za brisanje svih aerodroma iz aplikacijske evidencije aerodroma
     * @return String
     */
    public String obrisiAerodrome() {
        osobniAerodromi.obrisiAerodrome();
        return "";
    }
}
