
package Tests.CharacterTest;

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
    void berserkerShouldStartWith100Hp() {
        assertEquals(100, berserker.getHp());
    }


    @Test
    void berserkerShouldTakeDamage() {
        berserker.takeDamage(30);

        assertEquals(70, berserker.getHp());
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
        berserker.takeDamage(100);

        assertFalse(berserker.isAlive());
    }

    @Test
    void berserkerShouldHaveFiveActions() {
        assertEquals(5, berserker.getActions().size());
    }
}
