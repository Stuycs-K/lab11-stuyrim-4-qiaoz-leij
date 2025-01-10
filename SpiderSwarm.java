public class SpiderSwarm extends Adventurer {

    private Status poisoned;
    private Status paralyzed;
    private int webbing, maxWebbing;

    public SpiderSwarm() {
        super("Spider Swarm", 20);

        poisoned = new Status("Poisoned", 3);
        paralyzed = new Status("Paralyzed", 2);

        webbing = 1;
        maxWebbing = 5;
    }

    @Override
    public String getSpecialName() {
        return "Webbing";
    }

    @Override
    public int getSpecial() {
        return webbing;
    }

    @Override
    public int getSpecialMax() {
        return maxWebbing;
    }

    @Override
    public void setSpecial(int n) {
      webbing = n;
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = (int) (Math.random() * 6) + 1;
        other.applyDamage(dmg);
        poisoned.set(3);
        int n = restoreSpecial(1);
        return "Dealt " + dmg + " damage; Restored " + n + " " + getSpecialName();
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

    @Override
    public String specialAttack(Adventurer other) {
        if (webbing < 3) {
            return "Too little mana; " + attack(other);
          } else {
            webbing -= 3;
            paralyzed.set(2);
          }
        return "Paralyzed " + other.getName() + " for 2 turns";
    }
}
