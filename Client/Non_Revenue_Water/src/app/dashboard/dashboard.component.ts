import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  revenue=3000
  complaints=150
  newConnections=300
  houseRegistered=9165
  previousMonthRevenue=127589
  currentMonthRevenue=90000
  waterOutflow=61000
  waterInflow=60000
  totalLoss=this.waterOutflow-this.waterInflow
  data = [
    {
      "name": "France",
      "value": 36745,
      
    },
    {
      "name": "Spain",
      "value": 33000,
      
    },
    {
      "name": "Tanzania",
      "value": 25589
    },
    {
      "name": "Turkmenistan",
      "value": 10354
    },
    {
      "name": "Guam",
      "value": 57436
    },
    {
      "name": "Bermuda",
      "value": 35577
    }
  ];
  view: any[] = [1000, 1000];
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  colorScheme: any = { domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5','#a8385d','#aae3f5'] };
  showLegend: boolean = true;
  showXAxisLabel: boolean = true;
  showYAxisLabel: boolean = true;
  xAxisLabel = "pagewise"
  yAxisLabel = "Users"
  legendTitle = "Users"
  cardColor: string = "#232837";
  data1: any = [
    {
      "name": "Karthikeyan",
      "series": [
        {
          "name": "2016",
          "value": "15000"
        },
        {
          "name": "2017",
          "value": "12000"
        },
        {
          "name": "2018",
          "value": "25000"
        },
        {
          "name": "2019",
          "value": "1000"
        },
        {
          "name": "2020",
          "value": "30000"
        }
      ],
    },
  ]

}
