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

(fact "q works"
  (count (q '[:find ?t :where [?t :test/name]]))
  => 2)

(facts "about ent"
  (fact "ent returns nil if given a bogus id"
    (ent 12345)
    => nil)
  (fact "ent returns a datomic entity if given a good id"
    (ent? (ent (ffirst (q '[:find ?t :where [?t :test/name]]))))
    => true))

