import java.util.Comparator;

public class SortByYearComparator implements Comparator<Movie> {
    // Used for sorting in ascending order of
    // Movie name
    public int compare(Movie a, Movie b) {
        return a.year-b.year;

    }
}