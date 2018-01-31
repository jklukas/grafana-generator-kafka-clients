package example

import org.apache.kafka.connect.runtime.ConnectMetricsRegistry

import collection.JavaConverters._

object Hello extends Greeting with App {
  val prefix = "services.*.$service.*.org.apache.kafka.common.metrics"
  val connect = new ConnectMetricsRegistry
  var panelId = 501
  val rows = connect
    .getAllTemplates()
    .asScala
    .toList
    .groupBy(_.group()).map { case (group, templates) =>
    val panels = templates.map { template =>
      val tags = template.tags().asScala.map(s => "$" + s)
      val items = Seq(prefix, group) ++ tags ++ Seq(template.name())
      val target = items.mkString(".")
      val name = template.name()
      panelId += 1
      s"""    {"type": "graph", "id": $panelId, "span": 3, "datasource": "$$graphite", "title": "$name", "targets": [{"target": "$target"}]}"""
      }.mkString(",")
    s"""{"showTitle": true, "collapse": true, "title": "Kafka Connect group=$group", "panels": [${panels}]}"""
  }
  println(s""""rows": [${rows.mkString(",")}],""")
}

trait Greeting {
  lazy val greeting: String = "hello"
}
