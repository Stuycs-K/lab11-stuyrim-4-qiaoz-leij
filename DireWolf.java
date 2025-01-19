public class DireWolf extends Enemies {
    public DireWolf() {
        super("Dire Wolf", 25, new String[] {}, new String[] {}, "Bloodlust", 2, 7);
    }

    public String uniqueSupport() {
        String condition = "Strengthened";
        int duration = 1;
        for (Adventurer friend : getFriends()) {
            if (friend instanceof DireWolf) {
                friend.applyCondition("Strengthened", duration);
            }
        }
        int n = restoreSpecial(1);
        return getName() + " applied " + condition + " to all wolves for " + duration + " turn and restored " + n + " " + getSpecialName() + "!";
    }

    @Override
    public String attack(Adventurer other) {
        String condition = "Bleeding";
        int duration = 2;
        int dmg = other.applyDamage(rollDamage(6), "Piercing");
        other.applyCondition(condition, duration);
        return getName() + " dealt " + dmg + " Piercing Damage to " + other.getName() + " and applied " + condition + " for " + duration + " turns!";
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
            return getName() + " dealt " + dmg + " Piercing Damage and applied" + condition + " to " + other.getName() + " for " + duration + "turns!";
          }
    }
}
