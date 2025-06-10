/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.service;

import ffos.dgreger.model.Odgovor;
import ffos.dgreger.model.Pitanje;
import ffos.dgreger.model.dto.PitanjeDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author svenk
 */
@Service
public class PitanjeService extends GlavniService{
    public List<Pitanje> getAll(){
        return session.createQuery("from pitanje", Pitanje.class).list();
    }
    
    public Pitanje getBySifra(int sifra){
        return session.get(Pitanje.class, sifra);
    }
    
    public Pitanje post(PitanjeDTO o){
        Pitanje pitanje = new Pitanje(o.tekst());
        session.beginTransaction();
        session.persist(pitanje);
        session.getTransaction().commit();
        return pitanje;
    }
    
    public List<Odgovor> getOdgovore(int sifra){
        return session.createQuery("from odgovor o where o.pitanja.sifra = :sifra", Odgovor.class)
                .setParameter("sifra", sifra)
                .list();
    }
}
