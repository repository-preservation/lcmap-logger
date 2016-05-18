(ns lcmap.logger.app
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [twig.core :as twig]
            [lcmap.logger.components :as components]
            [lcmap.logger.util :as util])
  (:gen-class))

(defn -main
  "This is the entry point. Note, however, that the system components are
  defined in lcmap.logger.components. In particular,
  lcmap.logger.components.system brings together all the defined (and active)
  components; that is the module which is used to bring the system up when
  (component/start ...) is called.

  'lein run' will use this as well as 'java -jar'."
  [& args]
  ;; Set the initial log-level before the components set the log-levels for
  ;; the configured namespaces
  (twig/set-level! ['lcmap] :info)
  (let [system (components/init)
        local-ip  (.getHostAddress (java.net.InetAddress/getLocalHost))]
    (log/info "LCMAP Unified Logging service's local IP address:" local-ip)
    (component/start system)
    (util/add-shutdown-handler #(component/stop system))))
