; "Eid" is taken from
; https://github.com/Datomic/day-of-datomic/blob/master/src/datomic/samples/query.clj

(ns com.flyingmachine.datomic-junk
  (:require [datomic.api :as d]))

(defn max1
  [query-result]
  (assert (< (count query-result) 2))
  (assert (< (count (first query-result)) 2))
  (ffirst query-result))

;; Get id whether from number or datomic ent
(defprotocol Eid
  (e [_]))

(extend-protocol Eid
  java.lang.Long
  (e [n] n)

  datomic.Entity
  (e [ent] (:db/id ent)))

(defn ent
  "Datomic entity from id, or nil if none exists"
  [id db]
  (if-let [exists (max1 (d/q '[:find ?eid
                               :in $ ?eid
                               :where [?eid]]
                             db (e id)))]
    (d/entity db exists)
    nil))

(defn ents
  [db results]
  (map (fn [result]
         (-> result
             first
             (ent db)))
       results))

(defn ent? [x] (instance? datomic.query.EntityMap x))

;; The following functions help when retrieving entities when you
;; don't need to specify their relationships to other entities
;; and you only have one input, the db
(defn add-head
  [head seqs]
  (map #(into [head] %) seqs))

(defn single-eid-where
  "Used to build where clauses for functions below"
  [eid [attr-or-condition & conditions]]
  (add-head eid
            (concat [(flatten [attr-or-condition])]
                    conditions)))

(defn parse-conditions
  [eid conditions]
  (let [[where & opts] (partition-by #(or (= :in %) (= :inputs %)) conditions)]
    (merge {:where (single-eid-where eid where)
            :in ['$]}
           (reduce merge {}
                   (map #(hash-map (ffirst %) (second %))
                        (partition 2 opts))))))

(defn single-eid-query
  [find eid conditions]
  (let [parsed-conditions (parse-conditions eid conditions)]
    (apply d/q (merge {:find find}
                      (dissoc parsed-conditions :inputs))
           (:inputs parsed-conditions))))

(defn no-relation-where
  "Used to build where clauses for functions below"
  [eid [attr-or-condition & conditions]]
  (add-head eid
            (concat [(flatten [attr-or-condition])]
                    conditions)))

(defn no-relation-query-map
  [find variable conditions]
  {:find find
   :where (no-relation-where variable conditions)
   :in ['$]})

(defn no-relation-query
  [db find variable conditions]
  (d/q (no-relation-query-map find variable conditions) db))

(defn eid-by
  "Return eid of first entity matching conditions"
  [db & conditions]
  (ffirst (no-relation-query db ['?x] '?x conditions)))

(defn one
  "Return first entity matching conditions"
  [db & conditions]
  (if-let [id (apply eid-by db conditions)]
    (d/entity db id)))

(defn all
  "All entities matching condititions"
  [db & conditions]
  (ents db (no-relation-query db ['?x] '?x conditions)))

(defn ent-count
  [db & conditions]
  (or (ffirst (no-relation-query db '[(count ?x)] '?x conditions))
      0))

;; Transaction helpers
(defn tempids
  [& keys]
  (into {} (map #(vector %1 (d/tempid :db.part/user %2)) keys (iterate dec -1))))

(defn retractions
  [eids]
  (map #(vector :db.fn/retractEntity %) eids))

(defn retract
  [conn & eids]
  (d/transact conn (retractions eids)))

(def t d/transact)
