import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable()
export class PlayerService {
	public API = '//localhost:8080';
	public PLAYERS_API = this.API+'/players';
  public PLAYER_API = this.API+"/player";
  public PLAYER_CREATE_API = this.PLAYER_API+"/create";
  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any> {
    return this.http.get('//localhost:8080/players');
  }
  get(id : number) : Observable<any> {
  	return this.http.get(this.API+'/player/'+id);
  }
  save (player : any): Observable<any> {
    let result: Observable<Object>;
    result = this.http.post(this.PLAYER_CREATE_API, player);
    return result;
  }
}
