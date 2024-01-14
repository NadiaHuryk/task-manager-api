import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { TaskGroup } from "../../core/model/task-group";
import { TaskGroupService } from "../../core/service/task-group.service";
import { SharedService } from "../../core/service/shared.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
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
