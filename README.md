# Basic-Spring-Application
The Basic Spring Library App is a simple web application built with Spring Boot. It allows users to manage books and authors in a library system

Functionality
The Basic Spring Library App provides the following functionalities:

List Authors-
Access the (/authors) endpoint to view a list of all authors in the library.
Authors are displayed with their respective books.

Create Author-
Access the (/authors/create) endpoint to create a new author.
Fill in the author's name and submit the form to save the author to the database.

Edit Author-
Access the (/authors/edit/{id}) endpoint to edit an existing author.
Find the author you want to update and click edit to update author's details in the form.
Submit the form to save the changes to the database.

Delete Author-
Access the (/authors/delete/{id})endpoint to delete an author.
Find the author you want to delete and click delete to delete the author from the database.

List Books-
Access the (/books) endpoint to view a list of all books in the library.
Books are displayed with their respective authors.

Create Book-
Before creating a book, make sure to add the authors to the list using the (/authors) endpoints.
Access the (/books/create) endpoint to create a new book.
Fill in the book's title and select the author from the dropdown list.
Submit the form to save the book to the database.

Edit Book-
Access the (/books/edit/{id})endpoint to edit an existing book.
Find the book and select edit to update the book's details in the form.
Submit the form to save the changes to the database.

Delete Book-
Access the (/books/delete/{id}) endpoint to delete a book.
Find the book and click delete to delete the book from the database.

Testing-
The tests will validate the CRUD operations for both authors and books.
