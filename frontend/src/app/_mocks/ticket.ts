export class Ticket{
  id: number;
  departureDate: Date;
  arrivalDate: Date;
  from: string;
  to: string;


  constructor(id: number, departureDate: Date, arrivalDate: Date, from: string, to: string) {
    this.id = id;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.from = from;
    this.to = to;
  }
}
