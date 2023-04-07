package com.cydeo.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
public class Region {
    private int region_id;
    private String region_name;
    private List<RegionsLinks> links;


}