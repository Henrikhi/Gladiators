package gladiators.ui;

import gladiators.logic.Logics;
import static java.lang.System.out;
import java.util.Scanner;

public class GladiatorMain {

    public static void main(String[] args) {
        Logics gamelogics = new Logics();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello gladiator!");
        System.out.println("What is your name?");
        String heroName = scanner.nextLine();
        if (!heroName.isEmpty()) {
            gamelogics.newHero(heroName);
        }

        System.out.println("Welcome to the arena " + gamelogics.hero.getName() + "!");

    }

}
