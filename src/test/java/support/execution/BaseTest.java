package support.execution;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import support.drivers.Core;

public class BaseTest {

    protected final Core core = new Core();

    private String testName = "";

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testName = description.getMethodName();
            System.out.println("\n▶ INITIATING: " + testName);
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("✔ PASSED: " + testName);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("✘ FAILED: " + testName + " → " + e.getMessage());
        }
    };

    @Before
    public void setUp() {
        core.initializeApp();
    }

    @After
    public void tearDown() {
        core.quit();
    }
}
