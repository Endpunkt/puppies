import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Puppy } from '../common/puppy';

@Injectable({
  providedIn: 'root'
})
export class ResponseService {
  private uploadUrl = 'http://localhost:8080/upload';

  constructor(private http: HttpClient) { }

  uploadPuppy(puppy: any): Observable<any>{

    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return  this.http.post(`${this.uploadUrl}/addPuppy`, puppy, {headers});
  }

  uploadImage(formData: FormData):Observable<any>{
    return this.http.post<any>(`${this.uploadUrl}/image`, formData);
  }

}
