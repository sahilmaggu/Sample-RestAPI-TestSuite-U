#Sample-RestAPI-TestSuite-U

This is a Sample Project to showcase API tests using data driven framework.

#How to run the Project

There is a GlobaldatafileProperties which contains the environment specific value.

Example

ENV=E2E


Above command will run the tests on E2E environment and pick the test data from E2E Data file.

Other than that you can pass the environment through Maven as well.

#Major Bugs:
* (P1)No Authorization/Authentication is implemented in API's 
* (P1)Session expiration logic is missing.
* (P2)User Post API needs to send following attributes(username,full_name,email) in Response.


#Note:
* Tests being written using RestAssured.
* Just to clarify that I have not wriitten the null, negative value Testcases as this is a Customer/user Side API.
Those validation needs to be handle at UI level not at API levels.
