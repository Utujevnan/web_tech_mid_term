package com.smartstudy.controller;

import com.smartstudy.model.GroupMembership;
import com.smartstudy.service.GroupMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class GroupMembershipController {

    private final GroupMembershipService groupMembershipService;

    @PostMapping("/join")
    public ResponseEntity<GroupMembership> joinGroup(
            @RequestParam Long userId,
            @RequestParam Long groupId) {
        return ResponseEntity.ok(groupMembershipService.joinGroup(userId, groupId));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<GroupMembership>> getMembersByGroup(
            @PathVariable Long groupId) {
        return ResponseEntity.ok(groupMembershipService.getMembersByGroup(groupId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GroupMembership>> getGroupsByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(groupMembershipService.getGroupsByUser(userId));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> isMember(
            @RequestParam Long userId,
            @RequestParam Long groupId) {
        return ResponseEntity.ok(groupMembershipService.isMember(userId, groupId));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<GroupMembership>> getMembersByRole(
            @PathVariable String role) {
        return ResponseEntity.ok(groupMembershipService.getMembersByRole(role));
    }

    @GetMapping("/active")
    public ResponseEntity<List<GroupMembership>> getActiveMembers() {
        return ResponseEntity.ok(groupMembershipService.getActiveMembers());
    }
}