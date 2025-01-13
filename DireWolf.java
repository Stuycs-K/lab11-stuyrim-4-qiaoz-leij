public class DireWolf extends Enemies {
    private ArrayList<String> vulnerabilities = new ArrayList<String>();
    private ArrayList<String> resistances = new ArrayList<String>();

    public DireWolf() {
        super("Dire Wolf", 25, vulnerabilities, resistances, "Bloodlust", 2, 7);
    }

    public String uniqueSupport() {
        String condition = "Strengthened";
        int duration = 1;
        for (Adventurer enemy : getEnemies()) {
            if (enemy instanceof DireWolf) {
                enemy.applyCondition("Strengthened", duration);
            }
        }
        int n = restoreSpecial(1);
        return condition + "all wolves for " + duration + "turn; Restored " + n + " " + getSpecialName();
    }

    @Override
    public String attack(Adventurer other) {
        int dmg = rollDamage(6);
        String condition = "Bleeding";
        int duration = 2;
        other.applyDamage(dmg, "Piercing");
        other.applyCondition(condition, duration);
        return "Dealt " + dmg + " damage; Applied " + condition + " for " + duration + " turns";
    }

    @Override
    public String specialAttack(Adventurer other) {
        String condition = "Prone";
        int duration = 1;
        int dmg = (int) (Math.random() * 6 + Math.random() * 6) + 2;
        if (getSpecial() < 4) {
            return "Too little mana; " + attack(other);
          } else {
            setSpecial(getSpecial()-2);
            other.applyDamage(dmg);
            other.applyCondition(condition, duration);
          }
          return "Dealt " + dmg + " damage; "
            + condition + " " + other.getName() + " for " + duration + "turns";
    }
}
