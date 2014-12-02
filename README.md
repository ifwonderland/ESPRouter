ESPRouter
=========

Routing between Email Service Providers based on config 

1. Setup 

In order to run this application, you need to sign up free accounts at mailgun and mandrill, these are currently supported mail service providers. 

Once you get account setup, you need to enter API key and server URLs in esp-services-config.properties, this is currently located under src/main/resources, for convinience, ideally, this should be at a more secure and probably a folder in the system outside the project sources. 

Notice username and password are optional.

Once the above is done, you can now change configurations to switch between ESPs, the config file is located at src/main/resources, EmailRouteConfig.ini

Below is an example of this config file

[EmailServiceProvider]
SupportedServices=Mandrill,Mailgun
PreferedSerivce=Mailgun


Supported service lists all available and supported ESPs. Preferred service is the ESP that will be chosen to send emails. 

2. Run

Once the setup is done, you can run the service in two ways, 

2.1 Maven jetty:run

Under main root folder, run 
mvn clean jetty:run

This will start jetty at localhost:8080 and the path is
http://localhost:8080/esprouter

You can use either curl or some client tool to do a post such as 

{"to":"ifwonderland@gmail.com","to_name":"ifwonderland","from":"ifwonderland@gmail.com","from_name":"ifwonderland","subject":"test subject","body":"<html> test sent email this is email body. </html>"}

And this will do the job


2.2 Unit test run
You can also simple try test the app by run unit test at

/src/test/java/esp/router/unit/esp/router/unit/EmailServiceProviderRouterUnitTest.java










