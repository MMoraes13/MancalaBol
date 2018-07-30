import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable()
export class GameService {
	public API = '//localhost:8080';
  public GAME_API = this.API+"/game";
  public GAME_CREATE_API = this.GAME_API+"/create";	
  public GAME_GET_ALL_AVAILABLE_GAMES = this.GAME_API+"/gameslist";

  constructor(private http : HttpClient) { }


  getAll(): Observable<any> {
    return this.http.get(this.GAME_API);
  }
  getAllAvailableGames(): Observable <any> {
  	return this.http.get(this.GAME_GET_ALL_AVAILABLE_GAMES);
  }

  get(id : number) : Observable<any> {
  	return this.http.get(this.GAME_API+"/"+id);
  }
  save (player : any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.GAME_CREATE_API, player);
    return result;
  }
}
