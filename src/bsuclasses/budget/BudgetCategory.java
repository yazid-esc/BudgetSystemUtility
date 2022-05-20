
package bsuclasses.budget;

/**
 This {@code BudgetCategory} object will represent a category in a budget. For example, a June budget might
 have a "FieldTrip" expense category. Or, a  "Phone Bill" or "Savings" category. A BudgetCategory object has
 a name and a funds amount (the funds amount is the amount allocated to this specific spending category). For
 example, a "Bills" spending category might be given $1000 for the user's bills.

 @author Yazid Escudero
 @version 1.0.0
 @since 1.0.0
*/
public class BudgetCategory {

  // ATTRIBUTES /////
  private String name;
  private double funds;

  // CONSTRUCTORS /////
  public BudgetCategory(String name, double funds) {
    this.name = name;
    this.funds = funds;
  }

  public  BudgetCategory(BudgetCategory budgetCategory) throws NullPointerException{
    this(budgetCategory.name, budgetCategory.funds);
  }

  // SETTERS /////
  /**
   @param funds the funds to set
  */
  public void setFunds(double funds) {
    this.funds = funds;
  }

  // GETTERS /////
  /**
   @return the name
  */
  public void getName() {
    return this.name;
  }

  /**
   @return the funds
  */
  public void getFunds() {
    return this.funds;
  }

  // OTHER /////
  @Override
  public String toString() {
    String contents = String.format("%35s | %f.2", ("BudgetCategory: " + this.name), this.funds);
    return contents;
  }

  public static BudgetCategory readBudgetCategoryFromString(String budgetCategoryString) {
    String[] contents = budgetCategoryString.split(" ");

    // ARRAY VALUE MAP
    // .....................
    // contents[0] -> name
    // contents[1] -> funds
    // .....................

    BudgetCategory budgetCategory = new BudgetCategory(contents[0], Double.parseDouble(contents[1]));

    return budgetCategory;
  }

  public String toFileString() {
    return  (this.name + " " + this.funds + "\n");
  }
}
