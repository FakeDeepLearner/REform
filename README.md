###Version
To run this project, you need at least Java 17, to have access to sealed types.

###Running the project
In order to run this project, clone this repository to your favorite editor, IntelliJ recommended, and simply run
Main.java. Then, follow the prompts on the screen.
**IMPORTANT NOTE: DO NOT PUT COMMAS (,) IN ANY OF YOUR INPUTS, OTHERWISE THE PROGRAM WILL NOT WORK**

#What the project is
REform, originally a course project for CSC207 at the University of Toronto, is a real estate platform where sellers can
make and modify their listings (and of course view them); and buyers can favorite listings (and view their favorites),
filter properties from their price, number of bedrooms, etc....
All users, buyers and sellers; can report one another, view their inboxes and outboxes, view their login histories, see 
their chat history with another user, and log in/out.

Admins are the people who moderate the program. They can look at the reports sent by buyers or sellers and resolve them, 
ban users (until they are unbanned), view chat histories between any 2 users, look at the listings of a seller and create
new admin users.

All the information in the program is stored in separate csv files, which are parsed each time you run the program. 
This allows the data to not be lost when the program terminates. For example; if you create an account, terminate the 
program and run it again, you will still have access to the same account. Since we are using maps to store the basic 
entities like listings, messages, buyers and sellers; this behaviour is necessary.



