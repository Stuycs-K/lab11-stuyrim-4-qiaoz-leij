import java.util.ArrayList;

public abstract class Adventurer {
  private String name;
  private int HP, maxHP;
  private ArrayList<Condition> conditions;
  private ArrayList<Adventurer> enemies, friends;
  private ArrayList<String> vulnerabilities, resistances;

  // Abstract methods are meant to be implemented in child classes.
  /*
  all adventurers must have a custom special
  consumable resource (mana/rage/money/witts etc)
  */

  //give it a short name (fewer than 13 characters)
  public abstract String getSpecialName();
  //accessor methods
  public abstract int getSpecial();
  public abstract int getSpecialMax();
  public abstract void setSpecial(int n);

  //concrete method written using abstract methods.
  //refill special resource by amount, but only up to at most getSpecialMax()
  public int restoreSpecial(int n){
    if (n > getSpecialMax() - getSpecial()) {
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial() + n);
    return n;
  }

  /*
  all adventurers must have a way to attack enemies and
  support their allys
  */
  //hurt or hinder the target adventurer
  public abstract String attack(Adventurer other);

  /*This is an example of an improvement that you can make to allow
   * for more flexible targetting.
   */
  //heal or buff the party
  //public abstract String support(ArrayList<Adventurer> others);

  //heal or buff the target adventurer
  public abstract String support(Adventurer other);

  //heal or buff self
  public abstract String support();

  //hurt or hinder the target adventurer, consume some special resource
  public abstract String specialAttack(Adventurer other);

  /*
  standard methods
  */

  public void applyDamage(int amount, String type) {
    Condition block = getCondition("Block");
    // Vulnerabilities and Resistances are applied before block
    if (vulnerabilities.contains(type)) amount *= 2;
    if (resistances.contains(type)) amount /= 2;
    if (block != null) amount -= block.decreaseLevel(amount);
    this.HP -= amount;
  }

  public int rollDamage(int size) {
    boolean disadvantage = removeCondition("Disadvantage") || hasCondition("Blind");
    boolean advantage = removeCondition("Advantage") || hasCondition("Inspired") && !hasCondition("Deaf");
    int roll1 = Utility.rollDice(size), roll2 = Utility.rollDice(size);
    int bonus = hasCondition("Strengthened") ? Utility.rollDice(6) : 0;
    if (advantage && disadvantage) return roll1 + bonus;
    if (advantage) return Math.max(roll1, roll2) + bonus;
    if (disadvantage) return Math.min(roll1, roll2) + bonus;
    return roll1 + bonus;
  }

  // You did it wrong if this happens.
  public Adventurer() {
    this("Lester-the-noArg-constructor-string");
  }

  // Deprecated
  public Adventurer(String name) {
    this(name, 10, new ArrayList<String>(), new ArrayList<String>());
  }

  public Adventurer(String name, int hp, ArrayList<String> vulnerabilities, ArrayList<String> resistances) {
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.vulnerabilities = vulnerabilities;
    this.resistances = resistances;
    this.conditions = new ArrayList<Condition>();
    this.enemies = new ArrayList<Adventurer>();
    this.friends = new ArrayList<Adventurer>();
  }

  // toString method

  public String toString() {
    return this.getName();
  }

  // Get Methods

  public String getName() {
    return name;
  }

  public int getHP() {
    return HP;
  }

  public int getmaxHP() {
    return maxHP;
  }

  // Set Methods

  public void setName(String s){
    this.name = s;
  }

  public void setmaxHP(int newMax) {
    maxHP = newMax;
  }

  public void setHP(int health){
    this.HP = health;
  }

  // Condition Effects

  public void endTurn() {
    if (hasCondition("Poisoned")) applyDamage(Utility.rollDice(4), "Acid");
    if (hasCondition("Bleeding") && Utility.rollDice(2) == 1) applyDamage(Utility.rollDice(8), "Piercing");
    decreaseDurations(1);
  }

  public void decreaseDurations(int amount) {
    for (int i = 0; i < conditions.size(); i++) {
      // Removes if duration or level reaches 0
      if (conditions.get(i).decreaseDuration(amount) || conditions.get(i).getLevel() == 0) {
        conditions.remove(i);
        i--;
      }
    }
  }

  public boolean hasCondition(String conditionName) {
    return getCondition(conditionName) != null;
  }

  public void applyCondition(String conditionName, int duration) {
    applyCondition(conditionName, duration, 1);
  }

  public void applyCondition(String conditionName, int duration, int level) {
    Condition condition = getCondition(conditionName);
    if (condition == null) {
      conditions.add(new Condition(conditionName, duration, level));
    } else {
      condition.setDuration(Math.max(condition.getDuration(), duration));
      condition.setLevel(Math.max(condition.getLevel(), level));
    }
  }

  // Returns true if the condition was removed
  public boolean removeCondition(String conditionName) {
    int index = getConditionIndex(conditionName);
    if (index == -1) return false;
    removeConditionAtIndex(index);
    return true;
  }

  // Returns true if the condition was removed
  public boolean decreaseDuration(String conditionName, int amount) {
    int index = getConditionIndex(conditionName);
    if (index == -1) throw new IllegalArgumentException();
    if (conditions.get(index).decreaseDuration(amount)) {
      removeConditionAtIndex(index);
      return true;
    }
    return false;
  }

  private Condition getCondition(String conditionName) {
    int conditionIndex = getConditionIndex(conditionName);
    return conditionIndex == -1 ? null : conditions.get(conditionIndex);
  }

  private int getConditionIndex(String conditionName) {
    for (int i = 0; i < conditions.size(); i++) {
      if (conditions.get(i).getName().equals(conditionName)) return i;
    }
    return -1;
  }

  private void removeConditionAtIndex(int index) {
    if (index <= 0 || index > conditions.size()) throw new IllegalArgumentException();
    conditions.remove(index);
  }

  // Handling Enemies and Friends

  public ArrayList<Adventurer> getEnemies() {
    return enemies;
  }

  public ArrayList<Adventurer> getFriends() {
    return friends;
  }

  public void removeEnemy(Adventurer enemy) {
    enemies.remove(enemy);
  }

  public void removeFriend(Adventurer friend) {
    friends.remove(friend);
  }

  public void addEnemy(Adventurer enemy) {
    enemies.add(enemy);
  }

  public void addFriend(Adventurer friend) {
    friends.add(friend);
  }
}
