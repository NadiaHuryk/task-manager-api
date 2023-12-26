import { Injectable } from '@angular/core';
import { BehaviorSubject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private selectedTaskGroupIdSource = new BehaviorSubject<number | null>(null);
  selectedTaskGroupId$ = this.selectedTaskGroupIdSource.asObservable();

  setSelectedTaskGroupId(taskGroupId: number): void {
    this.selectedTaskGroupIdSource.next(taskGroupId);
  }
}
