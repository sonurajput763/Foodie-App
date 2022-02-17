import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
// import { Router } from '@angular/router';
import { UserAuth } from '../model/UserAuth';
import { UserService } from '../user.service';


@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  userauth: UserAuth = new UserAuth();
  error: boolean = false;
  constructor(private userservice: UserService, private route: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
  }

  login() {
    this.userservice.login(this.userauth).subscribe((data: any) => {
      this.userservice.saveEmail(this.userauth.email);
      console.log(data)
    }
    )
    this.userservice.getAccountType(this.userauth).subscribe((data: any) => {
      console.log(data)
      if (data == "customer") {
        this.route.navigate(['/main']);
      }
      else if (data == "vendor") {
        this.route.navigate(['/vendor']);
      }
  
    }
    ,
      (err) => {this.error=true;

      }
    )
  }
  LoginCut() {
    this.dialog.closeAll()
  }

}




