import books.Book;
import books.BookManager;
import books.LoanBook;
import com.sun.tools.jconsole.JConsoleContext;
import ports.FileManager;
import users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = formatter.format(currentDate);

        FileManager writeManager = new FileManager("inventario_y_vencimientos_" + formattedDate + ".txt", 0);
        FileManager readManager = new FileManager("Biblioteca Nacional de España.txt", 1);

        String information = readManager.read();
        readManager.closeReader();
        BookManager bookManager = new BookManager();
        bookManager.createBooks(information);
        ArrayList<Book> books = bookManager.getBooks();
        for (Book book : books) {
            writeManager.write(book.toString() + "\n----------\n");
        }
        writeManager.closeWriter();
    }
}
