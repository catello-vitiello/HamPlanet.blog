import unittest
from selenium import webdriver
from selenium.webdriver.support.wait import WebDriverWait
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.support import expected_conditions as EC

from selenium.webdriver.common.by import By
from time import sleep
from page import pages

from selenium.webdriver.common.by import By

logout = "http://localhost:8585/HamPlanet-blog/Logout"


class TestSignup(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        """Metodo di setup prima dell'esecuzione del test"""
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))  # Avvio Chrome
        cls.driver.implicitly_wait(10)  # Imposta il timeout implicito per cercare gli elementi

    @classmethod
    def tearDownClass(cls):
        """Metodo per chiudere il browser dopo il test"""
        cls.driver.quit()

    def test_signup_success(self):
        """Test di login con credenziali valide"""
        signup_page = pages.SignupPage(self.driver)  # Crea l'oggetto della pagina di login
        signup_page.signup("seliumtest", "test@gmail.com", "Selenium25#")  # Esegui login con credenziali corrette

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "loginpage"))
        )
        # Verifica che siamo sulla pagina giusta dopo il login
        self.assertEqual(self.driver.current_url, "http://localhost:8585/HamPlanet-blog/login.jsp",
                         "Non sei sulla login.")

    def test_signup_email_failure(self):
        """Test di signup con email errata"""
        signup_page = pages.SignupPage(self.driver)
        signup_page.signup("nappi", "n.it", "Selenium25#")  # Login con credenziali sbagliate

        # Verifica se l'errore è visibile, ad esempio:
        error_message = self.driver.find_element(By.ID, "invalid-email")
        self.assertTrue(error_message.is_displayed(), "Il messaggio di errore non è visibile")
        self.assertIn("Invalid email format!", error_message.text, "Il messaggio di errore non è "
                                                                   "corretto.")

    def test_signup_used_email(self):
        """Test di signup con email usata"""
        signup_page = pages.SignupPage(self.driver)
        signup_page.signup("nappi", "n@n.it", "Selenium25#")  # Login con credenziali sbagliate

        error_message = self.driver.find_element(By.ID, "invalid-email")
        # Verifica se l'errore è visibile, ad esempio:
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "invalid-email"))
        )

        self.assertTrue(error_message.is_displayed(), "Il messaggio di errore non è visibile")
        self.assertIn("Email gia in uso", error_message.text, "Il messaggio di errore non è "
                                                              "corretto.")

    def test_signup_password_failure(self):
        """Test di signup con password errata"""
        signup_page = pages.SignupPage(self.driver)
        signup_page.signup("nappi", "nappi@n.it", "Seleni")  # Login con credenziali sbagliate

        # Verifica se l'errore è visibile, ad esempio:
        error_message = self.driver.find_element(By.ID, "invalid-psw")
        self.assertTrue(error_message.is_displayed(), "Il messaggio di errore non è visibile")
        self.assertIn("Invalid password format!", error_message.text, "Il messaggio di errore non è "
                                                                      "corretto.")


# page_url = http://localhost:8585/HamPlanet-blog/login.jsp
class TestLogin(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        """Metodo di setup prima dell'esecuzione del test"""
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))  # Avvio Chrome
        cls.driver.implicitly_wait(10)  # Imposta il timeout implicito per cercare gli elementi

    @classmethod
    def tearDownClass(cls):
        """Metodo per chiudere il browser dopo il test"""
        cls.driver.quit()

    def test_login_success(self):
        """Test di login con credenziali valide"""
        login_page = pages.LoginPage(self.driver)  # Crea l'oggetto della pagina di login
        login_page.login("n@n.it", "test")  # Esegui login con credenziali corrette

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        # Verifica che siamo sulla pagina giusta dopo il login
        self.assertEqual(self.driver.current_url, "http://localhost:8585/HamPlanet-blog/Home", "Non sei sulla home.")
        self.driver.get(logout)

    def test_login_failure(self):
        """Test di login con credenziali errate"""
        login_page = pages.LoginPage(self.driver)
        login_page.login("nappi", "test")  # Login con credenziali sbagliate

        # Verifica se l'errore è visibile, ad esempio:
        error_message = self.driver.find_element(By.ID, "error-message")
        self.assertTrue(error_message.is_displayed(), "Il messaggio di errore non è visibile")
        self.assertIn("Email or password entered are incorrect!", error_message.text, "Il messaggio di errore non è "
                                                                                      "corretto.")


