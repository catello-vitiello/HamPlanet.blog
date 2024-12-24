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


}
