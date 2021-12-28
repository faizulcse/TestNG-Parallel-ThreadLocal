import org.testng.annotations.DataProvider;

public class DataProviderHelper {
    @DataProvider(name = "csv-data", parallel = false)
    public static Object[][] getCsvData() {
        return new Object[][]{{1}, {2}, {3}, {4}, {5}, {6}, {7}, {8}};
    }
}
