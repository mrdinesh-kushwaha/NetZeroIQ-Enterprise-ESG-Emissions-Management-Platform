package com.netzeroiq.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.netzeroiq.dto.AuditLogDto;
import com.netzeroiq.model.User;
import com.netzeroiq.repository.AuditLogRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuditController {

    private final AuditLogRepository auditLogRepository;

    @GetMapping("/audit")
    public ResponseEntity<List<AuditLogDto>> listAuditLogs(@AuthenticationPrincipal User user) {
        List<AuditLogDto> dtos = auditLogRepository
                .findTop200ByActorTenantOrderByTimestampDesc(user.getTenant())
                .stream().map(AuditLogDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/records/{id}/audit")
    public ResponseEntity<List<AuditLogDto>> recordAuditTrail(@PathVariable UUID id) {
        List<AuditLogDto> dtos = auditLogRepository
                .findByObjectTypeAndObjectIdOrderByTimestampDesc("NormalizedRecord", id.toString())
                .stream().map(AuditLogDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
