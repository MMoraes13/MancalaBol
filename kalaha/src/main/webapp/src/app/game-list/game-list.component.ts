import { Component, OnInit } from '@angular/core';
import { PlayerService } from '../players/player.service';
import { GameService } from '../games/game.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Subscription, of } from 'rxjs';

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']

})
export class GameListComponent implements OnInit {
	games: Array<any>
	game: any = {}
	sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private gameService: GameService) { }
  ngOnInit() {      
  	this.gameService.getAll().then(data => {
  		this.games = data;
  	})
  }

	join(form: NgForm) {
    
    this.gameService.join(form).then(result => {
      this.gotoGame(result);
    }, error => console.error(error));
  }
  create (form: NgForm) {
      this.gameService.save(form).then(result => {        
        this.gotoGame(result);
      }, error => console.error(error));
  }

  gotoList() {
    this.router.navigate(['game-list']);
  }

  gotoGame(game : any) {
    this.router.navigate(['game', game.id]);
  }


}
