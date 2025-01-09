import java.util.ArrayList;

public abstract class Adventurer{
  private String name;
  private int HP, maxHP;
  private ArrayList<Status> statusEffects;
  private ArrayList<Adventurer> enemies, friends;

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
    this.statusEffects = new ArrayList<Status>();
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

  // Status Effects
  public void decreaseAllStatuses(int amount) {
    for (int i = 0; i < statusEffects.size(); i++) {
      if (statusEffects.get(i).decrease(amount)) {
        statusEffects.remove(i);
        i--;
      }
    }
  }

  public boolean hasStatus(String statusName) {
    return getStatusIndex(statusName) != -1;
  }

  public void applyStatus(String statusName, int duration) {
    int index = getStatusIndex(statusName);
    if (index == -1) {
      statusEffects.add(new Status(statusName, duration));
    } else {
      statusEffects.get(index).increase(duration);
    }
  }

  // Returns true if the status was removed
  public boolean removeStatus(String statusName) {
    int index = getStatusIndex(statusName);
    if (index == -1) return false;
    removeStatusAtIndex(index);
    return true;
  }

  // Returns true if the status was removed
  public boolean decreaseStatus(String statusName, int amount) {
    int index = getStatusIndex(statusName);
    if (index == -1) throw new IllegalArgumentException();
    if (statusEffects.get(index).decrease(amount)) {
      removeStatusAtIndex(index);
      return true;
    }
    return false;
  }

  private int getStatusIndex(String statusName) {
    for (int i = 0; i < statusEffects.size(); i++) {
      if (statusEffects.get(i).getName().equals(statusName)) return i;
    }
    return -1;
  } 

  private void removeStatusAtIndex(int index) {
    if (index <= 0 || index > statusEffects.size()) throw new IllegalArgumentException();
    statusEffects.remove(index);
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
