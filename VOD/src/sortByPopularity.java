import java.util.List;

public class sortByPopularity implements SortingStrategy {
    @Override
    public void sort(List<Movie> list) {
        list.sort(new SortByPopularityComparator());

    }

}
