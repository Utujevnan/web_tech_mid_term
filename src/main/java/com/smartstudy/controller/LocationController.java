package com.smartstudy.controller;

import com.smartstudy.model.ELocationType;
import com.smartstudy.model.Location;
import com.smartstudy.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PostMapping("/save")
    public ResponseEntity<?> saveLocation(
            @RequestBody Location location,
            @RequestParam(required = false) Long parentId) {
        String result = locationService.saveLocation(location, parentId);
        if (result.equals("Location saved successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Location> getLocationByCode(@PathVariable String code) {
        return ResponseEntity.ok(locationService.getLocationByCode(code));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Location> getLocationByName(@PathVariable String name) {
        return ResponseEntity.ok(locationService.getLocationByName(name));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Location>> getLocationsByType(@PathVariable ELocationType type) {
        return ResponseEntity.ok(locationService.getLocationsByType(type));
    }

    @GetMapping("/{parentId}/children")
    public ResponseEntity<List<Location>> getChildrenByParentId(@PathVariable Long parentId) {
        return ResponseEntity.ok(locationService.getChildrenByParentId(parentId));
    }
}