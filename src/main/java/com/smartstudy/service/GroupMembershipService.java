package com.smartstudy.service;

import com.smartstudy.model.GroupMembership;
import com.smartstudy.model.User;
import com.smartstudy.model.StudyGroup;
import com.smartstudy.repository.GroupMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupMembershipService {

    private final GroupMembershipRepository groupMembershipRepository;
    private final UserService userService;
    private final StudyGroupService studyGroupService;

    public GroupMembership joinGroup(Long userId, Long groupId) {
        if (groupMembershipRepository.existsByUser_IdAndStudyGroup_Id(userId, groupId)) {
            throw new RuntimeException("User is already a member of this group");
        }
        User user = userService.getUserById(userId);
        StudyGroup studyGroup = studyGroupService.getStudyGroupById(groupId);
        GroupMembership membership = GroupMembership.builder()
                .user(user)
                .studyGroup(studyGroup)
                .groupRole("MEMBER")
                .joinedAt(LocalDateTime.now())
                .isActive(true)
                .build();
        return groupMembershipRepository.save(membership);
    }

    public List<GroupMembership> getMembersByGroup(Long groupId) {
        return groupMembershipRepository.findByStudyGroup_Id(groupId);
    }

    public List<GroupMembership> getGroupsByUser(Long userId) {
        return groupMembershipRepository.findByUser_Id(userId);
    }

    public boolean isMember(Long userId, Long groupId) {
        return groupMembershipRepository.existsByUser_IdAndStudyGroup_Id(userId, groupId);
    }

    public List<GroupMembership> getMembersByRole(String role) {
        return groupMembershipRepository.findByGroupRole(role);
    }

    public List<GroupMembership> getActiveMembers() {
        return groupMembershipRepository.findByIsActive(true);
    }
}