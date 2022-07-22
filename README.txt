README

Running the Program

To run the program, open `Main.java` which is located inside the `src` folder and run the class's `main` method.

Note: One of the features that we plan on adding for phase 2 is being able to create listings without a number, but for
now, please enter a listing number when you are creating a listing.

Additional Requirements Answered

a. Our program follows SOLID principles because for one, each class only has a single responsibility. To ensure that we
followed this principle we created a separate class called GenerateUniqueID that generates a unique integer for each
listing and message that can be used as an identifier. While we were originally planning on including such a method
inside the CreateListing and SendMessages use cases this would have given these classes multiple responsibilities.
Furthermore, we also followed the Dependency Inversion rule by ensuring that the use case classes do not explicitly
create CSV readers and writers and created an interface to ensure there is a layer of abstraction between the
controllers and use case classes.

b. Our program follows Clean Architecture because each layer of Clean Architecture in our program is only dependent on
the layer inside it. In order to incorporate a more intuitive file structure into our program we created directories
for each of the layers of clean architecture. This both makes our program easier to read and understand whilst also
allowing us to visualize dependencies and ensure that the rules of clean architecture are being followed.

c. Over the course of phase 1, we found that we had a large number of different types of users, and were calling
different classes and constructors in order to create a specific type of user (e.g. a buyer, seller, admin, non-admin).
We solved this problem by using the factory design pattern and created a class called UserFactory which has methods to
create a specific type of user. This reduces the number of dependencies in the code and makes it more extendable if we
choose to add different types of users in the future.

d. One of the code smells that we had in our program was duplicate code. The ListingProperties use case class had
search functions that would search for listings by individual properties. Because this created a lot of duplicate code,
ListingProperties was refactored so that all the SearchBy functions instead call a private SearchBy that takes in a
Predicate<Listing>. The commit hash of the fix was 73bae90946cd9870bb81bc0c87b213b8e491184d.

e. One of the major pieces of refactoring that we did was to restructure our phase 0 code so that it was better
integrated with the code we were writing for phase 1. To do so, we renamed our phase 0 directory to LoginSystem to
better represent its function and role in the program and we moved this directory inside our src folder. We packaged the
code we were writing for phase 1 inside a directory called RealEstate to separate the login system with the real estate
system. The commit hash for this refactoring was 5647895f56729a3862e554e0b8705a0e943682be. Another source of refactoring
was when we inverted dependencies inside the use case classes and created a number of csv gateway classes for the
corresponding use cases that needed to read and write to and from a file. The commit hash for this refactoring was
8c44fd8a17b039ada416b462022ab141fc0c8ae3.

f. Below are each person's contributions to phase 1:

Anudev (Github username - anudevgill): Created CreateListing use case with some of the csv code later being adpated and
pulled out into the gateway classes, wrote tests for CreateListingTest, wrote Javadocs for CreateListing and some
controllers, created CsvInterface which was used by gateways classes to invert dependencies, and wrote README.

Rana: (Github username - rjak03) Created the UserMessagesCSV controller and the SendMessages use case. Also created the
LoggedInManager controller based on previous version and added docstrings to some classes.

Eren: (GitHub username - FakeDeepLearner) Created the CSV controllers, helped in creating use cases, particularly 
CreateBuyer and CreateSeller. Created FavoriteListing. Fixed the issues in the tests. Modified the use cases to keep 
track of the current session. Refactored the csv controllers to use UserFactory. Wrote JavaDoc for the csv controllers.
Modified the LoggedInManager to also modify the listings created in the session.

Rohit: GitHub username - rshetty22166145) Worked on listing Container entity, Listing Properies Use-Case Class, 
Listing Properties Tests, Fixed Naming bugs and aaded on to Java Doc for Listing Properties class.

Felix: (GitHub username - ff-zhang) Created the Listing entity, UserFactory use case, and the Main class. Updated
LoggedOutManager controller to handle Buyer and Seller users. Refactored and abstracted classes such as Container and
ListingProperties. Wrote Javadocs for InputHandler, AdminUser, UserContainer, Listing, and others.

Andrew: (Github username - aychun) Created the Buyer and Seller entities, use cases of generating unique IDs of users
and messages, accessing database files (CSV file), wrote Javadocs for most use cases, and wrote unit tests.
