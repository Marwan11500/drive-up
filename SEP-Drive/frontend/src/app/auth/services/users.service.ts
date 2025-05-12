import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/api/auth/register';
  }


  createUser(user: FormData): Observable<any> {
    return this.http.post(this.apiUrl, user, { withCredentials: true });
  }
}
