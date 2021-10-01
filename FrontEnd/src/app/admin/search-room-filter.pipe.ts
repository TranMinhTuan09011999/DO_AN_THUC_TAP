import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../model/category';

@Pipe({
  name: 'searchRoomFilter'
})
export class SearchRoomFilterPipe implements PipeTransform {

  transform(categories: Category[], searchRoomValue: string): Category[] {
    if(!categories || !searchRoomValue){
      return categories;
    }
    return categories.filter(category => 
      category.room.name.toLocaleLowerCase().includes(searchRoomValue.toLocaleLowerCase()));
  }

}
