import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';
import { GameService } from '../games/game.service';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
	games: Array<any>
	game: Array<any>
  constructor(private gameService: GameService) { }

  ngOnInit() {
  	this.gameService.getAll().subscribe(data => {
  		this.games = data;
  	})
  }

}
