import { Component } from '@angular/core';
/*import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';*/

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Mancala, a product from bol.com';
  private socketUrl = 'http://localhost:8080/kalaha-wsocket';
  //private stompClient;

  constructor () {
  }

}
