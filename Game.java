import java.util.*;

public class Game {
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.WHITE + Text.BACKGROUND;
  private static LinkedList<String> actions = new LinkedList<String>();

  public static void main(String[] args) {
    run();
  }

  private static void drawLine(char start, char middle, char end) {
    System.out.print(start);
    for (int i = 2; i < WIDTH; i++) System.out.print(middle);
    System.out.print(end);
  }

  private static void drawSides(int start, int end) {
    for (int i = start; i < end; i++) {
      drawText("│", i, 1);
      drawText("│", i, WIDTH);
    }
  }

  private static void drawActions() {
    int row = 7;
    int neededRows = 0;
    for (String action : actions) neededRows += action.length() / (WIDTH - 2) + 1;
    if (neededRows > HEIGHT - 18) {
      for (int i = 0; i < 2; i++) actions.remove(0);
    }
    for (String action : actions) {
      TextBox(row, 2, WIDTH - 2, action.length() / (WIDTH - 2) + 1, action);
      row += action.length() / (WIDTH - 2) + 1;
    }
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    Text.clear();
    Text.hideCursor();
    Text.go(1, 1);
    drawLine('┌', '─', '┐');
    drawSides(2, 6);
    Text.go(6, 1);
    drawLine('├', '─', '┤');
    drawSides(7, HEIGHT - 8);
    Text.go(HEIGHT - 8, 1);
    drawLine('├', '─', '┤');
    drawSides(HEIGHT - 7, HEIGHT - 3);
    Text.go(HEIGHT - 3, 1);
    drawLine('├', '─', '┤');
    drawSides(HEIGHT - 2, HEIGHT);
    Text.go(HEIGHT, 1);
    drawLine('└', '─', '┘');
    Text.reset();
    Text.hideCursor();
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startRow, startCol);
    System.out.print(s);
  }

  /*Use this method to place text on the screen at a particular location.
  *When the length of the text exceeds width, continue on the next line
  *for up to height lines.
  *All remaining locations in the text box should be written with spaces to
  *clear previously written text.
  *@param row the row to start the top left corner of the text box.
  *@param col the column to start the top left corner of the text box.
  *@param width the number of characters per row
  *@param height the number of rows
  */
  public static void TextBox(int row, int col, int width, int height, String text){
    LinkedList<String> words = new LinkedList<String>(Arrays.asList(text.split(" ")));
    int currentWidth = 0;
    Text.go(row, col);
    while (! words.isEmpty() && height != 0) {
      if (words.getFirst().length() >= width - currentWidth) {
        for (int i = 0; i < width - currentWidth; i++) System.out.print(" ");
        row++;
        height--;
        currentWidth = 0;
        Text.go(row, col);
      }
      currentWidth += words.getFirst().length() + 1;
      System.out.print(words.getFirst() + " ");
      words.removeFirst();
    }
    height--;
    for (int i = 0; i < width - currentWidth; i++) System.out.print(" ");
    while (height > 0) {
      row++;
      height--;
      Text.go(row, col);
      for (int i = 0; i < width - 1; i++) System.out.print(" ");
    }
  }

  //return a random adventurer (choose between all available subclasses)
  //feel free to overload this method to allow specific names/stats.
  public static Adventurer createRandomAdventurer(){
    double x = Math.random();
    if (x < 0.33) {
      return new Monk("Monk");
    } else if (x < 0.67) {
      return new Bard("Bard");
    } else {
      return new Sorcerer("Sorcerer");
    }
  }

  public static Adventurer createRandomMob(){
    double x = Math.random();
    if (x < 0.5) {
      return new SpiderSwarm();
    } else {
      return new DireWolf();
    }
  }

  /*Display a List of 2-4 adventurers on the rows row through row+3 (4 rows max)
  *Should include Name HP and Special on 3 separate lines.
  *Note there is one blank row reserved for your use if you choose.
  *Format:
  *Bob          Amy        Jun
  *HP: 10       HP: 15     HP:19
  *Caffeine: 20 Mana: 10   Snark: 1
  * ***THIS ROW INTENTIONALLY LEFT BLANK***
  */
  public static void drawParty(ArrayList<Adventurer> party, int startRow){
    if (party.size() > 0) {
      int width = 78 / party.size();
      for (int i = 0; i < party.size(); i++) {
        TextBox(startRow, 2 + i * width, width, 1, party.get(i).getName());
        TextBox(startRow + 1, 2 + i * width, width, 1, "HP: " + colorByPercent(party.get(i).getHP(), party.get(i).getmaxHP()));
        TextBox(startRow + 2, 2 + i * width, width, 1, party.get(i).getSpecialName() + ": " + party.get(i).getSpecial());
        TextBox(startRow + 3, 2 + i * width, width, 1, party.get(i).getConditions());
      }
    }
  }

  //Use this to create a colorized number string based on the % compared to the max value.
  public static String colorByPercent(int hp, int maxHP){
    String output = String.format("%2s", hp+"")+"/"+String.format("%2s", maxHP+"");
    //COLORIZE THE OUTPUT IF HIGH/LOW:
    // under 25% : red
    // under 75% : yellow
    // otherwise : white
    return output;
  }

  //Display the party and enemies
  //Do not write over the blank areas where text will appear.
  //Place the cursor at the place where the user will by typing their input at the end of this method.
  public static void drawScreen(ArrayList<Adventurer> party, ArrayList<Adventurer> enemies){
    drawBackground();
    drawParty(party, 2);
    drawParty(enemies, HEIGHT - 7);
    drawActions();
  }

  public static String userInput(Scanner in){
    //Move cursor to prompt location
    Text.go(HEIGHT - 1, 2);
    System.out.print('>');
    //show cursor
    Text.showCursor();
    String input = in.nextLine();
    Text.go(HEIGHT -1, 3);
    //clear the text that was written
    for (int i = 0; i < WIDTH - 3; i++) System.out.print(" ");
    return input;
  }

  public static void prompt(String s) {
    TextBox(HEIGHT - 2, 2, WIDTH - 2, 1, s);
    Text.go(HEIGHT - 1, 3);
  }

  public static void quit(){
    Text.reset();
    Text.showCursor();
    Text.go(32,1);
  }

  public static void run(){
    //Clear and initialize
    Text.hideCursor();
    Text.clear();

    //Things to attack:
    //Make an ArrayList of Adventurers and add 1-3 enemies to it.
    //If only 1 enemy is added it should be the boss class.
    //start with 1 boss and modify the code to allow 2-3 adventurers later.
    ArrayList<Adventurer>enemies = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer y = createRandomMob();
    Adventurer z = createRandomMob();
    enemies.add(new Boss());
    enemies.add(y);
    enemies.add(z);
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    //Adventurers you control:
    //Make an ArrayList of Adventurers and add 2-4 Adventurers to it.
    ArrayList<Adventurer> party = new ArrayList<Adventurer>();
    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
    Adventurer a = createRandomAdventurer();
    Adventurer b = createRandomAdventurer();
    Adventurer c = createRandomAdventurer();
    party.add(a);
    party.add(b);
    party.add(c);

    for (Adventurer adventurer : enemies) {
      adventurer.setFriends(enemies);
      adventurer.setEnemies(party);
    }

    for (Adventurer adventurer : party) {
      adventurer.setFriends(party);
      adventurer.setEnemies(enemies);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 1;
    String input = ""; //blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    Adventurer currentAdventurer;
    String action = "";
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    prompt("Enter command for "+party.get(whichPlayer)+": attack/special/support/quit + target index");
    actions.add("Turn 1");
    input = userInput(in);
    
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //display event based on last turn's input
      if (partyTurn) {
        Integer target = null;
        currentAdventurer = party.get(whichPlayer);

        while (target == null) {
          try {
            target = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
          } catch (NumberFormatException e) {
              prompt("Invalid input. Enter again: ");
              input = userInput(in);
          }
        }
        
        if (currentAdventurer.hasCondition("Paralyzed")) {
          action = "Can't move. Paralyzed.";
        } 
        else if(input.startsWith("attack ") || input.startsWith("a ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          while (target < 0 || target >= enemies.size()) {
            prompt("Invalid index. Enter again: ");
            input = userInput(in);
            try {
              target = Integer.parseInt(input);
            } catch (NumberFormatException e) {}
          }
          action = currentAdventurer.attack(enemies.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special ") || input.startsWith("sp ")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          while (target < 0 || target >= enemies.size()) {
            prompt("Invalid index. Enter again: ");
            input = userInput(in);
            try {
              target = Integer.parseInt(input);
            } catch (NumberFormatException e) {}
          }
          action = currentAdventurer.specialAttack(enemies.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("su ") || input.startsWith("support ")){
          //"support 0" or "su 0" or "su 2" etc.
          //assume the value that follows su  is an integer.
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          while (target < 0 || target >= party.size()) {
            prompt("Invalid index. Enter again: ");
            input = userInput(in);
            try {
              target = Integer.parseInt(input);
            } catch (NumberFormatException e) {}
          }
          action = currentAdventurer.support(party.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        } else {
          prompt("Invalid input. Enter again: ");
          input = userInput(in);
          continue;
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;
      } else {
        currentAdventurer = enemies.get(whichOpponent);
        int target = (int) (Math.random() * party.size());
        switch (Utility.rollDice(10)) {
          case 1, 2, 3, 4, 5:
            action = currentAdventurer.attack(party.get(target));
            break;
          case 6, 7:
            if (currentAdventurer instanceof DireWolf && Math.random() > 0.7) {
              action = ((DireWolf) currentAdventurer).uniqueSupport();
            } else {
              action = currentAdventurer.support();
            }
            break;
          case 8, 9, 10:
            action = currentAdventurer.specialAttack(party.get(target));
            break;
          }
        whichOpponent++;
      } // End of one enemy
      actions.add(">" + action);
      
      if (!partyTurn && whichOpponent >= enemies.size()){
        // Ends the enemy turn after the last enemy goes
        whichPlayer = 0;
        whichOpponent = 0;
        turn++;
        partyTurn = true;
        for (int i = 0; i < party.size(); i++) {
          action = party.get(i).endTurn();
          if (action.length() != 1) actions.add(">" + action);
        }
        for (int i = 0; i < enemies.size(); i++) {
          action = enemies.get(i).endTurn();
          if (action.length() != 1) actions.add(">" + action);
        }
        actions.add("Turn " + turn);
      }

      if (partyTurn && whichPlayer >= party.size()) {
        partyTurn = false;
      }

      for (int i = 0; i < party.size(); i++) {
        if (party.get(i).isDead()) {
          actions.add(party.get(i).getName() + " is dead. Forever.");
          party.remove(i);
          i--;
        }
      }
      for (int i = 0; i < enemies.size(); i++) {
        if (enemies.get(i).isDead()) {
          actions.add(enemies.get(i).getName() + " is dead. Forever.");
          enemies.remove(i);
          i--;
        }
      }
      // Display the updated screen after input has been processed.
      drawScreen(party, enemies);

      if (party.size() == 0 || enemies.size() == 0) break;

      if (whichPlayer < party.size()) {
        //This is a player turn.
        //Decide where to draw the following prompt:
        prompt("Enter command for "+party.get(whichPlayer)+": attack/special/support/quit + target index");
      } else {
        //This is after the player's turn, and allows the user to see the enemy turn
        //Decide where to draw the following prompt:
        prompt("Press enter to see the next enemy turn");
      }
      input = userInput(in);
    } // End of main game loop

    //After quit reset things:
    if (party.size() == 0) {
      prompt("You lose.");
    } else if (enemies.size() == 0) {
      prompt("You win!");
    }
    quit();
  }
}
