
package bsuclasses.budget;

import java.util.ArrayList;
import java.util.Objects;

import java.time.LocalDateTime;

/**
 An {@code Expense} object represents a real life expense to be used for this program.
 For example, an $30 expense at a Neighboorhood Supermarket for

 <p>
    The {@code Expense} class provides utility methods for reading data from files.
    An example, a method can construct an {@code Expense} object from a string containing
    data for the object construction.
</p>

 @author Yazid Escudero
 @version 1.0.0
 @since 1.0.0
*/
public class Expense {

  // ATTRIBUTES ////////////////////
  private String name;
  private String description;
  private double cost;
  private LocalDateTime localDateTime;

  // CONSTRUCTORS ////////////////////

  /**
   Constructs an {@code Expense} object given all attributes needed

   @param name the name of the expense
   @param description the description of the expense
   @param cost the cost of the expense
  */
  public Expense(String name, String description, double cost, LocalDateTime localDateTime) {
    this.name = name;
    this.description = description;
    this.cost = cost;
    this.localDateTime = localDateTime;
  }

  /**
   Constructs an {@code Expense} object given another {@code Expense} object.

   @param expense the object whose state will be copied to the object being
                  constructed.

   @throw NullPointerException when {@code expense} parameter is null.
  */
  public Expense(Expense expense) throws NullPointerException{
    // Throws NullPointerExceptoin if parameter is null
    Objects.requireNonNull(expense);

    expense.copyTo(this);
  }

  // ACCESSORS ////////////////////

  /**
   @return the name of the object
  */
  public String getName() {
    return this.name;
  }

  /**
   @return the description of the object
  */
  public String getDescription() {
    return this.description;
  }

  /**
   @return the cost of the object
  */
  public double getCost() {
    return this.cost;
  }

  /**
   @return the local date and time of the expense
  */
  public LocalDateTime getLocalDateTime() {
    return this.localDateTime;
  }

  // MUTATORS ////////////////////

  /**
   @param name the name to set for the expense
  */
  public void setName(String name) {
    this.name = name;
  }

  /**
   @param description the description to set for the expense
  */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   @param cost the cost to set for the expense
  */
  public void setCost(double cost) {
    this.cost = cost;
  }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  // OTHER ////////////////////

  /**
   Copies {@code this} object's state to {@code expense} object.

   @param expense the object that is being copied into
  */
  public void copyTo(Expense expense) {
    expense.name = this.name;
    expense.description = this.description;
    expense.cost = this.cost;
    expense.localDateTime = this.localDateTime;
  }

  /**
   Constructs and returns an {@code Expense} object from the contents of {@code String contents}.

   @param contents the string that contains data contents to create and return {@code Expense}
                   object.
  */
  public static Expense readExpense(String contents) {
    String[] expenseData = contents.split("#");

    // Array data mapping
    //  expenseData[0] --> name
    //  expenseData[1] --> description
    //  expenseData[2] --> cost
    //  expenseData[3] --> localDateTime

    double cost = Double.parseDouble(expenseData[2]);
    LocalDateTime localDateTime = LocalDateTime.parse(expenseData[3]);

    return (new Expense(expenseData[0], expenseData[1], cost, localDateTime));
  }

  /**

  */
  public String toFileString() {


    // Since both the name and the description can have multiple words, special
    // separation techniques must be used to separate data in string
    //
    // String formatting:
    //
    // name of the expense#description of the expense#cost#localDateTime
    //
    // then we can run string.split("#") when reading the string


    StringBuilder contents = new StringBuilder();

    contents.append(this.name + "#");
    contents.append(this.description + "#");
    contents.append(this.cost + "#");
    contents.append((this.localDateTime).toString() + "\n");

    return (contents.toString());
  }

  public String toString() {
      StringBuilder contents = new StringBuilder();

      contents.append(String.format("%15s|", this.name));
      contents.append(String.format("%30s|", this.description));
      contents.append(String.format("%8s|", this.cost));
      contents.append(String.format("%15s|", this.localDateTime));

      return (contents.toString());
  }
}
