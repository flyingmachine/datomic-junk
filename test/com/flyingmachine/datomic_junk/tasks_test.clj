(ns com.flyingmachine.datomic-junk.tasks-test
  (:require [com.flyingmachine.datomic-junk :as dj]
            [com.flyingmachine.datomic-junk.tasks :as t]
            [com.flyingmachine.datomic-junk.test-helper :as th]
            [com.flyingmachine.datomic-junk.schema :as s]
            [datomic.api :as d])
  (:use midje.sweet))

(let [schema-attr :datomic-junk-schema-attr]
  (fact "you can load schemas from resources/schemas"
    (t/reload th/db-uri schema-attr [:dj-test])
    (s/has-schema? (d/db (th/conn)) schema-attr :dj-test)
    => truthy)
  (fact "you can create a transaction to rename schemas"
    (t/rename-schemas (d/db (th/conn)) schema-attr {:dj-test :rabble})
    => '([:db/add 13194139534313 :datomic-junk-schema-attr :rabble])))
