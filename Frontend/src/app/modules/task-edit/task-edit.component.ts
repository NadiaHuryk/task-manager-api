import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Task } from "../../core/model/task";
import { switchMap } from "rxjs";
import { TaskGroupService } from "../../core/service/task-group.service";
import { TaskGroup } from "../../core/model/task-group";
import { TaskService } from "../../core/service/task.service";

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.scss']
})
export class TaskEditComponent implements OnInit {
  taskId?: number;
  taskGroupName?: string;
  taskGroups: TaskGroup[] = [];
  task: Task = {
    title: '',
    description: '',
    priority: '',
    status: '',
    dueDate: '',
    userId: null,
    taskGroupId: null
  };
  priorityClass?: string;
  statusCircleClass?: string;
  constructor(
    private route: ActivatedRoute,
    private taskGroupService: TaskGroupService,
    private taskService: TaskService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.taskId = +id;
      this.loadTaskGroupsAndDetails();
    } else {
      console.error('Task ID is null');
    }
  }

  loadTaskGroupsAndDetails(): void {
    this.taskGroupService.getTaskGroups().pipe(
      switchMap(groups => {
        this.taskGroups = groups;
        if (this.taskId !== undefined) {
          return this.taskService.getTaskById(this.taskId);
        } else {
          throw new Error('Task ID is undefined');
        }
      })
    ).subscribe(task => {
      this.task = task;
      const matchingGroup = this.taskGroups.find(group => group.id === task.taskGroupId);
      this.taskGroupName = matchingGroup ? matchingGroup.name : 'Unnamed Group';
      this.setPriorityClass(this.task.priority);
      this.setStatusClass(this.task.status);
    }, error => {
      console.error('Error loading task details:', error);
    });
  }

  saveTask(): void {
    if (this.task) {
      this.taskService.updateTask(this.task).subscribe(updatedTask => {
        console.log('Task updated:', updatedTask);
        this.router.navigate(['/all-tasks']);
      });
    }
  }

  setPriorityClass(priority: string) {
    this.priorityClass = `priority-${priority.toLowerCase()}`;
  }

  onChangePriority(value: string) {
    this.setPriorityClass(value);
  }

  setStatusClass(status: string) {
    switch (status) {
      case 'NOT_STARTED':
        this.statusCircleClass = 'status-not-started';
        break;
      case 'IN_PROGRESS':
        this.statusCircleClass = 'status-in-progress';
        break;
      case 'COMPLETED':
        this.statusCircleClass = 'status-completed';
        break;
    }
  }
  onChangeStatus(value: string) {
    this.setStatusClass(value);
  }

  autoResizeTextarea(event: Event): void {
    const textarea = event.target as HTMLTextAreaElement;
    textarea.style.height = 'auto';
    textarea.style.height = `${textarea.scrollHeight}px`;
  }

  clearTextarea(): void {
    this.task.description = '';
  }
}
