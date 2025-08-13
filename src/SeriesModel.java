public class SeriesModel {
    private final String seriesId;
    private String seriesName;
    private String seriesAge;
    private int seriesNumberOfEpisodes;

    public SeriesModel(String seriesId, String seriesName, String seriesAge, int seriesNumberOfEpisodes) {
        if (seriesId == null || seriesId.trim().isEmpty()) {
            throw new IllegalArgumentException("Series ID cannot be null or empty.");
        }
        this.seriesId = seriesId.trim();
        this.seriesName = seriesName != null ? seriesName.trim() : "";
        this.seriesAge = seriesAge != null ? seriesAge.trim() : "";
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    public String getSeriesId() { return seriesId; }
    public String getSeriesName() { return seriesName; }
    public String getSeriesAge() { return seriesAge; }
    public int getSeriesNumberOfEpisodes() { return seriesNumberOfEpisodes; }

    public void setSeriesName(String seriesName) {
        if (seriesName != null && !seriesName.trim().isEmpty()) {
            this.seriesName = seriesName.trim();
        }
    }

    public void setSeriesAge(String seriesAge) {
        if (seriesAge != null && !seriesAge.trim().isEmpty()) {
            this.seriesAge = seriesAge.trim();
        }
    }

    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) {
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %s%nTitle: %s%nAge Restriction: %s%nEpisodes: %d",
                seriesId, seriesName, seriesAge, seriesNumberOfEpisodes
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof SeriesModel other)) return false;
        return seriesId.equalsIgnoreCase(other.seriesId);
    }

    @Override
    public int hashCode() {
        return seriesId.toLowerCase().hashCode();
    }
}