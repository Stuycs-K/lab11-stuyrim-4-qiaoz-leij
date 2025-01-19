public class Boss extends Enemies {
  public Boss() {
    super("Specter", 40, new String[] {"Psychic"}, new String[] {"Bludgeoning", "Cold", "Fire", "Lightning"}, "Ghastly Points", 0, 9);
  }

  @Override
  public String attack(Adventurer other) {
    if (Utility.rollDice(2) == 1) {
      int dmg = other.applyDamage(rollDamage(10) + rollDamage(10), "Psychic");
      restoreSpecial(1);
      return getName() + " dealt " + dmg + " Pyschic Damage to " + other.getName() + " and restored 1 Ghastly Point!";
    } else {
      other.applyCondition("Deaf", 2);
      other.applyCondition("Blind", 2);
      restoreSpecial(2);
      return getName() + " applied Deaf and Blind to " + other.getName() + " for 2 turns and restored 2 Ghastly Points!";
    }

  }
  
  @Override
  public String specialAttack(Adventurer other) {
    String condition = "Paralyzed";
    int duration = 3;
    if (getSpecial() < 5) {
      return attack(other);
    } else {
      setSpecial(getSpecial()-5);
      int dmg = other.applyDamage(rollDamage(10) + rollDamage(10) + rollDamage(10), "Psychic");
      other.applyCondition(condition, duration);
      return getName() + " dealt " + dmg + " Psychic Damage and applied " + condition + " to " + other.getName() + " for " + duration + "turns!";
    }
  }
}
