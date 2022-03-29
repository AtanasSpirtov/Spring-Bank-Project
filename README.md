# Spring-Bank-Project
2. This is a simple spring boot project with basic JDBC authentication security and some functionalities

# How to download:

1. First download the project as zip and open it with Intelij

2. When you open project you will see folder called `Database`

3. You will see some files ending with .sql

4. Open workbench or other Mysql visual tool for database architect.

5. Create schema called transactionmanager, create query, type `use transactionmanager` and execute query

6. Create query for every file in folder Database from point 3. , copy all the content , paste it and execute the query

7. When you executed all of the files in Database folder go to `src/main/resources/application.properties` and open it

8. Change `spring.datasource.username=?` and `spring.datasource.password=?` where `?` is your Local Mysql Connection username in the first case and you Local Mysql Connection password in the second case

9. After you finish go back to your project and run it

10. If you use angular client from `https://github.com/AtanasSpirtov/Angular-frontend-For-Bank-Project` go back and finish the steps

11. If you want to test functionalities via swagger type `http://localhost:8080/swagger-ui/index.html#/` and start testing

11.1 When you try to test it will prompt you to enter your username and password. Type `nasko` for username and `nasko` for password and enjoy

# Project functionalities

3.1 You can add or remove account or search account by its id or name

3.2 You can create a transaction between two accounts but you have to fill fields in json and after that to execute

3.3 You can list all transactions of a given source account

3.3 You can see transactions of an account listed in a pdf , excel file or to return in zip format

3.4 You can search account with one or two mistakes in the name with searchEngine functionality

3.5 Account searching uses cache proxy so if account is loaded it will return it to you much faster

3.6 You can register user, login and logout

3.7 The security is with httpBasic login and with jdbc authentication, the encryption of the password is with BcryptPasswordEncoder

3.8 There are different roles and every one of them can have list of authorities.

That is all for now, start testing and Enjoy!

