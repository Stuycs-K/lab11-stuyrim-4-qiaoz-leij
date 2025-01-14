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
    if (! consumeSpecial(1)) return getName() + " doesn't have enough spell slots to cast Bardic Inspiration.";
    other.applyCondition("Inspired", 1);
    return getName() + " spent 1 Spell Slot to apply Inspired to " + other.getName() + " for 1 turn!";
  }

  @Override
  public String support() {
    return support(this);
  }

  @Override
  public String specialAttack(Adventurer other) {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough spell slots to cast Dissonant Whispers.";
    String action = getName() + " spent 1 Spell Slot to create whispers around " + other.getName() + ", dealing ";
    int damage = other.applyDamage(rollDamage(8) + rollDamage(8) + rollDamage(8), "Psychic");
    return action + damage + " Psychic damage!";
  }
}
