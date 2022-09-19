import {Component} from '@angular/core';
import {FormBuilder} from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  options = this._formBuilder.group({
    bottom: 0,
    fixed: false,
    top: 0,
  });
  title = 'vacation-portal';

  constructor(private _formBuilder: FormBuilder) {}
}