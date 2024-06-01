import { Component, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  accountService = inject(AccountService);
  router = inject(Router);
  toastHeading = ""; toastDescription = ""; toastVisible = false;

  onLogin(form: NgForm) {
    console.log(form.value);
    if (form.valid) {
      this.accountService.loginAccount(form.value).subscribe({
        next: res => {
          form.reset();
          const response = res as any;
          localStorage.setItem("token", response.token);

          this.router.navigate(["userhome"])
        },
        error: err => {
          this.generateToast("Failure", "Unauthorized access")
        }
      })
    }

  }

  generateToast(heading: string, description: string) {
    this.toastHeading = heading;
    this.toastDescription = description;
    this.toastVisible = true;

    setTimeout(() => {
      this.toastVisible = false;
    }, 5000);

  }

}
