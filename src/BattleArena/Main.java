package BattleArena;

import Action.CombatAction;

import java.awt.*;
import java.util.List;
import java.util.Scanner;
import Characters.*;
import Characters.Character;

public class Main {

  public static void main(String[] args) {
    Frame frame = new Frame();

  }

  private static CombatAction chooseAction(Character player, Scanner scanner) {
    List<CombatAction> actions = player.getActions();

    System.out.println("Choose your action:");

    for (int i = 0; i < actions.size(); i++) {
      System.out.println((i + 1) + ". " + actions.get(i).getType());
    }

    int choice = scanner.nextInt();
    return actions.get(choice -1);
  }


}
