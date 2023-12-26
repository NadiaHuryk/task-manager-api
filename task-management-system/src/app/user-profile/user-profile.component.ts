import { Component, OnInit } from '@angular/core';
import { UserService } from "../service/user.service";
import { UserResponse } from "../model/user.response";
import { UserRequestDto } from "../model/userRequestDto";
import { Router } from "@angular/router";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  displayedUser: UserResponse = {
    id: 0,
    name: '',
    email: '',
    phone: '',
  }
  editableUser: UserRequestDto = {
    name: '',
    email: '',
    phone: '',
  };
  message: string = '';
  selectedFile: File | null = null;
  profilePictureUrl: string | null = null;

  constructor(
    private userService: UserService,
    private router: Router) {}

  ngOnInit(): void {
    this.getMe();
    this.loadProfilePicture();
  }

  getMe(): void {
    this.userService.getMe().subscribe(user => {
      this.displayedUser = user;
      this.editableUser = { ...user };
    });
  }

  updateUser(): void {
    this.userService.updateUser(this.editableUser).subscribe(updatedUser => {
      this.displayedUser = updatedUser;
      this.editableUser = { ...updatedUser };
      this.message = 'Profile updated successfully!';
      setTimeout(() => this.message = '', 3000);
    }, error => {
      this.message = 'Failed to update profile.';
      setTimeout(() => this.message = '', 3000);
    });
  }

  deleteAccount(): void {
    if (confirm('Are you sure you want to delete your account? This cannot be undone.')) {
      this.userService.deleteUser().subscribe(() => {
        this.router.navigate(['/registration']);
      }, error => {
        this.message = 'Failed to delete account.';
        setTimeout(() => this.message = '', 3000);
      });
    }
  }

  onFileSelected(event: Event): void {
    const element = event.target as HTMLInputElement;
    let files = element.files;
    if (files) {
      this.selectedFile = files[0];
      const uploadData = new FormData();
      uploadData.append('image', this.selectedFile, this.selectedFile.name);

      this.userService.uploadProfilePicture(uploadData).subscribe(
        response => {
          console.log(response);
          this.message = 'Profile picture updated successfully!';
          this.loadProfilePicture();
          setTimeout(() => this.message = '', 3000);
        },
        error => {
          console.error(error);
          this.message = 'Failed to upload profile picture.';
          setTimeout(() => this.message = '', 3000);
        }
      );
    }
  }

  loadProfilePicture(): void {
    this.userService.getProfilePicture().subscribe(blob => {
      const reader = new FileReader();
      reader.readAsDataURL(blob);
      reader.onloadend = () => {
        this.profilePictureUrl = reader.result as string;
      };
    });
  }
}
