
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
    void mageShouldStartWith85Hp() {
        assertEquals(85, mage.getHp());
    }

    @Test
    void mageShouldHaveCorrectAttackName() {
        assertEquals("Fireball", mage.getTypeOfStrike());
    }

    @Test
    void mageShouldHave15PercentCritChance() {
        assertEquals(15, mage.getCritChance());
    }

    @Test
    void mageShouldTakeDamage() {
        mage.takeDamage(20);

        assertEquals(65, mage.getHp());
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

        assertEquals(75, mage.getHp());
    }

    @Test
    void mageShouldNotHealAboveMaxHp() {
        mage.takeDamage(10);
        mage.heal(100);

        assertEquals(85, mage.getHp());
    }

    @Test
    void mageShouldBeAlive() {
        assertTrue(mage.isAlive());
    }

    @Test
    void mageShouldDieAtZeroHp() {
        mage.takeDamage(85);

        assertFalse(mage.isAlive());
    }

    @Test
    void mageShouldHaveThreeActions() {
        assertEquals(3, mage.getActions().size());
    }

}
