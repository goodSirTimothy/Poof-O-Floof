import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, ReplaySubject, AsyncSubject } from 'rxjs';
import { UserIpLocInfo, PhotoStreamMetaData } from '../services/models.service';
import { LocationService } from '../services/location.service';

@Injectable({ providedIn: 'root' })

export class PhotoUrlProviderService {
  private TEST_PHOTO_BUNDLE_URL = 'https://jsonplaceholder.typicode.com/photos?albumId=1';
  private PHOTO_BUNDLE_URL = 'http://localhost:8080/Poof-O-Floof/api/location';
  private MAX_PHOTO_STREAM_SIZE = 50;
  private photoBundleSize: number;

  private userIpLocInfo: UserIpLocInfo;

  // private photoStream$ = new ReplaySubject<TestPhotoJSON>(this.MAX_PHOTO_STREAM_SIZE);
  private photoStream$ = new ReplaySubject<AnimalPhotoJSON>(this.MAX_PHOTO_STREAM_SIZE);
  private photoStreamCurrentState = new PhotoStreamMetaData();
  private photoStreamCurrentState$ = new BehaviorSubject<PhotoStreamMetaData>(undefined);

  constructor(
    private http: HttpClient,
    private locService: LocationService
  ) {
    this.photoStreamCurrentState.maxStreamSize = this.MAX_PHOTO_STREAM_SIZE;
    this.requestANewPhotoBundle();
  }

  getMaxPhotoStreamSize() {
    return this.MAX_PHOTO_STREAM_SIZE;
  }

  // requestANewPhotoBundleTest() {
  //   this.http.get<TestPhotoJSON>(this.TEST_PHOTO_BUNDLE_URL)
  //     .subscribe(data => {
  //       this.photoStreamCurrentState.lastPhotoBundleSize = Object.keys(data).length;
  //       this.photoStreamCurrentState$.next(this.photoStreamCurrentState);
  //       this.photoStream$.next(data);
  //     });
  // }

  getUserIpLocInfo() {
    this.locService.getUserIpLocInfo().subscribe(
      (data) => {
        this.userIpLocInfo = data;
        console.log(data);
      }
    );
  }

  requestANewPhotoBundle() {
    this.http.post<AnimalPhotoJSON>(this.PHOTO_BUNDLE_URL, this.userIpLocInfo)
      .subscribe(
        data => {
          this.photoStreamCurrentState.lastPhotoBundleSize = Object.keys(data).length;
          this.photoStreamCurrentState$.next(this.photoStreamCurrentState);
          this.photoStream$.next(data);
        },
        error => {
          console.error(error.error);
        }
      );
  }


  getPhotoStream() {
    return this.photoStream$.asObservable();
  }

  getPhotoStreamCurrentState() {
    return this.photoStreamCurrentState$.asObservable();
  }

}

export interface AnimalPhotoJSON {
  animalId: string;
  photoId: string;
  fullUrl: string;
}

export interface TestPhotoJSON {
  albumId: number;
  id: number;
  title: string;
  url: string;
  thumbnailUrl: string;
}
