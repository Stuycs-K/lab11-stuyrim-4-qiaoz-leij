public class Bard extends Adventurer {
  private int spellSlots;
  private static final int maxSpellSlots = 10;

  public Bard(String name) {
    super(name, 25, new String[] {"Blugeoning"}, new String[] {"Psychic"});
    spellSlots = 4;
  }

  @Override
  public String getSpecialName() {
    return "Spell Slots";
  }

  @Override
  public int getSpecial() {
    return spellSlots;
  }

  @Override
  public int getSpecialMax() {
    return maxSpellSlots;
  }

  @Override
  public void setSpecial(int n) {
    if (n > maxSpellSlots || n < 0) throw new IllegalArgumentException();
    spellSlots = n;
  }

  @Override
  public String attack(Adventurer other) {
    int damage = other.applyDamage(rollDamage(8), "Psychic");
    other.applyCondition("Disadvantage", 1);
    String action = getName() + " insulted " + other.getName() + " for " + damage + " Psychic Damage";
    return action + "and applied Disadvantage on " + other.getName() + "'s next turn's attack!";
  }

  @Override
  public String support(Adventurer other) {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough spell slots to cast Bardic Inspiration.";
    other.applyCondition("Inspired", 1);
    return getName() + " spent 1 Spell Slot to apply Inspired to " + other.getName() + " for 1 turn!";
  }

  @Override
  public String support() {
    return support(this);
  }

  @Override
  public String specialAttack(Adventurer other) {
    if (! consumeSpecial(1)) return getName() + " doesn't have enough spell slots to cast Dissonant Whispers.";
    String action = getName() + " spent 1 Spell Slot to create whispers around " + other.getName() + ", dealing ";
    int damage = other.applyDamage(rollDamage(8) + rollDamage(8) + rollDamage(8), "Psychic");
    return action + damage + " Psychic damage!";
  }
}
