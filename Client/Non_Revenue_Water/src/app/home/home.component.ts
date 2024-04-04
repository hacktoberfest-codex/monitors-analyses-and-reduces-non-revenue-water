import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  items = [
    {name: 'Action', url: 'app/aaa'},
    {name: 'Another Action', url: 'app/bbb'},
     {name: 'Something else here', url: 'app/ccc'}
    ];

}
