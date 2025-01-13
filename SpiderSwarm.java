public class SpiderSwarm extends Enemies {

    private ArrayList<String> vulnerabilities = new ArrayList<String>();
    vulnerabilities.add("Fire");
    private ArrayList<String> resistances = new ArrayList<String>();
    resistances.add("Bludgeoning");
    public SpiderSwarm() {
        super("Spider Swarm", 20, vulnerabilities, resistances, "Webbing", 1, 5);
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = rollDamage(6);
        String condition = "Poisoned";
        int duration = 3;
        other.applyDamage(dmg, "Piercing");
        other.applyCondition(condition, duration);
        int n = restoreSpecial(1);
        return "Dealt " + dmg + " damage; Restored " + n + " " + getSpecialName() +
            "; " + condition + " " + other.getName() + " for " + duration + "turns";
    }

    @Override
    public String specialAttack(Adventurer other) {
        String condition = "Paralyzed";
        int duration = 2;
        if (getSpecial() < 3) {
            return "Too little mana; " + attack(other);
          } else {
            setSpecial(getSpecial()-3);;
            other.applyCondition(condition, duration);
          }
        return condition + " " + other.getName() + " for " + duration + " turns";
    }
}
