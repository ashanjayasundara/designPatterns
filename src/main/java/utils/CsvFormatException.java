package utils;

public class CsvFormatException extends Exception {
    public CsvFormatException(String message) {
        super(message);
    }

    public CsvFormatException(Throwable cause) {
        super(cause);
    }
}
