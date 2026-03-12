package com.smartstudy.repository;

import com.smartstudy.model.StudySession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudySessionRepository extends JpaRepository<StudySession, Long> {

    boolean existsByTitle(String title);

    List<StudySession> findByStudyGroup_Id(Long groupId);

    List<StudySession> findByCreatedBy_Id(Long userId);

    List<StudySession> findByStatus(String status);

    Page<StudySession> findAll(Pageable pageable);
}