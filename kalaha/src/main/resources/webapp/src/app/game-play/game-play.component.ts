import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';
import { GameService } from '../games/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription, of } from 'rxjs';
import { WebSocketService } from '../ws/web-socket.service';
import { Message } from '@stomp/stompjs';

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css'],
  viewProviders: [WebSocketService]

})
export class GamePlayComponent implements OnInit {
  gameBoard: any = {};
  game: any = {};
  board: any = {}
  sub: Subscription;
  uiData : any = {};
  private datasubscription: Subscription;
  private statesubscription: Subscription;  

  constructor(private route: ActivatedRoute,
              private router: Router,
              private gameService: GameService,
              private webSocketService: WebSocketService) { }

  ngOnInit() {
      this.webSocketService.connectWebSocket();
      this.datasubscription = this.webSocketService.getSocketDataObservable().subscribe(this.onData);
      this.statesubscription = this.webSocketService.getSocketStateObservable().subscribe(this.onStateChange);     
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];

      if (id) {
        this.gameService.get(id).then((game: any) => {
          if (game) {
            this.game = game;
            this.gameService.getBoard (id).then ((board: any) =>{
              this.board = board;
            })
            console.log(this.game);
            console.log(this.board);
          } else {
            console.log(`Game with id '${id}' not found, returning to list`);
          }
        });
      } 
    });  	
  }

  move (game :any, pit : number) {
    console.log(game);
     
    this.gameService.play (game, pit);
  }



  private onData = (message: Message) => {


    if (message.body == "end") alert ("Game over!");
    location.reload();
  }


  private onStateChange = (state: String) => {


    console.log('WS connection state changed '+state);


  }


  ngOnDestroy() {


    this.datasubscription.unsubscribe();


    this.statesubscription.unsubscribe();


  }  
}