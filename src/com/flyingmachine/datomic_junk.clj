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

(defn eid
  [& conditions]
  (let [conditions (add-head '?c conditions)]
    (-> {:find ['?c]
         :where conditions}
        q
        ffirst)))

(defn one
  [& conditions]
  (if-let [id (apply eid conditions)]
    (ent id)))

(defn all
  [common-attribute & conditions]
  (let [common (flatten ['?c common-attribute])
        conditions (concat [common]
                           (add-head '?c conditions))]
    (ents (q {:find ['?c] :where conditions}))))

(defn ent-count
  [& conditions]
  (or (ffirst (q {:find '[(count ?c)]
                  :where (add-head '?c conditions)}))
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
