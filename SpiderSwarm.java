public class SpiderSwarm extends Enemies {

    public SpiderSwarm() {
        super("Spider Swarm", 20, "Webbing", 1, 5);
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = (int) (Math.random() * 6) + 1;
        other.applyDamage(dmg);
        other.applyCondition("Poisoned", 3);
        int n = restoreSpecial(1);
        return "Dealt " + dmg + " damage; Restored " + n + " " + getSpecialName();
    }

    @Override
    public String specialAttack(Adventurer other) {
        if (getSpecial() < 3) {
            return "Too little mana; " + attack(other);
          } else {
            setSpecial(getSpecial()-3);;
            other.applyCondition("Paralyzed", 2);
          }
        return "Paralyzed " + other.getName() + " for 2 turns";
    }
}
