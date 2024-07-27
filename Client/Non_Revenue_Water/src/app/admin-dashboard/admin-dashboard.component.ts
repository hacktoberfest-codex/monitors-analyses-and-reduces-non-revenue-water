import { Component, OnInit, inject } from '@angular/core';
import { Dashboard } from '../model/dashboard';
import { DashboardService } from '../services/dashboard.service';
import { Transaction } from '../model/transaction';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  waterOutflow = 61000
  waterInflow = 60000
  totalLoss = this.waterOutflow - this.waterInflow
  revenueByLocation: any = [];
  view: any[] = [1000, 1000];
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  colorScheme: any = { domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'] };
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  showYAxisLabel: boolean = true;
  xAxisLabel = "Month"
  yAxisLabel = "Revenue"
  legendTitle = "Flow"
  legendTitleLocation = "Location"
  cardColor: string = "#232837";
  revenueByMonth: any = []
  waterflow: any = []

  dashboardService = inject(DashboardService);
  dashboard!: Dashboard;
  ngOnInit(): void {
    this.dashboardService.getAdminDashboardDetails().subscribe({
      next: res => {
        this.dashboard = res;
        // console.log(res.transactions);
        this.revenueByMonth = res.transactions
        this.revenueByLocation = res.revenueByLocations
        this.waterflow = res.waterFlows

      }
    });
  }
}
