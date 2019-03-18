### PetersonAlgorithm.java
It is realization Peterson's algorithm that was created in 1981 years.

#### Advantages:
* Does not allow two threads at the same time be in critical section.

#### Disadvantages:
* Designed for only two threads.
* Because of the infinite loop, the waiting thread is wasting CPU resources.