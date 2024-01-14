package player;

public class OurOwnException extends Exception {
    OurOwnException(){
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
