export class Flight {
  airline: string;
  basePrice: number;
  flightDate: string;
  flightNumber: number;
  from: string;
  to: string;


  constructor(airline: string, basePrice: number, flightDate: string, flightNumber: number, from: string, to: string) {
    this.airline = airline;
    this.basePrice = basePrice;
    this.flightDate = flightDate;
    this.flightNumber = flightNumber;
    this.from = from;
    this.to = to;
  }
}
