package Tests.User;

import Characters.*;
import User.User;
import User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void createUserShouldAssignKnight(){
        User user = userService.createUser("Gabriel", CharacterClass.KNIGHT);

        assertInstanceOf(Knight.class, user.getCharacter());
    }

    @Test
    void createUserShouldAssignBerserker() {
        User user = userService.createUser("Gabriel", CharacterClass.BERSERKER);

        assertInstanceOf(Berserker.class, user.getCharacter());
    }

    @Test
    void createUserShouldAssignMage() {
        User user = userService.createUser("Gabriel", CharacterClass.MAGE);

        assertInstanceOf(Mage.class, user.getCharacter());
    }

    @Test
    void createUserShouldAssignAssassin() {
        User user = userService.createUser("Gabriel", CharacterClass.ASSASSIN);

        assertInstanceOf(Assassin.class, user.getCharacter());
    }


    @Test
    void createUserShouldSetUsername() {
        User user = userService.createUser("Gabriel", CharacterClass.KNIGHT);

        assertEquals("Gabriel", user.getUsername());
    }

    @Test
    void createUserShouldRejectEmptyUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("", CharacterClass.KNIGHT);
        });
    }

    @Test
    void createUserShouldRejectBlankUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(" ", CharacterClass.KNIGHT);
        });
    }

    @Test
    void createUserShouldRejectNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null, CharacterClass.KNIGHT);
        });
    }

    @Test
    void createUserShouldRejectNullCharacterClass() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser("Gabriel", null);
        });
    }

}