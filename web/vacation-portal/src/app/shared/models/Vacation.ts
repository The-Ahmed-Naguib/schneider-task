import {VacationType} from './VacationType';

export class Vacation {
    id: number;
    vacationTypeEntity: VacationType;
    vacationStartDate: String;
    vacationEndDate: String;
    numberOfDays: number;
}
