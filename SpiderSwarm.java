public class SpiderSwarm extends Enemies {

    public SpiderSwarm() {
        super("Spider Swarm", 20, new String[]{"Fire"}, new String[]{"Bludgeoning"}, "Webbing", 1, 5);
    }

    @Override
    public String attack(Adventurer other) {
        String condition = "Poisoned";
        int duration = 3;
        int dmg = other.applyDamage(rollDamage(6), "Piercing");
        other.applyCondition(condition, duration);
        int n = restoreSpecial(1);
        return getName() + " dealt " + dmg + " Piercing Damage, restored " + n + " " + getSpecialName() +
            ", and applied " + condition + " to " + other.getName() + " for " + duration + " turns!";
    }

    @Override
    public String specialAttack(Adventurer other) {
        String condition = "Paralyzed";
        int duration = 2;
        if (getSpecial() < 3) {
            return attack(other);
          } else {
            setSpecial(getSpecial()-3);;
            other.applyCondition(condition, duration);
          }
        return getName() + " applied " + condition + " to " + other.getName() + " for " + duration + " turns!";
    }
}
