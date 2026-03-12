package com.smartstudy.controller;

import com.smartstudy.model.Subject;
import com.smartstudy.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.createSubject(subject));
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Subject>> getAllSubjectsSorted(
            @RequestParam(defaultValue = "name") String sortBy) {
        return ResponseEntity.ok(subjectService.getAllSubjectsSorted(sortBy));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<Subject>> getAllSubjectsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(subjectService.getAllSubjectsPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @GetMapping("/exists/{name}")
    public ResponseEntity<Boolean> subjectExistsByName(@PathVariable String name) {
        return ResponseEntity.ok(subjectService.subjectExistsByName(name));
    }
}