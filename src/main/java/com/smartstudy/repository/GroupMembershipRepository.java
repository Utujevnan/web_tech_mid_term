package com.smartstudy.repository;

import com.smartstudy.model.GroupMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GroupMembershipRepository extends JpaRepository<GroupMembership, Long> {

    boolean existsByUser_IdAndStudyGroup_Id(Long userId, Long groupId);

    List<GroupMembership> findByUser_Id(Long userId);

    List<GroupMembership> findByStudyGroup_Id(Long groupId);

    List<GroupMembership> findByGroupRole(String groupRole);

    List<GroupMembership> findByIsActive(Boolean isActive);
}