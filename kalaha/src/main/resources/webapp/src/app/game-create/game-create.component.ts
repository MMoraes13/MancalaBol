import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';
import { GameService } from '../games/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription, of } from 'rxjs';


@Component({
  selector: 'app-game-create',
  templateUrl: './game-create.component.html',
  styleUrls: ['./game-create.component.css']
})
export class GameCreateComponent implements OnInit {
	games: Array<any>
	game: any = {}
	sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private gameService: GameService) { }
  ngOnInit() {

  }

}
