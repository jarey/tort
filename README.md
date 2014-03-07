Tort (Test Osome Reporting Tool)
====

How often do you find yourself in a situation when your system test failed and you spend hours figuring out the reason?
You have a test report, but it doesn't explain you much about the problem? Tort is a tool that solves this problem.

The idea is that you have steps much like JBehave or Thucydides have, but they are much more detailed and can form a tree.
Any decent step usually consists of smaller ones: you register a user, but that itself means that you click a button, then
fill the fields, then press the button and here you go - you see a success or a failure on the screen. Even those tiny
sub-steps can have their own sub-steps. So now you have such hierarchy:

- Register a User with email `random@blah.com`, password `random`
 - Open main page
 - Press login button
 - Fill user data
  - Fill email `random@blah.com`
  - Fill password `random`
 - Press Submit button

Tort allows you building such detailed reports in a readable manner. YOU choose how detailed the report should be.

####How you should use Tort
Suppose you follow best practices while writing System Functional Tests and divide your code into test-case, webdriver
and page-object layer:

```java
class UserRegistrationTest {
  @Test
  void emptyEmail_shouldFailRegistration(){
    User user = new User("", randomPassword());
    UserRegistration.signUp(user);
    assertUserDoesNotExist(user);
  }
}

class UserRegistration {
  void signUp(User user) {
    report.info("Register a User {}", user); //!!
    mainPage.open();
    mainPage.openLoginDialog();
    report.info("Filling user data");
    loginDialog.fillEmail(user.email);
    loginDialog.fillPassword(user.password);
    loginDialog.submit();
  }
}

class MainPage {
  void openLoginDialog() {
     report.info("Press login button");
     loginButton.click();
  }
}

class LoginDialog {
  void fillEmail(String email) {
    report.info("Filling email `{}`", email);
    emailField.sendKeys(email);
  }
}
```

You see that you can put anything into the report? That's the keypoint when it comes to solid and detailed reporting.
It's hard to tell it's a report - it's much like a log. In the future we're going to add special annotations that are
going to help reduce the verbosity of the code, but that the essential idea - log whatever can help you in tests.