package com.netzeroiq.dto;

import lombok.Data;
import java.util.Map;

@Data
public class DataSourceRequest {
    private String name;
    private String sourceType;
    private String description;
    private Map<String, String> columnMapping;
}
