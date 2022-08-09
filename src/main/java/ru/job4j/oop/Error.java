package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void errorInfo() {
        System.out.println(active);
        System.out.println(status);
        System.out.println(message);
    }

    public static void main(String[] args) {
        Error error1 = new Error();
        Error error2 = new Error(false, 32, "Fatal error!");
        Error error3 = new Error(true, 404, "System shut down.");
        error1.errorInfo();
        error2.errorInfo();
        error3.errorInfo();
    }
}
