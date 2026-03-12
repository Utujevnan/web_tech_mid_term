package com.smartstudy.repository;

import com.smartstudy.model.StudyGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {

    boolean existsByName(String name);

    List<StudyGroup> findBySubject_Id(Long subjectId);

    List<StudyGroup> findByCreatedBy_Id(Long userId);

    Page<StudyGroup> findAll(Pageable pageable);

    List<StudyGroup> findByIsPrivate(Boolean isPrivate);
}