import { Component } from '@angular/core';
import { EmailParam } from './emailparam';
import { AppService } from './app.service';
import { EmailResult } from './emailresult';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  emailParam: EmailParam = new EmailParam();
  toErrorMsg: string;
  subjectErrorMsg: string;
  textErrorMsg: string;
  emailResult: EmailResult;
  sentResult: boolean;

  constructor(private appService: AppService) {}

  sendEmail(): void {
    console.log("to = " + this.emailParam.to);
    if(!this.fieldsValid()) {
      return;
    }

    console.log("sendEmail");
    this.appService.sendEmail(this.emailParam).then(response => {
      this.emailResult = response;
      console.log("response = " + JSON.stringify(this.emailResult));
      if(this.emailResult.status == 200) {
        this.sentResult = true;
      } else {
        this.sentResult = false;
      }
    });
  }

  private fieldsValid(): boolean {
    this.toErrorMsg = "";
    this.subjectErrorMsg = "";
    this.textErrorMsg = "";
    let allGood: boolean = true;
    if(this.emailParam.to == null || this.emailParam.to.trim() == "") {
      this.toErrorMsg = "To is a mandatory field";
      allGood = false;
    }
    if(this.emailParam.subject == null || this.emailParam.subject.trim() == "") {
      this.subjectErrorMsg = "Subject is a mandatory field";
      allGood = false;
    }
    if(this.emailParam.text == null || this.emailParam.text.trim() == "") {
      this.textErrorMsg = "Text is a mandatory field";
      allGood = false;
    }

    return allGood;
  }

  resetForm(): void {
    this.emailParam = new EmailParam();
    this.emailResult = null;
  }
}
