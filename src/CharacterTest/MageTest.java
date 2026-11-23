
package CharacterTest;

import Characters.Mage;
import Action.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MageTest {

    private Mage mage;

    @BeforeEach
    void setUp() {
        mage = new Mage("Hadir");
    }

    @Test
    void mageShouldStartWith100Hp() {
        assertEquals(100, mage.getHp());
    }

    @Test
    void mageShouldTakeDamage() {
        mage.takeDamage(20);

        assertEquals(80, mage.getHp());
    }

    @Test
    void mageHpShouldNeverBeNegative() {
        mage.takeDamage(200);

        assertEquals(0, mage.getHp());
    }

    @Test
    void mageShouldBeAbleToHeal() {
        mage.takeDamage(30);
        mage.heal(20);

        assertEquals(90, mage.getHp());
    }

    @Test
    void mageShouldNotHealAboveMaxHp() {
        mage.takeDamage(10);
        mage.heal(100);

        assertEquals(100, mage.getHp());
    }

    @Test
    void mageShouldBeAlive() {
        assertTrue(mage.isAlive());
    }

    @Test
    void mageShouldDieAtZeroHp() {
        mage.takeDamage(100);

        assertFalse(mage.isAlive());
    }

    @Test
    void mageShouldHaveThreeActions() {
        assertEquals(5, mage.getActions().size());
    }
}
