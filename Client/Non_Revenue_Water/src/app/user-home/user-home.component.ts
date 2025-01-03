import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';
import { UserAccount } from '../model/user_account';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  router = inject(Router);
  accountService = inject(AccountService);
  toastHeading = ""; toastDescription = ""; toastVisible = false;
  account!: UserAccount

  onLogout() {
    localStorage.removeItem("account");
    this.router.navigateByUrl("/login")
  }
  page: any;

  ngOnInit(): void {
    this.page= 'dashboard';
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
