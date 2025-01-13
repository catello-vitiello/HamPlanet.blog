from selenium.webdriver.common.by import By


# page_url = about:blank
class Page(object):
    def __init__(self, driver):
        self.driver = driver

