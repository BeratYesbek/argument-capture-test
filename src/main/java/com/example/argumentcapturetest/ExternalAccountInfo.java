package com.example.argumentcapturetest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ExternalAccountInfo {
    private boolean criminalRecord;
    private int fintechScore;
}
