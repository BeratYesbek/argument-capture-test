package com.example.argumentcapturetest;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {


    private ExternalService externalService;

    public AccountServiceImpl(ExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public void updateAccount(final Account account) {
        // get fintech point from some service
        ExternalAccountInfo externalAccountInfo = externalService.getDetailByNationalIdentity();

        setUpdatedInfo(account,externalAccountInfo);
        externalService.updateAccount(account);

        // update data in database
    }

    private void setUpdatedInfo(final Account account, ExternalAccountInfo accountInfo) {
        if (accountInfo.getFintechScore() > 70) {
            account.setAccountStatus(AccountStatus.SUCCESS);
        } else if (accountInfo.getFintechScore() < 70 && accountInfo.getFintechScore() >= 50) {
            account.setAccountStatus(AccountStatus.WARNING);
        } else if (accountInfo.getFintechScore() < 50) {
            account.setAccountStatus(AccountStatus.DANGEROUS);
        }
        account.setFintechScore(accountInfo.getFintechScore());
        account.setCriminalRecord(accountInfo.isCriminalRecord());
    }

}
