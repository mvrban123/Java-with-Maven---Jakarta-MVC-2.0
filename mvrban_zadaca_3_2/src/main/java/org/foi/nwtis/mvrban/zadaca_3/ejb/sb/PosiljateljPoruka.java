package org.foi.nwtis.mvrban.zadaca_3.ejb.sb;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import jakarta.jms.TextMessage;

@Stateless
public class PosiljateljPoruka {

    @Inject
    @JMSConnectionFactory("jms/NWTiS_QF_DZ3")
    private JMSContext context;
    
    @Resource(lookup = "jms/NWTiS_DZ3")
    Queue requestQueue;

    /**
     * Funckija koja šalje novu poruku u red poruka
     * @param tekstPoruke klase String 
     */
    public void saljiPoruku (String tekstPoruke){
        TextMessage poruka = context.createTextMessage(tekstPoruke);
        context.createProducer().send(requestQueue, poruka);
    }
}
