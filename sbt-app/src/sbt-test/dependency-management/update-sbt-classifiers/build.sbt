ivyConfiguration := {
  throw new RuntimeException("updateSbtClassifiers should use updateSbtClassifiers / ivyConfiguration")
}

dependencyResolution := {
  throw new RuntimeException("updateSbtClassifiers should use updateSbtClassifiers / dependencyResolution")
}

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.11.12",
    scalaOrganization := "doesnt.exist",
    name := "myProjectName",

    TaskKey[Unit]("checkModuleIdsInUpdateSbtClassifiers") := {
      val updateReport = updateSbtClassifiers.value
      val moduleReports = updateReport.configurations.find(_.configuration.name == "default").get.modules

      // Calling "distinct" as there are different entries for sources and javadoc classifiers with same module
      val moduleIds = moduleReports.map(_.module).distinct
      val moduleIdsShort = moduleIds.map(m => s"${m.organization}:${m.name}")

      val expectedModuleIds = Seq(
        "org.scala-sbt:zinc-classpath_3",
        "org.jline:jline-reader",
        "org.scala-sbt:zinc-persist_3",
        "org.apache.logging.log4j:log4j-api",
        "org.scala-lang:scala3-library_3",
        "com.eed3si9n:sjson-new-core_3",
        "org.scala-sbt:test-interface",
        "org.scala-lang.modules:scala-parser-combinators_3",
        "org.jline:jline-terminal-jna",
        "org.scala-lang:scala3-interfaces",
        "org.scala-sbt:compiler-interface",
        "org.reactivestreams:reactive-streams",
        "org.scala-sbt:launcher-interface",
        "com.eed3si9n:sjson-new-murmurhash_3",
        "org.jline:jline-terminal-jni",
        "net.java.dev.jna:jna",
        "org.scala-lang:tasty-core_3",
        "com.eed3si9n:gigahorse-apache-http_3",
        "com.lmax:disruptor",
        "org.scala-sbt:zinc-classfile_3",
        "org.slf4j:slf4j-api",
        "org.scala-sbt:zinc_3",
        "org.scala-lang.modules:scala-xml_3",
        "org.scala-sbt.ipcsocket:ipcsocket",
        "com.typesafe:ssl-config-core_3",
        "org.jline:jline-terminal",
        "org.scala-sbt:sbinary_3",
        "org.scala-sbt:zinc-core_3",
        "com.eed3si9n:shaded-apache-httpasyncclient",
        "org.scala-sbt:io_3",
        "com.eed3si9n:shaded-scalajson_3",
        "com.typesafe:config",
        "net.openhft:zero-allocation-hashing",
        "org.scala-lang:scala-reflect",
        "com.google.errorprone:error_prone_annotations",
        "org.scala-lang:scala-library",
        "com.eed3si9n:gigahorse-core_3",
        "com.eed3si9n:sjson-new-scalajson_3",
        "org.scala-sbt:zinc-apiinfo_3",
        "org.scala-lang:scala3-compiler_3",
        "com.swoval:file-tree-views",
        "org.fusesource.jansi:jansi",
        "org.jline:jline-builtins",
        "org.apache.logging.log4j:log4j-core",
        "org.jline:jline-native",
        "org.scala-sbt:template-resolver",
        "com.github.ben-manes.caffeine:caffeine",
        "com.eed3si9n:shaded-jawn-parser_3",
        "org.apache.logging.log4j:log4j-slf4j-impl",
        "org.scala-lang.modules:scala-parallel-collections_3",
        "org.checkerframework:checker-qual",
        "org.scala-sbt.jline:jline",
        "org.scala-lang.modules:scala-asm",
        "org.jline:jline-style",
        "org.scala-sbt:zinc-compile-core_3",
        "net.java.dev.jna:jna-platform",
        "org.scala-sbt.ivy:ivy",
        "com.github.mwiede:jsch",
      )
      def assertCollectionsEqual(message: String, expected: Seq[String], actual: Seq[String]): Unit =
        // using the new line for a more readable comparison failure output
        org.junit.Assert.assertEquals(message: String, expected.mkString("\n"), actual.mkString("\n"))

      assertCollectionsEqual(
        "Unexpected module ids in updateSbtClassifiers",
        expectedModuleIds,
        moduleIdsShort,
      )
    }
  )