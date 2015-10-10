# educommerce
A simple open source java based e-commerce application, mainly to be used for educational purposes!

Functionalities Implementation

Functionality: Login / Logout 
Database Connection: Yes
Database Remarks: Customer data is stored in the educommerce database under the 'customer' table.
Working Principle: Login and Logout is at the header.jsp file. If the customer isn't logged in, then Login is shown. If the customer is logged in then logout is displayed.
At the time customer press the Login button, they are redirected to the 'login.jsp' page. Here a customer can sign in or register for new account with certain usre information.
The login.jsp page redirects login request to LoginServlet and sign up request to SignUpServlet. 
