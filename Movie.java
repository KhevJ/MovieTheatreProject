import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movie {
    private final String name;
    private final LocalDateTime time;
    private final int amount;

    public Movie(String name, LocalDateTime time, int amount) {
        this.name = name;
        this.time = time;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = time.format(formatter);

        return formattedDateTime;
    }

    public int getAmount() {
        return amount;
    }
}
