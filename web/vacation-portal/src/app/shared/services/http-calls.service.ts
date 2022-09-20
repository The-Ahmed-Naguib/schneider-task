import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../../environments/environment';
import {ResponseDTO} from '../models/ResponseDTO';
import {VacationType} from '../models/VacationType';
import {Vacation} from '../models/Vacation';
import {VacationRequestDTO} from '../models/VacationRequestDTO';
import {EmployeeDTO} from '../models/EmployeeDTO';

@Injectable({
    providedIn: 'root'
})
export class HttpCallsService {

    constructor(public http: HttpClient) {
    }


    getVacationTypes(): Observable<ResponseDTO<VacationType[]>> {
        return this.http.get<ResponseDTO<VacationType[]>>(environment.hostUrl + '/lookups/vacation-type');
    }

    getVacationDuration(startDate: string, endDate: string): Observable<ResponseDTO<number>> {
        return this.http.get<ResponseDTO<number>>(environment.hostUrl + '/vacation/duration?startDate=' + startDate + '&endDate=' + endDate);
    }

    getOldVacations(): Observable<ResponseDTO<Vacation[]>> {
        return this.http.get<ResponseDTO<Vacation[]>>(environment.hostUrl + '/vacation/all');

    }

    getCurrentEmployeeDetails(): Observable<ResponseDTO<EmployeeDTO>> {
        return this.http.get<ResponseDTO<EmployeeDTO>>(environment.hostUrl + '/employee');

    }

    submitVacations(body: VacationRequestDTO): Observable<ResponseDTO<Vacation>> {
        return this.http.post<ResponseDTO<Vacation>>(environment.hostUrl + '/vacation', body);
    }
}
