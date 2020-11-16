# Task 3, Application Servers
## Web application
Your task is to write a quiz with the tomcat application server. The user enters username, password and email on a startpage before the quiz starts and each test has a default number of questions. The questions should be multiple choice checkboxes.

The design shall follow the MVC design pattern with

* HttpServlet(s) (as Controller)
* JavaBeans (as Model)
* jsp-pages (as View)

Netbeans/Tomcat/mySQL is the only environment we are supporting during labs. That does not mean we require you to use this combination but it's up to you to fix a working combination of IDE / application server / DB if you use another combination than stated above.

##Extra assignment
Add an additional admin interface where questions more questions can be added. All questions should be mirrored in a mySQL-database with JPA.

