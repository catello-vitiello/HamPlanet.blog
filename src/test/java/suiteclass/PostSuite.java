package suiteclass;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Suite clas for post package")
@SelectPackages("post.servlet")
public class PostSuite {

    @BeforeAll
    public static void inizio() {
        System.out.println("---->Start of the execution of the FAT test on DAO!\n\n");
    }

    @AfterAll
    public static void fine() {
        System.out.println("\n\n----> End of the execution of the FAT test on DAO!!");
    }


}
