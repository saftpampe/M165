package ch.iet_gibb.hockeyapp.arena;



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
@RequestMapping(ArenaController.PATH)
public class ArenaController {
    public static final String PATH = "/arenas";
    private final ArenaService arenaService;

    @Autowired
    public ArenaController(ArenaService arenaService) {
        this.arenaService = arenaService;
    }

    @GetMapping
    @Operation(summary = "Get all arenas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arenas found",
                    content = @Content(schema = @Schema(implementation = ArenaResponseDTO.class)))})
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(arenaService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get arena by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arena found",
                    content = @Content(schema = @Schema(implementation = ArenaResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Arena not found", content = @Content)})
    public ResponseEntity<?> findById(@Parameter(description = "Id of the arena") @PathVariable String id) {
        try {
            return ResponseEntity.ok(arenaService.findById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Arena not found");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new arena")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Arena created successfully",
                    content = @Content(schema = @Schema(implementation = ArenaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
    public ResponseEntity<?> create(@Valid @RequestBody ArenaRequestDTO arenaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(arenaService.insert(arenaRequestDTO));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an arena")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arena updated successfully",
                    content = @Content(schema = @Schema(implementation = ArenaResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Arena not found", content = @Content)})
    public ResponseEntity<?> update(@Parameter(description = "Id of the arena to update") @PathVariable String id,
                                    @Valid @RequestBody ArenaRequestDTO arenaRequestDTO) {
        try {
            return ResponseEntity.ok(arenaService.update(arenaRequestDTO, id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Arena not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an arena")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Arena deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Arena not found", content = @Content)})
    public ResponseEntity<?> delete(@Parameter(description = "Id of the arena to delete") @PathVariable String id) {
        try {
            arenaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Arena not found");
        }
    }
}
