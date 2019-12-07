import { Component, OnInit } from '@angular/core';
// import { testPhotos } from '../testPhotos';
import { PhotoUrlProviderService } from '../services/photo-url-provider.service';
import { ConditionalExpr } from '@angular/compiler';

@Component({
  selector: 'app-main-photo',
  templateUrl: './main-photo.component.html',
  styleUrls: ['./main-photo.component.css']
})

export class MainPhotoComponent implements OnInit {

  private photoStreamIndexArray: Array<number>;
  private photoDisplayIndex = 0;
  private photoStreamIndex = 0;
  private mainFramePhotoUrl: string;

  constructor(private photoUrlProvider: PhotoUrlProviderService) {
    const photoStreamArraySize = this.photoUrlProvider.getMaxPhotoStreamSize();
    this.photoStreamIndexArray = [...Array(photoStreamArraySize).keys()];
    this.shuffle(this.photoStreamIndexArray);
  }

  ngOnInit() {
    // this.getNewPhotoUrl();
    this.setMainFramePhotoUrl();
  }

  nextRandomPhoto() {
    this.setMainFramePhotoUrl();
    this.photoDisplayIndex += 1;
  }

  setMainFramePhotoUrl() {
    this.photoUrlProvider.getPhotoStream()
      .subscribe(
        data => {
          this.photoStreamIndex = this.photoStreamIndexArray[this.photoDisplayIndex];
          this.mainFramePhotoUrl = data[this.photoStreamIndex].url;
        }
      );
  }

  /**
   * Fisher–Yates shuffle algorithm, O(n) complexity
   * @param arr: Array to be shuffled
   */
  shuffle(arr: Array<any>) {
    for (let i = arr.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [arr[i], arr[j]] = [arr[j], arr[i]];
    }
    return arr;
  }

  // Photos = testPhotos;
  // photoNum = this.Photos.length;
  // photoUrl: string;

  // getNewPhotoUrl() {
  //   const rndPhotoNum = Math.round(Math.random() * (this.photoNum - 1));
  //   const photoLargeUrl = this.Photos[rndPhotoNum].large;
  //   this.photoUrl = photoLargeUrl;
  // }

}
