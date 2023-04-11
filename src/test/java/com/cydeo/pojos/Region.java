package com.cydeo.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Region {

    @JsonProperty("region_id")
    private int rId;
    private String region_name;
    private List<Links> links;


}
