(ns lcmap.logger.components.system
  "Top-level LCMAP Unified Logging system component

  For more information, logger the module-level code comments in
  ``lcmap.logger.components``."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord LCMAPLoggerSystem []
  component/Lifecycle

  (start [component]
    (log/info "LCMAP Unified Logging system dependencies started; finishing LCMAP Unified Logging startup ...")
    ;; XXX add any start-up needed for system as a whole
    (log/debug "LCMAP Unified Logging System startup complete.")
    component)

  (stop [component]
    (log/info "Shutting down top-level LCMAP Unified Logging ...")
    ;; XXX add any tear-down needed for system as a whole
    (log/debug "Top-level shutdown complete; shutting down system dependencies ...")
    component))

(defn new-lcmap-logger-toplevel []
  (->LCMAPLoggerSystem))
