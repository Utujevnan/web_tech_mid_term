package com.smartstudy.repository;

import com.smartstudy.model.ELocationType;
import com.smartstudy.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    boolean existsByCode(String code);

    Optional<Location> findByCode(String code);

    Optional<Location> findByName(String name);

    List<Location> findByType(ELocationType type);

    List<Location> findByParent_Id(Long parentId);
}