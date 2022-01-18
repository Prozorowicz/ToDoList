package io.github.prozorowicz.todo;

import io.github.prozorowicz.HibernateUtil;

import java.util.List;

    class ToDoRepository {
    List<ToDo> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from ToDo",ToDo.class).list();

        transaction.commit();
        session.close();
        return result;
    }
        ToDo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(ToDo.class,id);
        result.setDONE(!result.getDONE());
        transaction.commit();
        session.close();
        return result;
    }
        ToDo addTodo(ToDo newTodo) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(newTodo);
        transaction.commit();
        session.close();
        return newTodo;
    }
}
