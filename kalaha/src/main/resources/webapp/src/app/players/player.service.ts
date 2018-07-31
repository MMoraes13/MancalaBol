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

  getAll(): Promise<any> {
    return this.http.get(this.PLAYERS_API).toPromise().then(response => response);
  }
  
  get(id : number) : Promise<any> {
  	return this.http.get(this.API+'/player/'+id).toPromise().then(response => response);
  }
  save (player : any): Promise<any> {
    
    return this.http.post(this.PLAYER_CREATE_API, player)
    .toPromise()
    .then(response => response);

  }


}
