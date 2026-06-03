package com.netzeroiq.dto;

import lombok.*;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import com.netzeroiq.model.RawRecord;

@Data @Builder
public class RawRecordDto {
    private UUID id;
    private int rowIndex;
    private Map<String, Object> rawData;
    private String parseError;
    private Instant createdAt;

    public static RawRecordDto from(RawRecord r) {
        return RawRecordDto.builder()
                .id(r.getId()).rowIndex(r.getRowIndex())
                .rawData(r.getRawData()).parseError(r.getParseError())
                .createdAt(r.getCreatedAt())
                .build();
    }
}
