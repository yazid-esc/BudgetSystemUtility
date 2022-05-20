
package bsuclasses.budget;

import bsuclasses.budget.BudgetCategory;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;

import java.time.YearMonth;

/**
 A {@code Budget} object will represent a budget for a month as specified by the user.

 @author Yazid Escudero
 @version 1.0.0
 @since 1.0.0
*/
public class Budget {

  // ATTRIBUTES //////
  private double startingFunds;
  private double remainingFunds;
  private double unallocatedFunds;
  private ArrayList<BudgetCategory> categories;
  private YearMonth yearMonth;

  // CONSTRUCTORS /////

  /**
   @param startingFunds the startingFunds to be set
  */
  public Budget(double startingFunds) {
    this.startingFunds = startingFunds;
    this.yearMonth = YearMonth.now();
  }

  /**
   @param budget the {@code Budget} object whose contents/state will be copied into
                 {@code this} budget's contents/state
  */
  public Budget(Budget budget) {
    budget.copyTo(this);
  }

  /**
   @param startingFunds the startingFunds to set
   @param remainingFunds the remainingFunds to set
   @param unallocatedFunds the unallocatedFunds to set
   @param categories the categories to set
   @param yearMonth the yearMonth to set
  */
  public Budget(double startingFunds, double remainingFunds, double unallocatedFunds, ArrayList<BudgetCategories> categories, YearMonth yearMonth) {
    this.startingFunds = startingFunds;
    this.remainingFunds = remainingFunds;
    this.unallocatedFunds = unallocatedFunds;
    this.categories = categories;
    this.yearMonth = yearMonth;
  }

  // GETTERS /////
  /**
   @return the starting funds of this budget
  */
  public double getStartingFunds() {
    return this.startingFunds;
  }

  /**
   @return the remaining funds of this budget
  */
  public double getRemainingFunds() {
    return this.remainingFunds;
  }

  /**
   @return the unallocated funds of this budget
  */
  public double getUnallocatedFunds() {
    return this.unallocatedFunds;
  }

  // OTHER /////
  /**
   @param budget the {@code Budget} object who will have {@code this} budget's
                 state copied into it.
  */
  public void copyTo(Budget budget) {
    budget.startingFunds = this.startingFunds;
    budget.remainingFunds = this.remainingFunds;
    budget.unallocatedFunds = this.unallocatedFunds;
    budget.categories = this.categories;
    budget.yearMonth = this.yearMonth;
  }

  public String toString() {
    StringBuilder contents = new StringBuilder();

    contents.append("------------------------------------------------------------------------------------\n");
    contents.append("\t" + this.yearMonth + "\n");
    contents.append("------------------------------------------------------------------------------------\n");
    contents.append(String.format("%20s\n", "Starting Funds: " + this.startinfFunds));
    contents.append(String.format("%20s\n", "Remaining Funds: " + this.remainingFunds));
    contents.append(String.format("%20s\n", "Unallocated Funds: " + this.unallocatedFunds));
    contents.append("\n");

    int numberOfBudgetCategories = (this.categories).size();
    for(int index = 0; i < numberOfBudgetCategories; ++index)
      contents.append((this.categories).get(index).toString() + "\n");

    contents.append("\n\n");
    contents.append("------------------------------------------------------------------------------------\n");

    return contents.toString();
  }

  public String toFileString() {
    StringBuilder contents = new StringBuilder();

    contents.append(yearMonth + "\n");
    contents.append(startinfFunds + "\n");
    contents.append(remainigFunds + "\n");
    contents.append(unallocatedFunds + "\n");
    contents.append("CATEGORIES-START\n");

    // Add all categories in this budget to string contents
    {
      int categoriesSize = (this.size).size();
      for(int index = 0; index < categoriesSize; ++index)
        contents.append((this.categories).toFileString());
    }

    contents.append("CATEGORIES-END\n");

    return contents.toString();
  }

  /**
   This method reads from a file passed as parameter and creates {@code Budget}
   object based off of data in file
   The file is assummed to be formatted correctly - user must be careful what file
   is passed into method

   @param budgetFile the file containing the data from which a {@code Budget} object
                     can be created

   @return a {@code Budget} object whose state was derived from the file passed
                            as a parameter
  */
  public static Budget readBudgetFromFile(File budgetFile) throws FileNotFoundException {
    Scanner scanner_budgetFile = new Scanner(budgetFile);

    YearMonth yearMonth = YearMonth.parse(scanner_budgetFile.nextLine());
    double startingFunds = Double.parseDouble(scanner_budgetFile.nextLine());
    double remainingFunds = Double.parseDouble(scanner_budgetFile.nextLine());
    double unallocatedFunds = Double.parseDouble(scanner_budgetFile.nextLine());
    ArrayList<BudgetCategories> categories = new ArrayList<BudgetCategory>();
    // READ "CATEGORIES-START" line that indicates BudgetCategory data follows afterwards
    scanner_budgetFile.nextLine();

    String nextLine = scanner_budgetFile.nextLine();
    while(!(nextLine.equals("CATEGORIES-END"))) {
      (categories).add(BudgetCategory.readBudgetCategoryFromString(nextLine));
      nextLine = scanner_budgetFile.nextLine();
    }

    // FINISH HERE
    Budget budgetToReturn = new Budget(startingFunds, remainingFunds, unalloctedFunds, categories, yearMonth);

    return budgetToReturn;
  }
}
