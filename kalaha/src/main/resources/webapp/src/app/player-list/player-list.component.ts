import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';


@Component({
  selector: 'app-player-list',
  templateUrl: './player-list.component.html',
  styleUrls: ['./player-list.component.css']
})
export class PlayerListComponent {
  players: Array<any>;
  player: Array<any>;

  constructor(private playerService: PlayerService) { }

  ngOnInit() {
  this.playerService.getAll().subscribe(data => {
  	this.players = data;
  	});
  }

}
