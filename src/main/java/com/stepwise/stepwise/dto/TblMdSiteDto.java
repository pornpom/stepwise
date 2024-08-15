package com.stepwise.stepwise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TblMdSiteDto {
    @JsonIgnore
    private int page;
    @JsonIgnore
    private Integer perPage;
    @JsonIgnore
    private Sort.Direction direction;
    @JsonIgnore
    private String sort;

    private Integer siteId;
    private String siteName;
    private String location;
    private Double latitude;
    private Double longitude;
    private String status;
}
