package pantrypal;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateAccountTest {

    // General check #1 for create account
    @Test
    void testCreateAccountGeneral() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "chef12";
        String password = "coolchefboi";

        assertTrue(AccountService.accountSignup(username, password, middleware));
        assertTrue(AccountService.accountLogin(username, password, middleware));
    }

    // General check #2 for create account
    @Test
    void testCreateAccountGeneral2() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "asdfasdf";
        String password = "asdfasdf";

        assertTrue(AccountService.accountSignup(username, password, middleware));
        assertTrue(AccountService.accountLogin(username, password, middleware));
    }

    // General check #2 for create account
    @Test
    void testAccountExists() {
        IMiddlewareModel middleware = new MockMiddlewareModel();
        String username = "asdfasdf";
        String password1 = "pass1";
        String password2 = "pass2";

        assertTrue(AccountService.accountSignup(username, password1, middleware));
        assertTrue(AccountService.accountLogin(username, password1, middleware));

        assertFalse(AccountService.accountSignup(username, password2, middleware));
        assertFalse(AccountService.accountLogin(username, password2, middleware));
    }
}