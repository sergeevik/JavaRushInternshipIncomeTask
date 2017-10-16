package main.java.sergeevVA.dao;

import main.java.sergeevVA.model.Book;
import main.java.sergeevVA.model.FindBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BookDaoImp implements BookDao{



    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Book> findAll(){
        List<Book> from_books = sessionFactory.getCurrentSession().createQuery("from Book").list();
        return from_books;
    }

    @Override
    @Transactional
    public Book findById(int id){
        return sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    @Transactional
    public void removeBookById(int id){
        Session currentSession = sessionFactory.getCurrentSession();
        Book load = currentSession.load(Book.class, id);

        if (load != null){
            currentSession.delete(load);
        }
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(book);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Book> findBooksByTemplate(FindBook template) {
        String query = createQueryFrom(template);

        Session currentSession = sessionFactory.getCurrentSession();
        Query sessionQuery = currentSession.createQuery(query);
        validateQueryParam(sessionQuery, template);

        return (List<Book>)sessionQuery.getResultList();

    }

    private String createQueryFrom(FindBook template) {
        StringBuilder query = new StringBuilder("FROM Book as b");

        if (template == null)
            return query.toString();

        if (nextParamExist("", template))
            query.append(" where ");


        if (!template.getAuthor().isEmpty()){
            query.append("b.author = :author");
            if (nextParamExist("author", template))
                query.append(" and ");
        }

        if (!template.getTitle().isEmpty()){
            query.append("b.title = :title");
            if (nextParamExist("title", template))
                query.append(" and ");
        }

        if (template.getIsbn() > 0){
            query.append("b.isbn = :isbn");
            if (nextParamExist("isbn", template))
                query.append(" and ");
        }

        if (template.getYearAfter() > 0){
            query.append("b.printYear < :afterYear");
            if (nextParamExist("afterYear", template))
                query.append(" and ");
        }

        if (template.getYearBefore() > 0){
            query.append("b.printYear > :beforeYear");
            if (nextParamExist("beforeYear", template))
                query.append(" and ");
        }

        if (template.getReadAlready() == 1 || template.getReadAlready() == -1)
            query.append("b.readAlready = :readAlready");
        return query.toString();
    }

    private void validateQueryParam(Query sessionQuery, FindBook template) {
        if (template == null)
            return;

        if (!template.getAuthor().isEmpty()){
            sessionQuery.setParameter("author", template.getAuthor());
        }

        if (!template.getTitle().isEmpty()){
            sessionQuery.setParameter("title", template.getTitle());
        }

        if (template.getIsbn() > 0){
            sessionQuery.setParameter("isbn", template.getIsbn());
        }

        if (template.getYearAfter() > 0){
            sessionQuery.setParameter("afterYear", template.getYearAfter());
        }

        if (template.getYearBefore() > 0){
            sessionQuery.setParameter("beforeYear", template.getYearBefore());
        }

        if (template.getReadAlready() == 1){
            sessionQuery.setParameter("readAlready", true);
        }else if (template.getReadAlready() == -1){
            sessionQuery.setParameter("readAlready", false);
        }
    }

    /**
     * страшный метод проверки есть ли ещё параметры, нужен для формирования HQL запроса
     *
     * отвечает на вопрос надо ли писать "and" или"where"
     *
     * @param nameParam - имя параметра в шаблоне. Будут проверены все поля после этого.
     * @param tamplate - шаблон поиска
     * @return true - если есть не путой параметр после "nameParam"
     *         false - если все следующий параметры пустые
     */
    private boolean nextParamExist(String nameParam, FindBook tamplate){
        if (nameParam.isEmpty()){
            //если хоть один их параметров не пустой вернем true иначе false
            if (!tamplate.getAuthor().isEmpty() || !tamplate.getTitle().isEmpty()
                    || tamplate.getIsbn() > 0 || tamplate.getYearAfter() > 0
                    || tamplate.getYearBefore() > 0 || tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }else if (nameParam.equals("author")){
            if (!tamplate.getTitle().isEmpty() || tamplate.getIsbn() > 0 || tamplate.getYearAfter() > 0 ||
                    tamplate.getYearBefore() > 0 || tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }else if (nameParam.equals("title")){

            if (tamplate.getIsbn() > 0 || tamplate.getYearAfter() > 0 ||
                    tamplate.getYearBefore() > 0 || tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }else if (nameParam.equals("isbn")){

            if (tamplate.getYearAfter() > 0 || tamplate.getYearBefore() > 0 || tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }else if (nameParam.equals("afterYear")){

            if (tamplate.getYearBefore() > 0 || tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }else if (nameParam.equals("beforeYear")){

            if (tamplate.getReadAlready() != 0){
                return true;
            }else return false;

        }

        return false;
    }
}
