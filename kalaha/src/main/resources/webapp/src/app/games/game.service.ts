import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';



@Injectable()
export class GameService {
	public API = '//localhost:8080';
  public GAME_API = this.API+"/game";
  public GAME_CREATE_API = this.GAME_API+"/create";	
  public GAME_GET_ALL = this.API;
  public GAME_GET_ALL_AVAILABLE_GAMES = this.GAME_API+"/gameslist";
  public GAME_JOIN = this.GAME_API+"/join";

  constructor(private http : HttpClient) { }


  getAll(): Promise<any> {
    return this.http.get(this.GAME_API).toPromise().then(response => response);
  }
  getAllAvailableGames(): Promise <any> {
  	return this.http.get(this.GAME_GET_ALL_AVAILABLE_GAMES).toPromise().then(response => response);
  }

  get(id : number) : Promise<any> {
  	return this.http.get(this.GAME_API+"/"+id).toPromise().then(response => response);
  }
  save (player : any) : Promise<any> {
    let result: Promise<Object>;
    result = this.http.post(this.GAME_CREATE_API, {"id":localStorage.getItem ("currentUser")}).toPromise().then(response => response);
    return result;
  }

  join (game : any) : Promise <any> {
    let result: Promise<Object>;
    return this.http.patch(this.GAME_JOIN+"/"+game.id, 
             {"id":localStorage.getItem ("currentUser")}
             ).toPromise().then(response => response);
    
  }


}
