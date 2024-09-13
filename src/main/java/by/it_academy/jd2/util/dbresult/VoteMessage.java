package by.it_academy.jd2.util.dbresult;

import java.time.ZonedDateTime;

public class VoteMessage {
    private final String message;
    private final ZonedDateTime timestamp;

    public VoteMessage(String message, ZonedDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}


