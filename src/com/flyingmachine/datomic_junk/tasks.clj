(ns com.flyingmachine.datomic-junk.tasks
  "Helpers for creating and migrating databases. Assumes schemas are
  in resources/schemas"
  (:gen-class)
  (:require [datomic.api :as d]
            [clojure.java.io :as io]
            [com.flyingmachine.datomic-junk :as dj]
            [com.flyingmachine.datomic-junk.schema :as schema]))

;; Helper functions
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

(defn recreate
  "Delete and create a database"
  [uri]
  (d/delete-database uri)
  (d/create-database uri))

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
  (->> schema-names
       (map #(vector % (schema-data %)))
       (into {})))

(defn install-schemas
  [conn schema-attr schema-names]
  (apply schema/ensure-schemas
         conn
         schema-attr
         (schema-map schema-names)
         schema-names))

(defn rename-schemas
  [db schema-attr name-map]
  (let [schemas (dj/all db schema-attr)]
    (println schemas)
    (filter identity
            (map (fn [schema]
                   (if-let [new-name (get name-map (get schema schema-attr))]
                     [:db/add (:db/id schema) schema-attr new-name]))
                 schemas))))

(defn reload
  [uri schema-attr schema-names]
  (recreate uri)
  (install-schemas (d/connect uri) schema-attr schema-names))

(defn seed
  [conn]
  (dj/t conn (read-resource "fixtures/seeds.edn")))
