import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LogInComponent } from '../log-in/log-in.component';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private dialog:MatDialog) { }
image="/home/ubuntu/Pictures/FoodImages/3.jpg";
  ngOnInit(): void {
  }
  logindailog() {
    const dialogRef = this.dialog.open(LogInComponent);
    dialogRef.afterClosed().subscribe((result:any) => {
      console.log(result);
    });
  }
  registerdialog(){
    const dialogRef = this.dialog.open(RegisterComponent);
    dialogRef.afterClosed().subscribe((result:any) => {
      console.log(result);
    });

  }
}
function LoginComponent(LoginComponent: any) {
  throw new Error('Function not implemented.');
}

