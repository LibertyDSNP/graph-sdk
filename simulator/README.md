# Graph Simulator

This is a stand-alone utility that allows us to run different scenarios on the whole of social graph and test
the desired behavior against the sdk latest implementation.

## Initialized State
Calculating the initial state for all the graphs and keys is time-consuming, and we've already calculated
the state for 20000 users in the graph which is uploaded [here](https://drive.google.com/file/d/1WoQ_INPa4aOy4dCm1WUN9KXxq08P0-Rp/view?usp=sharing)
and if copied inside repository's root folder it can skip the initialization process, and you can directly run
test scenarios on already established state.

### Build
For being able to run the 20k users example the **maxPageId** on _frequency.json_ file should be changed to at least **64**
and build the simulator.

We will update this value after it was updated un Frequency chain first.

`make build-sim`

### Run

`make run-sim`