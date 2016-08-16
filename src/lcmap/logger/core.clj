(ns lcmap.logger.core
  (:require [clojure.tools.logging.impl :as impl]))

(defn- -log
  "TBD

  Originally from clojure.tools.logging.impl."
  ([level logger msg]
     (condp = level
       :trace (.trace logger msg)
       :debug (.debug logger msg)
       :info (.info logger msg)
       :warn (.warn logger msg)
       :error (.error logger msg)
       :fatal (.error logger msg)
       (throw (IllegalArgumentException. (str level)))))
  ([level logger msg e]
    (condp = level
       :trace (.trace logger msg e)
       :debug (.debug logger msg e)
       :info (.info logger msg e)
       :warn (.warn logger msg e)
       :error (.error logger msg e)
       :fatal (.error logger msg e)
       (throw (IllegalArgumentException. (str level))))))

(defn log
  "A general log function that allows for interception so that the LCMAP
  logging infrastructure may do more than just run a given log function
  (e.g., send log data to a centralised service)."
  ([level logger msg]
    ;; XXX call intercept function here, e.g., call to messaging service
    (-log level logger msg))
  ([level logger msg e]
    ;; XXX call intercept function here, e.g., call to messaging service
    (-log level logger msg e)))

(defn lcmap-logger-factory
  "Returns an LCMAP, messaging-ready, SLF4J-based implementation of the
  LoggerFactory protocol, or nil if not available.

  Copied from clojure.tools.logging.impl."
  []
  (try
    (Class/forName "org.slf4j.Logger")
    (eval
      `(do
        (extend org.slf4j.Logger
          impl/Logger
          {:enabled?
           (fn [^org.slf4j.Logger logger# level#]
             (condp = level#
               :trace (.isTraceEnabled logger#)
               :debug (.isDebugEnabled logger#)
               :info (.isInfoEnabled logger#)
               :warn (.isWarnEnabled logger#)
               :error (.isErrorEnabled logger#)
               :fatal (.isErrorEnabled logger#)
               (throw (IllegalArgumentException. (str level#)))))
           :write!
           (fn [^org.slf4j.Logger logger# level# ^Throwable e# msg#]
             (let [^String msg# (str msg#)]
               (if e#
                 (log level# logger# msg# e#)
                 (log level# logger# msg#))))})
        (reify impl/LoggerFactory
          (name [_#]
            "org.slf4j")
          (impl/get-logger [_# logger-ns#]
            (org.slf4j.LoggerFactory/getLogger ^String (str logger-ns#))))))
  (catch Exception e nil)))
