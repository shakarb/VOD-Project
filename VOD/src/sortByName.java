import java.util.List;

public class sortByName implements SortingStrategy {
    @Override
    public void sort(List<Movie> list) {
        list.sort(new SortByNameComparator());

    }

}
