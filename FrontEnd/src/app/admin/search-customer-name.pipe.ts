import { Pipe, PipeTransform } from '@angular/core';
import { Customer } from '../model/customer';

@Pipe({
  name: 'searchCustomerName'
})
export class SearchCustomerNamePipe implements PipeTransform {

  transform(customers: Customer[], searchValueName: string): Customer[] {
    if(!customers || !searchValueName){
      return customers;
    }
    return customers.filter(customer => 
      customer.lastname.toLocaleLowerCase().includes(searchValueName.toLocaleLowerCase()) ||
      customer.firstname.toLocaleLowerCase().includes(searchValueName.toLocaleLowerCase()));
  }

}
