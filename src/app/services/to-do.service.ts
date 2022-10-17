import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ToDo } from '../model/to-do.module';

@Injectable({
  providedIn: 'root'
})
export class ToDoService {

  private readonly BASE_URL = "http://localhost:8080/to-do";

  constructor(private httpClient: HttpClient) { }

  getAll(): Observable<ToDo[]>{
    return this.httpClient.get<ToDo[]>(this.BASE_URL);
  }

  getAllByCriterion(criterion:string): Observable<ToDo[]>{
    return this.httpClient.get<ToDo[]>(`${this.BASE_URL}/${criterion}`);
  }

  getTotal(): Observable<number>{
    return this.httpClient.get<number>(`${this.BASE_URL}/stats/total`);
  }

  getMissing(): Observable<number>{
    return this.httpClient.get<number>(`${this.BASE_URL}/stats/missing`);
  }

  getCompleted(): Observable<number>{
    return this.httpClient.get<number>(`${this.BASE_URL}/stats/completed`);
  }

  create(title:string, startDateTime:string, endDateTime:string, category: string): Observable<void>{
    return this.httpClient.post<void>(this.BASE_URL,
      {
        title: title,
        startDateTime:startDateTime,
        endDateTime:endDateTime,
        category:category
      });
  }

  update(id:string, completed: boolean, title:string, startDateTime:string, endDateTime:string, category: string): Observable<void>{
    return this.httpClient.put<void>(`${this.BASE_URL}/update/${id}`,
    {
      completed: completed,
      title: title,
      startDateTime:startDateTime,
      endDateTime:endDateTime,
      category:category
    });
  }

  remove(id:string) : Observable<void>{
    return this.httpClient.delete<void>(`${this.BASE_URL}/delete/${id}`);
  }

}
