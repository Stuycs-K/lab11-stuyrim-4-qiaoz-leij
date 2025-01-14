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
    int damage = rollDamage(8);
    other.applyDamage(damage, "Bludgeoning");
    String attackNote = getName() + " punched " + other.getName() + " for " + damage + " damage";
    if (Utility.rollDice(2) == 1) {
      restoreSpecial(1);
      attackNote += " and restored 1 Ki Point";
    }
    return attackNote;
  }

  @Override
  public String support(Adventurer other) {
    return support();
  }

  @Override
  public String support() {
    if (kiPoints < 1) return "The Monk doesn't have enough Ki Points to perform Patient Defense";
    
    return "";
  }

  @Override
  public String specialAttack(Adventurer other) {
    return "";
  }
}
