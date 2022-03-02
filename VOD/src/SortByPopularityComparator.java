import java.util.Comparator;

public class SortByPopularityComparator implements Comparator<Movie> {
    // Used for sorting in ascending order of
    // Movie name
    public int compare(Movie a, Movie b) {
        return (int)(a.getPopularity()-b.getPopularity());

    }
}