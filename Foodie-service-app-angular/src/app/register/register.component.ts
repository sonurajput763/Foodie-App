import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
// import { Router } from '@angular/router';
import { User } from '../model/User';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user:User=new User()
  constructor(private userservice:UserService,private route:Router,private dialog:MatDialog) { }
  file!: File; 
  ngOnInit(): void {
  }
  onChange(event:any) {
    this.file = event.target.files[0];
}
postData(){
  this.userservice.userprofile(this.file).subscribe((data: any)=>{
    alert("user added")
    console.log(data);
    
    

    // console.log("4444"+this.file)
  })
  this.userservice.userResgister(this.user).subscribe((data: any)=>{
    console.log(data);
  })
  this.userservice.authuserRegister(this.user).subscribe((data: any)=>{
    console.log(data)
    //  this.route.navigate(['/login'])
  })
  alert("User has been registered. Go to log in page");
}
close(){
  this.dialog.closeAll();
}

}
