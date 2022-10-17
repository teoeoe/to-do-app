package jlg.ro.todo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document("to-do-notes")
@Getter
public class ToDo {

    @Id
    private String id;

    private boolean completed = false;
    private String title;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Category category;

    public ToDo(){}
    public ToDo(final String id,final String title,final LocalDateTime startDateTime,final LocalDateTime endDateTime,final Category category) {
        this.id = id;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.category = category;
    }

    public ToDo(String id, boolean completed, String title, LocalDateTime startDateTime, LocalDateTime endDateTime, Category category) {
        this.id = id;
        this.completed = completed;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.category = category;
    }

    public void update(final boolean isCompleted, final String title, final LocalDateTime startDateTime, final LocalDateTime endDateTime, final Category category) {
        this.completed = isCompleted;
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.category = category;
    }
}
