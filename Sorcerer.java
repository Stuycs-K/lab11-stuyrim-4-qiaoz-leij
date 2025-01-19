public class Sorcerer extends Adventurer {
  private int sorcererPoints;
  private static final int maxSorcererPoints = 10;

  public Sorcerer(String name) {
    super(name, 20, new String[] {"Blugeoning", "Psychic"}, new String[] {"Fire, Cold", "Lightning"});
    sorcererPoints = 5;
  }

  @Override
  public String getSpecialName() {
    return "Sorcerer Points";
  }

  @Override
  public int getSpecial() {
    return sorcererPoints;
  }

  @Override
  public int getSpecialMax() {
    return maxSorcererPoints;
  }

  @Override
  public void setSpecial(int n) {
    if (n > maxSorcererPoints || n < 0) throw new IllegalArgumentException();
    sorcererPoints = n;
  }

  @Override
  public String attack(Adventurer other) {
    String damageType = "Lightning";
    switch (Utility.rollDice(3)) {
      case 1:
        damageType = "Fire";
        break;
      case 2:
        damageType = "Cold";
        break;
    }
    int damage = other.applyDamage(rollDamage(8) + rollDamage(8), damageType);
    return getName() + " blasted " + other.getName() + " for " + damage + damageType + " Damage!";
  }

  @Override
  public String support(Adventurer other) {
    return support();
  }

  @Override
  public String support() {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough Sorcerer Points to cast Panacea.";
    String action = getName() + " healed ";
    for (Adventurer friend : getFriends()) {
      action += friend.getName() + " for " + friend.restoreHP(Utility.rollDice(8)) + " HP and ";
    }
    return action.substring(0, action.length() - 5) + "!";
  }

  @Override
  public String specialAttack(Adventurer other) {
    if (! consumeSpecial(2)) return getName() + " doesn't have enough Sorcerer Points to cast Fireball.";
    String action = getName() + " spent 2 Sorcerer Points to hurl a fireball at all enemies, dealing ";
    for (Adventurer enemy : getEnemies()) {
      action += enemy.applyDamage(rollDamage(8), "Fire") + " Fire Damage to " + enemy.getName() + " and ";
    }
    return action.substring(0, action.length() - 5) + "!";
  }
}