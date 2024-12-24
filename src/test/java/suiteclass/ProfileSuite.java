package suiteclass;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.*;
import post.servlet.LikePostSTest;

@Suite
@SelectPackages("profile.servlet")

public class ProfileSuite {


    @BeforeAll
    public static void inizio() {
        System.out.println("---->Start of the execution of the FAT test on DAO!\n\n");
    }

    @AfterAll
    public static void fine() {
        System.out.println("\n\n----> End of the execution of the FAT test on DAO!!");
    }

}
