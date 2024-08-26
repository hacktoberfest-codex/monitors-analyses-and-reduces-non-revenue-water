import { Component, inject, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { AccountService } from '../services/account.service';
import { Complaint } from '../model/complaint';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {
  accountService = inject(AccountService);
  complaints: any = []
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  constructor() { }

  ngOnInit(): void {
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

  submitComplaint(form: NgForm) {
    console.log(form.value);
    if (form.valid) {
      // console.log(form);
      this.accountService.registerComplaint(form.value)
        .subscribe({
          next: res => {
            console.log(res);

            this.generateToast("Success", "Complaint submitted successfully");
            form.reset();
            this.complaintStatus();
          },
          error: err => {
            console.log(err);

            const error = err.error;
            this.generateToast(error['title'], error['detail'])
          }
        });
    }
  }

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

  generateToast(heading: string, description: string) {
    this.toastHeading = heading;
    this.toastDescription = description;
    this.toastVisible = true;

    setTimeout(() => {
      this.toastVisible = false;
    }, 5000);

  }

}
