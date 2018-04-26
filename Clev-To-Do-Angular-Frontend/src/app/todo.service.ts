import { Injectable } from '@angular/core';
import { Todo } from './todo';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

/**
 * Created by ashish.sethi on 04/26/18.
 */
 
@Injectable()
export class TodoService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: Http) { }

  getTodos():  Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/rest/clevtodos/')
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }
  
  completedTodos():  Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/rest/clevtodos/completed')
      .toPromise()
	  .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }
  
  pendingTodos():  Promise<Todo[]> {
    return this.http.get(this.baseUrl + '/rest/clevtodos/pending')
      .toPromise()
      .then(response => response.json() as Todo[])
      .catch(this.handleError);
  }

  createTodo(todoData: Todo): Promise<Todo> {
    return this.http.post(this.baseUrl + '/rest/clevtodos/', todoData)
      .toPromise().then(response => response.json() as Todo)
      .catch(this.handleError);
  }
  

  updateTodo(todoData: Todo): Promise<Todo> {
    return this.http.put(this.baseUrl + '/rest/clevtodos/' + todoData.id, todoData)
      .toPromise()
      .then(response => response.json() as Todo)
      .catch(this.handleError);
  }

  deleteTodo(id: string): Promise<any> {
    return this.http.delete(this.baseUrl + '/rest/clevtodos/' + id)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('Some error occured', error);
    return Promise.reject(error.message || error);
  }
}