
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

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

  private ArrayList<File> filesInBudgets;
  private ArrayList<File> filesInExpenses;

  // INITIALIZATION BLOCK /////
  {
    this.directoryBSUDatabase = new File("./BSUDatabase/");
    this.directoryBudgets = new File(this.directoryBSUDatabase, "budgets/");
    this.directoryExpenses = new File(this.directoryBSUDatabase, "expenses/");
    this.fileCompleteExpenses = new File(this.directoryExpenses, "completeexpenses.txt");

    this.filesInBudgets = new ArrayList<File>();
    this.filesInExpenses = new ArrayList<File>();

    // Populate this.filesInBudgets
    {
      File[] files = (this.directoryBudgets).listFiles();
      int numberOfFiles = files.length;
      for(int index = 0; index < numberOfFiles; ++index)
        (this.filesInBudgets).add(files[index]);
    }

    // Populate this.filesInExpenses
    {
      File[] files = (this.filesInExpenses).listFiles();
      int numberOfFiles = files.length;
      for(int index = 0; index < numberOfFiles; ++index) {
        String currentFileName = files[index];
        if(currentFileName.equals("completeexpenses.txt"))
          continue;

        (this.filesInExpenses).add(files[index]);
      }

    }
  }

  /**
   Makes sure that FileSystem directories and files necessary for program operations
   exist - creates them if something is missing and doesn't exist

   @throws IOExeption
           when something goes wrong creating necessary program files
  */
  public void verifyFileSystem() throws IOException {
    // First verify directories
    if(!((this.directoryBSUDatabase).exists()))
      (this.directoryBSUDatabase).mkdir();

    if(!((this.directoryBudgets).exists()))
      (this.directoryBudgets).mkdir();

    if(!((this.directoryExpenses).exists()))
      (this.directoryExpenses).mkdir();

    // Then verify files
    if(!((this.fileCompleteExpenses).exists()))
      (this.fileCompleteExpenses).createNewFile();
  }

  /**
   Method returns the contents of the requested file in a Scanner object.

   @param fileName This String denotes the file name to search for in the program's
                   FileSystem

   @returns A <code>Scanner</code> object if the file is found, <code>null</code>
            otherwise
   @throws FileNotFoundException
           If file is not found - whether file should exist or not - user should check
           if the file <em>should<em> have existed if this is thrown
  */
  public Scanner requestFileContents(String fileName) throws FileNotFoundException {
    if(fileName.equals((this.fileCompleteExpenses).getName()))
      return new Scanner(this.fileCompleteExpenses);

    // Check files in this.filesInBudgets
    {
      int size = (this.filesInBudgets).size();
      for(int index = 0; index < size; ++index) {
        if(fileName.equals((this.filesInBudgets).get(index).getName())
          return Scanner((this.filesInBudgets));
      }
    }

    // Check files in this.filesInExpenses
    {
      int size = (this.filesInExpenses).size();
      for(int index = 0; index < size; ++index) {
        if(fileName.equals((this.filesInExpenses).get(index).getName())
          return Scanner((this.filesInExpenses));
      }
    }

    // Indicates file was not found in program's filesystem
    return null;
  }

}
