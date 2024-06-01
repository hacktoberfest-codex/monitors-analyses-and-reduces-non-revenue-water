import { Component, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-admin-signup',
  templateUrl: './admin-signup.component.html',
  styleUrls: ['./admin-signup.component.css']
})
export class AdminSignupComponent {
  accountService = inject(AccountService);
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  onSubmit(form: NgForm) {
    if (form.valid) {
      // console.log(form);
      this.accountService.createAccountAdmin(form.value)
        .subscribe({
          next: res => {
            console.log(res);

            this.generateToast("Success", "Account created");
            form.reset();
          },
          error: err => {
            console.log(err);

            const error = err.error;
            this.generateToast(error['title'], error['detail'])
          }
        });
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
