package ch.iet_gibb.hockeyapp.team;

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
@RequestMapping(TeamController.PATH)
public class TeamController {
    public static final String PATH = "/teams";
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    @Operation(summary = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class)))})

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(teamService.findAll());
    }

    @GetMapping("{id}")
    @Operation(summary = "Get team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team found",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)})
    public ResponseEntity<?> findById(@Parameter(description = "Id of the team") @PathVariable String id) {
        try {
            return ResponseEntity.ok(teamService.findById(id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get teams by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No teams found", content = @Content)})
    public ResponseEntity<?> findByName(@Parameter(description = "Name of the team") @PathVariable String name) {
        try {
            return ResponseEntity.ok(teamService.findByName(name));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No teams found with that name");
        }
    }

    @PostMapping
    @Operation(summary = "Create a new team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created successfully",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)})
    public ResponseEntity<?> create(@Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(teamService.insert(teamRequestDTO));
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an existing team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team updated successfully",
                    content = @Content(schema = @Schema(implementation = TeamResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)})
    public ResponseEntity<?> update(@Parameter(description = "Id of the team to update") @PathVariable String id,
                                    @Valid @RequestBody TeamRequestDTO teamRequestDTO) {
        try {
            return ResponseEntity.ok(teamService.update(teamRequestDTO, id));
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete an existing team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Team deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Team not found", content = @Content)})
    public ResponseEntity<?> delete(@Parameter(description = "Id of the team to delete") @PathVariable String id) {
        try {
            teamService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found");
        }
    }
}
