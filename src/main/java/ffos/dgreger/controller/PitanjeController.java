/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.controller;

import ffos.dgreger.model.Pitanje;
import ffos.dgreger.model.dto.PitanjeDTO;
import ffos.dgreger.service.PitanjeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author svenk
 */
@RestController
@RequestMapping("/api/dgreger/pitanje")
public class PitanjeController {
    private final PitanjeService pitanjeService;

    public PitanjeController(PitanjeService pitanjeService) {
        this.pitanjeService = pitanjeService;
    }
    
    @GetMapping("/get")
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(pitanjeService.getAll(), HttpStatus.OK);
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
            
            Pitanje pitanje = pitanjeService.getBySifra(sifra);
            if (pitanje == null) {
                return new ResponseEntity<>("Pitanje s navedenom šifrom: " + sifra + " ne postoji!", HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/post")
    public ResponseEntity post(
            @RequestBody(required = true) PitanjeDTO dto
    ){
        try {
            if (dto == null) {
                return new ResponseEntity<>("Nisu uneseni traženi podaci!", HttpStatus.BAD_REQUEST);
            }
            
            if (dto.tekst() == null || dto.tekst().isEmpty()) {
                return new ResponseEntity<>("Tekst pitanja je obavezan!", HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(pitanjeService.post(dto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getOdgovore")
    public ResponseEntity getOdgovore(
            @RequestParam int sifra
    ){
        try {
            if (sifra <= 0) {
                return new ResponseEntity<>("Šifra mora biti veća od 0 " + sifra, HttpStatus.BAD_REQUEST);
            }
            
            Pitanje p = pitanjeService.getBySifra(sifra);
            if (p == null) {
                return new ResponseEntity<>("Ne postoji pitanje s navedenom šifrom " + sifra, HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(pitanjeService.getOdgovore(sifra), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
