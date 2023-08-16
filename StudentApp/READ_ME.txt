All endpoints (except the authentication ones) are secured.
Meaning, they require a JWT in order to proceed. The JWT I've implemented
is stateless. It carries the username and his roles.
There are following roles (Each additional role has the ability of the
preceding role):

* Student -> can view other students
* Teacher -> can add/update students
* Principal -> can delete students

Make sure to run the sql script , which will generate the tables and populate
the student table with the sample data.