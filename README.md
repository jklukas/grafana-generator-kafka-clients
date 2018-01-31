# grafana-generator-kafka-clients

Run via `sbt run` and output will print to the console.

This tool generates a JSON blob like:

```json
"rows": [{"showTitle": true, ...
```

that you can copy and paste into a Grafana dashboard's JSON.
It contains a panel for each metric defined in Kafka's metric
registries for client libs (currently just Connect metrics).

There's a hardcoded prefix appropriate for Simple's environment
and the path includes several variables like `$service` and `$task`
that it's assumed you've set up as dashboard template variables.
