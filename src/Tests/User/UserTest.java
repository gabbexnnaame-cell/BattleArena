package Tests.User;

import Characters.Knight;
import User.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import  org.junit.jupiter.api.BeforeEach;



class UserTest {

    private Knight knight;
    private User user;

    @BeforeEach
    void setUp() {
        knight = new Knight("Test Knight");
        user = new User("Gabriel", knight);
    }

    @Test
    void CreateUserShouldSetUsername(){
        assertEquals("Gabriel", user.getUsername());
    }

    @Test
    void CreateUserShouldSetCharacter(){
        assertEquals(knight, user.getCharacter());
    }

}
