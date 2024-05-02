import { Component,inject } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';
@Component({
  selector: 'app-paybill',
  templateUrl: './paybill.component.html',
  styleUrls: ['./paybill.component.css']
})
export class PaybillComponent {
  accountService = inject(AccountService);
  onDeposit(form: NgForm) {
    if (form.valid) {
      const balance = form.value.balance;
      this.accountService.depositBalance(balance).subscribe({
        // next: res => {
        //   this.generateToast("Success", "Amount deposited");
        // },
        // error: err => {
        //   console.log(err);

        //   const error = err.error;
        //   this.generateToast(error['title'], error['detail'])
        // },
        // complete: () => {
        //   form.reset();
        //   this.modalVisible = false;
        // }
      })
    }
  }
  view: any[] = [1000, 1000];
  showXAxis: boolean = true;
  showYAxis: boolean = true;
  gradient: boolean = true;
  colorScheme: any = { domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5'] };
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

  view1: any[] = [700, 400];

  // options
  showXAxis1 = true;
  showYAxis1 = true;
  gradient1 = false;
  showLegend1 = true;
  showXAxisLabel1 = true;
  xAxisLabel1 = 'Country';
  showYAxisLabel1 = true;
  yAxisLabel1 = 'Population';

  colorScheme1: any = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  data2: any = [
    {
      "name": "Germany",
      "value": 8940000
    },
    {
      "name": "USA",
      "value": 5000000
    },
    {
      "name": "France",
      "value": 7200000
    }
  ];



}
