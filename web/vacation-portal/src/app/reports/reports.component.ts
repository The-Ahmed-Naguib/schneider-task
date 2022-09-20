import {Component, OnInit} from '@angular/core';
import {HttpCallsService} from '../shared/services/http-calls.service';
import {VacationType} from '../shared/models/VacationType';
import {VacationRequestDTO} from '../shared/models/VacationRequestDTO';
import {EmployeeDTO} from '../shared/models/EmployeeDTO';

@Component({
    selector: 'app-reports',
    templateUrl: './reports.component.html',
    styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
    isNoDataFound = false;
    typeSelected = 0;
    headerRow: string[];
    dataRows: EmployeeDTO;
    vacationTypes: VacationType[];
    selectedDateTo = '';
    selectedDateFrom = '';
    leaveDuration = 0;


    constructor(public httpCallsService: HttpCallsService) {
    }

    ngOnInit() {
        this.headerRow = ['Start Date', 'End Date', 'Duration', 'Type'];
        this.getLookUps();
        this.getCurrentEmployeeDetails();

    }

    getLookUps() {
        this.httpCallsService.getVacationTypes().subscribe(res => {
            this.vacationTypes = res.data;
        })
    }

    selectType(value: any) {
        console.log(value)
        this.typeSelected = value;
    }

    submitVacation() {
        console.log('submitting : ')
        console.log( this.getSubmitBody())
        if (this.selectedDateFrom != '' && this.selectedDateTo != '' && this.leaveDuration > 0) {
            this.httpCallsService.submitVacations(this.getSubmitBody()).subscribe(res => {
                this.getOldVacations();
            }, error => {
                console.log(error);
            })
        }
    }


    getOldVacations() {
        this.httpCallsService.getOldVacations().subscribe(res => {
            this.dataRows.vacations = res.data;
            if (this.dataRows.vacations.length > 0) {
                this.isNoDataFound = false;
            } else {
                this.isNoDataFound = true;
            }
        }, error => {
            console.log(error);
            this.isNoDataFound = true;
        })
    }


    selectDateFrom(value: any) {
        this.selectedDateFrom = value;
        this.getLeaveDuration();
    }

    selectDateTo(value: any) {
        this.selectedDateTo = value;
        this.getLeaveDuration();

    }

    getLeaveDuration() {
        if (this.selectedDateFrom != '' && this.selectedDateTo != '') {
            this.httpCallsService.getVacationDuration(this.selectedDateFrom, this.selectedDateTo).subscribe(res => {
                this.leaveDuration = res.data;
            }, error => {
                console.log(error);
                this.leaveDuration = 0;
            })
        }
    }

    private getSubmitBody(): VacationRequestDTO {
        return {
            vacationStartDate: this.selectedDateFrom,
            vacationEndDate: this.selectedDateTo,
            vacationType: this.vacationTypes[this.typeSelected].type
        };
    }

    private getCurrentEmployeeDetails() {
        this.httpCallsService.getCurrentEmployeeDetails().subscribe(res => {
            this.dataRows = res.data;
            if (this.dataRows.vacations.length > 0) {
                this.isNoDataFound = false;
            } else {
                this.isNoDataFound = true;
            }
        }, error => {
            console.log(error);
            this.isNoDataFound = true;
        })
    }
}
