public class Boss extends Enemies {

  private ArrayList<String> vulnerabilities = new ArrayList<String>();
  vulnerabilities.add("Psychic");
  private ArrayList<String> resistances = new ArrayList<String>();
  resistances.add("Bludgeoning");
  resistances.add("Cold");
  resistances.add("Fire");
  resistances.add("Lightning");

  public Boss() {
      super("Specter", 40, vulnerabilities, resistances, "Ghastly Points", 0, 9);
  }

  @Override
  public String attack(Adventurer other) {
      int dmg = other.applyDamage(rollDamage(10) + rollDamage(10), "Psychic");
      restoreSpecial(1);
      return "Dealt " + dmg + " damage; Restored 1 Ghastly Point";
  }

  public String attack2(Adventurer other) {
      other.applyCondition("Deaf", 2);
      other.applyCondition("Blind", 2);
      restoreSpecial(2);
      return "Applied Deaf and Blind to " + other.getName() + " for 2 turns. Restored 2 Ghastly Points";
  }

  @Override
  public String specialAttack(Adventurer other) {
      String condition = "Paralyzed";
      int duration = 3;
      if (getSpecial() < 5) {
          return "Too little mana; " + attack(other);
        } else {
          setSpecial(getSpecial()-5);
          int dmg = other.applyDamage(rollDamage(10) + rollDamage(10) + rollDamage(10), "Psychic");
          other.applyCondition(condition, duration);
          return "Dealt " + dmg + " damage; "
            + condition + " " + other.getName() + " for " + duration + "turns";
        }
  }
}
