import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateTaskComponent } from './create-task/create-task.component';
import { DataReportComponent } from './data-report/data-report.component';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {path:"home", component: HomeComponent},
  {path: "create-task", component: CreateTaskComponent},
  {path: "data-report", component: DataReportComponent},
  {path: "**", redirectTo:"/home"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
