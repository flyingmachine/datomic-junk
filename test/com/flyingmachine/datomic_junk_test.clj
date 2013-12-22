(ns com.flyingmachine.datomic-junk-test
  (:require [com.flyingmachine.datomic-junk :refer :all]
            [datomic.api :as d])
  (:use midje.sweet))

(d/delete-database *db-uri*)
(d/create-database *db-uri*)

(def schema
  (into [] (map #(merge {:db/id (d/tempid :db.part/db)
                         :db.install/_attribute :db.part/db
                         :db/cardinality :db.cardinality/one}
                        %)
                [{:db/ident :test/name
                  :db/valueType :db.type/string}
                 {:db/ident :test/number
                  :db/valueType :db.type/long}])))

(def data [{:db/id #db/id[:db.part/db]
            :test/name "Bartleby"
            :test/number 3}
           {:db/id #db/id[:db.part/db]
            :test/name "Jean Valjean"
            :test/number 24601}])

(t schema)
(t data)

(fact "conn works"
  (instance? datomic.peer.LocalConnection (conn))
  => true)

(fact "returns db from conn"
  (instance? datomic.db.Db (db))
  => true)

(fact "q is datomic.api/q without having to pass db"
  (count (q '[:find ?t :where [?t :test/name]]))
  => 2)

(facts "about ent"
  (fact "ent returns nil if given a bogus id"
    (ent 12345)
    => nil)
  (fact "ent returns a datomic entity if given a good id"
    (ent? (ent (ffirst (q '[:find ?t :where [?t :test/name]]))))
    => true))

(fact "ents returns datomic entities given query results"
  (map :db/id (ents [[65] [66]]))
  => [65 66])

(facts "about eid"
  (fact "eid returns an entity id"
    (eid [:test/name])
    => 65)
  (fact "eid returns nil when nothing found"
    (eid [:test/name "blarb"])
    => nil))

(facts "about one"
  (fact "returns an entity given an id if it matches"
    (one [:test/name "Bartleby"])
    => (ent 65))
  (fact "works with just an attriube"
    (one :test/name)
    => (ent 65))
  (fact "returns nil if nothing matches"
    (one [:test/name "blarb"])
    => nil))

(facts "about all"
  (fact "returns ents"
    (map :db/id (all [:test/name]))
    => [65 66])
  (fact "works with just an attribute"
    (map :db/id (all :test/name))
    => [65 66])
  (fact "and you can provide an attribute value of course"
    (map :db/id (all [:test/name "Bartleby"]))
    => [65])
  (fact "returns empty seq if no results"
    (all [:test/name "blarb"])
    => empty?))

(facts "ent-count returns a count"
  (ent-count [:test/name])
  => 2
  (ent-count :test/name)
  => 2
  (ent-count [:test/name "blarb"])
  => 0)

(fact "t runs a transaction"
  @(t [{:test/name "Henry" :test/number 123 :db/id (d/tempid :db.part/user)}])
  (:test/name (one [:test/name "Henry"]))
  => "Henry")

(fact "retract retracts entities"
  @(t [{:test/name "Henry" :test/number 123 :db/id (d/tempid :db.part/user)}])
  (apply retract (map :db/id (all [:test/name "Henry"])))
  (one [:test/name "Henry"])
  => nil)

(fact "you can specify the db with with-db-uri or :inputs"
  (let [db (db)]
    (with-db-uri "datomic:mem://datomic-junk-uncreated-db"
      (d/delete-database *db-uri*)
      (d/create-database *db-uri*)
      (t schema)

      (ent-count [:test/name])
      => 0

      (ent-count :test/name :inputs db)
      => 2

      (map :db/id (ents [[65] [66]] db))
      => [65 66]

      (with-db db
        (ent-count [:test/name])
        => 2))))
