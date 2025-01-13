from time import sleep

from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


# page_url = http://localhost:8585/HamPlanet-blog/login.jsp
class LoginPage(object):
    # URL della pagina di login
    page_url = "http://localhost:8585/HamPlanet-blog/login.jsp"

    def __init__(self, driver):
        self.driver = driver

        self.driver.get("http://localhost:8585/HamPlanet-blog/login.jsp")
        self.email_field = self.driver.find_element(By.ID, "email")
        self.password_field = self.driver.find_element(By.ID, "password")
        self.login_button = self.driver.find_element(By.ID, "loginButton")

    def login(self, email, password):
        """Esegui la login inserendo le credenziali"""
        self.email_field.clear()  # Pulisce il campo username (in caso di eventuali valori precedenti)
        self.email_field.send_keys(email)

        self.password_field.clear()  # Pulisce il campo password (in caso di eventuali valori precedenti)
        self.password_field.send_keys(password)

        # Clicca sul pulsante di login
        self.login_button.click()


class SignupPage(object):
    page_url = "http://localhost:8585/HamPlanet-blog/signup.jsp"

    def __init__(self, driver, user=None):
        self.driver = driver
        self.driver.get(self.page_url)

        self.username_field = self.driver.find_element(By.ID, "username")
        self.email_field = self.driver.find_element(By.ID, "email")
        self.password_field = self.driver.find_element(By.ID, 'password')
        self.signupButton = self.driver.find_element(By.ID, 'signupButton')

        if user is not None:
            self.user = user
            if user == "cw":
                self.bio_field = self.driver.find_element(By.ID, 'descrizione')
            elif user == "s":
                self.token_field = self.driver.find_element(By.ID, 'token')

    def signup(self, username, email, password):
        self.username_field.clear()
        self.username_field.send_keys(username)

        self.email_field.clear()
        self.email_field.send_keys(email)

        self.password_field.clear()
        self.password_field.send_keys(password)
        self.signupButton.click()


class PostPage(object):

    def __init__(self, driver, post):
        self.page_url = f"http://localhost:8585/HamPlanet-blog/Post?postId={post}"
        self.driver = driver
        self.driver.get(self.page_url)

    def putlike(self):
        sleep(1)
        likeButton = self.driver.find_element(By.ID, "likeButton")
        self.driver.execute_script("arguments[0].click();", likeButton)
        sleep(3)

    def addCommento(self, comment):
        commento_field = self.driver.find_element(By.ID, 'commento_area')
        submitCommento = self.driver.find_element(By.ID, 'commentoSubmit')

        commento_field.send_keys(comment)
        self.driver.execute_script("arguments[0].click();", submitCommento)
        commento_field.clear()
        sleep(2)

    def removeCommento(self, commentid):
        comment_sec = self.driver.find_element(By.ID, f'commento-{commentid}')
        button = comment_sec.find_element(By.CSS_SELECTOR, 'button')

        self.driver.execute_script("arguments[0].click();", button)

    def removePost(self):
        deletePostButton = self.driver.find_element(By.ID, 'deletePostButton')

        deletePostButton.click()
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )


class CwPage(object):
    page_url = "http://localhost:8585/HamPlanet-blog/newpost.jsp"

    def __init__(self, driver):
        self.driver = driver
        self.driver.get(self.page_url)

        self.testo = self.driver.find_element(By.ID, 'content')
        self.title = self.driver.find_element(By.ID, 'title')
        self.file_input = driver.find_element(By.CSS_SELECTOR, "input[type='file']")

        self.submit_button = self.driver.find_element(By.ID, 'upload-post')

    def uploadPost(self, title: str, text: str, path: str):
        self.file_input.send_keys(path)
        self.title.send_keys(title)
        self.testo.send_keys(text)

        #self.submit_button.click()
        self.driver.execute_script("arguments[0].click();", self.submit_button)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )


class ProfilePage(object):
    page_url = "http://localhost:8585/HamPlanet-blog/account.jsp"

    def __init__(self, driver):
        self.driver = driver

        self.delete_account_button = self.driver.find_element(By.ID, 'elimina')
        self.username = self.driver.find_element(By.ID, 'userName')
        self.password = self.driver.find_element(By.ID, 'password')
        self.updateButton = self.driver.find_element(By.ID, 'updatebutton')

    def deleteProfile(self):
        self.driver.execute_script("arguments[0].click();", self.delete_account_button)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "loginpage"))
        )

    def updateProfile(self, username, password):
        self.username.clear()
        self.username.send_keys(username)
        self.password.send_keys(password)

        self.updateButton.click()
        self.driver.execute_script("arguments[0].click();", self.updateButton)
        sleep(10)






class HomePage(object):
    page_url = "http://localhost:8585/HamPlanet-blog/Home"

    def __init__(self, driver, cw=False):
        self.driver = driver
        self.driver.get(self.page_url)

        self.posts = self.driver.find_elements(By.CLASS_NAME, 'link-to-post')
        self.profile_link = self.driver.find_element(By.ID, 'profile-link')

        if cw:
            self.newpost_link = self.driver.find_element(By.ID, 'newpost-link')

    def viewpost(self, post_n: int) -> PostPage:
        for post in self.posts:
            if post.get_attribute("href").__contains__(f"postId={post_n}"):
                post.click()

        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "postpage"))
        )
        return PostPage(self.driver, post_n)

    def viewProfile(self) -> ProfilePage:
        self.profile_link.click()
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "user-account"))
        )
        return ProfilePage(self.driver)

    def newpost_page(self) -> CwPage:
        self.newpost_link.click()
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "newpost-page"))
        )
        return CwPage(self.driver)

    def home(self):
        self.driver.get(self.page_url)
        WebDriverWait(self.driver, 10).until(
            EC.presence_of_element_located((By.ID, "homepage"))
        )
