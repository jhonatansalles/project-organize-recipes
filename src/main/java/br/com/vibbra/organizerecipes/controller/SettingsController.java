package br.com.vibbra.organizerecipes.controller;

import br.com.vibbra.organizerecipes.model.entity.settings.Settings;
import br.com.vibbra.organizerecipes.model.response.SettingsResponse;
import br.com.vibbra.organizerecipes.service.settings.SettingsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/settings")
@PreAuthorize("hasRole('USER')")
public class SettingsController {

    @Autowired
    SettingsService settingsService;

    @ApiOperation(value = "Retrieve settings data.")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SettingsResponse> get() {
        return ResponseEntity.ok(settingsService.getSettings());
    }

    @ApiOperation(value = "Create/Update a specific settings with the data entered.")
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SettingsResponse> create(@RequestBody Settings settings) {
        return ResponseEntity.ok(settingsService.updateSettings(settings));
    }
}