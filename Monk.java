public class Monk extends Adventurer {
  private int kiPoints;
  private static final int maxKiPoints = 10;

  public Monk(String name) {
    super(name, 30, new String[] {}, new String[] {"Bludgeoning, Psychic"});
    kiPoints = 2;
  }

  @Override
  public String getSpecialName() {
    return "Ki Points";
  }

  @Override
  public int getSpecial() {
    return kiPoints;
  }

  @Override
  public int getSpecialMax() {
    return maxKiPoints;
  }

  @Override
  public void setSpecial(int n) {
    if (n > maxKiPoints || n < 0) throw new IllegalArgumentException();
    kiPoints = n;
  }

  @Override
  public String attack(Adventurer other) {
    int damage = other.applyDamage(rollDamage(8), "Bludgeoning");
    String action = getName() + " punched " + other.getName() + " for " + damage + " Bludgeoning Damage";
    if (Utility.rollDice(2) == 1) {
      restoreSpecial(1);
      action += " and restored 1 Ki Point";
    }
    return action + "!";
  }

  @Override
  public String support(Adventurer other) {
    return support();
  }

  @Override
  public String support() {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough Ki Points to perform Patient Defense.";
    for (Adventurer adventurer : getFriends()) {
      adventurer.applyCondition("Block", 1, 5);
    }
    return getName() + " spent 1 Ki Point to apply 5 Block to all allies for 1 turn!";
  }

  @Override
  public String specialAttack(Adventurer other) {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough Ki Points to perform Flurry of Blows.";
    String action = getName() + " spent 1 Ki Point to punch " + other.getName() + " three times for ";
    for (int i = 0; i < 2; i++) action += other.applyDamage(rollDamage(8), "Blugeoning") + ", ";
    action += "and " + other.applyDamage(rollDamage(8), "Blugeoning") + " Blugeoning Damage!";
    return action;
  }
}
