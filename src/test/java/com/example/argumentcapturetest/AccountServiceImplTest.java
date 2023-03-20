package com.example.argumentcapturetest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private ExternalService externalService;

    @Captor
    ArgumentCaptor<Account> accountCaptor;

    private final static Account account = Account.builder()
            .accountName("1453harunn")
            .accountNumber("1453-1923-1071")
            .id("fdoakfodkfopdkdsfds")
            .nationalIdentity("14531923101")
            .build();

    @Test
    public void testArgumentFintechStatusWillBeSuccess() {
        when(externalService.getDetailByNationalIdentity())
                .thenReturn(
                        ExternalAccountInfo.builder()
                                .criminalRecord(false)
                                .fintechScore(86).build());
        doNothing().when(externalService).updateAccount(account);
        accountService.updateAccount(account);


        verify(externalService).updateAccount(accountCaptor.capture());

        System.out.println("");
    }

}
