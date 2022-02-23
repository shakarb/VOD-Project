import java.util.List;

public class sortByCategory implements SortingStrategy {
    @Override
    public void sort(List<Movie> list) {
        list.sort(new SortByCategoryComparator());

    }

}   
