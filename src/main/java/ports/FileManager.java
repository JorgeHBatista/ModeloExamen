package ports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager  {

    private BufferedWriter writer;
    private BufferedReader reader;

    public FileManager (String name, int mode) {
        switch (mode) {
            case 0:
                try {
                    this.writer = new BufferedWriter(new FileWriter(name));
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                }
                break;
            case 1:
                try {
                    this.reader = new BufferedReader((new FileReader(name)));
                } catch (IOException e) {
                    System.err.println("Error reading from file: " + e.getMessage());
                }
                break;
            default:
                System.err.println("Incorrect mode, select 0 for writing or 1 for reading");
                break;

        }
    }

    public BufferedWriter getWriter() {
        return this.writer;
    }

    public BufferedReader getReader() {
        return this.reader;
    }

    public void write(String message) {
        if (getWriter() != null) {
            try {
                getWriter().write(message);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
    }

    public String read() {
        String message = "";
        if (this.getReader() != null) {
            try {
                String line = "";
                int iterator = 0;
                while ((line = this.getReader().readLine()) != null) {
                    if (iterator % 2 == 1) {
                        message += line + "\n";
                    }
                    iterator++;
                    if (iterator == 12) {
                        message += "-----\n";
                        iterator = 0;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return message;
    }

    public void closeReader() {
        try {
            this.getReader().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}