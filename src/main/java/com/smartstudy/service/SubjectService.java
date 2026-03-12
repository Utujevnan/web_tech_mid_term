package com.smartstudy.service;

import com.smartstudy.model.Subject;
import com.smartstudy.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public Subject createSubject(Subject subject) {
        if (subjectRepository.existsByName(subject.getName())) {
            throw new RuntimeException("Subject already exists with name: " + subject.getName());
        }
        if (subjectRepository.existsByCode(subject.getCode())) {
            throw new RuntimeException("Subject already exists with code: " + subject.getCode());
        }
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getAllSubjectsSorted(String sortBy) {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public Page<Subject> getAllSubjectsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return subjectRepository.findAll(pageable);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    public boolean subjectExistsByName(String name) {
        return subjectRepository.existsByName(name);
    }
}