package com.netzeroiq.dto;

import lombok.*;
import java.util.UUID;

import com.netzeroiq.model.Tenant;

@Data @Builder
public class TenantDto {
    private UUID id;
    private String name;
    private String slug;
    private String industry;

    public static TenantDto from(Tenant t) {
        if (t == null) return null;
        return TenantDto.builder()
                .id(t.getId()).name(t.getName())
                .slug(t.getSlug()).industry(t.getIndustry())
                .build();
    }
}
