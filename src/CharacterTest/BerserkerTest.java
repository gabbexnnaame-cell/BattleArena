
package CharacterTest;

import Characters.Berserker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BerserkerTest {

    private Berserker berserker;

    @BeforeEach
    void setUp() {
        berserker = new Berserker("Hadir");
    }

    @Test
    void berserkerShouldStartWith110Hp() {
        assertEquals(110, berserker.getHp());
    }


    @Test
    void berserkerShouldTakeDamage() {
        berserker.takeDamage(30);

        assertEquals(80, berserker.getHp());
    }

    @Test
    void berserkerHpShouldNeverBeNegative() {
        berserker.takeDamage(200);

        assertEquals(0, berserker.getHp());
    }

    @Test
    void berserkerShouldBeAlive() {
        assertTrue(berserker.isAlive());
    }

    @Test
    void berserkerShouldDieAtZeroHp() {
        berserker.takeDamage(110);

        assertFalse(berserker.isAlive());
    }

    @Test
    void berserkerShouldHaveThreeActions() {
        assertEquals(3, berserker.getActions().size());
    }
}
