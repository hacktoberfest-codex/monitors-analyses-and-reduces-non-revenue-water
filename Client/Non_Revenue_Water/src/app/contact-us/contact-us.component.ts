import { Component, inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent {
  accountService = inject(AccountService)
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  
  contactUS(form: NgForm) {
    console.log(form.value);
    this.accountService.contactUserForm(form.value).subscribe({
      next: res => {
        console.log(res);
        this.generateToast("Thank You", "Our Customer Service Executive will contact you soon!! Till then keep exploring our website!!")

      },
      error: err => {
        console.log(err);

        const error = err.error;
        this.generateToast(error['title'], error['detail'])
      }
    });
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
