/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.controller;

import ffos.dgreger.model.Odgovor;
import ffos.dgreger.model.dto.OdgovorDTO;
import ffos.dgreger.service.OdgovorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author svenk
 */
@RestController
@RequestMapping("/api/dgreger/odgovor")
public class OdgovorController {
    private OdgovorService odgovorService;

    public OdgovorController(OdgovorService odgovorService) {
        this.odgovorService = odgovorService;
    }
    
    @GetMapping("/get")
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(odgovorService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getBySifra")
    public ResponseEntity getBySifra(
            @RequestParam int sifra
    ){
        try {
            if (sifra <= 0) {
                return new ResponseEntity<>("Šifra mora biti veća od 0!", HttpStatus.BAD_REQUEST);
            }
            
            Odgovor odgovor = odgovorService.getBySifra(sifra);
            if (odgovor == null) {
                return new ResponseEntity<>("Tvrtka s navedenom šifrom " + sifra + " ne postoji!", HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/post")
    public ResponseEntity post(
            @RequestBody(required = true) OdgovorDTO dto
    ){
        try {
            if (dto == null) {
                return new ResponseEntity<>("Podaci nisu primljeni", HttpStatus.BAD_REQUEST);
            }
            
            if (dto.tekst() == null || dto.tekst().isEmpty()) {
                return new ResponseEntity<>("Tekst odgovora je obavezan!", HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(odgovorService.post(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
