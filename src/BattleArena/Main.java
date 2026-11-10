package BattleArena;

import Characters.Character;
import Characters.*;

public class Main {

  public static void main(String[] args) {

    Character player = new Knight("Player");
    Character computer = new Assassin("Computer");

    Game game = new Game(player, computer);

    game.startGame();
  }
}