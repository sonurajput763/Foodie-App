import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/User';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private userservice:UserService) { }
user!:User;
image:any;
  ngOnInit(): void {
    this.userservice.getuserByEmail(this.userservice.userEmail).subscribe(data=>{
      this.user=data
     this.userservice.getUserImage(this.user.profileImage).subscribe(x=>{
      this.image = x.url;
    }
    );
  })
}

}
