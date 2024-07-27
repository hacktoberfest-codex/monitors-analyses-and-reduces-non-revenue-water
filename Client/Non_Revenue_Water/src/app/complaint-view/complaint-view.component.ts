import { Component, OnInit, inject } from '@angular/core';
import { Complaint } from '../model/complaint';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-complaint-view',
  templateUrl: './complaint-view.component.html',
  styleUrls: ['./complaint-view.component.css']
})
export class ComplaintViewComponent implements OnInit {
  accountService = inject(AccountService);
  complaints: any = []

  selectedComplaint: Complaint | null = null;

  toastHeading = ""; toastDescription = ""; toastVisible = false;

  constructor() { }

  ngOnInit(): void {
    this.accountService.getAllComplaints().subscribe({
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

  editComplaint(complaint: Complaint): void {
    this.selectedComplaint = { ...complaint }; // Spread Operator The JavaScript spread operator (...) allows us to quickly copy all or part of an existing array or object into another array or object.
    // console.log(this.selectedComplaint);//Selected one

  }

  updateComplaint(): void {
    if (this.selectedComplaint) {
      // console.log(this.selectedComplaint);//Updated one
      const index = this.complaints.findIndex((c: any) => c.accountId === this.selectedComplaint!.accountId);
      // console.log(index);

      if (index !== -1) {
        this.complaints[index] = this.selectedComplaint;
        console.log(this.selectedComplaint);

        this.accountService.updateComplaint(this.selectedComplaint).subscribe({
          next: res => {
            this.generateToast("Success", "Complaint Updated");
            // if (res === this.selectedComplaint) {
            //   this.generateToast("Success", "Complaint Updated");
            // }
            // else {
            //   this.generateToast("Error", "Index not found");
            // }
          }
        });
        this.selectedComplaint = null;
      }
      else {
        this.generateToast("Error", "Index not found");
      }
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
