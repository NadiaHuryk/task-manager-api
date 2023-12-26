import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistrationComponent } from "./registration/registration.component";
import { LoginComponent } from "./login/login.component";
import { UserProfileComponent } from "./user-profile/user-profile.component";
import { TaskComponent } from "./task/task.component";
import { TaskListComponent } from "./task-list/task-list.component";
import { TaskEditComponent } from "./task-edit/task-edit.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'me', component: UserProfileComponent },
  { path: 'create-task', component: TaskComponent },
  { path: 'all-tasks', component: TaskListComponent },
  { path: 'task/:id/edit', component: TaskEditComponent },
  { path: '', redirectTo: '/registration', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
