Java8 in action:

Chapter 1: Java8 Why should you care?

* Multi-core cpu has fast cache attached to each processor core
* Peter landin, computer scientist, "The next 700 programming language"
* behaviour parameterization, passing code as a method argument

* no shared mutable data and ability to pass methods and functions-code-to other methods is
  the paradigm of functional programming
* method reference :: syntax meaning use this method as a value
* writing programs that pass functions around as first-class values
  e.g. (int x) -> x + 1
  older way:

* Collections is only about storing and accessing data. whereas streams are about describing computations
  on data
* list.sort(Comparator) instead of Collections.sort(list, Comparator) - default method of interface
* list.parallelStream().filter(f -> f.getType().equals('mtb').collect(toList());
* no interaction during execution between elements is a ideal scenario for parallel processing.
* a default method can be added in interface having its own implementation without restriction to comply it's impl for writing impl of methods.

Q A single class can implement multiple interfaces. so, if you have multiple
default implementation in several interfaces, does that mean you have a multiple inheritance in java.
A Yes to some context!

* Why should switch in java be limited to primitive data types of numbers and strings.
  It could also be used for pattern matching with more complex data types.

Summary
- evolve or wither pressure on language ecosystem
- effective and concise
- functions are first class values
- streams generalizes many aspects of collection + the support of parallel processing
- you can use default method in an interface
- avoiding nullpointer with optional and pattern matching with switch.

------------------------------------------------------------------

Chapter 2
Passing code with behaviour parameterization

* behaviour parameterization
* taking a block of code, and making it available without executing it
* the method's behaviour is parameterized based on the block of code.
* you could declare one interface and have the common method
  e.g. boolean test(Apple a)
  and write several implementations for the interface and pass the interface object to method.
  this solution is very much verbose

* inline declaration of interface impl with anonymous classes.
* many methods in java api can be parameterized with different behaviours.

* sorting with a Comparator
* executing a block of code with Runnable
* GUI event handling

Summary
- behaviour parameterization is the pattern to take multiple behaviours as parameter
- it is more adaptive to changing requirements
- prior to java8, it could have been achieved with interface impl and anonymous classes.
  but the verbosity is much higher.

- BottomLine: It is more readible and concise.

------------------------------------------------------------------

Chapter 3
Lambda expressions

* a lambda expressions can be explained as a consise representation of anonymous function that can be passed around
* it doesn't have a name but a list of parameters, a body a return type and also possibly list of exceptions.

rules and syntax
* if only one statement which returns value don't need to write explicit return
* if no method argument specify () ->

* a functional interface is an interface with exactly one abstract method
* Function descriptor: abstract method signature defines the lambda signature.
* It is good practice to decorate interface with @FunctionalInterface annotation
* similar to @Override annotation on method even it's not mandatory

* passing interface impl as method argument with one helper method

E.g.

public class Lambda2 {

    public static String processFile(CustomBufferedReader bufferedReader) throws IOException{
        try (BufferedReader bufferedReader1 = new BufferedReader(new FileReader("sample.txt"))) {
            return bufferedReader.process(bufferedReader1);
        }
    }

    public static void main(String[] args) throws IOException {
        String fileContents = processFile((bf) -> bf.readLine() + bf.readLine());
        System.out.println(fileContents);
    }

}

@FunctionalInterface
interface CustomBufferedReader {
String process(BufferedReader bf) throws IOException;
}


* Predicate<T> - used to do boolean test on objects

E.g. Predicate<String> isNotBlank = (String s) -> (s != null && s.length() > 0);

* Consumer<T> - accept method to work on the object

E.g. Consumer<Integer> consumer = i -> System.out.println("sending email to "+ i);
consumer.accept(2);


* Function<R, T> -- to convert value from T to R
* R apply(T t) --- abstract method


* Lambdas are allowed to access variables from outside it's scope.
* it can access instance variables and static variables.
* but local variables have to final or are effectively final.
* instance variables are stored in heap whereas local variables are in stack
* if a lambda could access a free local variable in a thread. Thread deallocates and the other thread can try to access same variable.
* Hence, java implements access to free local variable as access to copy of it not the original one.



* Method references can seen as a shorthand for lambdas calling only a specific method
* E.g. (Apple a) -> a.getWeight() can be written as Apple::getWeight()

* chaining of predicates
* So, a.or(b).and(c) is equal to (a || b) && c.
* left to right evaluation


* composing function
* chaining Function<T, R> with another Function<T, R> with .andThen()

* Function<Integer, Integer> f = x -> x+1;
* Function<Integer, Integer> q = x -> x*2;
* Function<Integer, Integer> h = f.andThen(g);

* int result = h.apply(1);

-------------------------------------------------------------
Chapter 4
Streams

* A stream is a sequence of elements from a source that supports data processing operations.
* collections are about data; streams are about computations.
* Source - streams consume elements from a data providing resources such as collections, arrays and/or I/O resources
  The elements of a stream coming from a list maintains the same order as list

* data processing operations - database like operations and filter, map, reduce, find, match, sort and so on.
* stream operations can be executed either sequentially or in parallel

* pipelining - chaining of operations. Every stream operations return a stream itself, this enables optimization such as laziness and short-circuiting.
* internal interaction - streams does the iteration of elements internally as opposed to explicit iterator for list operations

* collect -- no result is produced until collect is called, lazy execution
* lazily constructed collection
* demand driven
* in contrast, collection is eagerly constructed
* A collection in java8 is like a movie stored in DVD
* A stream in java8 is like a movie streamed over the internet

* stream can be consumed only once!
* Stream<String> s = list.stream();
* s.forEach(System.out::println);
* s.forEach(System.out::println); //ERROR! Illegal State Exception

* the internal iteration of stream library can automatically choose a data representation and implementation of parallelism to match your hardware

* intermediate operations -- map, reduce, limit, distinct, filter, sorted which returns streams itself
* terminal operations -- forEach(), collect(), count() which is terminal operation and lazily executes the pipeline

* the elements of stream are computed on demand



--------------------------------------------------------------

Chapter 5
Working with streams

* flatMap: method lets you replace each value of a stream with another stream and then concatenates all the
  Generated streams into a single stream

        List<String> str2 = Arrays.asList(arr1).stream()
                .map(s -> s.split(""))    //converts each word into array of individual letters
                .flatMap(f -> Arrays.stream(f))  //convert each stream into a single stream
                .distinct()
                .collect(Collectors.toList());

* finding and matching
* finding whether some elements in a set of data match a given property.
* API: anyMatch, allMatch, noneMatch, findAny methods
* boolean b = list.stream().anyMatch(d -> d.getWeight() > 100);

* makes use of short circuiting.
* if series of the AND operation.  If first condition is false, no need to evaluate further expressions. It will always result false.
  This is called as short circuiting.

* list.stream().anyMatch(<boolean>); list.stream().allMatch(<boolean>)
* allMatch, noneMatch, findFirst, findAny don't need to process the whole stream. It returns result as soon as one is produced
* findAny method returns an arbitrary element of the current stream.
* the stream is optimised to perform single pass and finish as soon as result is found  for e.g. menu.stream().filter(Dish::isVegeterian).findAny();

Optional
* in a nutshell, optional is a container class to represent the existence or absence of a value.
* Methods: isPresent(), ifPresent(Consumer<T> block), T get(), T orElse(T other)

E.g
list.stream().filter(Dish::isVegeterian).findAny().ifPresent(d -> System.out.println(d.getName()));
(Optional<Dish>)


* when to use findFirst and findAny
- use findFirst is more constraining when doing parallel operations.
- use findAny as it is not constraining when doing parallel operations. (parallelStream())

Reducing
* combining elements of a stream to express more complicated queries.
* A stream is reduced to a single value.
* in Functional programming jargon, this is referred to as a fold because you can view this operation as repeatedly folding a long piece of paper.
* summing the numbers in a stream.  E.g. int sum = numbers.stream().reduce(0, (a, b) -> a + b);

Numeric streams:
* `menu.stream().map(d -> d.getCalories()).reduce(0. Integer::sum);`  //boxing of Integer to int, hence inefficient
* instead, java8 provides specialised number streams called as primitive stream specialisations
* above code can be written as: `menu.stream().mapToInt(Dish::getCalories).sum();`

* converting to specialized version are mapToInt, mapToDouble and mapToLong returns IntStream, DoubleStream, LongStream instead of Stream<T>

Converting back to stream of objects
* `IntStream intStream = menu.stream().mapToInt(Dish::getCalories);` - converting a stream to numeric stream
* `Stream<Integer> stream = intStream.boxed()` - converting numeric stream to a Stream

* There's a primitive specialized version of Optional as well for 3 stream specialization: OptionalInt, OptionalDouble, OptionalLong

Numeric ranges
* `IntStream.rangeClosed(1, 100)` - range of number from 1 to 100 (inclusive of 1 and 100)
* `IntStream.range(1, 100)` - range of number from 2 to 99 (exclusive of 1 and 100)

Summary
* Some operations such as filter and map are stateless. They don't store any state
* while some operations such as reduce, sorted, distinct store the status to calculate value because they need to buffer all the elements
* such operations are called stateful operations
* streams can be created from values, arrays, files, and specific methods such as iterate and generate
* an infinite stream is a stream that no fixed size.

--------------------------------------------------------------------------------

Chapter 6
Collecting data with streams

* collect like reduce is a terminal operation.
* it takes as argument various recipes of accumulating elements of a stream into a summary result
* E.g. Group a list of transactions by currency to obtain sum of the values of all the transactions with that currency

* this is achieved by Collectors interface
* summingInt method calculates sum of every element
* often, you may need multiple methods at once viz. sum, min, max avg etc.
* summarizingInt factory method can be used in such cases

Generalised summarisation with reduction
* The Collectors.reducing factory method is a generalisation of all of them.

E.g. `int sum = menu.stream.collect(Collectors.summingInt(Dish::getCalories));`

Can also be expressed as

`int sum = menu.stream.collect(Collectors.reducing(0, Dish::getCalories, (I,j) -> I + j));`

It takes three arguments

1. The first argument is starting value, in above case it's 0
2. The second argument is identity function or a transformation function
3. The third argument is BinaryOperator that aggregates two items into a single value

* above 3-argument version of reducing, similarly there is 1-argument version of reducing as below

`int sum = menu.stream.collect(Collectors.reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? D1 :d2));`

* for one argument method, the first argument is the first element of stream.
* the identity function is the function returning the argument as it is. (no transformation)

Grouping

* Group items by name. Returns -> Map<GroupKey, List<T>> response
* E.g. `Map<String, List<Dish>> typeDishes = list.stream().collect(Collectors.groupingBy(Dish::getType));`

Multi-level grouping

* multi level grouping can be achieved with 2-argument version of groupingBy method

* E.g. `Map<Dish.Type, Map<CalorificValue, List<Dish>> multi = list.stream.collect(groupingBy(Dish::getType, groupingBy(dish -> getCalorificValue(dish.getCalories)))`;
* `CalorificValue getCalorificValue(int calories);`


* a second groupingBy passed to argument of first groupingBy can be any method of Collectors
* E.g. `List<Dish.Type, Integer> countByDishType = list.stream.collect(groupingBy(Dish::getType, counting());`

Mapping
* mapping collector that is commonly used in conjunction with groupingBy
* used to define custom group
* first argument is transformation function, and second argument is toSet or toList collector
* for second argument also can pass implementation of Set by `Collectors.toCollection(HashSet::new);`

Partitioning
* partitionBy method requires a Predicate as it's argument
* it divides the stream into two entries in the map I.e {True={elements..}, False={elements..}) based on a predicate
* Partitioning is the specialized version of groupingBy where key is only boolean value

* e.g. `Map<Boolean, List<Dish>> vegOrNonVegDishes = list.stream.collect(partitionBy(d -> d.getType() == "veg"), toList())`

* you can also create your own collectors by implementing Collectors interface

-----------------------------------------------------------------

Chapter 7:
Parallel data processing and performance

* a parallel stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread
* decomposability of collections data structure is important, so selection of data structure is very important
* LinkedList has poor decomposability (since it's ordered and linked) over ArrayList which is excellent for decomposing elements
* certain problems cannot be paralleled like findFirst
* a parallel stream internally makes use of fork-join framework which is a thread pool of size = no. of processor cores on a machine
* Stream.iterate(1, n) produces streams sequentially, so even though you write parallelStream on it, it has no effect
* The generator of stream should not be sequential.
* for any stream.filter(..).parallelStream().filter(..).stream() - the last operation that is used (in this case sequential) will take effect
* Divide streams into chunks, operate on each chunk parallel, and then merge the results of each chunk
* you can get full control of decomposition process by implementing the Spliterator

Fork/join framework
* fork join framework works on the principle of divide-and-conquer algorithm
* it forks the single largest stream into chunks, divide until best possible chunk, work on the smallest chunk sequentially and then combine results
* it's an implementation of ExecutorService interface, which distributes those subtasks to worker threads in pool, called ForkJoinPool
* to submit task to forkjoinpool, it needs to be subclassed with RecursiveTask<R> abstract class
* it defines, T compute() method which can be implemented
* the join() method blocks the caller until the result is computed
* the fork() method creates a new thread from the pool
* the invoke() method shouldn't be used inside compute method. It's always used to call from the calling method to begin parallel computation
* since, the fork-join thread pool uses all the available cores, it's wise to use as singleton instead of creating new fork-join pool every time.
* re-usability is the key
* when multiple cores work on the tasks, each core maintains a doubly linked list to pick the task
* fork-join pool also makes use of work stealing, where the idle cpu core picks up the task from the queue of another core.
* every cpu core picks up task from a double linked list from the front until queue is empty, whereas work stealing happens from the tail of the queue.
* with RecursiveTask, we divided the array into two halves recursively, but when you call parallelStream it is done automatically
* this automatic mechanism is achieved by Spliterator interface.

Spliterator
* spliterator is the another interface in java8; its name stands as "splittable iterator"
* the interface provides four methods:
1. Boolean tryAdvance(Consumer<? Super ?> action) --> consume elements sequentially, and return true if there are more elements
2. Spliterator<T> trySplit() --> split the elements and create another Spliterator<T>
3. Long estimateSize() - estimation of number of elements
4. Int characteristics() --> ORDERED, DISTINCT, SORTED, SIZED, NONNULL, IMMUTABLE, CONCURRENT, SUBSIZED


-----------------------------------------------------------------

### Part 3:
### Effective Java8 Programming

#### Chapter 8
#### Refactoring, testing and debugging

* refactoring code from anonymous classes to lambda expressions is tricky
* traits: the argument of a object in method with same abstract method could not be expressed in lambda expressions, need to have only one abstract method
* benefits; concise reading, leverages parallel execution, behaviour parameterisation, method references
* coding pattern: deferred execution and execute around
* deferred execution pattern talks about evaluating a statement only after some condition (essentially a predicate), instead of cluttered if checks
* execute around pattern is about having a frequently required code for e.g. try with resources for opening buffered reader and inside try providing function interface implementation.
* With Java8 lambda expressions, we could use Strategy and Template method method in a more concise way
* Strategy pattern is pattern having interface with method to operate on a fixed object. Diff implementation will operate on it differently as per requirements
* Using lambda expressions instead of having to write concrete classes we can define it inline e.g. Strategy isNumericValidator = (text) -> String.isNumeric(text);
* Observer pattern is yet another pattern which can be expressed in lambda, but it's a no-go if the processing logic of event is not short and involves multiple methods and objects.
* chain of responsibility pattern is pattern where we want to apply operation one after the other, output of predecessors is input to successors
* lambda can be well leveraged by using UnaryOperator<T> for every ops and Function<T, R> for applying chain with UnaryOperator1.andThen(UnaryOperator2)

* Testing lambdas and debugging can be tricky; the stack trace displays the cryptic source where it doesn't have any name
* one way is to use method reference with map operation
* the other way is to use peek(x -> sop) after each intermediate operation for debugging. Unlike other operation peek does not consume the element


------------------------------------------------------------------

#### Chapter 9
#### Default methods

* Earlier, interface had only method declarations, which allowed implementation to implement them
* It was difficult to evolve the interfaces for new features, if one more method added to interface, the implementation has to comply that giving a nightmare to developers
* To avoid that, Java8 comes up with default method which is a method having the definitions without needing the implementors to comply for writing the implementation.
* This aided java8 library designers in a great way

* Resolution rules when class implements from two interfaces having same method signature

1) First, an explicit method declaration in the class or superclass takes priority
2) otherwise, the method with same signature in the most specific default providing interface is selected
3) finally, if there's still a conflict between equal contenders, the class needs to explicitly override the method and choose which one interface method to be used.

