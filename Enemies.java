public abstract class Enemies extends Adventurer {

  private String specialResourceName;
  private int specialResource, specialResourceMax;

  public Enemies(String name, int HP, String[] vulnerabilities, String[] resistances, String specialResourceName, int specialResource, int specialResourceMax) {
      super(name, HP, vulnerabilities, resistances);

      this.specialResourceName = specialResourceName;
      this.specialResource = specialResource;
      this.specialResourceMax = specialResourceMax;
  }

  @Override
  public String getSpecialName() {
      return specialResourceName;
  }

  @Override
  public int getSpecial() {
      return specialResource;
  }

  @Override
  public int getSpecialMax() {
      return specialResourceMax;
  }

  @Override
  public void setSpecial(int n) {
    specialResource = n;
  }

  @Override
  public String support(Adventurer other) {
    int heal = Utility.rollDice(6) + Utility.rollDice(6);
    String healMsg;
    if (Math.random() < 0.5) {
      if (other.getHP() + heal < other.getmaxHP()) {
        other.setHP(other.getHP() + heal);
        healMsg = getName() + " healed " + other.getName() + " for " + heal + " HP!";
      } else {
        other.setHP(other.getmaxHP());
        healMsg = getName() + " tried to heal " + other.getName() + " but they were already at max HP.";
      }
    } else {
      healMsg = getName() + " accidently dealt " + other.applyDamage(heal, "Piercing") + " Piercing damage to " + other.getName();
    }
    return healMsg;
  }

  @Override
  public String support() {
      return support(this);
  }

}
