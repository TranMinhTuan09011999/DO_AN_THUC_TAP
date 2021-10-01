import { Pipe, PipeTransform } from '@angular/core';
import { Employee } from '../model/employee';

@Pipe({
  name: 'searchEmployeeNameFilter'
})
export class SearchEmployeeNameFilterPipe implements PipeTransform {

  transform(employees: Employee[], searchValueName: string): Employee[] {
    if(!employees || !searchValueName){
      return employees;
    }
    return employees.filter(employee => 
      employee.lastname.toLocaleLowerCase().includes(searchValueName.toLocaleLowerCase()) ||
      employee.firstname.toLocaleLowerCase().includes(searchValueName.toLocaleLowerCase()));
  }

}
