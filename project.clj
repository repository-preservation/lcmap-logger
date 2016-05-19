(defproject gov.usgs.eros/lcmap-logger "0.5.0-SNAPSHOT"
  :description "LCMAP Unified Logging"
  :url "https://github.com/USGS-EROS/lcmap-logger"
  :license {:name "NASA Open Source Agreement, Version 1.3"
            :url "http://ti.arc.nasa.gov/opensource/nosa/"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/data.codec "0.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/core.memoize "0.5.8"]
                 ;; Componentization
                 [com.stuartsierra/component "0.3.0"]
                 ;; Logging and Error Handling -- note that we need to explicitly pull
                 ;; in a version of slf4j so that we don't get conflict messages on the
                 ;; console
                 [twig "0.1.6"]
                 [dire "0.5.3"]
                 [slingshot "0.12.2"]
                 [gov.usgs.eros/lcmap-client-clj "0.0.1"]
                 [gov.usgs.eros/lcmap-config "0.5.0-SNAPSHOT"]
                 ;; Dev and project metadata
                 [leiningen-core "2.5.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-pprint "1.1.1"]
            [lein-codox "0.9.1"]
            [lein-simpleton "1.3.0"]]
  :repl-options {:init-ns lcmap.logger.dev}
  :main lcmap.logger.app
  :target-path "target/%s"
  :codox {:project {:name "LCMAP Unified Logging & Log Services"
                    :description "Unified Logging and Log Services for the USGS Land Change Monitoring Assessment and Projection (LCMAP) Computation and Analysis Platform"}
          :namespaces [#"^lcmap.logger\."]
          :output-path "docs/master/current"
          :doc-paths ["docs/source"]
          :metadata {:doc/format :markdown
                     :doc "Documentation forthcoming"}}
  :profiles {
    ;; configuration for dev environment -- if you need to make local changes,
    ;; copy `:env { ... }` into `{:user ...}` in your ~/.lein/profiles.clj and
    ;; then override values there
    :dev {
      :dependencies [[org.clojure/tools.namespace "0.2.11"]
                     [slamhound "1.5.5"]]
      :aliases {"slamhound" ["run" "-m" "slam.hound"]}
      :source-paths ["dev-resources/src"]
      :plugins [[lein-kibit "0.1.2"]
                [jonase/eastwood "0.2.3"]]}})

