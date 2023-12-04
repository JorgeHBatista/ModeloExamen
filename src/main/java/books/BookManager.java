package books;

import users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookManager {

    private final ArrayList<Book> books;
    public BookManager() {
        this.books = new ArrayList<Book>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void createBooks(String information) {
        String[] booksData = information.split("-----\n");
        for (String bookData: booksData) {
            try {
                String[] data = bookData.split("\n");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date requestedDate = dateFormat.parse(data[2]);
                Date expiredDate = dateFormat.parse(data[3]);
                User user = new User(Integer.parseInt(data[5]));
                LoanBook loanBook = new LoanBook(data[0], data[1], requestedDate, expiredDate, data[4], user);
                loanBook.computeDaysOfDelay();
                this.books.add(loanBook);
            } catch (ParseException e) {
                System.err.println("Error: The string is not a valid date");
                throw new RuntimeException(e);
            }

        }
    }

}
