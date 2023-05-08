# LibrarySysyem
desc
<!-- TODO ## Description -->

## Class dependency graph
```mermaid
classDiagram
    MainUI --> LibrarianUI : login
    MainUI --> StudentUI : login
    LibrarianUI --> Students
    LibrarianUI --> Books
    Books --> Book
    StudentUI --> StudentEdit
    StudentUI --> Books
    Students --> Student
    StudentEdit --> Student
    LibrarianUI --> BookEdit
    BookEdit --> Book

    class MainUI{
        +void main(String[] args)
        +void login()
    }
    class LibrarianUI{
        Books books
        Students students

        +void addBook()
        +void removeBook()
        +void editBook()
        +void addStudent()
        +void removeStudent()
        +void editStudent()
        +void showBooks()
        +void showStudents()
    }
    class StudentUI{
        Student student

        +void showBooks()
        +void rentBook()
        +void returnBook()
        +String locateBook()
        +void editAccount()
    }
    class Students{
        HashMap students

        +void addStudent()
        +void removeStudent()
        +void editStudent()
        +void findStudent()

        +void saveStudents()
        +Students loadStudents()
    }
    class Student{
        int id
        String name
        int year
        HashMap borrowed_books

        +void borrowBook()
        +void returnBook()

        +void saveStudent()
        +Student loadStudent()
    }
    class Books{
        HashMap books

        +void addBook()
        +void removeBook()
        +void editBook()
        +void findBook()

        +void saveBooks()
        +Books loadBooks()
    }
    class Book{
        int isbn
        String name
        String author
        int year

        +void saveBook()
        +Book loadBook()
    }
    class StudentEdit{
        +void editStudent()
    }
    class BookEdit{
        +void editBook()
    }
```

## Stuff
### Wymagania
Przynajmniej siedem zaimplementowanych nietrywialnych klas (Zawierające przynajmniej jedno pole oraz jedną metodę)

### Co się składa na projekt
* Przedstawienie analizy obiektowej za pomocą diagramu klas (UML).
* Analiza obiektowa (pisemnie)
    * spis klas, które implementuje program wraz z jednoakapitowym opisem roli klasy w systemie (może być wygenerowane automatem typu Doxygen)
    * diagram klas (UML)
    * użyte wzorce projektowe
* pliki źródłowe
* wersja skompilowana

### Terminy
* 16 maja - opis projektu, diagram UML (np w Visual Paradigm)
* 20 czerwca - pliki źródłowe, wersja skompilowana, wygenerowana dokumentacja, wskazanie wzorców projektowych

https://www.lucidchart.com/pages/pl/czym-jest-uml-unified-modeling-language