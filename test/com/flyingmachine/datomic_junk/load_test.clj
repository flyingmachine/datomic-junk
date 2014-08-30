(ns com.flyingmachine.datomic-junk.load-test
  (:require [com.flyingmachine.datomic-junk :as dj]
            [com.flyingmachine.datomic-junk.load :as l]
            [com.flyingmachine.datomic-junk.test-helper :as th]
            [datomic.api :as d])
  (:use midje.sweet))

(def schema
  (th/inflate-attrs
   [;; tag
    {:db/ident :tag/name
     :db/valueType :db.type/string}
    
    ;; topic
    {:db/ident :topic/title
     :db/valueType :db.type/string}
    {:db/ident :topic/tags
     :db/valueType :db.type/ref
     :db/cardinality :db.cardinality/many}

    ;; post
    {:db/ident :post/content
     :db/valueType :db.type/string}
    {:db/ident :post/topic
     :db/valueType :db.type/ref}]))

(def data
  [{:topic/title "Were-Simmons?"
    :topic/tags [{:tag/name "health"}
                 {:tag/name "emergency"}]}
   [{:post/topic {:topic/title "Were-Simmons?"}}
    {:post/content "I was bit by a were-Simmons. Is there any hope for me?"}
    {:post/content "No, sorry. You will turn into Richard Simmons on the full moon."}]])

(facts "Loading data"
  (th/recreate)
  (dj/t (th/conn) schema)
  (l/create-entities th/db-uri :db.part/user data)

  (fact "there's one topic")
  (count (dj/all (th/db) :topic/title))
  => 1

  (let [topic (dj/one (th/db) :topic/title)]
    (fact "the topic has two tags"
      (set (map :tag/name (:topic/tags topic)))
      => #{"emergency" "health"})

    (fact "there are two posts belonging to the topic"
      (set (map :post/content (:post/_topic topic)))
      => #{"I was bit by a were-Simmons. Is there any hope for me?"
           "No, sorry. You will turn into Richard Simmons on the full moon."})))
