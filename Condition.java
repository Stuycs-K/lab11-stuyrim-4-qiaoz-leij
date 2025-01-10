public class Condition {
  private String name;
  private int duration, level;

  public Condition(String name, int duration, int level) {
    this.name = name;
    this.duration = duration;
    this.level = level;
  }

  public Condition(String name, int duration) {
    Condition(name, duration, 1); // Default level is 1
  }

  public Condition(String name) {
    Condition(name, -1); // Permanent
  }

  public String getName() {
    return name;
  }

  public int getDuration() {
    return duration;
  }

  // Returns true if status has run out
  public boolean decrease(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    if (duration == -1) return true;
    duration -= amount;
    return duration <= 0;
  }

  public boolean decrease() {
    return decrease(1);
  }

  public void increase(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    if (duration != -1) duration += amount;
  }

  public void increase() {
    increase(1);
  }

  public void set(int amount) {
    if (amount <= 0) throw new IllegalArgumentException();
    duration = n;
  }
}
