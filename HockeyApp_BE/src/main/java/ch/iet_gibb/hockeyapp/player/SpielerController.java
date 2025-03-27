package ch.iet_gibb.hockeyapp.player;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(SpielerController.PATH)
public class SpielerController {
    public static final String PATH = "/players";
    private final SpielerService spielerService;

    @Autowired
    public SpielerController(SpielerService spielerService) {
        this.spielerService = spielerService;
    }

    @GetMapping
    @Operation(summary = "Get all players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players found",
                    content = @Content(schema = @Schema(implementation = SpielerResponseDTO.class)))})

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(spielerService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get player by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player found",
                    content = @Content(schema = @Schema(implementation = SpielerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)})
    public ResponseEntity<?> findById(@Parameter(description = "Id of the player") @PathVariable String id) {
        try {
            return ResponseEntity.ok(spielerService.findById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @PostMapping("/team")
    @Operation(summary = "Get players by team id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players found",
                    content = @Content(schema = @Schema(implementation = SpielerResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No players found for this team", content = @Content)})
    public ResponseEntity<?> findByTeam(@Valid @RequestBody SpielerRequestDTO spielerRequestDTO) {
        try {
            return ResponseEntity.ok(spielerService.findByTeamId(spielerRequestDTO.getTeam()));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No players found for this team");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created successfully",
                    content = @Content(schema = @Schema(implementation = SpielerResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
    public ResponseEntity<?> create(@Valid @RequestBody SpielerRequestDTO spielerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spielerService.insert(spielerRequestDTO));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated successfully",
                    content = @Content(schema = @Schema(implementation = SpielerResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)})
    public ResponseEntity<?> update(@Parameter(description = "Id of the player to update") @PathVariable String id,
                                    @Valid @RequestBody SpielerRequestDTO spielerRequestDTO) {
        try {
            return ResponseEntity.ok(spielerService.update(spielerRequestDTO, id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Player deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Player not found", content = @Content)})
    public ResponseEntity<?> delete(@Parameter(description = "Id of the player to delete") @PathVariable String id) {
        try {
            spielerService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }
}
