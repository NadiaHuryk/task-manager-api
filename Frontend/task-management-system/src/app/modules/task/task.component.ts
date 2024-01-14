import { Component, OnInit } from '@angular/core';
import { Task } from "../../core/model/task";
import { Router } from "@angular/router";
import { TaskService } from "../../core/service/task.service";
import { SharedService } from "../../core/service/shared.service";
import { UserService } from "../../core/service/user.service";
import ClassicEditor from 'src/ckeditor5/build/ckeditor';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent implements OnInit {
  public Editor = ClassicEditor;
  newTask: Task = {
    title: '',
    description: '',
    priority: 'HIGH',
    status: 'NOT_STARTED',
    dueDate: '',
    userId: null,
    taskGroupId: null
  };

  priorityClass?: string;
  statusCircleClass?: string;

  constructor(
    private taskService: TaskService,
    private sharedService: SharedService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.sharedService.selectedTaskGroupId$.subscribe((taskGroupId) => {
      this.newTask.taskGroupId = taskGroupId;
    });

    this.userService.getMe().subscribe((user) => {
      this.newTask.userId = user.id;
    });

    this.setPriorityClass(this.newTask.priority);
    this.setStatusClass(this.newTask.status);
  }

  createTask(): void {
    if (this.newTask.description) {
      this.newTask.description = this.stripHtmlTags(this.newTask.description);
    }

    this.taskService.createTask(this.newTask).subscribe({
      next: (task) => {
        console.log('Task created:', task);
        this.router.navigate(['/all-tasks']);
      },
      error: (error) => {
        console.error('Error creating task:', error);
      }
    });
  }

  stripHtmlTags(html: string): string {
    return html.replace(/<[^>]*>/g, '');
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

  clearEditor() {
    this.newTask.description = '';
  }
}


