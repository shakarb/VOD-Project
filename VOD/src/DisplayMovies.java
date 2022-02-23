import java.util.ArrayList;
import java.util.List;

public class DisplayMovies {
    SortingStrategy sortingStrategy;
    List<Movie> list;

    public DisplayMovies(List<Movie> list, SortingStrategy sortingStrategy) {
        super();
        this.list = list;
        this.sortingStrategy = sortingStrategy;
    }

    public void sortMovies() {
        sortingStrategy.sort(list);
    }

    public SortingStrategy getSortingStrategy() {
        return sortingStrategy;
    }

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

}