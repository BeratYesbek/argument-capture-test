package com.example.argumentcapturetest;

public interface ExternalService {
     ExternalAccountInfo getDetailByNationalIdentity();

     void updateAccount(Account account);
}
