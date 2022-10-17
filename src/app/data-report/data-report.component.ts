import { Component, OnInit } from '@angular/core';
import { ToDoService } from '../services/to-do.service';

@Component({
  selector: 'app-data-report',
  templateUrl: './data-report.component.html',
  styleUrls: ['./data-report.component.css']
})
export class DataReportComponent implements OnInit {

  totalToDoNumber : number = 0;
  completedToDoNumber : number = 0;
  missingToDoNumber : number = 0;
  dynamicWidth: string = "75%";

  constructor(private toDoService: ToDoService) {}

  ngOnInit(): void {
    this.toDoService.getTotal().subscribe((response) => this.totalToDoNumber = response);
    this.toDoService.getCompleted().subscribe((response)=> this.completedToDoNumber = response);
    this.toDoService.getMissing().subscribe((response)=> this.missingToDoNumber = response);
    
  }

  initialiseWidth() : string{
    return this.dynamicWidth = `${Math.round(this.completedToDoNumber*100/this.totalToDoNumber)}`+"%";
  }



}
