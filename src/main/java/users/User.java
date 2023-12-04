package users;

public class User {

    private final int DNI;
    private boolean defaulter;

    public User(int DNI) {
        this.DNI = DNI;
    }

    public int getDNI() {
        return DNI;
    }

    public boolean getDefaulter() {
        return this.defaulter;
    }

    public void setDefaulter(boolean defaulter) {
        this.defaulter = defaulter;
    }
}
