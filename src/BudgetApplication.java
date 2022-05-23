
import java.util.Scanner;

import bsuclasses.files.*;
import bsuclasses.budget.*;
import bsuclasses.operations.*;

public class BudgetApplication {

  // CLASS VARIABLES //////////
  private static Scanner scanner;
  private static Operations ops;


  private static int MAX_TRIES = 3;

  // INITALIZATION BLOCK
  {
    scanner = new Scanner(System.in);
    ops = new Operations();
  }

  public static void main(String[] args) {

    // Program variables
    final int NUMBER_OF_MENU_OPTIONS = 3;
    int menuChoice = -1;

    quit_loop:
    while(true){
      // Present Main Menu
      System.out.println("                MAIN MENU");
      System.out.println("-------------------------------------------------------------------------------------");

      // Menu options printed
      {
          System.out.println("\t1. Define budget for current month");
          System.out.println("\t2. Add spending category to current month's budget");
          System.out.println("\t3. Exit");

          System.out.println("\n\n");
      }

      // Get User Input
      System.out.println("Enter number of selection [ex: press '2' then 'ENTER']");
      System.out.print(">>  ");

      try {
        menuChoice = Integer.parseInt(scanner.nextLine());

        // Verify menu selection is in range
        boolean selectionInRange = (menuChoice > 0) && (menuChoice <= NUMBER_OF_MENU_OPTIONS);
        if(!selectionInRange)
          throw new Exception("Choice entered by user is not among valid choice range");

      } catch(NumberFormatExceptoin nfe) {
        System.out.println("Entered invalid input that could not be understood as a numerical input\n\nPlease try again\n\n");

        continue;
      } catch(Exception e) {
        System.out.println(e.getMessage() + "\n\n");
        System.out.println("Try again...");

        continue;
      }

      switch(menuChoice) {
        case 1:
        {
          defineBudgetForCurrentMonth();
          break;
        }

        case 2:
        {

          break;
        }

        case 3:
        {
          System.out.println("User has elected to quit - TERMINATING PROGRAM\n\n");
          break quit_loop;
        }
      }
    }

  }

  public void defineBudgetForCurrentMonth() {
    // Check if budget for current month has already been defined
    if(!ops.getBudgetInitializedForThisMonth()) {
      System.out.println("The budget for this month [" + ops.getNow() + "] is ALREADY DEFINED\n");
      return;
    }

    // Get user input - only MAX_TRIES attempts;
    double startingFunds = 0.0;
    int attempts = 0;
    while(true) {
      Syste.out.print("Enter starting funds for budget\n>>  ");

      try {
        startingFunds = Double.parseDouble(scanner.nextLine());

        if(startingFunds < 0) {
          throw new Exception();
        }
      } catch(NumberFormatExeption nfe) {
        System.out.println("Wrong input type entered\n");
        System.out.println(attempts + " out of " MAX_TRIES + "attempts used");
        ++attempts;
        if(attempts == MAX_TRIES) {
          System.out.println("Quitting....");
          return;
        }
      } catch(Exception e) {
        System.out.println("Negative input value entered - only positive or '0'\n");
        System.out.println(attempts + " out of " MAX_TRIES + "attempts used");
        ++attempts;
        if(attempts == MAX_TRIES) {
          System.out.println("Quitting....");
          return;
        }
      }

      // DO NOT WORRY ABOUT METHOD'S RETURN VALUE
      ops.defineNewBudget(startingFunds);

    }
  }

}