```
interace A { 
	default void foo() {}
}
	
interface B {
        default void foo() {}
}

Class D {
      Void foo() {
		sout("hello");  //rule1 applies, if subclass does not define foo()
F
	}

}

Class C implements A,B extends D{

	void foo() {
            sout("in class c");  //this will print as per rule 1
         }

	public static void main(String[] s) {
		new C().foo();   //class c method (if provided) orElse class D method
		
	}

}

```


------------------------------------------------------------------

#### Chapter 10
#### Using Optional as as a better alternative to null

* Null references were introduced to indicate the absence of values
* Having null checks on every object and field values is quite bloated and cluttered.
* Groovy had addressed this problem using safe navigation operator. e.g. car?.insurance?.name  - if any value is found to be null in chain, it returns null
* Java introduced the null handling with Optional
* with optional, it is also possible to use map, flatMap and filter functions
* the primitive Optional, OptionalInt, OptionalDouble, OptionalLong are not much useful. unlike Optional<Integer> they lack methods map, flatMap and filter and since
* Optional contains at most one value is not much concern for performance.


------------------------------------------------------------------

#### Chapter 11
#### CompletableFuture: composable asynchronous programming

* The capability of running a time consuming code in a separate thread is the new trend.
* This is achieved by either using your own thread with Runnable and Thread but often with the limitation of not returning any result, since it has void return
* Future is the interface modelled in java5 to define such code which will be executed separately and result is obtained in future i.e. later point of time.
* This Future interface however has some limitations, it does not have capability to chain with other Futures
* Hence, the CompletableFuture is introduced in Java8 for Future, in a similar way as to what Streams is for Collection.
* there are 2 ways of doing parallel ops: 1) parallel stream with map 2) using completable future
* the second way of using completable future provides more flexibility in terms of providing custom executor thread pool
* parallel stream internally uses Runtime.getRuntime().availableProcessors() -1 cores and it may degrade performance if number of jobs increases.
* another advise is to use completablefuture for I/o intensive or network intensive jobs
* and use parallel streams for compute intensive tasks
* join on CompletableFuture does not throw exception.


