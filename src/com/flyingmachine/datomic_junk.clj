(ns com.flyingmachine.datomic-junk
  (:require [datomic.api :as d]
            [com.flyingmachine.config :refer :all]
            [environ.core :refer [env]]))
(defconfig config env :datomic)
(def ^:dynamic *db-uri* (config :db-uri))
(def ^:dynamic *db* nil)

(defmacro with-db-uri
  [db-uri & body]
  `(binding [*db-uri* ~db-uri]
     ~@body))

(defmacro with-db
  [db & body]
  `(binding [*db* ~db]
     ~@body))

(defn conn
  []
  (d/connect *db-uri*))

(defn db
  []
  (d/db (conn)))

(defn q
  ([query] (d/q query (or *db* (db))))
  ([query & inputs] (apply d/q query inputs)))

(defn ent
  "Datomic entity from id, or nil if none exists"
  [id]
  (let [db (db)]
    (if-let [exists (ffirst (d/q '[:find ?eid :in $ ?eid :where [?eid]] db id))]
      (d/entity db exists)
      nil)))

(defn ents
  [results]
  (map (comp ent first) results))

(defn ent? [x] (instance? datomic.query.EntityMap x))

(defn add-head
  [head seqs]
  (map #(concat [head] %) seqs))

(defn single-eid-where
  "Used to build where clauses for functions below"
  [eid-name [attr-or-condition & conditions]]
  (add-head eid-name
            (concat [(flatten [attr-or-condition])]
                    conditions)))

(defn single-eid-query
  [find eid conditions]
  (q {:find find
      :where (single-eid-where eid conditions)}))

(defn eid
  "Return eid of first entity matching conditions"
  [& conditions]
  (ffirst (single-eid-query ['?x] '?x conditions)))

(defn one
  "Return first entity matching conditions"
  [& conditions]
  (if-let [id (apply eid conditions)]
    (ent id)))

(defn all
  "All entities matching condititions"
  [& conditions]
  (ents (single-eid-query ['?x] '?x conditions)))

(defn ent-count
  [& conditions]
  (or (ffirst (single-eid-query '[(count ?x)] '?x conditions))
      0))

(def t #(d/transact (conn) %))

(defn resolve-tempid
  [tempids tempid]
  (d/resolve-tempid (db) tempids tempid))

(defn tempids
  [& keys]
  (into {} (map #(vector %1 (d/tempid :db.part/user %2)) keys (iterate dec -1))))

(defn retractions
  [eids]
  (map #(vector :db.fn/retractEntity %) eids))

(defn retract
  [& eids]
  (t (retractions eids)))
