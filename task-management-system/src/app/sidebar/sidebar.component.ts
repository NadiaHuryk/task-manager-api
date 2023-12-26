import { Component, OnInit } from '@angular/core';
import { TaskGroup } from "../model/taskGroup";
import { TaskGroupService } from "../service/task-group.service";
import { SharedService } from "../service/shared.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  taskGroups: TaskGroup[] = [];
  selectedTaskGroupId?: number;

  constructor(
    private taskGroupService: TaskGroupService,
    private router: Router,
  private sharedService: SharedService
  ) {}

  navigateToCreateTask(): void {
    this.router.navigate(['/create-task']);
  }

  ngOnInit(): void {
    this.taskGroupService.getTaskGroups().subscribe((data: TaskGroup[]) => {
      this.taskGroups = data;
    });
  }

  selectTaskGroup(taskGroup: TaskGroup): void {
    this.selectedTaskGroupId = taskGroup.id;
    this.sharedService.setSelectedTaskGroupId(this.selectedTaskGroupId);
  }
}
