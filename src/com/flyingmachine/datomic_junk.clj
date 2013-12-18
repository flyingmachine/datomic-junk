(ns com.flyingmachine.datomic-junk
  (:require [datomic.api :as d]
            [com.flyingmachine.config :refer :all]
            [environ.core :refer [env]]))
(defconfig config env :datomic)
(def ^:dynamic *db-uri* (config :db-uri))

(defn conn
  []
  (d/connect *db-uri*))

(defn db
  []
  (d/db (conn)))

(def q #(d/q % (db)))

(defn ent
  [id]
  (if-let [exists (ffirst (d/q '[:find ?eid :in $ ?eid :where [?eid]] (db) id))]
    (d/entity (db) exists)
    nil))

(defn ents
  [results]
  (map (comp ent first) results))

(defn ent? [x] (instance? datomic.query.EntityMap x))

(defn add-head
  [head seqs]
  (map #(concat [head] %) seqs))

(defn single-eid-where
  [eid-name [attr-or-condition & conditions]]
  (add-head eid-name
            (concat [(flatten [attr-or-condition])]
                    conditions)))

(defn eid
  [& conditions]
  (-> {:find ['?c]
       :where (single-eid-where '?c conditions)}
      q
      ffirst))

(defn one
  [& conditions]
  (if-let [id (apply eid conditions)]
    (ent id)))

(defn all
  [& conditions]
  (ents (q {:find ['?c]
            :where (single-eid-where '?c conditions)})))

(defn ent-count
  [& conditions]
  (or (ffirst (q {:find '[(count ?c)]
                  :where (single-eid-where '?c conditions)}))
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
