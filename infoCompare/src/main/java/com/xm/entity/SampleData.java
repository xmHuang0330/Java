package com.xm.entity;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SampleData {

    private String sampleName;
    private String lane;
    private Integer index;
    private String tablet;
    private String well;
    private Integer bglYLt;
    private Integer mr36aLt;

}
