package io.github.prozorowicz.todo;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ToDo", urlPatterns = {"/api/todos/*"})
public class ToDoServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(ToDoServlet.class);

    private ToDoRepository repository;
    private ObjectMapper mapper;
    /**
     * Servlet container needs it.
     */
    @SuppressWarnings("unused")
    public ToDoServlet(){
        this(new ToDoRepository(), new ObjectMapper());
    }
    ToDoServlet(ToDoRepository repository, ObjectMapper mapper){
        this.repository=repository;
        this.mapper = mapper;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Got request with parameters " + req.getParameterMap());
        repository.findAll();
        resp.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(resp.getOutputStream(), repository.findAll());
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer toDoId = null;
        try {
            toDoId = Integer.valueOf(req.getPathInfo().substring(1));
            resp.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(resp.getOutputStream(),repository.toggleTodo(toDoId));
        } catch (NumberFormatException e) {
            logger.warn("Non-numeric ToDo id used: " + toDoId);
        }

    }
}
