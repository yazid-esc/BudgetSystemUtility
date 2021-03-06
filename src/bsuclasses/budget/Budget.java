
package bsuclasses.budget;

import bsuclasses.budget.BudgetCategory;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.File;

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
    this.remainingFunds = startingFunds;
    this.unallocatedFunds = startingFunds;

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
  public Budget(double startingFunds, double remainingFunds, double unallocatedFunds, ArrayList<BudgetCategory> categories, YearMonth yearMonth) {
    this.startingFunds = startingFunds;
    this.remainingFunds = remainingFunds;
    this.unallocatedFunds = unallocatedFunds;
    this.categories = categories;
    this.yearMonth = yearMonth;
  }

  // ACCESSORS /////
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

  public YearMonth getYearMonth() {
    return this.yearMonth;
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
    contents.append(String.format("%20s\n", "Starting Funds: " + this.startingFunds));
    contents.append(String.format("%20s\n", "Remaining Funds: " + this.remainingFunds));
    contents.append(String.format("%20s\n", "Unallocated Funds: " + this.unallocatedFunds));
    contents.append("\n");

    int numberOfBudgetCategories = (this.categories).size();
    for(int index = 0; index < numberOfBudgetCategories; ++index)
      contents.append((this.categories).get(index).toString() + "\n");

    contents.append("\n\n");
    contents.append("------------------------------------------------------------------------------------\n");

    return contents.toString();
  }

  public String toFileString() {
    StringBuilder contents = new StringBuilder();

    contents.append(this.yearMonth + "\n");
    contents.append(this.startingFunds + "\n");
    contents.append(this.remainingFunds + "\n");
    contents.append(this.unallocatedFunds + "\n");
    contents.append("CATEGORIES-START\n");

    // Add all categories in this budget to string contents
    {
      int categoriesSize = (this.categories).size();
      for(int index = 0; index < categoriesSize; ++index)
        contents.append((this.categories).get(index).toFileString());
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
    ArrayList<BudgetCategory> categories = new ArrayList<BudgetCategory>();
    // READ "CATEGORIES-START" line that indicates BudgetCategory data follows afterwards
    scanner_budgetFile.nextLine();

    String nextLine = scanner_budgetFile.nextLine();
    while(!(nextLine.equals("CATEGORIES-END"))) {
      (categories).add(BudgetCategory.readBudgetCategoryFromString(nextLine));
      nextLine = scanner_budgetFile.nextLine();
    }


    Budget budgetToReturn = new Budget(startingFunds, remainingFunds, unallocatedFunds, categories, yearMonth);

    return budgetToReturn;
  }

  // TODO: Javadoc
  public void addCategory(BudgetCategory category) throws Exception {
    // Check for same name - if same name, then throw error
    for(BudgetCategory current : this.categories) {
      String parameterName = category.getName();
      String currentName = current.getName();

      if(parameterName.equalsIgnoreCase(currentName))
        throw new Exception("Category " + parameterName + " already exists");
    }

    double categoryFunds = category.getFunds();

    if(categoryFunds > this.unallocatedFunds) {
      throw new Exception("Unsufficient funds to support category amount of " + categoryFunds);
    }

    // Add category to budget
    this.unallocatedFunds -= categoryFunds;
    (this.categories).add(category);
  }

  // TODO: Javadoc
  public boolean removeCategory(String categoryName) {
    // Find category in this.categories whose name matches string categoryName
    BudgetCategory categoryToRemove = null;
    for(BudgetCategory current : this.categories) {
      String currentName = current.getName();

      if(categoryName.equalsIgnoreCase(currentName)) {
        categoryToRemove = current;
      }
    }

    // false returned if category was not found
    if(categoryToRemove == null) {
      return false;
    }

    double categoryToRemoveFunds = categoryToRemove.getFunds();
    (this.categories).remove(categoryToRemove);

    // Funds from categoryToRemove are no longer considered allocated
    this.unallocatedFunds += categoryToRemoveFunds;

    return true; // success
  }
}
