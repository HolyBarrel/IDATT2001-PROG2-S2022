package edu.ntnu.idatt2001.magnulal;
public class MainClass { //not task 6
    public static void main(String[] args) {
        Army humanArmy = new Army("Alliance");
        Army orcArmy = new Army("Horde");
        for (int i = 0; i < 500; i++) {
            humanArmy.add(new InfantryUnit("Footman", 100, 30, 7));
            orcArmy.add(new InfantryUnit("Grunt", 100, 32, 6));
        }
        for (int i = 0; i < 250; i++) {
            humanArmy.add(new CavalryUnit("Knight", 100, 40, 22));
            orcArmy.add(new CavalryUnit("Raider", 100, 42, 18));
        }
        for (int i = 0; i < 250; i++) {
            humanArmy.add(new RangedUnit("Archer", 100, 50, 5));
            orcArmy.add(new RangedUnit("SpearOrc", 100, 55, 3));
        }
        //Commanders
        humanArmy.add(new CommanderUnit("MountainKing", 180, 40, 25)); //if armor is to high,
        // the battle will go on forever
        orcArmy.add(new CommanderUnit("Gul'dan", 180, 45, 15));
        Battle battleOfAzeroth = new Battle(humanArmy, orcArmy);
        System.out.println(battleOfAzeroth.simulate());
    }
}
