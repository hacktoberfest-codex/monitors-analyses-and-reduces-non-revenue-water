import { Component, inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { UserAccount } from '../model/user_account';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {
  accountService = inject(AccountService);
  complaints: any = []
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  account!: UserAccount;
  constructor() { }

  // On page load it will call ngOnInit and upadte the complaint status
  ngOnInit(): void {

    // Get the complaint status of the registered complaint
    this.complaintStatus();

    // Get the name and email of the user from the db
    this.getAccountNameAndEmail();
  }

  //Submit Complaint
  submitComplaint(form: NgForm) {
    console.log(form.value);
    if (form.valid) {
      console.log(form);
      this.accountService.registerComplaint(form.value)
        .subscribe({
          next: res => {
            // console.log(res);

            this.generateToast("Success", "Complaint submitted successfully");
            form.reset();
            this.complaintStatus();// call this method to update the newly registered complaint
            this.getAccountNameAndEmail();// call this method to update the name and email of the form 
          },
          error: err => {
            console.log(err);

            const error = err.error;
            this.generateToast(error['title'], error['detail'])
          }
        });
    }
  }

  //Get Complaint Status
  complaintStatus() {
    this.accountService.getComplaintStatus().subscribe({
      next: res => {
        this.complaints = res;
      },
      error: err => {
        console.log(err);

        const error = err.error;
        this.generateToast(error['title'], error['detail'])
      }
    });
  }

  //Get account name and email from db to autofill the name and email filed of the complaint form 
  getAccountNameAndEmail() {
    this.accountService.getCurrentAccount().subscribe({
      next: res => {
        this.account = res;
        // console.log(res);

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
