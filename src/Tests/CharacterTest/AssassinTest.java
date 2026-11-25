package Tests.CharacterTest;

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
    void assassinShouldStartWith100Hp() {
        assertEquals(100, assassin.getHp());
    }

    @Test
    void assassinShouldTakeDamage() {
        assassin.takeDamage(20);

        assertEquals(80, assassin.getHp());
    }


    @Test
    void assassinHpShouldNeverBeNegative() {
        assassin.takeDamage(200);

        assertEquals(0, assassin.getHp());
    }

    @Test
    void assassinShouldHaveThreeActions() {
        assertEquals(5, assassin.getActions().size());
    }

    }
