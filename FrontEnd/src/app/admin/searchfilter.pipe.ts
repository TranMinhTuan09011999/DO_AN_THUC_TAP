import { Pipe, PipeTransform } from '@angular/core';
import { Customer } from '../model/customer';

@Pipe({
  name: 'searchfilter'
})
export class SearchfilterPipe implements PipeTransform {

  transform(customers: Customer[], searchValue: string): Customer[] {
    if(!customers || !searchValue){
      return customers;
    }
    return customers.filter(customer => 
      customer.id.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }

}
