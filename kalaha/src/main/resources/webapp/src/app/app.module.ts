import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { PlayerService } from './players/player.service';

import { AppComponent } from './app.component';
import { PlayerListComponent } from '../app/player-list/player-list.component';
import { PlayerEditComponent } from '../app/player-edit/player-edit.component';

import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { GameComponent } from './game/game.component';


const appRoutes: Routes = [
  { path: '', redirectTo: '/player-list', pathMatch: 'full' },
  {
    path: 'player-add',
    component: PlayerEditComponent
  },
  {
    path: 'player-list',
    component: PlayerListComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    PlayerListComponent,
    PlayerEditComponent,
    GameComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [PlayerService],
  bootstrap: [AppComponent]
})
export class AppModule { }

