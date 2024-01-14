import { Component, OnInit } from '@angular/core';
import { UserResponse } from "../../core/model/user-response";
import { UserService } from "../../core/service/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  displayedUser: UserResponse = {
    id: 0,
    name: '',
    email: '',
  };
  constructor(private userService: UserService) {}
  ngOnInit(): void {
    this.userService.getMe().subscribe(user => {
      this.displayedUser = user;
    });
  }
}
