; Eid and only are taken from https://github.com/Datomic/day-of-datomic/blob/master/src/datomic/samples/query.clj
; and are covered by the EPL

(ns com.flyingmachine.datomic-junk
  (:require [datomic.api :as d]))


(defn only
  "Return the only item from a query result"
  [query-result]
  (assert (= 1 (count query-result)))
  (assert (= 1 (count (first query-result))))
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
  [db id]
  (if-let [exists (ffirst (d/q '[:find ?eid
                                 :in $ ?eid
                                 :where [?eid]]
                               db (e id)))]
    (d/entity db exists)
    nil))

(defn ents
  [db results]
  (map (fn [result]
         (->> result
              first
              (ent db)))
       results))

(defn ent? [x] (instance? datomic.query.EntityMap x))

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

(defn eid-by
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
