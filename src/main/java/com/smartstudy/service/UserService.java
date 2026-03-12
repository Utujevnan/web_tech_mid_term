package com.smartstudy.service;

import com.smartstudy.model.Location;
import com.smartstudy.model.User;
import com.smartstudy.model.UserProfile;
import com.smartstudy.repository.LocationRepository;
import com.smartstudy.repository.UserRepository;
import com.smartstudy.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final LocationRepository locationRepository;

    public User createUser(User user, UserProfile userProfile, Long villageId) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        // find village and set it
        Location village = locationRepository.findById(villageId)
                .orElseThrow(() -> new RuntimeException("Village not found with id: " + villageId));
        user.setVillage(village);
        user.setCreatedAt(LocalDateTime.now());
        user.setIsActive(true);
        user.setUserProfile(null);
        User savedUser = userRepository.save(user);
        userProfile.setUser(savedUser);
        UserProfile savedProfile = userProfileRepository.save(userProfile);
        savedUser.setUserProfile(savedProfile);
        return userRepository.save(savedUser);
    }

    public UserProfile updateUserProfile(Long userId, UserProfile updatedProfile) {
        UserProfile existingProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found for user: " + userId));
        existingProfile.setBio(updatedProfile.getBio());
        existingProfile.setPhoneNumber(updatedProfile.getPhoneNumber());
        existingProfile.setProfilePhoto(updatedProfile.getProfilePhoto());
        existingProfile.setDateOfBirth(updatedProfile.getDateOfBirth());
        existingProfile.setGender(updatedProfile.getGender());
        return userProfileRepository.save(existingProfile);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersSorted(String sortBy) {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public Page<User> getAllUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersByProvince(Long provinceId) {
        return userRepository.findByVillage_Parent_Parent_Parent_Parent_Id(provinceId);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean usernameExists(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
}