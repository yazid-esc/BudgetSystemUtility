
import java.io.File;
import java.util.ArrayList;


/**
 A FileManager object manages the program's files and folders, and their overall
 stucture - only one object should be created for this program/system and no more.

 @author Yazid Escudero
 @version 1.0.0
*/
public class FileManager {

  // CLASS ATTRIBUTES /////


  // ATTRIBUTES /////
  private File directoryBSUDatabase;
  private File directoryBudgets;
  private File directoryExpenses;
  private File fileCompleteExpenses;

  // INITIALIZATION BLOCK /////
  {
    this.directoryBSUDatabase = new File("./BSUDatabase/");
    this.directoryBudgets = new File(this.directoryBSUDatabase, "budgets/");
    this.directoryExpenses = new File(this.directoryBSUDatabase, "expenses/");
    this.fileCompleteExpenses = new File(this.directoryExpenses, "completeexpenses.txt");
  }

  

}
