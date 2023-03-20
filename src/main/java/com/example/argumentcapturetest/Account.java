package com.example.argumentcapturetest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Account {
    private String id;
    private String accountNumber;
    private String accountName;
    private String nationalIdentity;
    private boolean criminalRecord;
    private int fintechScore;
    private AccountStatus accountStatus;

}
