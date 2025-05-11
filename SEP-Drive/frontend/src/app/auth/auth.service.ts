import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/api/auth/login';
  private apiUrl2fa = 'http://localhost:8080/api/auth/verify';

  constructor(private http: HttpClient) {}

  loginUser(username: string, password: string): Observable<any> {
    console.log("Sending login request with:", username, password); // <-- Add this line
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = {
      username: username,
      password: password
    };

    return this.http.post(this.apiUrl, body, { headers });
  }

  verifyCode(username: string, code: string): Observable<any> {
    console.log("Sending verification request with:", username, code);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = JSON.stringify({
      username: username,
      code: code
    });

    console.log("Request Body:", body);

    return this.http.post(this.apiUrl2fa, body, { headers });
  }

}
