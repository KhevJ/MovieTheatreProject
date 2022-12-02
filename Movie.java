

public class Movie {
    private final int MovieId;
    private final String name;

    private final String time;
    private final int amount;

    public Movie(int MovieId, String name, String time, int amount) {
        this.MovieId = MovieId;
        this.name = name;
        this.time = time;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getAmount() {
        return amount;
    }

    public int getMovieId() {
        return MovieId;
    }
}
