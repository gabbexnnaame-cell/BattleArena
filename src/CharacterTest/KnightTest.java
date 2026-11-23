package CharacterTest;

import Characters.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private Knight knight;

    @BeforeEach
    void setUp() {
        knight = new Knight("Hadir");
    }

    @Test
    void knightShouldHave120Hp() {
        assertEquals(100, knight.getHp());
    }


    @Test
    void knightShouldTakeDamage() {
        knight.takeDamage(20);

        assertEquals(80, knight.getHp());
    }


    @Test
    void knightShouldBlock() {
        knight.setBlocking(true);
        knight.setBlockPercentage(50);

        assertTrue(knight.isBlocking());
        assertEquals(50, knight.getBlockPercentage());
    }
}
