package jlg.ro.todo.model;

import java.util.List;

public interface ToDoRepository {

    void save(final ToDo toDo);
    void delete(final String id);
    List<ToDo> getAll();
    ToDo getById(final String id);

}
