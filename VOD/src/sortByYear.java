import java.util.List;

public class sortByYear implements SortingStrategy {
    @Override
    public void sort(List<Movie> list) {
        list.sort(new SortByYearComparator());

    }

}