Pipelining two async CF

* CF.supplyAsync(() -> getTask1Output()).thenCombine(CF.supplyAsync(() -> getTask2Output()), (t1Output, t2Output) -> t1Output * t2Output)

------------------------------------------------------------------

#### Chapter 12
#### New DateTime API in java 8

* Java8 introduced brand new date and Time API with classes LocalDate, LocalDateTime, Period, Instant and Duration in java.time package
* LocalDate represents only date not time. E.g. year, month, dayOfWeek, day etc.
* LocalTime represents only time not date. E.g. minutes, hours, seconds, etc.
* LocalDateTime is a combination of LocalDate and LocalTime.
* Instant: machines cannot understand the date/time. Hence instant is a single numeric representation of time
* It is number of seconds elapsed since unix epoch time, set by convention to midnight of jan 1, 1970 utc.
* Instant cannot be used to represent the human readable time units. It's intended for machines only
* All the classes LocalDate, LocalTime, LocalDateTime, Period, Inst
* Duration: you can create duration between two Temporal instances. viz. LocalTime, Instant, LocalDateTime
* you can manipulate date with methods such as with(), plus(), minus() etc.
* ISO-8601 is a defacto calendar for world clock. You can also use other calendars like Japanese, HijrahDate (Islamic) etc.
* TemporalAdjusters has methods to manipulate date in relative way for complex operations. (Get date of first Sunday of month, Get late day of month etc.)
* You can also implement your own TemporalAdjusturs by implementing interface TemporalAdjuster (not 's' !)


------------------------------------------------------------------

#### Chapter 13
#### Thinking functionally

* A function is said to be following functional programming if it accepts the input and produces output without modifying any shared variable of any object.
* A function is referentially transparent if it always return the same result fo same argument
* Tail recursion can be more efficient than classic recursion since it uses single stack frame.
* Tail recursion is achieved by calling same method at the very end after doing operation.

------------------------------------------------------------------

#### Chapter 14
#### Functional programming techniques

* high order functions are functions with one of the following
* the argument is the function and the return type of the method is function
* currying is a technique where you accept the input x & y, and return the function operating on x and y. The trick is x and y can have values for every operation that defines its logic
* in simple words, it is applicable when you have common business logic that will give different results based on value of parameters.
* E.g. DoubleUnaryOperator<Double> converCelciusToFarhenite(int x, int y), DoubleUnaryOperator<Double> convertKmtoMiles(int x, int y) etc.
* persistent data structures: in functional style  programming it always discouraged to update global variables or mutate existing variables.
* the idea is to create new objects i.e. imitate and use the data structure
