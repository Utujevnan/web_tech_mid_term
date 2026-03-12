package com.smartstudy.service;

import com.smartstudy.model.StudySession;
import com.smartstudy.model.StudyGroup;
import com.smartstudy.model.User;
import com.smartstudy.repository.StudySessionRepository;
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
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final StudyGroupService studyGroupService;
    private final UserService userService;

    public StudySession createStudySession(StudySession studySession, Long groupId, Long userId) {
        if (studySessionRepository.existsByTitle(studySession.getTitle())) {
            throw new RuntimeException("Study session already exists with title: " + studySession.getTitle());
        }
        StudyGroup studyGroup = studyGroupService.getStudyGroupById(groupId);
        User user = userService.getUserById(userId);
        studySession.setStudyGroup(studyGroup);
        studySession.setCreatedBy(user);
        studySession.setStatus("SCHEDULED");
        return studySessionRepository.save(studySession);
    }

    public List<StudySession> getAllStudySessions() {
        return studySessionRepository.findAll();
    }

    public List<StudySession> getAllStudySessionsSorted(String sortBy) {
        return studySessionRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
    }

    public Page<StudySession> getAllStudySessionsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studySessionRepository.findAll(pageable);
    }

    public StudySession getStudySessionById(Long id) {
        return studySessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study session not found with id: " + id));
    }

    public List<StudySession> getStudySessionsByGroup(Long groupId) {
        return studySessionRepository.findByStudyGroup_Id(groupId);
    }

    public List<StudySession> getStudySessionsByStatus(String status) {
        return studySessionRepository.findByStatus(status);
    }

    public boolean studySessionExistsByTitle(String title) {
        return studySessionRepository.existsByTitle(title);
    }
}