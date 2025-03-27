package ch.iet_gibb.hockeyapp.game;

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
@RequestMapping(SpielController.PATH)
public class SpielController {
    public static final String PATH = "/spiele";
    private final SpielService spielService;

    @Autowired
    public SpielController(SpielService spielService) {
        this.spielService = spielService;
    }

    @GetMapping
    @Operation(summary = "Get all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games found",
                    content = @Content(schema = @Schema(implementation = SpielResponseDTO.class)))})

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(spielService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get game by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found",
                    content = @Content(schema = @Schema(implementation = SpielResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content)})
    public ResponseEntity<?> findById(@Parameter(description = "Id of the game") @PathVariable String id) {
        try {
            return ResponseEntity.ok(spielService.findById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created successfully",
                    content = @Content(schema = @Schema(implementation = SpielResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
    public ResponseEntity<?> create(@Valid @RequestBody SpielRequestDTO spielRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spielService.insert(spielRequestDTO));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update a game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game updated successfully",
                    content = @Content(schema = @Schema(implementation = SpielResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content)})
    public ResponseEntity<?> update(@Parameter(description = "Id of the game to update") @PathVariable String id,
                                    @Valid @RequestBody SpielRequestDTO spielRequestDTO) {
        try {
            return ResponseEntity.ok(spielService.update(spielRequestDTO, id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Game deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Game not found", content = @Content)})
    public ResponseEntity<?> delete(@Parameter(description = "Id of the game to delete") @PathVariable String id) {
        try {
            spielService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found");
        }
    }
}
