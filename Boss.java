public class Boss extends Enemies {

  private ArrayList<String> vulnerabilities = new ArrayList<String>();
  vulnerabilities.add("Psychic");
  private ArrayList<String> resistances = new ArrayList<String>();
  resistances.add("Bludgeoning");
  resistances.add("Cold");
  resistances.add("Fire");
  resistances.add("Lightning");

  public Boss() {
      super("Specter", 40, vulnerabilities, resistances, "Ghastly Points", 0, 9);
  }
}
