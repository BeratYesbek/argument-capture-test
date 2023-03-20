package com.example.argumentcapturetest;

import org.springframework.stereotype.Service;

@Service
public class ExternalServiceImpl implements ExternalService {

    public ExternalAccountInfo getDetailByNationalIdentity() {
        return ExternalAccountInfo.builder().criminalRecord(false)
                .fintechScore(78).build();
    }

    @Override
    public void updateAccount(Account account) {
    }
}
