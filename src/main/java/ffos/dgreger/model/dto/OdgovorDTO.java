/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ffos.dgreger.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author svenk
 */
public record OdgovorDTO(
        @Schema (example = "1") int sifraPitanja,
        @Schema (example = "Edukacija o sigurnom i odgovornom ponašanju na internetu") String tekst,
        @Schema (example = "true") boolean jeTocan
        ) {
    
}
