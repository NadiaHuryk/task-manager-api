import { Component, OnInit } from '@angular/core';
import { UserService } from "../service/user.service";
import { UserResponse } from '../model/user.response';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
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
