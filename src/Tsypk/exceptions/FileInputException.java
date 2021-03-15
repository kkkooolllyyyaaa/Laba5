package Tsypk.exceptions;

/**
 * Исключение, сообщающее о некорректной работе с файлом.
 */
public class FileInputException extends Exception {
    public FileInputException(String message) {
        super(message);
    }
}
