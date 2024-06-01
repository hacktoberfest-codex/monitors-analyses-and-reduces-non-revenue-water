import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent {
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
