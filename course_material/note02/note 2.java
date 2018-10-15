int carMakerNum =
   cars.stream() 
       .map( (Car car)-> car.getMake() ) 
       .reduce(0,
               (result,carMaker)-> ++result
               (result,intermediateR)->result);

TO

int carMakerNum =
   cars.stream() 
       .map( (Car car)-> car.getMake() ) 
       .reduce(0,
               (result,carMaker)-> ++result,
               (result,intermediateR)->result);

The difference in the two versions is that the latter has comma (,) in 
the end of the 5th line (in the end of the second parameter for reduce()).
