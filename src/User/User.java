package User;

import Characters.Character;

public class User {

    private String username;
    private Character character;

    public User(String username,Character character) {
        this.username = username;
        this.character = character;
    }

    public String getUsername() {
        return username;
    }

    public Character getCharacter() {
        return character;
    }
}
