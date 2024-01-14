import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CKEditorModule } from "@ckeditor/ckeditor5-angular";
import { NgxPaginationModule } from "ngx-pagination";
import { HeaderComponent } from "./shared/header/header.component";
import { TaskEditComponent } from "./modules/task-edit/task-edit.component";
import { TaskListComponent } from "./modules/task-list/task-list.component";
import { TaskComponent } from "./modules/task/task.component";
import { SidebarComponent } from "./shared/sidebar/sidebar.component";
import { AuthInterceptorService } from "./core/service/auth-interceptor.service";
import { UserProfileComponent } from "./modules/user-profile/user-profile.component";
import { RegistrationComponent } from "./modules/registration/registration.component";
import { LoginComponent } from "./modules/login/login.component";


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    UserProfileComponent,
    SidebarComponent,
    TaskComponent,
    HeaderComponent,
    TaskListComponent,
    TaskEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CKEditorModule,
    NgxPaginationModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
