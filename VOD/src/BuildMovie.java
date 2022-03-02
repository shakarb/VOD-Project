public interface BuildMovie {
    public Movie build();
    public String getTitle();
    public String[] getCategory();
    public int getYear();
    public String[] getActors();
    public boolean getIsAvailable();
    public double getPopularity();
    public double getRunningTime();
    public int getPrice();
}
