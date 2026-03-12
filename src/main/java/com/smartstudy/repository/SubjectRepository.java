package com.smartstudy.repository;

import com.smartstudy.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    boolean existsByName(String name);

    boolean existsByCode(String code);
}