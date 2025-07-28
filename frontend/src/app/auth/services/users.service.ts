import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private readonly apiUrl: string;
  authService: any;

  constructor(private http: HttpClient) {
    this.apiUrl = 'http://localhost:8080/api/auth/register';
  }

  createUser(user: FormData): Observable<any> {
    console.log("🚀 Sending registration request with FormData:", user);

    const headers = new HttpHeaders({
      'Accept': 'application/json',
    });

    return this.http.post(this.apiUrl, user, {
      headers: headers,
      observe: 'response',
      responseType: 'text'
    }).pipe(
      map((response) => {
        console.log("🌐 Full Backend Response (RAW):", response);

        try {
          if (response.body != null) {
            const parsedResponse = JSON.parse(response.body);
            console.log("✅ Successfully parsed JSON:", parsedResponse);
            return parsedResponse; // ✅ Return the parsed response instead of undefined
          }
        } catch (error: any) {
          console.error("❌ Failed to parse JSON:", error.message);
        }

        console.log("📝 Returning raw response instead.");
        return response.body;
      }),
      catchError((error) => {
        console.error("❌ Error during registration request:", error.message);
        throw error;
      })
    );
  }
}
