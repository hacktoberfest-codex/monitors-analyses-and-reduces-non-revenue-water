import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
interface Contribution {
  name: string;
  email: string;
  type: string;
  description: string;
  attachment?: File;
}
@Component({
  selector: 'app-contribute',
  templateUrl: './contribute.component.html',
  styleUrls: ['./contribute.component.css']
})
export class ContributeComponent implements OnInit {
  contribution: Contribution = {
    name: '',
    email: '',
    type: 'Information',
    description: ''
  };

  constructor() { }

  ngOnInit(): void { }

  submitContribution(from: NgForm): void {
    alert('Contribution submitted successfully!');
    console.log(this.contribution);
    // Here you would typically send the contribution data to a server
    this.resetForm();
  }

  onFileSelected(event: any): void {
    const file: File = event.target.files[0];
    this.contribution.attachment = file;
  }

  resetForm(): void {
    this.contribution = {
      name: '',
      email: '',
      type: 'Information',
      description: ''
    };
  }

}
