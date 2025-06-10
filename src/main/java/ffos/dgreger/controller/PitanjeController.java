/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.controller;

import ffos.dgreger.model.Pitanje;
import ffos.dgreger.model.dto.PitanjeDTO;
import ffos.dgreger.service.PitanjeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Pitanje", description = "Dostupne rute za entitet Pitanje. Sve funkcionalnosti - GET (get, getBySifra i getOdgovore) i POST.")
@RestController
@RequestMapping("/api/dgreger/pitanje")
public class PitanjeController {
    private final PitanjeService pitanjeService;

    public PitanjeController(PitanjeService pitanjeService) {
        this.pitanjeService = pitanjeService;
    }
    
    @Operation(
            summary = "Dohvaća sva pitanja", tags = {"get", "pitanje"},
            description = "Dohvaća sve pitanja sa svim podacima"
    )
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pitanje.class)))),
                @ApiResponse(responseCode = "500", description = "Interna pogreška servera", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
            })
    @GetMapping("/get")
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(pitanjeService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
        @Operation(
            summary = "Dohvaća pitanje po šifri",
            description = "Dohvaća pitanje po danoj šifri sa svim podacima. "
            + "Ukoliko ne postoji pitanje za danu šifru vraća prazan odgovor",
            tags = {"pitanje", "getBy"},
            parameters = {
                @Parameter(
                        name = "sifra",
                        allowEmptyValue = false,
                        required = true,
                        description = "Primarni ključ pitanja u bazi podataka, mora biti veći od nula",
                        example = "2"
                )})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Pitanje.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "204", description = "Ne postoji pitanje za danu šifru", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html")),
        @ApiResponse(responseCode = "400", description = "Šifra mora biti veća od nula", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html")),
        @ApiResponse(responseCode = "500", description = "Interna pogreška servera", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
    })
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
    
    @Operation(
            summary = "Kreira novo pitanje",
            tags = {"post", "pitanje"},
            description = "Kreira novo pitanje. Tekst pitanja obavezno!")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Kreirano", content = @Content(schema = @Schema(implementation = Pitanje.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Loš zahtjev (nije primljen dto objekt ili ne postoji tekst)", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html")),
        @ApiResponse(responseCode = "500", description = "Interna pogreška servera", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
    })
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
    
    @Operation(
            summary = "Dohvaća sve odgovore jednog pitanja",
            description = "Vraća sve odgovore koji pripadaju pitanju s navedenom šifrom.",
            parameters = {
                @Parameter(
                        name = "sifra",
                        allowEmptyValue = false,
                        required = true,
                        description = "Primarni ključ pitanja u bazi podataka",
                        example = "3"
                )
            }
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Popis odjela unutar tvrtke",
                content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "204", description = "Šifra mora biti veća od nula ili nema odjela za zadanu tvrtku",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Interna pogreška servera",
                content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
    })
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
