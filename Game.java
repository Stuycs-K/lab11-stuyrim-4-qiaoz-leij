import java.util.*;

import javax.swing.event.SwingPropertyChangeSupport;
public class Game {
  private static final int WIDTH = 80;
  private static final int HEIGHT = 30;
  private static final int BORDER_COLOR = Text.BLACK;
  private static final int BORDER_BACKGROUND = Text.BLACK + Text.BACKGROUND;
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
    if (actions.size() > HEIGHT - 15) actions.remove(0);
    for (String action : actions) {
      TextBox(row, 2, WIDTH - 2, 1, action);
    }
  }

  //Display the borders of your screen that will not change.
  //Do not write over the blank areas where text will appear or parties will appear.
  public static void drawBackground(){
    // System.out.print(Text.changeColor(BORDER_COLOR));
    // System.out.print(Text.changeColor(BORDER_BACKGROUND));
    Text.clear();
    Text.hideCursor();
    Text.go(1, 1);
    drawLine('┌', '─', '┐');
    drawSides(2, 6);
    Text.go(1, 6);
    drawLine('├', '─', '┤');
    drawSides(7, HEIGHT - 8);
    Text.go(1, HEIGHT - 8);
    drawLine('├', '─', '┤');
    drawSides(HEIGHT - 7, HEIGHT - 3);
    Text.go(1, HEIGHT - 3);
    drawLine('├', '─', '┤');
    drawSides(HEIGHT - 2, HEIGHT);
    Text.go(1, HEIGHT);
    drawLine('└', '─', '┘');
    Text.reset();
    Text.hideCursor();
  }

  //Display a line of text starting at
  //(columns and rows start at 1 (not zero) in the terminal)
  //use this method in your other text drawing methods to make things simpler.
  public static void drawText(String s,int startRow, int startCol){
    Text.go(startCol, startRow);
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
    while (text.length() >= width && height != 0) {
      Text.go(col, row);
      System.out.print(text.substring(0, width));
      text = text.substring(width);
      row++;
      height--;
    }
    if (height != 0) {
      Text.go(col, row);
      System.out.print(text);
      for (int i = 0; i < width - text.length(); i++) System.out.print(" ");
      height--;
    }
    while (height > 0) {
      for (int i = 0; i < width; i++) System.out.print(" ");
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
        TextBox(startRow + 3, 2 + i * width, width, 1, party.get(i).getFirstCondition());
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
    Text.go(2, HEIGHT - 1);
    System.out.print('>');
    //show cursor
    Text.showCursor();
    String input = in.nextLine();
    Text.go(3, HEIGHT -1);
    //clear the text that was written
    for (int i = 0; i < WIDTH - 3; i++) System.out.print(" ");
    return input;
  }

  public static void prompt(String s) {
    TextBox(HEIGHT - 2, 2, WIDTH - 2, 1, s);
    Text.go(3, HEIGHT - 1);
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
    enemies.add(new Boss());
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
    for (Adventurer adventurer : party) {
      adventurer.setFriends(party);
      adventurer.setEnemies(enemies);
    }
    /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/

    boolean partyTurn = true;
    int whichPlayer = 0;
    int whichOpponent = 0;
    int turn = 0;
    String input = "";//blank to get into the main loop.
    Scanner in = new Scanner(System.in);
    Adventurer currentAdventurer;
    String action = "";
    String death = "";
    //Draw the window border

    //You can add parameters to draw screen!
    drawScreen(party, enemies);//initial state.

    //Main loop

    //display this prompt at the start of the game.
    prompt("Enter command for "+party.get(whichPlayer)+": attack/special/support/quit + target index");
    
    while(! (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit"))){
      //Read user input
      input = userInput(in);
      //display event based on last turn's input
      if(partyTurn && whichPlayer < party.size()){
        int target = Integer.parseInt(input.substring(input.indexOf(" ") + 1));
        currentAdventurer = party.get(whichPlayer);
        if(input.startsWith("attack") || input.startsWith("a")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          while (target < 0 || target >= enemies.size()) {
            prompt("Invalid index. Enter again: ");
            input = userInput(in);
            target = Integer.parseInt(input);
          }
          action = currentAdventurer.attack(enemies.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        else if(input.startsWith("special") || input.startsWith("sp")){
          /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
          while (target < 0 || target >= enemies.size()) {
            prompt("Invalid index. Enter again: ");
            input = userInput(in);
            target = Integer.parseInt(input);
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
            target = Integer.parseInt(input);
          }
          Text.go(3, HEIGHT - 1);
          action = currentAdventurer.support(party.get(target));
          /*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<*/
        }
        //You should decide when you want to re-ask for user input
        //If no errors:
        whichPlayer++;

      } else {
          partyTurn = false;
          currentAdventurer = enemies.get(0);
          int target = (int) (Math.random() * 3);
          switch (Utility.rollDice(10)) {
            case 1, 2, 3, 4, 5:
              action = currentAdventurer.attack(party.get(target));
              break;
            case 6, 7:
              if (currentAdventurer instanceof DireWolf) {
                if (Math.random() > 0.7) {
                  action = ((DireWolf) currentAdventurer).uniqueSupport();
                }
                action = currentAdventurer.support();
              } else {
                action = currentAdventurer.support();
              }
              break;
            case 8, 9, 10:
              action = currentAdventurer.specialAttack(party.get(target));
              break;
            }
          whichOpponent++;

          if (whichOpponent < enemies.size()) {
          prompt("Press enter to see next turn");
          userInput(in);
          }
          currentAdventurer.endTurn();
      } // End of one enemy

      if (!partyTurn && whichOpponent >= enemies.size()){
        // Ends the enemy turn after the last enemy goes
        whichPlayer = 0;
        whichOpponent = 0;
        turn++;
        partyTurn = true;
      }

      for (int i = 0; i < party.size(); i++) {
        if (party.get(i).getHP()<=0) {
          death += "; " + party.get(i) + " is dead. Forever.";
          party.remove(i);
          i--;
        }
      }
      for (int i = 0; i < enemies.size(); i++) {
        if (enemies.get(i).getHP()<=0) {
          death += "; " + enemies.get(i) + " is dead. Forever.";
          enemies.remove(i);
          i--;
        }
      }
      // Display the updated screen after input has been processed.
      drawScreen(party, enemies);
      Text.go(3, 7);
      System.out.print(action + death);
      death = "";

      if (party.size() == 0) {
        break;
      } else if (enemies.size() == 0) {
        break;
      }

      if (whichPlayer < party.size()) {
        //This is a player turn.
        //Decide where to draw the following prompt:
        prompt("Enter command for "+party.get(whichPlayer)+": attack/special/support/quit + target index");

      } else{
        //This is after the player's turn, and allows the user to see the enemy turn
        //Decide where to draw the following prompt:
        prompt("Press enter to see enemy turn");
        partyTurn = false;
        whichOpponent = 0;
      }
    } // End of main game loop


    //After quit reset things:
    if (party.size() == 0) {
      prompt("You lose. Press enter to quit.");
      userInput(in);
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.exit(0);
    } else {
      prompt("You win! Press enter to quit.");
      userInput(in);
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.exit(0);
    }
    
  }
}
