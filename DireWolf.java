public class DireWolf extends Enemies {
    public DireWolf() {
        super("Dire Wolf", 25, new String[] {}, new String[] {}, "Bloodlust", 2, 7);
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
        String condition = "Bleeding";
        int duration = 2;
        int dmg = other.applyDamage(rollDamage(6), "Piercing");
        other.applyCondition(condition, duration);
        return "Dealt " + dmg + " damage; Applied " + condition + " for " + duration + " turns";
    }

    @Override
    public String specialAttack(Adventurer other) {
        String condition = "Prone";
        int duration = 1;
        if (getSpecial() < 4) {
            return attack(other);
          } else {
            setSpecial(getSpecial()-4);
            int dmg = other.applyDamage(rollDamage(6) + rollDamage(6), "Piercing");
            other.applyCondition(condition, duration);
            return "Dealt " + dmg + " damage; "
              + condition + " " + other.getName() + " for " + duration + "turns";
          }
    }
}
