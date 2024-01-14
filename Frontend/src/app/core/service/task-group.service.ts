import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { TaskGroup } from "../model/task-group";
import { Observable } from "rxjs";
import { environment } from "../../ environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TaskGroupService {
  private baseUrl = `${environment.apiUrl}/task-groups`;

  constructor(private http: HttpClient) { }
  getTaskGroups(): Observable<TaskGroup[]> {
    return this.http.get<TaskGroup[]>(this.baseUrl);
  }
}
