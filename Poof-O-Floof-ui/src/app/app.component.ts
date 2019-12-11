import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LocationService } from './services/location.service';
import { UserIpLocInfo } from './services/models.service';
import { Observable } from 'rxjs';
// import { NgxGalleryOptions, NgxGalleryImage, NgxGalleryAnimation } from 'ngx-gallery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'Poof-O-Floof';
  userIpLocInfo: UserIpLocInfo;
 // galleryOptions: NgxGalleryOptions[];
 // galleryImages: NgxGalleryImage[];

  constructor(
    private http: HttpClient,
    private locService: LocationService
  ) { this.locService.getUserIpLocInfo(); }

  ngOnInit(): void {
    this.getUserIpLocInfo();
  /*  this.galleryOptions = [
       {
          width: '600px',
          height: '400px',
          thumbnailsColumns: 4,
          imageAnimation: NgxGalleryAnimation.Slide
      },
         // max-width 800
         {
          breakpoint: 800,
          width: '100%',
          height: '600px',
          imagePercent: 80,
          thumbnailsPercent: 20,
          thumbnailsMargin: 20,
          thumbnailMargin: 20
      },
          // max-width 400
          {
            breakpoint: 400,
            preview: false
        }
    ];

    this.galleryImages = [
      {
          small: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/1/?bust=1575244003&width=100',
          medium: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/1/?bust=1575244003&width=300',
          big: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/1/?bust=1575244003&width=600'
      },
      {
          small: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/2/?bust=1575244003&width=100',
          medium: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/2/?bust=1575244003&width=300',
          big: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728967/2/?bust=1575244003&width=600'
      },
      {
          small: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728966/1/?bust=1575244003&width=100',
          medium: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728966/1/?bust=1575244003&width=300',
          big: 'https://dl5zpyw5k3jeb.cloudfront.net/photos/pets/46728966/1/?bust=1575244003&width=600'
      }
  ];*/

  }

  getUserIpLocInfo() {
    this.locService.getUserIpLocInfo().subscribe(
      (data) => {
        this.userIpLocInfo = data;
      }
    );
  }

}
