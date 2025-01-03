import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { UserAccount } from '../model/user_account';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
  router = inject(Router);
  accountService = inject(AccountService);
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  account!: UserAccount
  
  onLogout() {
    localStorage.removeItem("token");
    this.router.navigateByUrl("/adminlogin")
  }
  page: any;

  ngOnInit(): void {
    this.page = 'dashboard';
    this.accountService.getCurrentAccount().subscribe({
      next: res => {
        this.account = res;
      },
      error: err => {
        console.log(err);

        const error = err.error;
        this.generateToast(error['title'], error['detail'])
      }
    })
  }

  generateToast(heading: string, description: string) {
    this.toastHeading = heading;
    this.toastDescription = description;
    this.toastVisible = true;

    setTimeout(() => {
      this.toastVisible = false;
    }, 5000);

  }

  tabChange(page: any) {
    this.page = page;
  }
}
