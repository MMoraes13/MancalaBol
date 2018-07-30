import { Component, OnInit } from '@angular/core';

import { Subscription, of } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { PlayerService} from '../players/player.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-player-edit',
  templateUrl: './player-edit.component.html',
  styleUrls: ['./player-edit.component.css']
})
export class PlayerEditComponent implements OnInit {
	player: any = {};
	sub: Subscription;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private playerService: PlayerService) { }

 ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.playerService.get(id).subscribe((player: any) => {
          if (player) {
            this.player = player;
            
          } else {
            console.log(`Player with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

save(form: NgForm) {
    this.playerService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  gotoList() {
    this.router.navigate(['/player-list']);
  }


}
