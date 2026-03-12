package com.smartstudy.service;

import com.smartstudy.model.ELocationType;
import com.smartstudy.model.Location;
import com.smartstudy.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public String saveLocation(Location location, Long parentId) {
        if (parentId != null) {
            Location parent = locationRepository.findById(parentId).orElse(null);
            if (parent != null) {
                location.setParent(parent);
            }
        }
        boolean exists = locationRepository.existsByCode(location.getCode());
        if (exists) {
            return "Location with that code already exists";
        } else {
            locationRepository.save(location);
            return "Location saved successfully";
        }
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
    }

    public Location getLocationByCode(String code) {
        return locationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Location not found with code: " + code));
    }

    public Location getLocationByName(String name) {
        return locationRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Location not found with name: " + name));
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<Location> getLocationsByType(ELocationType type) {
        return locationRepository.findByType(type);
    }

    public List<Location> getChildrenByParentId(Long parentId) {
        return locationRepository.findByParent_Id(parentId);
    }
}