import { Component, inject, OnInit } from '@angular/core';
import { UserDashboard } from '../model/user_dashboard';
import { DashboardService } from '../services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  revenue = 3000
  complaints = 150
  newConnections = 300
  houseRegistered = 9165
  previousMonthRevenue = 127589
  currentMonthRevenue = 90000
  waterOutflow = 61000
  waterInflow = 60000
  totalLoss = this.waterOutflow - this.waterInflow

  view: any[] = [1000, 1000];
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  colorScheme: any = { domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'] };
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  showYAxisLabel: boolean = true;
  xAxisLabel = "Months"
  yAxisLabel = "Water Usage"
  legendTitle = "Users"
  cardColor: string = "#232837";
  data1: any = [
    {
      "name": "User",
      "series": [
        {
          "name": "Jan",
          "value": "50000"
        },
        {
          "name": "Feb",
          "value": "26000"
        },
        {
          "name": "March",
          "value": "63000"
        },
        {
          "name": "April",
          "value": "60000"
        },
        {
          "name": "May",
          "value": "80000"
        },//test
        {
          "name": "June",
          "value": "100000"
        },
        {
          "name": "July",
          "value": "40000"
        },
        {
          "name": "Aug",
          "value": "90000"
        },
        {
          "name": "Sept",
          "value": "85000"
        },
        {
          "name": "Oct",
          "value": "50000"
        },
        {
          "name": "Nov",
          "value": "90000"
        },
        {
          "name": "Dec",
          "value": "120000"
        }
      ],
    },
  ]

  view1: any[] = [700, 400];

  // options
  showXAxis1 = true;
  showYAxis1 = true;
  gradient1 = false;
  showLegend1 = true;
  showXAxisLabel1 = true;
  xAxisLabel1 = 'Day';
  showYAxisLabel1 = true;
  yAxisLabel1 = 'Water Usage';
  legendTitle1 = "Days"
  colorScheme1: any = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA', '#a8385d', '#7aa3e5', '#E44D25']
  };

  data2: any = [
    {
      "name": "Monday",
      "value": 89400
    },
    {
      "name": "Tuesday",
      "value": 50000
    },
    {
      "name": "Wednesday",
      "value": 72000
    },
    {
      "name": "Thursday",
      "value": 42000
    },
    {
      "name": "Friday",
      "value": 15000
    },
    {
      "name": "Saturday",
      "value": 28000
    },
    {
      "name": "Sunday",
      "value": 62000
    }
  ];

  dashboardService = inject(DashboardService);
  userDashboard!: UserDashboard;
  ngOnInit(): void {
    console.log('dashboard')
    this.dashboardService.getUserDashboardDetails().subscribe({
      next: res => {
        this.userDashboard = res;
      }
    });
  }

}
