import { Component, OnInit } from '@angular/core';
import { testPhotos } from '../testPhotos';
import { PhotoUrlProviderService } from '../services/photo-url-provider.service';
import { ConditionalExpr } from '@angular/compiler';

@Component({
  selector: 'app-main-photo',
  templateUrl: './main-photo.component.html',
  styleUrls: ['./main-photo.component.css']
})

export class MainPhotoComponent implements OnInit {
  Photos = testPhotos;
  photoNum = this.Photos.length;
  photoUrl: string;

  private photoStreamIndexArray: Array<number>;
  private photoDisplayIndex = 0;
  testPhotoUrl: string;

  constructor(private photoUrlProvider: PhotoUrlProviderService) {
    const photoStreamArraySize = this.photoUrlProvider.getMaxPhotoStreamSize();
    this.photoStreamIndexArray = [...Array(photoStreamArraySize).keys()];
    this.shuffle(this.photoStreamIndexArray);
  }

  getNewPhotoUrl() {
    const rndPhotoNum = Math.round(Math.random() * (this.photoNum - 1));
    const photoLargeUrl = this.Photos[rndPhotoNum].large;
    this.photoUrl = photoLargeUrl;
  }

  getTestPhotoUrl() {
    this.photoUrlProvider.getNextPhotoUrl()
      .subscribe(
        data => {
          console.log(data[this.photoStreamIndexArray[this.photoDisplayIndex]]);
          this.testPhotoUrl = data[this.photoStreamIndexArray[this.photoDisplayIndex]].url;
          this.photoDisplayIndex += 1;
        }
      );
  }

  shuffle(arr) {
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [arr[i], arr[j]] = [arr[j], arr[i]];
    }
    return arr;
  }


  ngOnInit() {
    this.getNewPhotoUrl();
    this.getTestPhotoUrl();
  }

}