class TestPost(unittest.TestCase):

    driver = None

    @classmethod
    def setUpClass(cls):
        """Metodo di setup prima dell'esecuzione del test"""
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))  # Avvio Chrome
        cls.driver.implicitly_wait(10)  # Imposta il timeout implicito per cercare gli elementi


    @classmethod
    def tearDownClass(cls):
        """Metodo per chiudere il browser dopo il test"""
        cls.driver.quit()

    def test_2_putLike(self):
        login = pages.LoginPage(self.driver)
        login.login("pippo@p.it", "test")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )

        postPage = pages.PostPage(self.driver, 1)
        postPage.putlike()
        sleep(1)

        likestatus = self.driver.find_element(By.ID, 'likeIcon')
        self.assertIn('http://localhost:8585/HamPlanet-blog/Icon/like.png', likestatus.get_attribute('src'), 'like errato')
        self.driver.get(logout)

    def test_1_unLike(self):
        login = pages.LoginPage(self.driver)
        login.login("pippo@p.it", "test")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )

        postPage = pages.PostPage(self.driver, 1)
        postPage.putlike()
        sleep(1)

        likestatus = self.driver.find_element(By.ID, 'likeIcon')
        self.assertIn('http://localhost:8585/HamPlanet-blog/Icon/nolike.png', likestatus.get_attribute('src'), 'like errato')
        self.driver.get(logout)

    def test_comment(self):
        login = pages.LoginPage(self.driver)
        login.login("pippo@p.it", "test")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        postPage = pages.PostPage(self.driver, 1)
        postPage.addCommento("selenium test")

        comment_div = self.driver.find_element(By.ID, 'commento-4')
        commento = comment_div.find_element(By.CSS_SELECTOR, 'p.card-text')

        self.assertIn("selenium test", commento.text)
        self.driver.get(logout)

    def test_remove_comment(self):
        login = pages.LoginPage(self.driver)
        login.login("sbocciario@hamplanet.blog.it", "test")
        sleep(1)

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )

        post_page = pages.PostPage(self.driver, 1)
        post_page.removeCommento(4)

        comments = self.driver.find_elements(By.CSS_SELECTOR, 'p.card-text')
        self.assertNotIn("selenium test", comments)


class TestPostAddRemove(unittest.TestCase):
    driver = None
    upload_file = "E:\\Download\\1000.jpeg"

    @classmethod
    def setUpClass(cls):
        """Metodo di setup prima dell'esecuzione del test"""
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))  # Avvio Chrome
        cls.driver.implicitly_wait(10)  # Imposta il timeout implicito per cercare gli elementi

    @classmethod
    def tearDownClass(cls):
        """Metodo per chiudere il browser dopo il test"""
        cls.driver.quit()

    def test_insert_post(self):
        login = pages.LoginPage(self.driver)
        login.login("n@n.it", "test")
        sleep(1)

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        home = pages.HomePage(self.driver, cw=True)

        newpost_page = home.newpost_page()

        newpost_page.uploadPost("selenium title", "test selenium", self.upload_file)

        self.assertEqual(self.driver.current_url, "http://localhost:8585/HamPlanet-blog/Home", "Non sei sulla home.")

    def test_remove_post(self):
        login = pages.LoginPage(self.driver)
        login.login("sbocciario@hamplanet.blog.it", "test")
        sleep(1)

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        home = pages.HomePage(self.driver)
        post_page = home.viewpost(5)

        post_page.removePost()
        self.assertEqual(self.driver.current_url, "http://localhost:8585/HamPlanet-blog/Home", "Non sei sulla home.")


class TestProfile(unittest.TestCase):
    driver = None

    @classmethod
    def setUpClass(cls):
        """Metodo di setup prima dell'esecuzione del test"""
        cls.driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()))  # Avvio Chrome
        cls.driver.implicitly_wait(10)  # Imposta il timeout implicito per cercare gli elementi

    @classmethod
    def tearDownClass(cls):
        """Metodo per chiudere il browser dopo il test"""
        cls.driver.quit()

    def test_1_view_profile(self):
        login = pages.LoginPage(self.driver)
        login.login("test@gmail.com", "Selenium25#")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        homepage = pages.HomePage(self.driver)
        homepage.viewProfile()


        self.assertEqual(self.driver.current_url, "http://localhost:8585/HamPlanet-blog/account.jsp",
                         "Non sei sulla home.")

    def test_2_update_profile(self):
        login = pages.LoginPage(self.driver)
        login.login("test@gmail.com", "Selenium25#")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        homepage = pages.HomePage(self.driver)
        profile_page = homepage.viewProfile()
        profile_page.updateProfile("test update", "Seleniumm21*")

        WebDriverWait(self.driver, 10).until(
            EC.visibility_of_element_located((By.CLASS_NAME, "modal"))
        )


    def test_3_delete_profile(self):
        login = pages.LoginPage(self.driver)
        login.login("test@gmail.com", "Seleniumm21*")

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
        home_page = pages.HomePage(self.driver)
        account_page = home_page.viewProfile()

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user-account"))
        )

        account_page.deleteProfile()

        self.assertEqual("http://localhost:8585/HamPlanet-blog/login.jsp", self.driver.current_url,
                         "Non sei sulla login.")


if __name__ == "__main__":
    unittest.main()
