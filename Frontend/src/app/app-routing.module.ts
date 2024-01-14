import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./modules/login/login.component";
import { RegistrationComponent } from "./modules/registration/registration.component";
import { UserProfileComponent } from "./modules/user-profile/user-profile.component";
import { TaskComponent } from "./modules/task/task.component";
import { TaskListComponent } from "./modules/task-list/task-list.component";
import { TaskEditComponent } from "./modules/task-edit/task-edit.component";


const routes: Routes = [
  { path: 'login', component: LoginComponent, data: { hideHeader: true } },
  { path: 'registration', component: RegistrationComponent, data: { hideHeader: true } },
  { path: 'me', component: UserProfileComponent, data: { hideHeader: false } },
  { path: 'create-task', component: TaskComponent, data: { hideHeader: false } },
  { path: 'all-tasks', component: TaskListComponent, data: { hideHeader: false } },
  { path: 'task/:id/edit', component: TaskEditComponent, data: { hideHeader: false } },
  { path: '', redirectTo: '/registration', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
