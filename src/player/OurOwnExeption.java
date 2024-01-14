package player;

public class OurOwnExeption extends Exception {
    OurOwnExeption(){
        super("player balance is lower than entered value, exchange failed");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "player balance is lower than entered value, exchange failed";
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
