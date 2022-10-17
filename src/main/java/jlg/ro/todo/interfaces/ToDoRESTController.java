package jlg.ro.todo.interfaces;

import jlg.ro.todo.model.ToDo;
import jlg.ro.todo.service.DTO.CreateToDoDTO;
import jlg.ro.todo.service.DTO.EditToDoDTO;
import jlg.ro.todo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/to-do")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoRESTController {

    private ToDoService toDoService;

    @PostMapping
    public ResponseEntity<Void> create(final @RequestBody CreateToDoDTO dto){
        this.toDoService.create(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ToDo>> read(){
        return ResponseEntity.ok(this.toDoService.getAll());
    }

    @GetMapping("/{criterion}")
    public ResponseEntity<List<ToDo>> readByCriterion(final @PathVariable String criterion){
        return ResponseEntity.ok(this.toDoService.getAllByCriterion(criterion));
    }

    @GetMapping("/stats/total")
    public ResponseEntity<Long> getTotalToDoNumber(){
        return ResponseEntity.ok(this.toDoService.getToDoNumber());
    }

    @GetMapping("/stats/completed")
    public ResponseEntity<Long> getCompletedToDoNumber(){
        return ResponseEntity.ok(this.toDoService.getCompletedToDosNumber());
    }

    @GetMapping("/stats/missing")
    public ResponseEntity<Long> getMissingToDoNumber(){
        return ResponseEntity.ok(this.toDoService.getMissingToDosNumber());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(final @PathVariable String id, final @RequestBody EditToDoDTO dto){
        this.toDoService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(final @PathVariable String id){
        this.toDoService.delete(id);
        return ResponseEntity.ok().build();
    }

}
