import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})
export class UserHomeComponent {
  router = inject(Router);
  onLogout() {
    localStorage.removeItem("account");
    this.router.navigateByUrl("/signup")
  }
  page: any;

  ngOnInit(): void {
    this.page= 'dashboard';
  }

  tabChange(page: any) {
    this.page = page;
  }
}
