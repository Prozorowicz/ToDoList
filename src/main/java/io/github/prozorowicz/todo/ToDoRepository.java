package io.github.prozorowicz.todo;

import io.github.prozorowicz.HibernateUtil;

import java.util.List;

public class ToDoRepository {
    List<ToDo> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from ToDo",ToDo.class).list();

        transaction.commit();
        session.close();
        return result;
    }
        public ToDo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(ToDo.class,id);
        result.setDONE(!result.getDONE());
        transaction.commit();
        session.close();
        return result;
    }
}
