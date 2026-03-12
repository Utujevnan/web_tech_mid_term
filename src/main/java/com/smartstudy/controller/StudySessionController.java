package com.smartstudy.controller;

import com.smartstudy.model.StudySession;
import com.smartstudy.service.StudySessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class StudySessionController {

    private final StudySessionService studySessionService;

    @PostMapping
    public ResponseEntity<StudySession> createStudySession(
            @RequestBody StudySession studySession,
            @RequestParam Long groupId,
            @RequestParam Long userId) {
        return ResponseEntity.ok(studySessionService.createStudySession(studySession, groupId, userId));
    }

    @GetMapping
    public ResponseEntity<List<StudySession>> getAllStudySessions() {
        return ResponseEntity.ok(studySessionService.getAllStudySessions());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<StudySession>> getAllStudySessionsSorted(
            @RequestParam(defaultValue = "title") String sortBy) {
        return ResponseEntity.ok(studySessionService.getAllStudySessionsSorted(sortBy));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<StudySession>> getAllStudySessionsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(studySessionService.getAllStudySessionsPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySession> getStudySessionById(@PathVariable Long id) {
        return ResponseEntity.ok(studySessionService.getStudySessionById(id));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudySession>> getStudySessionsByGroup(
            @PathVariable Long groupId) {
        return ResponseEntity.ok(studySessionService.getStudySessionsByGroup(groupId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudySession>> getStudySessionsByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(studySessionService.getStudySessionsByStatus(status));
    }

    @GetMapping("/exists/{title}")
    public ResponseEntity<Boolean> studySessionExistsByTitle(@PathVariable String title) {
        return ResponseEntity.ok(studySessionService.studySessionExistsByTitle(title));
    }
}