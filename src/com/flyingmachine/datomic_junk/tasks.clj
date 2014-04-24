(ns com.flyingmachine.datomic-junk.tasks
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.java.io :as io]
            [com.flyingmachine.datomic-junk :as dj]
            [com.flyingmachine.datomic-junk.schema :as schema]))

(defn slurp-resource
  [path]
  (-> path
      io/resource
      slurp))

(defn read-resource
  [path]
  (-> path
      slurp-resource
      read-string))

(defn create
  []
  (d/create-database dj/*db-uri*))

(defn delete
  []
  (d/delete-database dj/*db-uri*))

(defn recreate
  []
  (delete)
  (create))

(def schema-attr (dj/config :schema-attr))
(defn schema-path
  [schema-name]
  (str "schemas/" (name schema-name) ".edn"))

(defn schema-data
  [schema-name]
  {:txes [(-> schema-name
              schema-path
              read-resource)]})

(defn schema-map
  [schema-names]
  (reduce (fn [m name]
            (assoc m name (schema-data name)))
          {}
          schema-names))

(defn install-schemas
  [schemas]
  (apply schema/ensure-schemas
         (into [(dj/conn) schema-attr (schema-map schemas)] schemas)))

(defn rename-schemas
  [name-map]
  (let [schemas (dj/all schema-attr)]
    (filter identity (map (fn [schema]
                            (if-let [new-name (get name-map (get schema schema-attr))]
                              [:db/add (:db/id schema) schema-attr new-name]))
                          schemas))))

(defn reload
  [schemas]
  (recreate)
  (install-schemas schemas))
