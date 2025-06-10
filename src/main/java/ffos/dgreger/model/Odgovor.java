/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author svenk
 */
@Entity(name = "odgovor")
public class Odgovor extends Entitet{
    @ManyToOne
    private Pitanje pitanje;
    @Column(nullable = false)
    private String tekst;
    @Column(columnDefinition = "bit", name = "tocan")
    private boolean jeTocan;

    public Odgovor() {
    }

    public Odgovor(Pitanje pitanje, String tekst, boolean jeTocan) {
        this.pitanje = pitanje;
        this.tekst = tekst;
        this.jeTocan = jeTocan;
    }

    public Pitanje getPitanja() {
        return pitanje;
    }

    public void setPitanja(Pitanje pitanja) {
        this.pitanje = pitanja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public boolean isJeTocan() {
        return jeTocan;
    }

    public void setJeTocan(boolean jeTocan) {
        this.jeTocan = jeTocan;
    }
}
