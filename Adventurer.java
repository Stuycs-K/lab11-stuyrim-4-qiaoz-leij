import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP, maxHP;
  private ArrayList<Condition> conditions;
  private ArrayList<Adventurer> enemies, friends;
  private boolean hasAdvantage, hasDisadvantage;

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
    if( n > getSpecialMax() - getSpecial()){
      n = getSpecialMax() - getSpecial();
    }
    setSpecial(getSpecial()+n);
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

  public void applyDamage(int amount){
    this.HP -= amount;
  }

  // You did it wrong if this happens.
  public Adventurer(){
    this("Lester-the-noArg-constructor-string");
  }

  public Adventurer(String name){
    this(name, 10);
  }

  public Adventurer(String name, int hp){
    this.name = name;
    this.HP = hp;
    this.maxHP = hp;
    this.conditions = new ArrayList<Condition>();
    this.enemies = new ArrayList<Adventurer>();
    this.friends = new ArrayList<Adventurer>();
  }

  // toString method
  public String toString(){
    return this.getName();
  }

  // Get Methods
  public String getName(){
    return name;
  }

  public int getHP(){
    return HP;
  }

  public int getmaxHP(){
    return maxHP;
  }
  public void setmaxHP(int newMax){
    maxHP = newMax;
  }

  // Set Methods
  public void setHP(int health){
    this.HP = health;
  }

  public void setName(String s){
    this.name = s;
  }

  // Condition Effects
  public void decreaseAllConditiones(int amount) {
    for (int i = 0; i < conditions.size(); i++) {
      if (conditions.get(i).decrease(amount)) {
        conditions.remove(i);
        i--;
      }
    }
  }

  public boolean hasCondition(String conditionName) {
    return getConditionIndex(conditionName) != -1;
  }

  public void applyCondition(String conditionName, int duration) {
    int index = getConditionIndex(conditionName);
    if (index == -1) {
      conditions.add(new Condition(conditionName, duration));
    } else {
      if (conditions.get(index).getDuration() < duration) conditions.get(index).set(duration);
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
  public boolean decreaseCondition(String conditionName, int amount) {
    int index = getConditionIndex(conditionName);
    if (index == -1) throw new IllegalArgumentException();
    if (conditions.get(index).decrease(amount)) {
      removeConditionAtIndex(index);
      return true;
    }
    return false;
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
