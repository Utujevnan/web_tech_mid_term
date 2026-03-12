package com.smartstudy.repository;

import com.smartstudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findByVillage_Id(Long villageId);

    List<User> findByVillage_Parent_Id(Long cellId);

    List<User> findByVillage_Parent_Parent_Id(Long sectorId);

    List<User> findByVillage_Parent_Parent_Parent_Id(Long districtId);

    List<User> findByVillage_Parent_Parent_Parent_Parent_Id(Long provinceId);
}
