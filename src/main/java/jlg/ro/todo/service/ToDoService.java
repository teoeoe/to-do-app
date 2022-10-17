package jlg.ro.todo.service;

import jlg.ro.todo.model.Category;
import jlg.ro.todo.model.ToDo;
import jlg.ro.todo.model.ToDoRepository;
import jlg.ro.todo.service.DTO.CreateToDoDTO;
import jlg.ro.todo.service.DTO.EditToDoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
@AllArgsConstructor
public class ToDoService {

    private ToDoRepository toDoRepository;

    public void create(final CreateToDoDTO dto){
        final ToDo toDo = mapToDomainCreate(UUID.randomUUID().toString(), dto);
        this.toDoRepository.save(toDo);
    }

    public List<ToDo> getAll(){
        return this.toDoRepository.getAll();
    }

    public List<ToDo> getAllByCriterion(final String criterion){
        final List<ToDo> sortedList = sortTasksByCriterion(criterion,this.toDoRepository.getAll());
        return sortedList;
    }
    public void update(final String id, final EditToDoDTO dto){
        final ToDo toDo = this.toDoRepository.getById(id);
        final ToDo updatedToDO = mapToDomainEdit(id, dto);
        toDo.update(
                updatedToDO.isCompleted(),
                updatedToDO.getTitle(),
                updatedToDO.getStartDateTime(),
                updatedToDO.getEndDateTime(),
                updatedToDO.getCategory()
        );

        this.toDoRepository.save(toDo);
    }
    public void delete(final String id){
        this.toDoRepository.delete(id);
    }

    ///
    public Long getToDoNumber(){
        return this.getAll().stream().count();
    }
    public Long getCompletedToDosNumber(){
        final Long toDoNumber = this.getToDoNumber();
        final Long completedToDoNumber = this
                .getAll()
                .stream()
                .filter(toDo -> toDo.isCompleted() == true)
                .count();
        return this
                .getAll()
                .stream()
                .filter(toDo -> toDo.isCompleted() == true)
                .count();
    }

    public Long getMissingToDosNumber(){
        final Long toDoNumber = this.getToDoNumber();
        final Long completedToDoNumber = this.getCompletedToDosNumber();
        return toDoNumber-completedToDoNumber;
    }


    ///
    public ToDo mapToDomainEdit(final String id, final EditToDoDTO dto){
        final ToDo toDo = new ToDo(
                id,
                dto.isCompleted(),
                dto.getTitle(),
                LocalDateTime.parse(dto.getStartDateTime()),
                LocalDateTime.parse(dto.getEndDateTime()),
                Category.getByLabel(dto.getCategory())
        );
        return toDo;
    }

    public ToDo mapToDomainCreate(final String id, final CreateToDoDTO dto){

        if(LocalDateTime.parse(dto.getEndDateTime()).isBefore(LocalDateTime.parse(dto.getStartDateTime()))){
            System.out.println(LocalDateTime.parse(dto.getEndDateTime()));
            System.out.println(LocalDateTime.parse(dto.getStartDateTime()));

            throw new RuntimeException("Invalid entry dates. Due date can't be before start date");
        }
        final ToDo toDo = new ToDo(
                id,
                dto.getTitle(),
                LocalDateTime.parse(dto.getStartDateTime()),
                LocalDateTime.parse(dto.getEndDateTime()),
                Category.getByLabel(dto.getCategory())
        );
        return toDo;
    }

    public List<ToDo> sortTasksByStartDate(final List<ToDo> taskList){
        final List<ToDo> sortedList = new ArrayList<>(taskList);
        Collections.sort(sortedList, new SortByTitleComparator());
        return sortedList;
    }

    public List<ToDo> sortTasksByCriterion(final String criterion, final List<ToDo> taskList){
        final List<ToDo> sortedList = new ArrayList<>(taskList);

        switch (criterion){
            case "startDate":
                Collections.sort(sortedList, new SortByStartDateComparator());
                break;
            case "endDate":
                Collections.sort(sortedList, new SortByEndDateComparator());
                break;
            case "title":
                Collections.sort(sortedList, new SortByTitleComparator());
                break;
        }
        Collections.sort(sortedList, new SortByCompletedComparator());
        return sortedList;
    }

}

class SortByStartDateComparator implements Comparator<ToDo>{

    @Override
    public int compare(final ToDo o1,final ToDo o2) {
        return o1.getStartDateTime().compareTo(o2.getStartDateTime());
    }

}

class SortByEndDateComparator implements Comparator<ToDo>{

    @Override
    public int compare(final ToDo o1,final ToDo o2) {
        return o1.getEndDateTime().compareTo(o2.getEndDateTime());
    }

}

class SortByTitleComparator implements Comparator<ToDo>{

    @Override
    public int compare(final ToDo o1,final ToDo o2) {
        return o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase());
    }

}

class SortByCompletedComparator implements Comparator<ToDo>{

    @Override
    public int compare(final ToDo o1,final ToDo o2) {
        return Boolean.valueOf(o1.isCompleted()).compareTo(Boolean.valueOf(o2.isCompleted()));
    }
}