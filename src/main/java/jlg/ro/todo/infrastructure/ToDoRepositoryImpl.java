package jlg.ro.todo.infrastructure;

import jlg.ro.todo.model.ToDo;
import jlg.ro.todo.model.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ToDoRepositoryImpl implements ToDoRepository {

    private MongoTemplate mongoTemplate;

    @Override
    public void save(final ToDo toDo) {
        this.mongoTemplate.save(toDo);
    }

    @Override
    public void delete(final String id) {
        final Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        this.mongoTemplate.remove(query, ToDo.class);
    }

    @Override
    public List<ToDo> getAll() {
        return this.mongoTemplate.findAll(ToDo.class);
    }

    @Override
    public ToDo getById(final String id) {
        return this.mongoTemplate.findById(id, ToDo.class);
    }
}
