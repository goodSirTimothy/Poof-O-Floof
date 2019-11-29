import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
// import { NgxGalleryModule } from 'ngx-gallery';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    // NgxGalleryModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
