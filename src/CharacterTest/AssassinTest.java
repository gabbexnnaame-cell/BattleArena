package CharacterTest;

import Characters.Assassin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssassinTest {

    private Assassin assassin;

    @BeforeEach
    void setUp() {
        assassin = new Assassin("Hadir");
    }

    @Test
    void assassinShouldStartWith90Hp() {
        assertEquals(90, assassin.getHp());
    }

    @Test
    void assassinShouldTakeDamage() {
        assassin.takeDamage(20);

        assertEquals(70, assassin.getHp());
    }

    @Test
    void assassinHpShouldNeverBeNegative() {
        assassin.takeDamage(200);

        assertEquals(0, assassin.getHp());
    }

    @Test
    void assassinShouldHaveThreeActions() {
        assertEquals(3, assassin.getActions().size());
    }

    @Test
    void assassinShouldHaveCorrectCritChance() {
        assertEquals(30, assassin.getCritChance());
    }

    @Test
    void assassinShouldHaveCorrectTypeOfStrike() {
        assertEquals("Shadow Strike", assassin.getTypeOfStrike());
    }
}
