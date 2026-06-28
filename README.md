# Gardening Robot Project

This project is a Java application that simulates an autonomous gardening robot capable of performing different gardening tasks such as watering, planting, mowing, harvesting, fertilizing, and treating plants.

The project demonstrates object-oriented design and the use of several classic design patterns to create a modular, extensible, and maintainable system.

## Overview

The application models a robotic gardening system where robots interact with a virtual garden, execute commands, communicate with external systems, manage tools, and change their behavior depending on their current state.

The main goal of the project is to demonstrate how design patterns can be combined to solve real software engineering problems.

## Implemented Design Patterns

### Proxy

`RobotProxy` acts as an intermediary between the client and the real `Robot`. It forwards requests while allowing additional logic, such as validation without modifying the robot itself.

### Adapter

`OldWateringAdapter` adapts the legacy `OldWateringSystem` to the common `ITool` interface. This allows old watering equipment to be used by robots alongside modern tools.

### Decorator

Tool functionality can be extended dynamically using decorators.

Implemented decorators include:

* `LightToolDecorator`
* `SoundToolDecorator`
* `VoiceToolDecorator`

Each decorator wraps another tool and adds additional behavior without changing the original tool implementation.

### Composite

`RobotGroup` represents a collection of robots that can be treated as a single object. Individual robots and robot groups share a common interface, allowing operations to be performed on both uniformly.

### Iterator

The project provides custom iterators:

* `RobotComponentIterator`
* `RobotGroupIterator`

These iterators allow sequential traversal of robot groups without exposing their internal structure.

### Bridge

The Bridge pattern separates power management strategies from power source implementations.

- **Abstraction:** `PowerManager`
- **Refined Abstractions:**
  - `EcoPowerManager`
  - `BalancePowerManager`
  - `MaxPerformanceManager`
- **Implementor:** `IPowerSource`
- **Concrete Implementors:** power source classes implementing `IPowerSource`.

`PowerManager` maintains a reference to an `IPowerSource` object and delegates low-level energy operations such as charging, energy consumption, switching to a backup source, and retrieving the current power level. The refined abstractions (`EcoPowerManager`, `BalancePowerManager`, and `MaxPerformanceManager`) implement different power management strategies while remaining independent of the concrete power source implementation.


### Flyweight

`MapImageFactory` stores and reuses shared `MapSegmentImage` objects instead of creating identical images repeatedly. This reduces memory usage by caching image instances.

### Facade

`GardenFacade` provides a simplified interface for interacting with the garden map. Clients can perform complex map operations without working directly with multiple map-related classes.

### Information Expert

Responsibilities are assigned to the classes that own the necessary data.

Examples include:

* `Robot` manages its own state and actions.
* `CentralController` coordinates robots and task execution.
* Knowledge base classes manage gardening information.
* `MapSegment` stores information about individual map cells.

### Factory Method

Specialized factories create different robot types.

Examples include:

* `WateringRobotFactory`
* `HarvestingRobotFactory`
* `FertilizingRobotFactory`

Each factory encapsulates the construction logic for one robot type.

### Abstract Factory

Families of related robot components are created through abstract factories.

Examples include factories for:

* movement systems
* navigation
* communication
* power sources
* power managers
* tools

This guarantees compatible components while keeping creation logic independent from client code.

### Singleton

`CentralController` is implemented as a Singleton.

The static `getInstance()` method guarantees that only one central controller exists during program execution.

### Prototype

The project supports cloning through the `Prototype<T>` interface.

`Robot`, tools, movement systems, navigation modules, communication modules, and other components implement deep cloning, allowing complete robot configurations to be duplicated efficiently.

### Object Pool

`GenericToolPool` manages reusable tool objects.

Instead of creating new tools every time, tools are acquired from the pool and returned after use, reducing object creation overhead.

### Builder

Garden map segments are constructed step by step using builders.

Main classes include:

* `MapSegmentBuilder`
* `StandartMapSegmentBuilder`

Different directors (such as lawn and garden directors) configure builders to create various map layouts.

### State

Robot behavior depends on its current state.

Implemented states include:

* `IdleState`
* `MovingState`
* `WorkingState`
* `ChargingState`
* `ErrorState`

Each state defines how the robot responds to commands and events.

### Memento

`CentralController` implements the Memento pattern using the nested `Snapshot` and `HistoryManager` classes.

Snapshots store deep copies of robots and the tool pool, allowing the system state to be restored using undo functionality.

### Observer

Robots implement the observable interface and notify registered observers about important events through `RobotEvent`.

This keeps monitoring components loosely coupled from robot logic.

### Command

Each gardening action is represented by a separate command object.

Implemented commands include:

* `MoveCommand`
* `WaterCommand`
* `PlantCommand`
* `HarvestCommand`
* `FertilizeCommand`
* `MowCommand`
* `TreatCommand`
* `WeedCommand`
* `ChargeCommand`

Commands encapsulate robot actions independently from the controller.

### Chain of Responsibility

Tasks are processed through a chain of `RobotTaskHandler` objects.

Each handler attempts to execute the command. If it cannot, the request is forwarded to the next robot in the chain until a suitable robot is found.

### Visitor

`RobotActivityVisitor` performs operations on map segments without modifying their classes.

This allows new processing logic to be added independently of the map implementation.

## Project Structure

The project is organized into several packages:

* **commands** — robot commands
* **components** — communication modules and knowledge bases
* **core** — interfaces and core abstractions
* **factories** — robot factories
* **map** — garden map implementation
* **robot** — robot logic and states
* **tools** — robot tools, decorators, and adapters
* **visitor** — visitor implementations

## How to Build

Compile the project using your preferred Java build process or IDE.

For example, from the project root:

```bash
javac -d out $(find src -name "*.java")
```

## How to Run

After compilation, run the main application:

```bash
java -cp out Main
```

If you are using an IDE (such as IntelliJ IDEA or Eclipse), simply run the `Main` class.
