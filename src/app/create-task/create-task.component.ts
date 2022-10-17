import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToDoService } from '../services/to-do.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {

  createTaskFormGroup: FormGroup;

  showMessage: boolean = false;
  showErrorMessage: boolean = false;

  constructor(private formBuilder: FormBuilder, private toDoService: ToDoService) { 
    this.createTaskFormGroup = this.formBuilder.group({
      titleControl: this.formBuilder.control("",[Validators.required]),
      startDateControl: this.formBuilder.control("",[Validators.required]),
      endDateControl: this.formBuilder.control("",[Validators.required]),
      categoryControl: this.formBuilder.control("",[Validators.required])
    })
  }

  ngOnInit(): void {
  }

  onSubmit(){

    if(this.createTaskFormGroup.valid){
      const title = this.createTaskFormGroup.value.titleControl;
      const startDate = this.createTaskFormGroup.value.startDateControl;
      const endDate = this.createTaskFormGroup.value.endDateControl;
      const category = this.createTaskFormGroup.value.categoryControl;
  
      this.showErrorMessage = false;
      this.toDoService.create(title,startDate,endDate,category).subscribe();
    }else{
      this.showErrorMessage = true;
    }

    this.showMessage = this.createTaskFormGroup.touched;
  
  }

}
