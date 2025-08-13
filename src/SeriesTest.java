import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {
    private Series series;
    private final String testSeriesId = "S001";
    private final String testSeriesName = "Test Series";
    private final String testSeriesAge = "12";
    private final int testSeriesEpisodes = 24;

    @BeforeEach
    public void setUp() {
        series = new Series(new Scanner(System.in));
        SeriesModel testSeries = new SeriesModel(testSeriesId, testSeriesName, testSeriesAge, testSeriesEpisodes);
        series.addSeries(testSeries);
    }

    @Test
    public void testSearchSeries() {
        SeriesModel found = series.getSeriesById(testSeriesId);
        assertNotNull(found);
        assertEquals(testSeriesName, found.getSeriesName());
        assertEquals(testSeriesAge, found.getSeriesAge());
        assertEquals(testSeriesEpisodes, found.getSeriesNumberOfEpisodes());
    }

    @Test
    public void testSearchSeries_notFound() {
        SeriesModel found = series.getSeriesById("NON_EXISTENT");
        assertNull(found);
    }

    @Test
    public void testUpdateSeries() {
        SeriesModel toUpdate = series.getSeriesById(testSeriesId);
        assertNotNull(toUpdate);

        String newName = "Updated Series";
        String newAge = "16";
        int newEpisodes = 30;

        toUpdate.setSeriesName(newName);
        toUpdate.setSeriesAge(newAge);
        toUpdate.setSeriesNumberOfEpisodes(newEpisodes);

        SeriesModel updated = series.getSeriesById(testSeriesId);
        assertEquals(newName, updated.getSeriesName());
        assertEquals(newAge, updated.getSeriesAge());
        assertEquals(newEpisodes, updated.getSeriesNumberOfEpisodes());
    }

    @Test
    public void testDeleteSeries() {
        int initialSize = series.seriesList.size();
        boolean deleted = series.deleteSeriesById(testSeriesId);
        assertTrue(deleted);

        SeriesModel deletedSeries = series.getSeriesById(testSeriesId);
        assertNull(deletedSeries);
        assertEquals(initialSize - 1, series.seriesList.size());
    }

    @Test
    public void testDeleteSeries_notFound() {
        int initialSize = series.seriesList.size();
        boolean deleted = series.deleteSeriesById("NON_EXISTENT");
        assertFalse(deleted);
        assertEquals(initialSize, series.seriesList.size());
    }

    @Test
    public void testIsValidAge_valid() {
        assertTrue(series.isValidAge("2"));
        assertTrue(series.isValidAge("10"));
        assertTrue(series.isValidAge("18"));
    }

    @Test
    public void testIsValidAge_invalid() {
        assertFalse(series.isValidAge("1"));
        assertFalse(series.isValidAge("19"));
        assertFalse(series.isValidAge("0"));
        assertFalse(series.isValidAge("abc"));
        assertFalse(series.isValidAge(null));
        assertFalse(series.isValidAge(""));
    }

    @Test
    public void testIsValidEpisodeCount() {
        assertTrue(series.isValidEpisodeCount("1"));
        assertTrue(series.isValidEpisodeCount("100"));
        assertFalse(series.isValidEpisodeCount("0"));
        assertFalse(series.isValidEpisodeCount("-5"));
        assertFalse(series.isValidEpisodeCount("abc"));
        assertFalse(series.isValidEpisodeCount(null));
        assertFalse(series.isValidEpisodeCount(""));
    }
}