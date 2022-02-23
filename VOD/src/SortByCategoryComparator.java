import java.util.Comparator;

public class SortByCategoryComparator implements Comparator<Movie> {
    // Used for sorting in ascending order of
    // Movie name
    public int compare(Movie a, Movie b) {
        return a.category.compareTo(b.category);

    }
}