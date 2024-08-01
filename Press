import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

public class Press{
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private Map<String, Integer> edition = new HashMap<>();
    private String pathToBooKDir;


    /**
     * This constructor will create a new press object with a path that is specified
     *
     * @param pathToBooKDir this is the path of the book storage
     * @param shelfSize     this is the maximum number of books that can be stored on a shelf.
     *                      shelf represents a mapping between BookIDS and editions that are available
     *                      edition represents another mapping between BookIDS and the latest edition numbers
     */
    public Press(String pathToBooKDir, int shelfSize) {
        this.shelfSize = shelfSize;
        this.pathToBooKDir = pathToBooKDir;
        this.shelf = new HashMap<>();//here we start the map for storing books lists
        this.edition = new HashMap<>();//this mapping is used to help us to track editions of books
        File location = new File(pathToBooKDir); //This is the directory that we represent as a file object
        File[] files = location.listFiles(); // This will list all files in the directory

        //here we ensure that the directory is not empty
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile() && files[i].getName().endsWith(".txt")) { // we check if the file ends with .txt
                    edition.put(files[i].getName(), 0); //We start the bookIDS initialization from 0
                    shelf.put(files[i].getName(), new ArrayList<>()); //and here we initialize the shelf mapping for each bookID
                }
            }
        }

    }


    /**
     * This method will request a number of certain books to be printed.*
     *
     * @param bookID  the id of the book that was requested
     * @param edition is the book edition
     *                amount will the number of copies requested
     * @return a list of books (Objects) that were requested
     * @throws IllegalArgumentException if the book ID is not existent
     * @throws RuntimeException         if there occurs any errors while printing
     */

    protected Book print(String bookID, int edition) {

        //This will represent a specified book file
        File newFile = new File(pathToBooKDir, bookID);

        //Here we check if the file exists otherwise we throw an exception
        if (!newFile.exists()) {
            throw new IllegalArgumentException("The BookID provided does not match any of the files");
        }

        //this will be the content of the book
        StringBuilder bookContent = new StringBuilder();
        String author = null; // Book's author
        String title = null;// Book's title
        boolean contentStarted = false; // We use this to know when the content starts

        //We use a bufferReader to read the file efficiently
        try (BufferedReader reader = Files.newBufferedReader(newFile.toPath(),
                StandardCharsets.UTF_8)) {
            String lineString; // This will keep each line that we read from the file
            while ((lineString = reader.readLine()) != null) {
                if (!contentStarted) {
                    if (lineString.startsWith("Author: ")) {
                        author = lineString.substring(8).trim(); // we will extract the author after the specified prefix
                    } else if (lineString.startsWith("Title: ")) {
                        title = lineString.substring(7).trim();// we will extract the title after the specified prefix
                    } else if (lineString.startsWith("*** START OF")) {
                        contentStarted = true;
                        //Here the content will start after this line
                        //Also the trim method is used to clean any extra white spaces.
                    }
                } else {
                    bookContent.append(lineString).append("\n");//here we append each line of the content/book content
                }
            }
        } catch (IOException e) {
            //if there is any I/O exception, we will print a message
            throw new RuntimeException("There was an error printing the book", e);
        }

        //This is used to make sure/ensure that there is no empty variable in our book
        if (title == null || author == null || bookContent.length() == 0) {
            //is so we will trow a runtimeException
            throw new RuntimeException("Something is wrong with the format!");
        }

        return new Book(title, author, bookContent.toString(), edition);
    }

    /**
     * This gives us of bookIDS that are available
     *
     * @return an array list which contains the booksIDs available
     */
    public List<String> getCatalogue() {
        return new ArrayList<>(shelf.keySet()); //This method will convert our bookIDS into a list, so we can work with them easier
    }

    /**
     * @param bookID represents the ID of the requested book
     * @param amount represents the number of copies that were requested
     * @return a list of requested Boo objects
     */
    public List<Book> request(String bookID, int amount) {
        //This list is used to store all requested books
        List<Book> booksRequests = new ArrayList<>();
        if (!shelf.containsKey(bookID)) {
            //If a bookID id is not there we will throw an exception
            throw new IllegalArgumentException("This bookID does not exist!");
        }

        //The list is used to give us the current stock for a book id
        List<Book> booksStock = new ArrayList<>();
        //This will give us the number of the current edition of a book (by a bookID)
        int currentBookEdition = edition.get(bookID);

        //the for loops is used to iterate through each requested book, amount keeps the number of requests
        for (int i = 0; i < amount; i++) {
            if (booksStock.isEmpty()) {
                currentBookEdition = currentBookEdition + 1;//We increment the edition number for the prints that are upcoming
                Book newPrintedBook = print(bookID, currentBookEdition); //Here we print a new book, with the updated edition number
                booksStock.add(newPrintedBook); //we add the new book to ours stock
                edition.put(bookID, currentBookEdition); // We add the new edition book into our catalogue
            }
            Book bookThatWasRequested = booksStock.remove(0); // Here we remove the book that was requested by the buyer
            booksRequests.add(bookThatWasRequested);//here we add the requested book to our list
        }
        return booksRequests;
    }


/*
//Press testing main (I made this as an addition of code grader tests, I hope I managed to test other cases to) (it was also modified numerous time, I just left it like this)
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter the path to the book directory:");
        String pathToBookDir = scn.nextLine();
        System.out.println("Enter the size of shelf :");
        int shelfSize = scn.nextInt();
        scn.nextLine();
        Press press = new Press(pathToBookDir, shelfSize);
        System.out.println("Enter the book name, that you want to print, including .txt to print:");
        String bookID = scn.nextLine();
        System.out.println("Enter the number of copies to be printed:");
        int copies = scn.nextInt();
        scn.nextLine();
        try {
            List<Book> printedBooks = press.request(bookID, copies);
            System.out.println("The book was printed:");
            for (Book book : printedBooks) {
                System.out.println("Book That was printed : " + book.getTitle() + ", Edition: " + book.getEdition());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Books Catalogue:");
        List<String> catalogue = press.getCatalogue();
        for (String bookName : catalogue) {
            System.out.println(bookName);
        }
        scn.close();
    }
 */


}
