import org.testng.annotations.Test;

public class ParallelTest extends BaseTest {

    @Test(dataProvider = "csv-data", dataProviderClass = DataProviderHelper.class, testName = "Data driven test with Excel data")
    public void dataDrivenTest(Object[] data) {
        testNameThread.set(testNameThread.get() + "_" + data[0]);
//        System.out.println("Running with Thread Id: " + Thread.currentThread().getId());
    }
}
