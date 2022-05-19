
package bsuclasses.budget;

import java.util.ArrayList;

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
}
