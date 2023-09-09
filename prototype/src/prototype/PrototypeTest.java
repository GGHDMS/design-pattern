package prototype;

import java.util.ArrayList;

class Book {
    private String author;
    private String title;

    public Book(String author, String title) {
        this.author = author;
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}

class BookShelf implements Cloneable{
    private ArrayList<Book> shelf;

    public BookShelf() {
        shelf = new ArrayList<>();
    }

    public void addBook(Book book) {
        shelf.add(book);
    }

    public ArrayList<Book> getShelf() {
        return shelf;
    }

    public void setShelf(ArrayList<Book> shelf) {
        this.shelf = shelf;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        BookShelf another = new BookShelf();

        for (Book book : shelf) {
            another.addBook(new Book(book.getAuthor(), book.getTitle()));
        }

        return another;
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "shelf=" + shelf +
                '}';
    }
}


public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {

        BookShelf bookShelf = new BookShelf();

        bookShelf.addBook(new Book("조정래", "태백산맥"));
        bookShelf.addBook(new Book("박완서", "나목"));
        bookShelf.addBook(new Book("박경리", "토지"));

        System.out.println(bookShelf);

        BookShelf another = (BookShelf) bookShelf.clone();

        System.out.println(another);

        bookShelf.getShelf().get(0).setAuthor("조정래");
        bookShelf.getShelf().get(0).setTitle("한강");

        System.out.println(bookShelf);
        System.out.println(another);
    }
}
