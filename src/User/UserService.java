package User;

import Characters.*;
import Characters.Character;

public class UserService {

    public User createUser(String username, CharacterClass characterChoice) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (characterChoice == null) {
            throw new IllegalArgumentException("Character choice cannot be null");
        }

        Character character;

        switch (characterChoice) {
            case KNIGHT:
                character = new Knight(username);
                break;
            case BERSERKER:
                character = new Berserker(username);
                break;
            case MAGE:
                character = new Mage(username);
                break;
            case ASSASSIN:
                character = new Assassin(username);
                break;
            default:
                throw new IllegalArgumentException("Invalid character choice");
        }

        return new User(username, character);
    }
}
