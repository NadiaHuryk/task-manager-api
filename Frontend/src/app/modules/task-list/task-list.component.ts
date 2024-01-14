import { Component, OnInit } from '@angular/core';
import { Task } from "../../core/model/task";
import { Router } from "@angular/router";
import { formatStatus } from "../../core/model/status-utils";
import { TaskService } from "../../core/service/task.service";

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss']
})
export class TaskListComponent implements OnInit {
  tasks: Task[] = [];
  currentPage = 1;
  pageSize = 10;
  filteredTasks: Task[] = [];
  searchQuery: string = '';

  constructor(
    private taskService: TaskService,
    private router: Router) {
  }

  ngOnInit() {
    this.loadTasks();
  }

  loadTasks() {
    this.taskService.getTasks().subscribe(
      tasks => {
        this.tasks = tasks.map(task => ({
          ...task,
          status: formatStatus(task.status)
        }));
        this.filteredTasks = this.tasks;
      },
      error => console.error('Error fetching tasks', error)
    );
  }

  onSearchChange(searchValue: string): void {
    this.searchQuery = searchValue;
    this.filterTasks();
  }

  filterTasks(): void {
    if (this.searchQuery) {
      this.filteredTasks = this.tasks.filter(task =>
        task.title.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
        task.description.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    } else {
      this.filteredTasks = this.tasks;
    }
  }

  getStatusClass(status: string): string {
    let cssClass = '';
    switch (status) {
      case 'Not started':
        cssClass = 'status-not-started';
        break;
      case 'In progress':
        cssClass = 'status-in-progress';
        break;
      case 'Completed':
        cssClass = 'status-completed';
        break;
      default:
        cssClass = '';
    }
    return cssClass;
  }

  onEditTask(taskId: number | undefined): void {
    if (taskId === undefined) {
      console.error('Task ID is undefined');
      return;
    }
    this.router.navigate(['/task', taskId, 'edit']);
  }

  deleteTask(taskId: number) {
    this.taskService.deleteTask(taskId).subscribe(
      () => {
        console.log(`Task with ID ${taskId} deleted`);
        this.loadTasks();
      },
      error => {
        console.error('Error deleting task', error);
      }
    );
  }

  onPageChange(page: number) {
    this.currentPage = page;
  }
}
