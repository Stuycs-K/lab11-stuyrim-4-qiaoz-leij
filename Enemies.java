import java.util.ArrayList;

public abstract class Enemies extends Adventurer {

  private String specialResourceName;
  private int specialResource, specialResourceMax;

  public Enemies(String name, int HP, ArrayList<String> vulnerabilities, ArrayList<String> resistances, String specialResourceName, int specialResource, int specialResourceMax) {
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

  private int heal() {
      int heal;
      heal = (int) (Math.random() * 6) + (int) (Math.random() * 6) + 2;
      if (Math.random() < 0.5) {
          heal = -heal;
      }
      return heal;
  }

  @Override
  public String support(Adventurer other) {
      int heal = heal();
      String healMsg;
      if (heal > 0) {
          if (other.getHP() + heal < other.getmaxHP()) {
              other.setHP(other.getHP() + heal);
              healMsg = "Healed " + other.getName() + " " + heal + "HP";
            } else {
              other.setHP(other.getmaxHP());
              healMsg = "At max HP";
            }
      } else {
          if (other.getHP() + heal > 0) {
              other.setHP(other.getHP() + heal);
              healMsg = other.getName() + " Lost " + heal + "HP";
            } else {
              other.setHP(0);
              healMsg = "Dead";
            }
      }
      return healMsg;
  }

  @Override
  public String support() {
      return support(this);
  }

}
