import { Component,inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  router = inject(Router);
  
  onLogout() {
    localStorage.removeItem("account");
    this.router.navigateByUrl("/signin")
  }

}
