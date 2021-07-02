package org.foi.nwtis.mvrban.zadaca_3.mdb;

import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.nwtis.mvrban.zadaca_3.wsep.OglasnikAerodroma;

@MessageDriven(mappedName = "jms/NWTiS_DZ3")
public class PrimateljPoruka implements MessageListener{
    
    @Inject
    @JMSConnectionFactory("jms/NWTiS_QF_DZ3")
    private JMSContext context;

    @Inject
    OglasnikAerodroma oglasnikAerodroma;
    
    /**
     * Funckija koja poziva funkciju iz oglasnika aerodroma koja služi za slanje poruka prema zadaci_3_3
     * @param message tipa string
     */
    public void onMessage(Message message) {
        System.out.println("Primatelj poruka:"+message);
        String text;
        try {
            text = ((TextMessage) message).getText();
            System.out.println("Primatelj poruka:"+text);
            oglasnikAerodroma.posaljiPoruku(text);
            
        } catch (JMSException ex) {
            Logger.getLogger(PrimateljPoruka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }   
}
