package com.example.argumentcapturetest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AccountServiceImplTestKT {

    @InjectMocks
    private lateinit var accountServiceImpl: AccountServiceImpl
    @Mock
    private lateinit var externalService: ExternalService

    @Test
    fun `account status will be successful according to fintech point`() {
        Mockito.`when`(externalService.detailByNationalIdentity)
                .thenReturn(
                        ExternalAccountInfo.builder()
                                .criminalRecord(false)
                                .fintechScore(86).build())
        Mockito.doNothing().`when`(externalService).updateAccount(account)
        accountServiceImpl.updateAccount(account)

        val capture = ArgumentCaptor.forClass(Account::class.java);

        verify(externalService).updateAccount(capture.capture())
        assertEquals(AccountStatus.SUCCESS, capture.value.accountStatus)
    }

    @Test
    fun `account status will be warning according to fintech point`() {
        Mockito.`when`(externalService.detailByNationalIdentity)
                .thenReturn(
                        ExternalAccountInfo.builder()
                                .criminalRecord(false)
                                .fintechScore(59).build())
        Mockito.doNothing().`when`(externalService).updateAccount(account)
        accountServiceImpl.updateAccount(account)

        val capture = ArgumentCaptor.forClass(Account::class.java);

        verify(externalService).updateAccount(capture.capture())
        assertEquals(AccountStatus.SUCCESS, capture.value.accountStatus)
    }

    @Test
    fun `account status will be dangerous according to fintech point`() {
        Mockito.`when`(externalService.detailByNationalIdentity)
                .thenReturn(
                        ExternalAccountInfo.builder()
                                .criminalRecord(false)
                                .fintechScore(34).build())
        Mockito.doNothing().`when`(externalService).updateAccount(account)
        accountServiceImpl.updateAccount(account)

        val capture = ArgumentCaptor.forClass(Account::class.java);

        verify(externalService).updateAccount(capture.capture())
        assertEquals(AccountStatus.DANGEROUS, capture.value.accountStatus)
    }

    companion object {
        val account = Account.builder()
                .accountName("1453harunn")
                .accountNumber("1453-1923-1071")
                .id("fdoakfodkfopdkdsfds")
                .nationalIdentity("14531923101")
                .build();
    }
}