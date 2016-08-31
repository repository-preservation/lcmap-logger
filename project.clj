(defproject gov.usgs.eros/lcmap-logger "1.0.0-SNAPSHOT"
  :parent-project {
    :coords [gov.usgs.eros/lcmap-system "1.0.0-SNAPSHOT"]
    :inherit [
      :deploy-repositories
      :license
      :managed-dependencies
      :plugins
      :pom-addition
      :repositories
      :target
      ;; XXX The following can be un-commented once this issue is resolved:
      ;;     * https://github.com/achin/lein-parent/issues/3
      ;; [:profiles [:uberjar :dev]]
      ]}
  :description "LCMAP Unified Logging"
  :url "https://github.com/USGS-EROS/lcmap-logger"
  :dependencies [[org.clojure/clojure]
                 [org.clojure/core.match]
                 [org.clojure/data.codec]
                 [org.clojure/data.json]
                 [org.clojure/data.xml]
                 [org.clojure/core.memoize]
                 ;; Componentization
                 [com.stuartsierra/component]
                 ;; Logging
                 [clojusc/twig]
                 ;; Error Handling
                 [dire]
                 [slingshot]
                 ;; Messaging
                 [com.novemberain/langohr]
                 ;; LCMAP Components
                 [gov.usgs.eros/lcmap-config]
                 ;; Dev and project metadata
                 [leiningen-core]]
  :plugins [[lein-parent "0.3.0"]]
  :repl-options {:init-ns lcmap.logger.dev}
  :main lcmap.logger.app
  :codox {:project {:name "lcmap.logger"
                    :description "Unified Logging and Log Services for the USGS Land Change Monitoring Assessment and Projection (LCMAP) Computation and Analysis Platform"}
          :namespaces [#"^lcmap.logger\."]
          :output-path "docs/master/current"
          :doc-paths ["docs/source"]
          :metadata {:doc/format :markdown
                     :doc "Documentation forthcoming"}}
  :profiles {
    :uberjar {:aot :all}
    ;; configuration for dev environment -- if you need to make local changes,
    ;; copy `:env { ... }` into `{:user ...}` in your ~/.lein/profiles.clj and
    ;; then override values there
    :dev {
      :dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]
                     [slamhound "1.5.5"]]
      :aliases {"slamhound" ["run" "-m" "slam.hound"]}
      :source-paths ["dev-resources/src"]}})
