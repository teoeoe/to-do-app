package jlg.ro.todo.service.DTO;

import lombok.Getter;

@Getter
public class CreateToDoDTO {

    private String title;
    private String startDateTime;
    private String endDateTime;
    private String category;
}