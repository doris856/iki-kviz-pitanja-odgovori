/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.service;

import ffos.dgreger.model.Odgovor;
import ffos.dgreger.model.Pitanje;
import ffos.dgreger.model.dto.OdgovorDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author svenk
 */
@Service
public class OdgovorService extends GlavniService{
    public List<Odgovor> getAll(){
        return session.createQuery("from odgovor", Odgovor.class).list();
    }
    
    public Odgovor getByOdgovor(int sifra){
        return session.get(Odgovor.class, sifra);
    }
    
    public Odgovor post(OdgovorDTO o){
        session.beginTransaction();
        Pitanje p = session.get(Pitanje.class, o.sifraPitanja());
        Odgovor odgovor = new Odgovor();
        odgovor.setPitanja(p);
        odgovor.setTekst(o.tekst());
        odgovor.setJeTocan(o.jeTocan());
        session.persist(odgovor);
        session.getTransaction().commit();
        return odgovor;
    }
}
