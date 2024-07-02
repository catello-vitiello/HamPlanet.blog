package suiteclass;

import commento.dao.CommentoDAOTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import post.dao.PostDAOTest;
import profile.ProfileDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProfileDAOTest.class, PostDAOTest.class, CommentoDAOTest.class})
public class AllDAOTestFire {

    @BeforeClass
    public static void inizio() {
        System.out.println("---->Start of the execution of the FAT test on DAO!\n\n");
    }

    @AfterClass
    public static void fine() {
        System.out.println("\n\n----> End of the execution of the FAT test on DAO!!");
    }

}
