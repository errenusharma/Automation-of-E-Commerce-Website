# Automation-of-E-Commerce-Website

Project: Automating the various tasks in flipkart Industry: E-commerce Problem Statement: Perform UI, Functional, Regression, Black Box , Sanity and Browser Compatibilty Testing using automation tools for effective testing Topics: This Project deals with various aspects of automating scenarios using POM, TestNG, and Maven with Reports. Highlight: Structure, group & launch test with TestNG Project flow management with Maven Creating Object Repository with POM Test Scenarios :

Automate flipkart Login Module SR# Test Scenario Test Cases Test Steps Test Data Expected Result SS1 Verify Flipkart Login Enter valid User Id &Password 1. Go to https://www.flipkart.com

Enter Valid UserId

Enter Valid Password

Click Login

Close Browser UserId valid Password valid Test URL

Page Display My Account after Successful Login

SS2 Verify Flipkart Login Enter valid User Id & Invalid Password Same as above UserId valid Password Invalid Test URL Page displays Error Message Your username or password is incorrect

SS3 Verify Flipkart Login Enter Invalid User Id & valid Password Same as above UserId Invalid Password valid Test URL Page displays Error Message Your username or password is incorrect

SS4 Verify Flipkart Login Enter Invalid User Id & Invalid Password Same as above UserId Invalid Password Invalid Test URL Page displays Error Message Your username or password is incorrect

After Valid login : Automate Add to Cart and Check Out Modules
Steps to get access to Test Site

Go to https://www.flipkart.com
Enter Valid UserId
Enter Valid Password
Click Login
Place Verification Point 1
Enter data in search box
Click on Apple Iphone7
Switch to another Tab
Click on Add to Cart
Place Verification Point 2 GoToCart
Click on Place Order
Place Verification Point 3 Checkout
Close Browser Expected Result Verification Point-1 Page should Display My Account after Successful Login Verification Point-2 Page should display URL as https://www.flipkart.com/viewcart?otracker=PP_GoToCart Verifiction Point-3 Page should display Delivery Address and URL as https://www.flipkart.com/checkout/init?loginFlow=false