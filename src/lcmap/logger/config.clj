(ns lcmap.logger.config
  (:require [lcmap.config.helpers :as helpers]
            [schema.core :as schema]))

(def opt-spec [])

(def logger-schema
  {:lcmap.logging {:level schema/Str
                   :namespaces [schema/Str]}})
(def cfg-schema
  (merge logger-schema
         {schema/Keyword schema/Any}))

(def defaults
  {:ini helpers/*lcmap-config-ini*
   :args *command-line-args*
   :spec opt-spec
   :schema cfg-schema})
