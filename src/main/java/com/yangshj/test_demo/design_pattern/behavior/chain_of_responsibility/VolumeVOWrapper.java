package com.yangshj.test_demo.design_pattern.behavior.chain_of_responsibility;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VolumeVOWrapper {

    private String volumeName;

    private Integer volumeSize;


    private String requestParams;
}
