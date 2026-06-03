package com.netzeroiq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.netzeroiq.model.AuditLog;
import com.netzeroiq.model.User;
import com.netzeroiq.repository.AuditLogRepository;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void logEvent(User actor, AuditLog.Action action, String objectType,
                         String objectId, String fieldName, String oldValue,
                         String newValue, String note) {
        AuditLog log = AuditLog.builder()
                .actor(actor)
                .action(action)
                .objectType(objectType)
                .objectId(objectId)
                .fieldName(fieldName != null ? fieldName : "")
                .oldValue(oldValue)
                .newValue(newValue)
                .note(note != null ? note : "")
                .build();
        auditLogRepository.save(log);
    }

    public void logEvent(User actor, AuditLog.Action action, String objectType,
                         String objectId, String note) {
        logEvent(actor, action, objectType, objectId, null, null, null, note);
    }
}
