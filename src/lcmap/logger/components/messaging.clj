(ns lcmap.logger.components.messaging
  "Database LCMAP REST Service system component

  For more information, see the module-level code comments in
  ``lcmap.logger.components``."
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [langohr.core :as langohr]
            [langohr.channel]
            [langohr.queue]
            [langohr.exchange]
            [langohr.consumers]
            [langohr.basic]))

(defn get-config
  ""
  [component]
  (get-in component [:cfg :lcmap.logger]))

(defn config->opts
  ""
  [data]
  {:host (:msg-host data)
   :port (:msg-port data)
   :username (:msg-username data)
   :password (:msg-password data)
   :vhost (:msg-vhost data)
   :connection-name (:msg-connection-name data)})

(defrecord MessagingClient []
  component/Lifecycle

  (start [component]
    (log/info "Starting LCMAP Unified Logging messaging ...")
    (let [msg-cfg (get-config component)
          opts (config->opts msg-cfg)]
      (log/debug "Using config:" msg-cfg)
      (let [conn (langohr/connect opts)
            ch (langohr.channel/open conn)
            ex-name (:msg-exchange-name msg-cfg)
            ex-type "direct"
            ex-opts {:durable false :auto-delete true}]
        (log/debug "Setting up logger message exchange ...")
        (langohr.exchange/declare ch ex-name ex-type ex-opts)
        (log/debug "Setting up logger message queues ...")
        (as-> msg-cfg val
              (:msg-queue val)
              (langohr.queue/declare ch val)
              (langohr.queue/bind ch (:queue val) ex-name))
        (log/debug "Component keys:" (keys component))
        (log/debug "Successfully created messaging connection:" conn)
        (-> component
            (assoc :conn conn)
            (assoc :ch ch)))))

  (stop [component]
    (log/info "Stopping LCMAP Unified Logging messaging ...")
    (log/debug "Component keys" (keys component))
    (let [msg-cfg (get-config component)
          conn (:conn component)
          ch (:ch component)]
      (log/debug "Unbinding queues ...")
      (->> msg-cfg
           :msg-queues
           (map #(langohr.queue/unbind ch % (:msg-exchange-name))))
      (log/debugf "Deleting connection and channel objects: %s, %s" conn ch)
      (if (nil? ch)
        (log/debug "Channel not defined")
        (langohr/close ch))
      (if (nil? conn)
        (log/debug "Connection not defined")
        (langohr/close conn))
      (-> component
          (assoc :ch nil)
          (assoc :conn nil)))))

(defn new-messaging-client []
  (->MessagingClient))
