package com.deloitte.yash.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.deloitte.yash.controllers.AccountRestController;
import com.deloitte.yash.models.Account;
import com.deloitte.yash.utils.AccountInput;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
class CheckBalanceIntegrationTest {

    @Autowired
    private AccountRestController accountRestController;

    @Test
    void givenAccountDetails_whenCheckingBalance_thenVerifyAccountCorrect() {
        // given
        var input = new AccountInput();
        input.setSortCode("53-68-92");
        input.setAccountNumber("73084635");

        // when
        var body = accountRestController.checkAccountBalance(input).getBody();

        // then
        var account = (Account) body;
        assertThat(account).isNotNull();
        assertThat(account.getOwnerName()).isEqualTo("Paul Dragoslav");
        assertThat(account.getSortCode()).isEqualTo("53-68-92");
        assertThat(account.getAccountNumber()).isEqualTo("73084635");
    }
}
