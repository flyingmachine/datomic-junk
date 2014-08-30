(ns com.flyingmachine.datomic-junk.test-helper
  (:require [com.flyingmachine.datomic-junk :as dj]
            [com.flyingmachine.datomic-junk.tasks :as t]
            [datomic.api :as d]))

(def db-uri "datomic:mem://datomic-junk")

(d/delete-database db-uri)
(d/create-database db-uri)

(def recreate (partial t/recreate db-uri))
(def conn (partial d/connect db-uri))
(defn db [] (d/db (conn)))

(defn inflate-attrs
  [attrs]
  (vec (map #(merge {:db/id (d/tempid :db.part/db)
                     :db.install/_attribute :db.part/db
                     :db/cardinality :db.cardinality/one}
                    %)
            attrs)))

(def schema
  (inflate-attrs
   [{:db/ident :test/name
     :db/valueType :db.type/string}
    {:db/ident :test/number
     :db/valueType :db.type/long}]))

(def data [{:db/id #db/id[:db.part/db]
            :test/name "Bartleby"
            :test/number 3}
           {:db/id #db/id[:db.part/db]
            :test/name "Jean Valjean"
            :test/number 24601}])

(defn reload
  []
  (recreate)
  (dj/t (conn) schema)
  (dj/t (conn) data))
