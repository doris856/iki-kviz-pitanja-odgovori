/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 *
 * @author svenk
 */
@Entity(name = "pitanja")
public class Pitanje extends Entitet{
    @Column(nullable = false)
    private String tekst;

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Pitanje() {
    }

    public Pitanje(String tekst) {
        this.tekst = tekst;
    }
}
