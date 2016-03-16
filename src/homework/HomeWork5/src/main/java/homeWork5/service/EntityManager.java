package homeWork5.service;

import homeWork5.entity.Author;
import homeWork5.entity.Book;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by marva on 15.03.16.
 */
public class EntityManager {

    EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
    javax.persistence.EntityManager em = emfactory.createEntityManager();

    public void newBook(String bookName, Author author) {
        em.getTransaction().begin();
        checkName(bookName);
        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        em.persist(book);
        em.getTransaction().commit();
    }

    public void newAuthor(String name) {
        em.getTransaction().begin();
        checkName(name);
        Author author = new Author();
        author.setName(name);
        em.persist(author);
        em.getTransaction().commit();
    }

    public Author getAuthor(String searchName) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT a FROM Author a WHERE a.name =:name",Author.class);
        query.setParameter("name", searchName);
        Author a = (Author)query.getSingleResult();
        em.getTransaction().commit();
        return a;
    }

    private void checkName(String name) {
        if(name.isEmpty() || name.equals(null)) {
            throw new IllegalArgumentException("Vigane nimi");
        }
    }

    public void searchAuthorsByName(String searchName) {
        searchName = "%"+searchName+"%";
        searchName = searchName.toLowerCase().replace(' ','%');
        System.out.println(searchName);
        Query query = em.createQuery("SELECT a FROM Author a WHERE LOWER(a.name) LIKE :name",Author.class);
        query.setParameter("name", searchName);
        List<Author> result=query.getResultList();

        if(result.size() ==0) {
            System.out.println("isikuid ei leitud");
        }

        for (Author a:result) {
            System.out.printf("%s\n", a.getName());
        }
    }

    public void searchBooksByName(String searchName) {
        searchName = "%"+searchName+"%".replace(' ','%');
        searchName = searchName.toLowerCase().replace(' ','%');
        System.out.println(searchName);
        Query query = em.createQuery("SELECT b FROM Book b WHERE LOWER(b.name) LIKE :name",Book.class);
        query.setParameter("name", searchName);
        List<Book> result=query.getResultList();

        if(result.size() ==0) {
            System.out.println("isikuid ei leitud");
        }

        for (Book b:result) {
            System.out.printf("%s %s\n", b.getName(), b.getAuthor().getName());
        }
    }

    public void printAuthors() {
        em.getTransaction().begin();
        List<Author> authors = em.createQuery("SELECT a FROM Author a ORDER BY a.id", Author.class).getResultList();
        em.getTransaction().commit();
        for(Author a:authors) {
            System.out.println(a.getName());
        }
    }

    public void printBooks() {
        em.getTransaction().begin();
        List<Book> books = em.createQuery("SELECT b FROM Book b ORDER BY b.id", Book.class).getResultList();
        em.getTransaction().commit();

        for(Book b:books) {
            System.out.printf("%s   %s  %d\n", b.getName(), b.getAuthor().getName(),b.getId());
        }
    }

    public void killConnection() {
        if(em.isOpen()) {
            em.close();
            emfactory.close();
            System.out.println("ühendus suletud");
        }
    }
}
