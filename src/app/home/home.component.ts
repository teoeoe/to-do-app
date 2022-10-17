import { Component, OnInit } from '@angular/core';
import { ToDo } from '../model/to-do.module';
import { ToDoService } from '../services/to-do.service';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  styles: [
    `
      .activeClass { background-color: white }
      .inactiveClass { background-color: rgba(207, 167, 189, 0.523) }
    `
  ]
})
export class HomeComponent implements OnInit {

  toDos: ToDo[] =[];
  isTaskCompleted : boolean = false;
  editFormGroup: FormGroup;
  sortingMethodsArray: string[] = ['startDate', 'endDate', 'title'];
  sortingMethod: string = "sortBy";

  constructor(private toDoService: ToDoService, private modalService : NgbModal, private formBuilder: FormBuilder) {
    this.editFormGroup = this.formBuilder.group({
      idControl: this.formBuilder.control(""),
      editCompletedControl: this.formBuilder.control(""),
      editTitleControl: this.formBuilder.control("",[Validators.required]),
      editStartDateControl: this.formBuilder.control("",[Validators.required]),
      editEndDateControl: this.formBuilder.control("",[Validators.required]),
      editCategoryControl: this.formBuilder.control("",[Validators.required])
    })
   }

  ngOnInit(): void {
    this.initData();
  }

  // private initData(){
  //   this.toDoService.getAll().subscribe((response)=> this.toDos = response);
  // }

  initData(){
    this.toDoService.getAllByCriterion(this.sortingMethod).subscribe((response)=> this.toDos = response);
  }

  openEdit(content:any, toDo : ToDo){
    this.modalService.open(content, {
      backdropClass: 'light-blue-backdrop',
      size: "lg"
    });


    this.editFormGroup.patchValue({
      idControl: toDo.id,
      editCompletedControl: toDo.completed,
      editTitleControl: toDo.title,
      editStartDateControl: toDo.startDateTime,
      editEndDateControl: toDo.endDateTime,
      editCategoryControl: toDo.category.toLowerCase()
    })
  }

  onSubmitChanges(){

    const id = this.editFormGroup.value.idControl;
    const completed = this.editFormGroup.value.editCompletedControl;
    const title= this.editFormGroup.value.editTitleControl;
    const startDate = this.editFormGroup.value.editStartDateControl;
    const endDate = this.editFormGroup.value.editEndDateControl;
    const category = this.editFormGroup.value.editCategoryControl;

    this.toDoService.update(id,completed,title,startDate,endDate,category).subscribe(
      () => this.initData()
    )
  }

  changeTaskCompletion(toDo: ToDo){

    const id = toDo.id;
    const completed = !toDo.completed;
    const title= toDo.title;
    const startDate = toDo.startDateTime;
    const endDate = toDo.endDateTime;
    const categ = toDo.category.toLowerCase();

    this.toDoService.update(id,completed,title,startDate,endDate,categ).subscribe(
      () => this.initData()
    )
  }

  removeTask(id:string) : void{
    this.toDoService.remove(id).subscribe(
      () => this.initData() 
    )
  }

}
