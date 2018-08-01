import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';
import { GameService } from '../games/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription, of } from 'rxjs';

@Component({
  selector: 'app-game-play',
  templateUrl: './game-play.component.html',
  styleUrls: ['./game-play.component.css']
})
export class GamePlayComponent implements OnInit {
  gameBoard: any = {};
  game: any = {};
  board: any = {}
  sub: Subscription;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private gameService: GameService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      console.log(id);
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
}