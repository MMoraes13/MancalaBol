import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable()
export class PlayService {
	public API = '//localhost:8080';
	public PLAY = this.API+"/play";

  constructor(private http : HttpClient) { }



  play (game: any, pit : any) : Promise <any> {
  	
    return this.http.post(this.PLAY+"/"+game.id+"/"+pit, {})
    .toPromise().then(response => response, error => alert(error));
    
  }


}
