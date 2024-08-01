import java.util.Scanner;

public class Book {

    private String title;
    private String author;
    private String content;
    private int edition;

    /**
     * This constructor wll create as a new book object
     *
     * @param title   is our book's title
     * @param author  is our book's author
     * @param content is our book's content
     * @param edition is our book's edition
     */

    public Book(String title, String author, String content, int edition) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.edition = edition;
    }

    /**
     * This our Book's title getter method
     *
     * @return the title of our book
     */

    public String getTitle() {
        return title;
    }

    /**
     * This our Book's author getter method
     *
     * @return the author of the book
     */

    public String getAuthor() {
        return author;
    }

    /**
     * This our Book's content getter method
     *
     * @return the content of the book
     */

    public String getContent() {
        return content;
    }

    /**
     * This our Book's edition getter method
     *
     * @return the edition of the book
     */

    public int getEdition() {
        return edition;
    }

    /**
     * This method will calculate the number of pages that book has, as requested in the assigment sheet
     *
     * @return the number of pages
     *         I've been casting the formula in the return line, so I don't have to use an extra variable
     */
    public int getPages() {
        return (int) Math.ceil((double) content.length() / 750);
    }


    /**
     * This is a toString method that will return all details about a book, I could return it in one line, but I wondered to make my code to be a bit more clear
     *
     * @return book's details
     */
    public String toString() {
        String bookTitle = "Title: " + title;
        String bookAuthor = "Author: " + author;
        String bookEdition = "Edition: " + Integer.toString(edition);
        return bookTitle + "\n" + bookAuthor + "\n" + bookEdition + "\n";


    }

    //Main method for testing (I made this as an addition of code grader tests, I hope I managed to test other cases to) (it was also modified numerous time, I just left it like this)


    /*
    //I will not do java doc for the main method since it was not requested, and I've implemented it just to make sure that everything works
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Your Input\n");
        System.out.println("Enter Title:");
        String Title = scn.nextLine();
        System.out.println("Enter Author:");
        String Author = scn.nextLine();
        System.out.println("Enter Content:");
        String Content = scn.nextLine();
        System.out.println("Enter Edition:");
        int edition = scn.nextInt();
        System.out.println("This is the output");
        Book book = new Book(Title, Author, Content, edition);
        System.out.println("Book Class Tester:");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Edition: " + book.getEdition());
        System.out.println("Number of Pages: " + book.getPages());
        System.out.println("This is a tester for the description\n");
        System.out.println("Full Description:\n" + book.toString());
        scn.close();
    }
     */

}
