package com.smartstudy.service;

import com.smartstudy.model.StudyGroup;
import com.smartstudy.model.User;
import com.smartstudy.model.Subject;
import com.smartstudy.repository.StudyGroupRepository;
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
public class StudyGroupService {

    private final StudyGroupRepository studyGroupRepository;
    private final UserService userService;
    private final SubjectService subjectService;

    public StudyGroup createStudyGroup(StudyGroup studyGroup, Long userId, Long subjectId) {
        if (studyGroupRepository.existsByName(studyGroup.getName())) {
            throw new RuntimeException("Study group already exists with name: " + studyGroup.getName());
        }
        User user = userService.getUserById(userId);
        Subject subject = subjectService.getSubjectById(subjectId);
        studyGroup.setCreatedBy(user);
        studyGroup.setSubject(subject);
        studyGroup.setCreatedAt(LocalDateTime.now());
        studyGroup.setIsPrivate(false);
        return studyGroupRepository.save(studyGroup);
    }

    public List<StudyGroup> getAllStudyGroups() {
        return studyGroupRepository.findAll();
    }

    public List<StudyGroup> getAllStudyGroupsSorted(String sortBy) {
        return studyGroupRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public Page<StudyGroup> getAllStudyGroupsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studyGroupRepository.findAll(pageable);
    }

    public StudyGroup getStudyGroupById(Long id) {
        return studyGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study group not found with id: " + id));
    }

    public List<StudyGroup> getStudyGroupsBySubject(Long subjectId) {
        return studyGroupRepository.findBySubject_Id(subjectId);
    }

    public List<StudyGroup> getStudyGroupsByUser(Long userId) {
        return studyGroupRepository.findByCreatedBy_Id(userId);
    }

    public boolean studyGroupExistsByName(String name) {
        return studyGroupRepository.existsByName(name);
    }
}