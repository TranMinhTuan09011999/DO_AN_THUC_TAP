import { Pipe, PipeTransform} from '@angular/core';
import { Provider } from '../model/provider';

@Pipe({
  name: 'searchProviderFilter'
})
export class SearchProviderFilterPipe implements PipeTransform {

  transform(providers: Provider[], searchIdValue: string): Provider[] {
    if(!providers || !searchIdValue){
      return providers;
    }
    return providers.filter(provider => 
      provider.providerId.toLocaleLowerCase().includes(searchIdValue.toLocaleLowerCase()));
  }

}
