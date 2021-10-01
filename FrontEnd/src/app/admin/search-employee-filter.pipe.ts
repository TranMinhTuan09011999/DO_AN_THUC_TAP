import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../model/employee';

@Pipe({
  name: 'searchEmployeeFilter'
})
export class SearchEmployeeFilterPipe implements PipeTransform {

  transform(employees: Employee[], searchValue: string): Employee[] {
    if(!employees || !searchValue){
      return employees;
    }
    return employees.filter(employee => 
      employee.id.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }

}
