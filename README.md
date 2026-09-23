# Java Compute

[![CI](https://github.com/kazimdafedar/java-compute/actions/workflows/ci.yml/badge.svg)](https://github.com/kazimdafedar/java-compute/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A hands-on Java portfolio project focused on **Streams API**, **Collectors**, and **functional interfaces** (`Predicate`, `Supplier`). Each example is runnable, unit-tested, and written for clarity — useful for interview prep and day-to-day data processing.

## Why this project

Modern Java backends rely heavily on declarative data transformations. This repo demonstrates practical stream patterns recruiters and interviewers commonly ask about: grouping, flat-mapping, optional handling, custom collectors, and functional composition.

## Examples

| Example | Concept | Key API |
|---------|---------|---------|
| `FlattenListExample` | Flatten nested lists | `flatMap`, `collect` |
| `GroupByAgeExample` | Group and sort domain objects | `groupingBy`, `Comparator` |
| `GroupByLengthExample` | Group strings, map keys, find unique chars | `groupingBy`, `toMap` |
| `PairSumExample` | Find pairs with target sum | `flatMap`, `filter` |
| `SecondHighestExample` | Nth highest distinct value | `distinct`, `skip`, `Optional` |
| `SumOfSquaresExample` | Primitive stream aggregation | `mapToInt`, `sum` |
| `PredicateExample` | Reusable validation logic | `Predicate` |
| `SupplierExample` | Lazy value provision | `Supplier` |

## Project structure

```
src/main/java/com/kazim/javacompute/
├── examples/          # Runnable stream examples
└── model/             # Shared domain types (Person record)

src/test/java/         # JUnit 5 tests
.github/workflows/     # CI pipeline (build + test on every push)
```

## Getting started

**Requirements:** JDK 25+, Maven 3.9+

```bash
git clone https://github.com/kazimdafedar/java-compute.git
cd java-compute

# Run all tests
mvn test

# Run a specific example
mvn -q exec:java -Dexec.mainClass=com.kazim.javacompute.examples.FlattenListExample
```

## Run any example

Replace the class name below with any example from the table above:

```bash
mvn -q exec:java -Dexec.mainClass=com.kazim.javacompute.examples.SecondHighestExample
```

## Roadmap

- [ ] Add `partitioningBy` and custom collector examples
- [ ] Add parallel stream benchmarks
- [ ] Publish a short "Streams cheat sheet" in the wiki

## Author

**Kazim Dafedar** — [LinkedIn](https://www.linkedin.com/in/kazim-dafedar/) · [GitHub](https://github.com/kazimdafedar)

## License

MIT — see [LICENSE](LICENSE).
