(ns com.flyingmachine.datomic-junk-test
  (:require [com.flyingmachine.datomic-junk :refer :all]
            [com.flyingmachine.datomic-junk.test-helper :as th]
            [datomic.api :as d])
  (:use midje.sweet))


(th/reload)

(let [conn (th/conn)
      db (d/db conn)]
  (facts "about ent"
    (fact "ent returns nil if given a bogus id"
      (ent 12345 db)
      => nil)
    (fact "ent returns a datomic entity if given a good id"
      (ent? (ent (ffirst (d/q '[:find ?t :where [?t :test/name]] db)) db))
      => true))

  (fact "ents returns datomic entities given query results"
    (map :db/id (ents db [[65] [66]]))
    => [65 66])
  
  (facts "about eid-by"
    (fact "eid returns an entity id"
      (eid-by db [:test/name])
      => 65)
    (fact "works with multiple a/v pairs"
      (eid-by db [:test/name "Bartleby"] [:test/number 3])
      => 65)
    (fact "eid returns nil when nothing found"
      (eid-by db [:test/name "blarb"])
      => nil))

  (facts "about one"
    (fact "returns an entity given an id if it matches"
      (one db [:test/name "Bartleby"])
      => (ent 65 db))
    (fact "can accept multiple a/v pairs"
      (one db [:test/name "Bartleby"] [:test/number 3])
      => (ent 65 db))
    (fact "works with just an attriube"
      (one db :test/name)
      => (ent 65 db))
    (fact db "returns nil if nothing matches"
      (one db [:test/name "blarb"])
      => nil))

  (facts "about all"
    (fact "returns ents"
      (map :db/id (all db [:test/name]))
      => [65 66])
    (fact "works with just an attribute"
      (map :db/id (all db :test/name))
      => [65 66])
    (fact "and you can provide an attribute value of course"
      (map :db/id (all db [:test/name "Bartleby"]))
      => [65])
    (fact "returns empty seq if no results"
      (all db [:test/name "blarb"])
      => empty?))

  (facts "ent-count returns a count"
    (ent-count db [:test/name])
    => 2
    (ent-count db [:test/name "Bartleby"])
    => 1
    (ent-count db :test/name)
    => 2
    (ent-count db [:test/name "blarb"])
    => 0)
  
  (fact "retract retracts entities"
    @(t conn [{:test/name "Henry" :test/number 123 :db/id (d/tempid :db.part/user)}])
    (apply retract conn (map :db/id (all db [:test/name "Henry"])))
    (one db [:test/name "Henry"])
    => nil))
