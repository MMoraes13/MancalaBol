import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { PlayerService } from './players/player.service';
import { GameService } from './games/game.service';

import { AppComponent } from './app.component';
import { PlayerListComponent } from '../app/player-list/player-list.component';
import { PlayerEditComponent } from '../app/player-edit/player-edit.component';

import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { GameListComponent } from './game-list/game-list.component';



const appRoutes: Routes = [
  { path: '', redirectTo: '/player-list', pathMatch: 'full' },
  {
    path: 'player-add',
    component: PlayerEditComponent
  },
  {
    path: 'player-list',
    component: PlayerListComponent
  },
  {
    path: 'game-list',
    component: GameListComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    PlayerEditComponent,
    GameListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PlayerService, GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }

