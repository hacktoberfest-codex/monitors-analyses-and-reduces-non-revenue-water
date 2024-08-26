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
  xAxisLabel = "Month"
  yAxisLabel = "Revenue"
  legendTitle = "Users"
  cardColor: string = "#232837";
  data1: any = [
    {
      "name": "Outflow",
      "series": [
        {
          "name": "Jan",
          "value": "15000"
        },
        {
          "name": "Feb",
          "value": "12000"
        },
        {
          "name": "Mar",
          "value": "25000"
        },
        {
          "name": "Apr",
          "value": "1000"
        },
        {
          "name": "May",
          "value": "30000"
        },
        {
          "name": "June",
          "value": "40000"
        },
        {
          "name": "July",
          "value": "35000"
        },
        {
          "name": "Aug",
          "value": "10000"
        },
        {
          "name": "Sept",
          "value": "39000"
        },
        {
          "name": "Oct",
          "value": "50000"
        },
        {
          "name": "Nov",
          "value": "20000"
        },
        {
          "name": "Dec",
          "value": "34000"
        }
      ],
      
    },
    {
      "name": "Inflow",
      "series": [
        {
          "name": "Jan",
          "value": "12000"
        },
        {
          "name": "Feb",
          "value": "9000"
        },
        {
          "name": "Mar",
          "value": "19000"
        },
        {
          "name": "Apr",
          "value": "10"
        },
        {
          "name": "May",
          "value": "21000"
        },
        {
          "name": "June",
          "value": "30000"
        },
        {
          "name": "July",
          "value": "34000"
        },
        {
          "name": "Aug",
          "value": "8000"
        },
        {
          "name": "Sept",
          "value": "35000"
        },
        {
          "name": "Oct",
          "value": "45000"
        },
        {
          "name": "Nov",
          "value": "15000"
        },
        {
          "name": "Dec",
          "value": "30000"
        }
      ],
      
    },
    
    
  ]

  ngOnInit(): void {
    console.log('dashboard')
  }

}
