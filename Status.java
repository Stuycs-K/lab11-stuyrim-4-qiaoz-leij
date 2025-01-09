public class Status {
  private String name;
  private int duration;

  public Status(String name, int duration) {
    this.name = name;
    this.duration = duration;
  }

  public Status(String name) {
    this.name = name;
    this.duration = -1; // Permanent
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
}
