
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
  boolean budgetInitializedForThisMonth;

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

    // Initialize this.budgetInitializedForThisMonth
    //
    // If this.budgets contains a budget with a YearMonth object that matches this.now
    // then the budget for this month is considered initialized
    {
      int size = (this.budgets).size();
      for(int index = 0; index < size; ++index) {
        if((this.now).equals((this.budgets).get(index).getYerMonth())) {
          this.budgetInitializedForThisMonth = true;
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
}
