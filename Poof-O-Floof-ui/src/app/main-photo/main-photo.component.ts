import { Component, OnInit } from '@angular/core';
import { PhotoUrlProviderService } from '../services/photo-url-provider.service';
import { PhotoStreamMetaData } from '../services/models.service';
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
  private psCurrentState: PhotoStreamMetaData;
<<<<<<< HEAD
=======

>>>>>>> 79aa4852fc2d5f1132d77d9562868af50f51f06b
  constructor(private photoUrlProvider: PhotoUrlProviderService) {
    this.psCurrentState = new PhotoStreamMetaData();
    const photoStreamArraySize = this.photoUrlProvider.getMaxPhotoStreamSize();
    this.photoStreamIndexArray = [...Array(photoStreamArraySize).keys()];
    this.shuffle(this.photoStreamIndexArray);
  }
<<<<<<< HEAD
=======
  
>>>>>>> 79aa4852fc2d5f1132d77d9562868af50f51f06b
  ngOnInit() {
    this.setPhotoStreamCurrentState();
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

  setPhotoStreamCurrentState() {
    this.photoUrlProvider.getPhotoStreamCurrentState()
      .subscribe(
        data => {
          this.psCurrentState = data;
        }
      );
  }

<<<<<<< HEAD
  setPhotoStreamCurrentState() {
    this.photoUrlProvider.photoStreamCurrentState$()
      .subscribe(
        data => {
          this.psCurrentState = data;
        }
      );
  }
=======
  
>>>>>>> 79aa4852fc2d5f1132d77d9562868af50f51f06b
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
<<<<<<< HEAD
=======

  
>>>>>>> 79aa4852fc2d5f1132d77d9562868af50f51f06b
}
