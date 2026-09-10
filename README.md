# Report Engine

A small Java report engine, built as an object-oriented design exercise.
Two report types are produced from a data source and exported to CSV.

## Design

- `Report` is abstract and holds id, title, status and records. `SalesReport`
  and `InventoryReport` implement `generateSummary()`.
- `ReportFactory` builds a report from a type string and assigns its id.
- `DataSource` is an interface, so `ReportService` never depends on where the
  data comes from. Tests substitute a Mockito mock for it.
- `Exportable` isolates the output format. `CsvExporter` is the only
  implementation today.

### Why a factory rather than a switch in the service

`ReportService` has one job: check the source is reachable, fetch the records,
hand back a report. If it also decided which concrete class to instantiate, it
would depend on every report type, and every new type would mean editing the
service and re-testing it. The factory keeps that decision in one place and
also owns id generation, so ids stay unique whoever asks for a report.

Adding a third type costs one new subclass and one branch in the factory.
`ReportService`, its tests, and the exporters do not change.

The trade-off is that the type is a string checked at runtime: an unknown value
fails with a `ReportException` when the code runs, not when it compiles. An
enum would move that check to compile time and is the next thing I would change.

## Run

```bash
mvn compile exec:java
mvn test
```

The main class is configured in the `exec-maven-plugin` in `pom.xml`, so no
`-Dexec.mainClass=...` flag is needed (this also avoids a PowerShell quoting
gotcha with that flag on Windows).

Expected output:

```
Sales Report : 'Bilan annuel' : [3] records, total : [245.5]

product,amount
Pizza,120.0
Pasta,80.5
Salad,45.0
```

## Stack

Java 17, Maven, JUnit 5, Mockito.