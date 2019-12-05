import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, ReplaySubject, AsyncSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })

export class PhotoUrlProviderService {
  private TEST_PHOTO_BUNDLE_URL = 'https://jsonplaceholder.typicode.com/photos?albumId=1';
  private PHOTO_BUNDLE_URL = 'http://poof-o-floof/api';
  private MAX_PHOTO_STREAM_SIZE = 50;
  private photoStream$ = new ReplaySubject<TestPhotoJSON>(this.MAX_PHOTO_STREAM_SIZE);

  constructor(private http: HttpClient) {
    this.getPhotoBundle();
  }

  getMaxPhotoStreamSize() {
    return this.MAX_PHOTO_STREAM_SIZE;
  }

  getPhotoBundle() {
    this.http.get<TestPhotoJSON>(this.TEST_PHOTO_BUNDLE_URL)
      .subscribe(data => {
        this.photoStream$.next(data);
      });
  }

  getNextPhotoUrl() {
    return this.photoStream$.asObservable();
  }

}


export interface PhotoBundleJSON {
  animalId: string;
}

export interface TestPhotoJSON {
  albumId: number;
  id: number;
  title: string;
  url: string;
  thumbnailUrl: string;
}
