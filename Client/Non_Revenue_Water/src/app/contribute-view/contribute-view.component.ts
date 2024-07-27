import { Component } from '@angular/core';
export interface Contribution {
  name: string;
  email: string;
  type: string;
  description: string;
  attachment?: string; // URL to attachment, optional
}
@Component({
  selector: 'app-contribute-view',
  templateUrl: './contribute-view.component.html',
  styleUrls: ['./contribute-view.component.css']
})
export class ContributeViewComponent {
  contributions: Contribution[] = [
    {
      name: 'John Doe',
      email: 'john.doe@example.com',
      type: 'Issue Report',
      description: 'There is a significant water leak on Main Street.',
      attachment: 'https://example.com/attachment1.pdf'
    },
    {
      name: 'Jane Smith',
      email: 'jane.smith@example.com',
      type: 'Suggestion',
      description: 'Proposing a new method to monitor water usage.',
      attachment: 'https://example.com/attachment2.pdf'
    },
    // Add more contributions as needed
  ];

  constructor() { }
}
