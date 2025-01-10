public class Condition {
  private String name;
  private int duration, level;

  public Condition(String name, int duration, int level) {
    this.name = name;
    this.duration = duration;
    this.level = level;
  }

  public Condition(String name, int duration) {
    this(name, duration, 1); // Default level is 1
  }

  public Condition(String name) {
    this(name, -1); // Permanent
  }

  // Accessor Methods

  public String getName() {
    return name;
  }

  public int getDuration() {
    return duration;
  }

  public int getLevel() {
    return level;
  }

  // Modifying Level

  // Returns the amount decreased
  public int decreaseLevel(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    int decreaseAmount = Math.min(amount, level - 0); // Minimum level is 0
    level -= decreaseAmount;
    return decreaseAmount;
  }

  public int decreaseLevel() {
    return decreaseLevel(1);
  }

  public void increaseLevel(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    level += amount;
  }

  public void increaseLevel() {
    increaseLevel(1);
  }

  public void setLevel(int level) {
    if (level < 1) throw new IllegalArgumentException();
    this.level = level;
  }
  
  // Modifying Duration

  public boolean decreaseDuration(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    if (duration == -1) return true;
    duration -= amount;
    return duration <= 0;
  }

  public boolean decreaseDuration() {
    return decreaseDuration(1);
  }

  public void increaseDuration(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    if (duration != -1) duration += amount;
  }

  public void increaseDuration() {
    increaseDuration(1);
  }

  public void setDuration(int duration) {
    if (duration <= 0) throw new IllegalArgumentException();
    this.duration = duration;
  }
}
