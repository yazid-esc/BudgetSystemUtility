
package bsuclases.operations;

import bsuclasses.budget.*;
import bsuclases.files.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFounException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.YearMonth;

/**
  An object of {@code Operations} class serves as a "center of operations" that sits between the
  user interface class {@code BudgetApplication} in {@code BudgetApplication.java} and the other
  program files.

  @author Yazid Escudero
  @version 1.0.0
  @since 1.0.0
*/
public class Operations {

  // ATTRIBUTES /////
  private FileManager fileManager;
  private ArrayList<Budgets> budgets;
  private YearMonth now;
  private boolean budgetInitializedForThisMonth;
  private Budget currentBudget;

  // INITIALIZATION BLOCK /////
  {
    this.fileManager = new FileManager();
    this.now = YearMonth.now();

    // Initalize this.budgets
    {
      ArrayList<File> filesInBudgets = (this.fileManager).getFilesInBudgetsClone();
      int size = filesInBudget.size();

      for(int index = 0; index < size; +index)
        (this.budgets).add(Budgets.readBudgetFromFile(filesInBudgets.get(index)));
    }

    // Initialize this.budgetInitializedForThisMonth and this.currentBudget
    //
    // If this.budgets contains a budget with a YearMonth object that matches this.now
    // then the budget for this month is considered initialized
    //
    // If this.budget contains a budget for this month, initialize this.currentBudget
    // to that budget

    {
      int size = (this.budgets).size();
      for(int index = 0; index < size; ++index) {
        if((this.now).equals((this.budgets).get(index).getYerMonth())) {
          this.budgetInitializedForThisMonth = true;
          this.currentBudget = (this.budgets).get(index);
          break;
        }
      }
    }
  }

  // No constructors

  // CLONE ACCESSORS /////

  public ArrayList<Budgets> getBudgetsClone() {
    return (ArrayList<Budgets>)((this.budgets).clone());
  }

  // ACCESSORS
  public YearMonth getNow() {
    return this.now;
  }

  public boolean getBudgetInitializedForThisMonth() {
    return this.budgetInitializedForThisMonth;
  }

  // Do not return file manager - file manager operations should be carried out
  // on behalf of this Operations object

  // OTHER /////

  /**
   Defines a {@code Budget} for this current month if one is not already initialized.
  */
  public boolean defineNewBudget(double startingFunds) {
    // Check if current month already has a budget
    if(this.budgetInitializedForThisMonth)
      return false;

    Budget newBudget = new Budget(startingFunds);
    try {
      (this.fileManager).newBudgetFile(newBudget.getYearMonth());
    } catch(Exception e) {
      System.out.println(e.getMessage() + "\n\n");
      e.printStackTrace();

      System.out.println("File " + newBudget.getYearMonth() + ".txt should not exist, but it does - FATAL");
      System.out.println("")
      System.exit(0);
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n\n");
      ioe.printStackTrace();

      System.out.println("\n\nSomething went wrong when creating file for new budget...");
      System.out.println("Aborting attempt....\n\n");
      return false;
    }

    // Add newBudget to program's filesystem
    try {
      (this.fileManager).writeToFile(newBudget.getYearMonth() + ".txt", newBudget.toFileString(), false);
    } catch(IOException ioe) {
      System.out.println(ioe.getMessage() + "\n\n");
      ioe.printStackTrace();

      System.out.println("Something went wrong when writing to file" + newBudget.getYearMonth() + ".txt....");
      System.out.println("Aborting process.....");
      return false;
    }


    // Add newBudget to ArrayList budgets
    (this.budgets).add(newBudget);

    // Update this.currentBudget to reference budget in this.budgets corresponding to
    // this month
    int indexOfLastBudget = (this.budgets).size() - 1
    (this.currentBudget) = (this.budgets).get(indexOfLastBudget);

    // Update to reflect existing budget for current month
    this.budgetInitializedForThisMonth = true;
    return true;
  }

  public void addCategory(BudgetCategory category) {
    try {
      (this.currentBudget).addCategory(category);
    } catch(Exception e) {
      System.out.println("Error adding category:\n\n" + e.getMessage() + "\n\n");

      return;
    }

    System.out.println("Budget category:\n" + category + "\n\nhas been successfully added to this month's budget");
  }
}
