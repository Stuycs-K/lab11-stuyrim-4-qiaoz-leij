import java.util.ArrayList;
import java.util.Arrays;

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

  public boolean consumeSpecial(int n) {
    if (n > getSpecial()) return false;
    setSpecial(getSpecial() - n);
    return true;
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

  public boolean isDead() {
    return this.HP == 0;
  }

  public int applyDamage(int amount, String type) {
    Condition block = getCondition("Block");
    // Vulnerabilities and Resistances are applied before block
    if (vulnerabilities.contains(type)) amount *= 2;
    if (resistances.contains(type)) amount /= 2;
    if (block != null) {
      amount -= block.decreaseLevel(amount);
      if (block.getLevel() == 0) removeCondition("Block");
    }
    amount = Math.min(this.HP, amount);
    this.HP -= amount;
    if (HP == 0) {
      for (int i = 0; i < friends.size(); i++) friends.get(i).removeFriend(this);
      for (int i = 0; i < enemies.size(); i++) enemies.get(i).removeEnemy(this);
    }
    return amount;
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
    this(name, 10, new String[]{}, new String[]{});
  }

  public Adventurer(String name, int hp, String[] vulnerabilities, String[] resistances) {
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.vulnerabilities = new ArrayList<String>(Arrays.asList(vulnerabilities));
    this.resistances = new ArrayList<String>(Arrays.asList(resistances));
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

  public int restoreHP(int heal) {
    int healthRestored;
    if (HP + heal < maxHP) {
      HP = heal + HP;
      healthRestored = heal;
    } else {
      healthRestored = maxHP - HP;
      HP = maxHP;
    }
    return healthRestored;
  }

  // Condition Effects

  public String getConditions() {
    String output = "";
    for (Condition condition : conditions) {
      if (condition.getName() == "Block") {
        output += condition.getName() + " " + condition.getDuration() + "," + condition.getLevel();
      } else {
        output += condition.getName() + " " + condition.getDuration();
      }
      if (! condition.equals(conditions.getLast())) output += "; ";
    }
    return output;
  }

  public String endTurn() {
    String returnValue = "";
    if (hasCondition("Poisoned")) {
      returnValue = getName() + " took " + applyDamage(Utility.rollDice(4), "Acid") + " Acid Damage from Poison";
    }
    if (hasCondition("Bleeding") && Utility.rollDice(2) == 1) {
      if (returnValue.length() == 0) {
        returnValue = getName() + " took ";
      } else {
        returnValue += " and ";
      }
      returnValue += applyDamage(Utility.rollDice(8), "Piercing") + " Piercing Damage from Bleeding";
    }
    decreaseDurations(1);
    return returnValue + "!";
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
    if (index < 0 || index >= conditions.size()) throw new IllegalArgumentException();
    conditions.remove(index);
  }

  // Handling Enemies and Friends

  public ArrayList<Adventurer> getEnemies() {
    return enemies;
  }

  public ArrayList<Adventurer> getFriends() {
    return friends;
  }

  public void setEnemies(ArrayList<Adventurer> enemies) {
    this.enemies = enemies;
  }

  public void setFriends(ArrayList<Adventurer> friends) {
    this.friends = friends;
  }

  public void removeEnemy(Adventurer enemy) {
    enemies.remove(enemy);
  }

  public void removeFriend(Adventurer friend) {
    friends.remove(friend);
  }

  public Adventurer getRandomFriend() {
    return friends.get(Utility.rollDice(friends.size()) - 1);
  }

  public Adventurer getRandomEnemy() {
    return enemies.get(Utility.rollDice(enemies.size()) - 1);
  }
}