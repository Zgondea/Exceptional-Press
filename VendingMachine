import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private List<Book> shelf;
    private double locationFactor;
    private int cassette, safe;
    private String password;
    private int[] validCoins = {1, 2, 5, 10, 20, 50, 100, 200};


    /**
     * This method constructs a new Vending machine which has a shelf, a location factor, a password, a cassette and a safe
     * shelf is the list of books in our vending machine
     *
     * @param locationFactor influence the price of the book
     * @param password       this is required for different operations like restocking
     *                       cassette tell us the total value of coins in the vending machine introduced by a buyer
     *                       safe tell us how much money is in the safe of the vending machine
     */
    public VendingMachine(double locationFactor, String password) {
        //Here we initialize a new vending machine with all required parameters
        this.shelf = new ArrayList<>();
        this.locationFactor = locationFactor;
        this.password = password;
        this.cassette = 0;
        this.safe = 0;

    }


    /**
     * This method will get the current value of coins/money in the cassette
     *
     * @return the value of coins/money currently in the cassette
     */
    public int getCassette() {
        return cassette;
    }


    /**
     * @param coin represent the value of the coin that the buyer inserts
     * @throws IllegalArgumentException if the coin is invalid
     */
    public void insertCoin(int coin) {
        //here we check if the coins are valid, if so we accept them otherwise we throw an error msg.
        for (int validCoin : validCoins) {
            if (validCoin == coin) {
                cassette = cassette + coin;
                return;
            }
        }
        throw new IllegalArgumentException("The coin is invalid!");
    }

    /**
     * @return the original value of the cassette after canceling a transaction
     */

    public int cancel() {
        //This will cancel out the transaction and return the coins that were inside until that point
        int originalCassetteValue = cassette;
        cassette = 0;
        return originalCassetteValue;
    }


    /**
     * This is used to restock the machine with a list of books
     *
     * @param books    is the list of books to be restocked?
     * @param password is a string needed to be able to restock the books
     * @throws InvalidPasswordException if the password is wrong
     */
    public void restock(List<Book> books, String password) {
        //We use this method to add books to our vending machine if the password matches otherwise we throw an exception/error msg
        if (!password.equals(password)) {
            throw new InvalidPasswordException("Password is invalid!");
        }
        shelf.addAll(books);
    }

    /**
     * This method is used to empty the safe.
     *
     * @param password the password required so we can empty the safe
     * @return money value that was withdrawn
     * @throws InvalidPasswordException if the password is not correct
     * @deprecated
     */
    public int emptySafe(String password) {
        //Here we check if the password matches, if so we will empty our safe otherwise we will throw an exception/error msg
        if (!password.equals(password)) {
            throw new InvalidPasswordException("Password is invalid!");

        }
        int originalSafeValue = safe;
        safe = 0;
        return originalSafeValue;

    }


    /**
     * This will give us a list with the books that are available in the machine
     *
     * @return the list of books available
     */
    public List<String> getCatalogue() {
        //This will convert our books into strings and will return us a list will books that are available in our vending machine
        List<String> catalogue = new ArrayList<>();
        for (Book book : shelf) {
            catalogue.add(book.toString());
        }
        return catalogue;
    }


    /**
     * This will give us the price of the book and it's index
     *
     * @param index its index of the book
     * @return book's price
     * @throws IndexOutOfBoundsException if book's index is invalid
     */
    public int getPrice(int index) {
        //In this method we will calculate the price based on the location factor and book's index. If the index does not exist, we will throw and exception
        if (0 > index || index > shelf.size()) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        Book book = shelf.get(index);
        int price = (int) Math.ceil(book.getPages() * locationFactor);
        return price;
    }


    /**
     * This method is used to buy a book from the vending machine
     *
     * @param index is the index of the book we wish to buy
     * @return the book that someone bought
     * @throws IndexOutOfBoundsException if the index is invalid
     * @throws CassetteException         if the funds are insufficient
     */
    public Book buyBook(int index) {
        //This will check if the index of the book that someone wants to buy existing, if not, we throw and exception.
        //If the index is valid, we will check if there are sufficient funds in the cassette, if so the transaction is completed, if not we will throw a cassette exception
        if (0 > index || index > shelf.size()) {
            throw new IndexOutOfBoundsException("Index is invalid");
        }

        int price = getPrice(index);
        if (cassette < price) {
            throw new CassetteException("The funds in the cassette are insufficient ");
        }

        Book book = shelf.remove(index);
        cassette = cassette - price;
        safe = safe + price;
        return book;
    }



    /*
    //Vending Machine testing main (I made this as an addition of code grader tests, I hope I managed to test other cases to) (it was also modified numerous time, I just left it like this)
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Your Input\n");
        System.out.println("Enter Location Factor:");
        double locationFactor = scn.nextDouble();
        scn.nextLine();
        System.out.println("Enter Password:");
        String password = scn.nextLine();
        VendingMachine machine = new VendingMachine(locationFactor, password);
        List<Book> books = new ArrayList<>();
        System.out.println("Here we restock the Vending Machine.");
        System.out.println("Enter number of books that you want to restock:");
        int numberOfBooks = scn.nextInt();
        scn.nextLine();
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println("Enter Book Title:");
            String title = scn.nextLine();
            System.out.println("Enter Author:");
            String author = scn.nextLine();
            System.out.println("Enter Content:");
            String content = scn.nextLine();
            System.out.println("Enter Edition:");
            int edition = scn.nextInt();
            scn.nextLine();
            books.add(new Book(title, author, content, edition));
        }
        machine.restock(books, password);
        System.out.println("Current Catalogue:");
        List<String> catalogue = machine.getCatalogue();
        for (String details : catalogue) {
            System.out.println(details);
        }
        System.out.println("Which is the coin value:");
        int coin = scn.nextInt();
        scn.nextLine();
        try {
            machine.insertCoin(coin);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Please enter the number/index of the book that you want to buy:");
        int bookIndex = scn.nextInt();
        scn.nextLine();
        try {
            Book purchasedBook = machine.buyBook(bookIndex);
            System.out.println("Book that was bought: " + purchasedBook.getTitle());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Updated Version of Catalogue:");
        catalogue = machine.getCatalogue();
        for (String details : catalogue) {
            System.out.println(details);
        }
        scn.close();
    }
     */
}
