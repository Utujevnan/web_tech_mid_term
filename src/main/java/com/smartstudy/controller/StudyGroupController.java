package com.smartstudy.controller;

import com.smartstudy.model.StudyGroup;
import com.smartstudy.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @PostMapping
    public ResponseEntity<StudyGroup> createStudyGroup(
            @RequestBody StudyGroup studyGroup,
            @RequestParam Long userId,
            @RequestParam Long subjectId) {
        return ResponseEntity.ok(studyGroupService.createStudyGroup(studyGroup, userId, subjectId));
    }

    @GetMapping
    public ResponseEntity<List<StudyGroup>> getAllStudyGroups() {
        return ResponseEntity.ok(studyGroupService.getAllStudyGroups());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<StudyGroup>> getAllStudyGroupsSorted(
            @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.ok(studyGroupService.getAllStudyGroupsSorted(sortBy));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<StudyGroup>> getAllStudyGroupsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studyGroupService.getAllStudyGroupsPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroup> getStudyGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(studyGroupService.getStudyGroupById(id));
    }

    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<StudyGroup>> getStudyGroupsBySubject(
            @PathVariable Long subjectId) {
        return ResponseEntity.ok(studyGroupService.getStudyGroupsBySubject(subjectId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StudyGroup>> getStudyGroupsByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(studyGroupService.getStudyGroupsByUser(userId));
    }

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> studyGroupExistsByName(@PathVariable String name) {
        return ResponseEntity.ok(studyGroupService.studyGroupExistsByName(name));
    }
}