import { Component,inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent {
  accountService = inject(AccountService);
  totalRecord = Array(10);
  modalVisible = false;
  toastHeading = ""; toastDescription = ""; toastVisible = false;

  onDeposit(form: NgForm) {
    if (form.valid) {
      const balance = form.value.balance;
      this.accountService.depositBalance(balance).subscribe({
        next: res => {
          this.generateToast("Success", "Amount deposited");
        },
        error: err => {
          console.log(err);

          const error = err.error;
          this.generateToast(error['title'], error['detail'])
        },
        complete: () => {
          form.reset();
          this.modalVisible = false;
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
