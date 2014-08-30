# datomic-junk

This library helps with a few things:

* [**Querying**](#querying)
    * Provides helpers functions for retrieving entities when you
      don't need to specify their relationships to other entities and
      you only have one input, the db.
    * Includes a couple utility functions like `ent-count` and `tempids`
* [**Tasks**](#tasks) Makes it easier to create a database and add
  schema attributes. Assumes schemas are in `resources/schemas`
* [**Data Loading**](#data-loading) Allows you to more succinctly
  express entities and relationships for loading into datomic. This is
  good for loading seed data, for example

## Installation

In your `project.clj`, use

```clojure
[com.flyingmachine/datomic-junk "0.2.1"]
```

## Querying

Functions from the `com.flyingmachine.datomic-junk` namespace allow
you to more succinctly express simple queries. Examples:

```clojure
;; datomic - find the biebs
(q '[:find '?c :where [['?c :person/name "Justin Biebs"]]] db)

;; datomic-junk
(one db [:person/name "Justin Biebs"])


;; datomic - find all people
(q '[:find '?c :where [['?c :person/name]]] db)
;; datomic-junk
(all db :person/name)

;; datomic - find people with name X
(q '[:find '?c :where [['?c :person/name "X"]]] db)
;; datomic-junk
(all db [:person/name "X"])

;; Each datomic-junk function can take multiple conditions
(all db [:person/birth-year 1955] [:favorite/color "burgundy"])
```

Also [check out the tests](https://github.com/flyingmachine/datomic-junk/blob/master/test/com/flyingmachine/datomic_junk_test.clj) for more examples.

## Tasks

In the `com.flyingmachine.datomic-junk.tasks` namespace:

`install-schemas` takes a seq of schema names like

```clojure
["topic" "post" "tag"]
```

and reads the corresponding `.edn` files under `resources/schemas`. It
then runs the transactions in those files if they haven't been run
already.

`recreate` recreates a database.

## Data Loading

Functions in the `com.flyingmachine.datomic-junk.load` namespace allow
you to more succinctly write entity and relationship data for loading
into datomic. For example, say you have a simple forum schema with
tags, topics, and posts, like this (truncated):

```clojure
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
  :db/valueType :db.type/ref}]
```

You could create entities using this data:

```clojure
[{:topic/title "Were-Simmons?"
  :topic/tags [{:tag/name "health"}
               {:tag/name "emergency"}]}
               
 [{:post/topic {:topic/title "Were-Simmons?"}}
  {:post/content "I was bit by a were-Simmons. Is there any hope for me?"}
  {:post/content "No, sorry. You will turn into Richard Simmons on the full moon."}]]
```

This would create one topic with two tags and two posts. The first
element of the vector is a map, which tells datomic-junk to create an
entity. Its `:topic/tags` attribute is a vector, and datomic-junk
creates those entities and associates them with the topic.

The second element is a vector. This tells datomic-junk to merge the
first map of the vector (`{:post/topic {:topic/title
"Were-Simmons?"}}`) with all of the maps that follow. It's as if you
wrote this:

```clojure
[{:topic/title "Were-Simmons?"
  :topic/tags [{:tag/name "health"}
               {:tag/name "emergency"}]}
 {:post/content "I was bit by a were-Simmons. Is there any hope for me?"
  :post/topic {:topic/title "Were-Simmons?"}}
 {:post/content "No, sorry. You will turn into Richard Simmons on the full moon."
  :post/topic {:topic/title "Were-Simmons?"}}]
```

When the value of a map key is itself a map, like `{:post/topic
{:topic/title "Were-Simmons?"}}`, then datomic-junk uses the inner map
as a query. In this way, each of the posts is associated with the
topic that gets created.
