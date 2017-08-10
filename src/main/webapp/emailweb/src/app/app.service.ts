import { Injectable } from '@angular/core';
import { Headers, Http }       from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { EmailParam } from './emailparam';
import { EmailResult } from './emailresult';

@Injectable()
export class AppService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) {}

  sendEmail(emailParam: EmailParam): Promise<EmailResult> {
   return this.http
      .post('/email', JSON.stringify(emailParam), {headers: this.headers})
      .toPromise()
      .then(res => res.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
