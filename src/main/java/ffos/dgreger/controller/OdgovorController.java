/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.controller;

import ffos.dgreger.model.Odgovor;
import ffos.dgreger.model.dto.OdgovorDTO;
import ffos.dgreger.service.OdgovorService;
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

@Tag(name = "Odgovor", description = "Dostupne rute za entitet odgovor. Funkcionalnosti - GET (get, getBySifra) i POST.")
@RestController
@RequestMapping("/api/dgreger/odgovor")
public class OdgovorController {
    private final OdgovorService odgovorService;

    public OdgovorController(OdgovorService odgovorService) {
        this.odgovorService = odgovorService;
    }
    
    @Operation(
            summary = "Dohvaća sve odgovore", tags = {"get", "odgovore"},
            description = "Dohvaća sve odgovore sa svim podacima"
    )
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Odgovor.class)))),
                @ApiResponse(responseCode = "500", description = "Interna pogreška servera", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
            })
    @GetMapping("/get")
    public ResponseEntity get(){
        try {
            return new ResponseEntity<>(odgovorService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Operation(
            summary = "Dohvaća odgovor po šifri",
            description = "Dohvaća odgovor po danoj šifri sa svim podacima. "
            + "Ukoliko ne postoji odgovor za danu šifru vraća prazan odgovor",
            tags = {"odgovor", "getBy"},
            parameters = {
                @Parameter(
                        name = "sifra",
                        allowEmptyValue = false,
                        required = true,
                        description = "Primarni ključ odgovora u bazi podataka, mora biti veći od nula",
                        example = "2"
                )})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Odgovor.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "204", description = "Ne postoji odgovor za danu šifru", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html")),
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
            
            Odgovor odgovor = odgovorService.getBySifra(sifra);
            if (odgovor == null) {
                return new ResponseEntity<>("Tvrtka s navedenom šifrom " + sifra + " ne postoji!", HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @Operation(
            summary = "Kreira novi odgovor",
            tags = {"post", "odgovor"},
            description = "Kreira novi odgovor. Tekst odgovora obavezan!")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Kreirano", content = @Content(schema = @Schema(implementation = Odgovor.class), mediaType = "application/json")),
        @ApiResponse(responseCode = "400", description = "Loš zahtjev (nije primljen dto objekt ili ne postoji tekst odgovora)", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html")),
        @ApiResponse(responseCode = "500", description = "Interna pogreška servera", content = @Content(schema = @Schema(implementation = String.class), mediaType = "text/html"))
    })
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
