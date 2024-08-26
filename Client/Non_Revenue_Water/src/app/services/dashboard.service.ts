import { Injectable, inject } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Dashboard } from '../model/dashboard';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  http = inject(HttpClient);
  BASE_URL = environment.base_url + "/dashboards";


  constructor() { }

  getAdminDashboardDetails() {
    return this.http.get<Dashboard>(this.BASE_URL + "/admin");
  }
}
