import { Component, OnInit } from '@angular/core';
import { ProfileService } from "../shared/services/profile.service";
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profile-page',
  standalone: false,
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.scss']
})
export class ProfilePageComponent implements OnInit {
  profileData: any = {};

    constructor(
        private profileService: ProfileService,
        private route: ActivatedRoute,
        private router: Router
    ) {}

  ngOnInit(): void {
    // Subscribe to route changes
    this.route.paramMap.subscribe(params => {
      const username = params.get('username');
      console.log('Fetching data for:', username);

      if (username) {
        // Fetch the data from the backend
        this.profileService.getAllProfiles().subscribe({
          next: (data: any[]) => {
            this.profileData = data.find(user => user.username === username);

            if (this.profileData) {
              console.log('User Found:', this.profileData);
            } else {
              console.warn('User not found!');
            }
          },
          error: (err) => {
            console.error('Error fetching profile data:', err);
          },
          complete: () => {
            console.log('Data fetch complete');
          }
        });
      }
    });
  }

}
