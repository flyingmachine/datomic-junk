(ns com.flyingmachine.datomic-junk.load
  "Allows you to more succinctly write entity data for loading into
  datomic. See https://github.com/flyingmachine/datomic-junk#loading
  for details."
  (:require [datomic.api :as d]
            [com.flyingmachine.datomic-junk :as dj]
            [clojure.edn :as edn]))

(declare create-entities)

(defn- entity-map-val
  "When the val is a map, find the corresponding ent id"
  [v uri]
  (apply dj/eid-by (d/db (d/connect uri)) v))

(defn- entity-vector-val
  "When the val is a vector, create child ents"
  [v uri partition]
  (let [x (create-entities uri partition v)]
    (map (comp first vals :tempids) x)))

(defn- entity-val
  [v uri partition]
  (cond (map? v) (entity-map-val v uri)
        (vector? v) (entity-vector-val v uri partition)
        :else v))

(defn- entity-transaction
  ([raw-entity uri partition]
     (entity-transaction raw-entity uri partition []))
  ([raw-entity uri partition tx-data]
     (reduce (fn [final [k v]]
               (assoc final k (entity-val v uri partition)))
             {:db/id (d/tempid partition)}
             raw-entity)))

(defn merge-common
  "Allows you to express common attributes across entities"
  [raw]
  (reduce (fn [records x]
            (if (sequential? x)
              (into records (map #(merge (first x) %) (rest x)))
              (conj records x)))
          []
          raw))

(defn create-entities
  [uri partition entities]
  (doall (map (fn [raw-entity]
                (let [tx-data [(entity-transaction raw-entity uri partition)]]
                  @(d/transact (d/connect uri) tx-data)))
              (merge-common entities))))

(defn load-edn-string
  [string uri partition]
  (create-entities uri partition (edn/read-string string)))
