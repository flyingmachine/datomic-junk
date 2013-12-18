; nREPL 0.1.8
Run `(doc midje)` for Midje usage.

user> 
#<Namespace com.flyingmachine.datomic-junk-test>
com.flyingmachine.datomic-junk-test> (conn)
#<LocalConnection datomic.peer.LocalConnection@44489479>

FAIL "conn works" at (datomic_junk_test.clj:9)
    Expected: true
      Actual: #<LocalConnection datomic.peer.LocalConnection@44489479>

com.flyingmachine.datomic-junk-test> (db)
datomic.db.Db@c50911f3
com.flyingmachine.datomic-junk-test> (type (db))
datomic.db.Db

FAIL at (datomic_junk_test.clj:12)
    Midje could not understand something you wrote: 
            This form: ((instance? (db) datomic.db.Db) =>)
        ... has the wrong shape. Expecting: (<actual> => <expected> [<keyword-value pairs>*])

FAIL "returns db from conn" at (datomic_junk_test.clj:12)
    Expected: true
      Actual: java.lang.ClassCastException: datomic.db.Db cannot be cast to java.lang.Class
              com.flyingmachine.datomic_junk_test$eval7333$base_function__6353__auto____7334$fn__7335$fn__7336.invoke(datomic_junk_test.clj:12)
              com.flyingmachine.datomic_junk_test$eval7333$base_function__6353__auto____7334$fn__7335.invoke(datomic_junk_test.clj:11)
              com.flyingmachine.datomic_junk_test$eval7333$base_function__6353__auto____7334.invoke(datomic_junk_test.clj:11)
              com.flyingmachine.datomic_junk_test$eval7333.invoke(datomic_junk_test.clj:11)
              com.flyingmachine.datomic_junk_test$eval7308.invoke(form-init1441551262237717699.clj:1)

FAIL "conn works" at (datomic_junk_test.clj:9)
    Expected: true
      Actual: false

FAIL "q works" at (datomic_junk_test.clj:33)
    Expected: 2
      Actual: java.lang.Exception: processing rule: (q__7983 ?t)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.invoke(datomic_junk.clj:17)
              com.flyingmachine.datomic_junk_test$eval7972$base_function__6353__auto____7973$fn__7974$fn__7975.invoke(datomic_junk_test.clj:33)
              com.flyingmachine.datomic_junk_test$eval7972$base_function__6353__auto____7973$fn__7974.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval7972$base_function__6353__auto____7973.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval7972.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval7911.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk-test> (q '[:find ?t :where [?t :test/name]])
IllegalArgumentExceptionInfo :db.error/not-an-entity Unable to resolve entity: :test/name  datomic.error/arg (error.clj:55)
com.flyingmachine.datomic-junk-test> (all :test/name)
IllegalArgumentExceptionInfo :db.error/not-an-entity Unable to resolve entity: :test/name  datomic.error/arg (error.clj:55)

FAIL "q works" at (datomic_junk_test.clj:33)
    Expected: 2
      Actual: java.lang.Exception: processing rule: (q__8109 ?t)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.invoke(datomic_junk.clj:17)
              com.flyingmachine.datomic_junk_test$eval8098$base_function__6353__auto____8099$fn__8100$fn__8101.invoke(datomic_junk_test.clj:33)
              com.flyingmachine.datomic_junk_test$eval8098$base_function__6353__auto____8099$fn__8100.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval8098$base_function__6353__auto____8099.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval8098.invoke(datomic_junk_test.clj:32)
              com.flyingmachine.datomic_junk_test$eval8037.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk-test> 
com.flyingmachine.datomic-junk-test> 
com.flyingmachine.datomic-junk-test> (conj [1] [2]
                                           )
[1 [2]]
com.flyingmachine.datomic-junk-test> (into [1] [2]
                                           )
[1 2]
com.flyingmachine.datomic-junk-test> (all :test/name)
IllegalArgumentExceptionInfo :db.error/not-an-entity Unable to resolve entity: :test/name  datomic.error/arg (error.clj:55)
com.flyingmachine.datomic-junk-test> (t schema)
IllegalArgumentExceptionInfo :db.error/datoms-conflict Two datoms in the same transaction conflict: {:d1 #Datum{:e 63 :a 10 :v :test/name :tx 13194139534312 :added true}, :d2 #Datum{:e 63 :a 10 :v :test/number :tx 13194139534312 :added true}}  datomic.error/arg (error.clj:55)
com.flyingmachine.datomic-junk-test> (d/tempid :db.part/db)
#db/id[:db.part/db -1000025]
com.flyingmachine.datomic-junk-test> (d/tempid :db.part/db)
#db/id[:db.part/db -1000026]
com.flyingmachine.datomic-junk-test> schema
({:db/ident :test/name, :db/valueType :db.type/string, :db/id #db/id[:db.part/user -1000027], :db.install/_attribute :db.part/db, :db/cardinality :db.cardinality/one} {:db/ident :test/number, :db/valueType :db.type/long, :db/id #db/id[:db.part/user -1000028], :db.install/_attribute :db.part/db, :db/cardinality :db.cardinality/one})
com.flyingmachine.datomic-junk-test> (all :test/name)
({:db/id 65} {:db/id 66})

FAIL "q works" at (datomic_junk_test.clj:38)
    Expected: 2
      Actual: java.lang.IllegalArgumentException: :db.error/nil-input Nil/null input to query
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:450)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:17)
              com.flyingmachine.datomic_junk_test$eval9557$base_function__6353__auto____9558$fn__9559$fn__9560.invoke(datomic_junk_test.clj:38)
              com.flyingmachine.datomic_junk_test$eval9557$base_function__6353__auto____9558$fn__9559.invoke(datomic_junk_test.clj:37)
              com.flyingmachine.datomic_junk_test$eval9557$base_function__6353__auto____9558.invoke(datomic_junk_test.clj:37)
              com.flyingmachine.datomic_junk_test$eval9557.invoke(datomic_junk_test.clj:37)
              com.flyingmachine.datomic_junk$eval9493.invoke(form-init1441551262237717699.clj:1)

FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: java.lang.Exception: processing rule: (q__9598 ?eid)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:17)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9574$fn__9575$fn__9576.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9574$fn__9575.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9574.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk$eval9493.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: java.lang.IllegalArgumentException: :db.error/nil-input Nil/null input to query
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:450)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:17)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9582$fn__9583$fn__9584.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9582$fn__9583.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573$base_function__6353__auto____9582.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572$fn__9573.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571$base_function__6353__auto____9572.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9571.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk$eval9493.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk-test> (empty? nil)
true
com.flyingmachine.datomic-junk-test> (empty? '())
true

FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:25)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9808$fn__9809$fn__9810.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9808$fn__9809.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9808.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9726.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:25)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9816$fn__9817$fn__9818.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9816$fn__9817.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807$base_function__6353__auto____9816.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806$fn__9807.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805$base_function__6353__auto____9806.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9805.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9726.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:25)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10012$fn__10013$fn__10014.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10012$fn__10013.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10012.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9930.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:25)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10020$fn__10021$fn__10022.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10020$fn__10021.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011$base_function__6353__auto____10020.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010$fn__10011.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009$base_function__6353__auto____10010.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10009.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval9930.invoke(form-init1441551262237717699.clj:1)


FAIL "ent returns nil if given a bogus id" at (form-init1441551262237717699.clj:2)
    Expected: nil
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.doInvoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:25)
              com.flyingmachine.datomic_junk_test$eval10092$base_function__6353__auto____10093$fn__10094$fn__10095.invoke(form-init1441551262237717699.clj:2)
              com.flyingmachine.datomic_junk_test$eval10092$base_function__6353__auto____10093$fn__10094.invoke(form-init1441551262237717699.clj:1)
              com.flyingmachine.datomic_junk_test$eval10092$base_function__6353__auto____10093.invoke(form-init1441551262237717699.clj:1)
              com.flyingmachine.datomic_junk_test$eval10092.invoke(form-init1441551262237717699.clj:1)
(12345)

com.flyingmachine.datomic-junk-test> (apply d/q '[:find ?eid :in $ ?eid :where [?eid]] '(12345))
IllegalArgumentExceptionInfo :db.error/too-few-inputs Query expected 2 inputs but received 1  datomic.error/arg (error.clj:55)

FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10338$fn__10339$fn__10340.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10338$fn__10339.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10338.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10256.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10346$fn__10347$fn__10348.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10346$fn__10347.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337$base_function__6353__auto____10346.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336$fn__10337.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335$base_function__6353__auto____10336.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10335.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10256.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10505$fn__10506$fn__10507.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10505$fn__10506.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10505.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10423.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10513$fn__10514$fn__10515.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10513$fn__10514.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504$base_function__6353__auto____10513.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503$fn__10504.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502$base_function__6353__auto____10503.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10502.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10423.invoke(form-init1441551262237717699.clj:1)


FAIL "ent returns a datomic entity if given a good id" at (form-init1441551262237717699.clj:2)
    Expected: true
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10541$base_function__6353__auto____10542$fn__10543$fn__10544.invoke(form-init1441551262237717699.clj:2)
              com.flyingmachine.datomic_junk_test$eval10541$base_function__6353__auto____10542$fn__10543.invoke(form-init1441551262237717699.clj:1)
              com.flyingmachine.datomic_junk_test$eval10541$base_function__6353__auto____10542.invoke(form-init1441551262237717699.clj:1)
              com.flyingmachine.datomic_junk_test$eval10541.invoke(form-init1441551262237717699.clj:1)

FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: clojure.lang.ArityException: Wrong number of args (2) passed to: datomic-junk$q
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10636$base_function__6353__auto____10637$fn__10638$base_function__6353__auto____10639$fn__10640$fn__10641.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval10636$base_function__6353__auto____10637$fn__10638$base_function__6353__auto____10639$fn__10640.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10636$base_function__6353__auto____10637$fn__10638$base_function__6353__auto____10639.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10636$base_function__6353__auto____10637$fn__10638.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10636$base_function__6353__auto____10637.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10636.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10558.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns nil if given a bogus id" at (datomic_junk_test.clj:43)
    Expected: nil
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10800$fn__10801$fn__10802.invoke(datomic_junk_test.clj:43)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10800$fn__10801.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10800.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10718.invoke(form-init1441551262237717699.clj:1)


FAIL "about ent - ent returns a datomic entity if given a good id" at (datomic_junk_test.clj:46)
    Expected: true
      Actual: java.lang.IllegalArgumentException: :db.error/too-few-inputs Query expected 2 inputs but received 1
              datomic.error$arg.invoke(error.clj:55)
              datomic.error$arg.invoke(error.clj:53)
              datomic.query$q.invoke(query.clj:454)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$ent.invoke(datomic_junk.clj:21)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10808$fn__10809$fn__10810.invoke(datomic_junk_test.clj:46)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10808$fn__10809.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799$base_function__6353__auto____10808.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798$fn__10799.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797$base_function__6353__auto____10798.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10797.invoke(datomic_junk_test.clj:41)
              com.flyingmachine.datomic_junk_test$eval10718.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk-test> (type (one :test/name))
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (one :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (q '[:find ?c :where [?c :test/name]])
#{[65] [66]}
com.flyingmachine.datomic-junk-test> (one :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (one :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk-test> 
#<Namespace com.flyingmachine.datomic-junk>
com.flyingmachine.datomic-junk> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk> (eid :test/name)
(:test/name)
com.flyingmachine.datomic-junk> (eid :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk> (eid :test/name)
(:test/name)
com.flyingmachine.datomic-junk> (seq :test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk> (concat ['?c] ':test/name)
IllegalArgumentException Don't know how to create ISeq from: clojure.lang.Keyword  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk> (one [:test/name])
{:db/id 65}
com.flyingmachine.datomic-junk> (eid [:test/name])
65
com.flyingmachine.datomic-junk> (eid [:test/name "dafas"])
nil

FAIL "about ents - returns ents given eids" at (datomic_junk_test.clj:51)
    Expected: []
      Actual: java.lang.IllegalArgumentException: Don't know how to create ISeq from: java.lang.Long
              com.flyingmachine.datomic_junk_test$eval12576$base_function__6353__auto____12577$fn__12578$base_function__6353__auto____12579$fn__12580.invoke(datomic_junk_test.clj:49)
              com.flyingmachine.datomic_junk_test$eval12576$base_function__6353__auto____12577$fn__12578$base_function__6353__auto____12579.invoke(datomic_junk_test.clj:49)
              com.flyingmachine.datomic_junk_test$eval12576$base_function__6353__auto____12577$fn__12578.invoke(datomic_junk_test.clj:49)
              com.flyingmachine.datomic_junk_test$eval12576$base_function__6353__auto____12577.invoke(datomic_junk_test.clj:49)
              com.flyingmachine.datomic_junk_test$eval12576.invoke(datomic_junk_test.clj:49)
              com.flyingmachine.datomic_junk$eval12465.invoke(form-init1441551262237717699.clj:1)

com.flyingmachine.datomic-junk> (ents [65 66])
IllegalArgumentException Don't know how to create ISeq from: java.lang.Long  clojure.lang.RT.seqFrom (RT.java:505)
com.flyingmachine.datomic-junk> (ents [[65] [66]])
({:db/id 65} {:db/id 66})

FAIL "about ents - returns ents given query results" at (datomic_junk_test.clj:51)
    Expected: [{:db/id 65} {:db/id 66}]
      Actual: ({:db/id 65} {:db/id 66})

FAIL "about ents - returns ents given query results" at (datomic_junk_test.clj:51)
    Expected: ({:db/id 65} {:db/id 66})
      Actual: ({:db/id 65} {:db/id 66})

FAIL "about all - returns nil if no resutls" at (datomic_junk_test.clj:75)
    Expected: nil
      Actual: ()

FAIL "ent-count returns a count" at (datomic_junk_test.clj:79)
    Expected: 2
      Actual: java.lang.Exception: processing rule: (q__14356 ?e)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$ent_count.invoke(datomic_junk.clj:57)
              com.flyingmachine.datomic_junk_test$eval14335$base_function__6353__auto____14336$fn__14337$fn__14338.invoke(datomic_junk_test.clj:79)
              com.flyingmachine.datomic_junk_test$eval14335$base_function__6353__auto____14336$fn__14337.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval14335$base_function__6353__auto____14336.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval14335.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk$eval14103.invoke(form-init1441551262237717699.clj:1)


FAIL "ent-count returns a count" at (datomic_junk_test.clj:79)
    Expected: 2
      Actual: java.lang.Exception: processing rule: (q__14977 ?e)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$ent_count.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval14966$base_function__6353__auto____14967$fn__14968$fn__14969.invoke(datomic_junk_test.clj:79)
              com.flyingmachine.datomic_junk_test$eval14966$base_function__6353__auto____14967$fn__14968.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval14966$base_function__6353__auto____14967.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval14966.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk$eval14734.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk> 
com.flyingmachine.datomic-junk> 

FAIL "ent-count returns a count" at (datomic_junk_test.clj:79)
    Expected: 2
      Actual: java.lang.Exception: processing rule: (q__15222 ?e)
              datomic.datalog$eval_rule$fn__5796.invoke(datalog.clj:978)
              datomic.datalog$eval_rule.invoke(datalog.clj:958)
              datomic.datalog$eval_query.invoke(datalog.clj:1000)
              datomic.datalog$qsqr.invoke(datalog.clj:1054)
              datomic.datalog$qsqr.invoke(datalog.clj:1022)
              datomic.query$q.invoke(query.clj:455)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$ent_count.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval15211$base_function__6353__auto____15212$fn__15213$fn__15214.invoke(datomic_junk_test.clj:79)
              com.flyingmachine.datomic_junk_test$eval15211$base_function__6353__auto____15212$fn__15213.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval15211$base_function__6353__auto____15212.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval15211.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk$eval14979.invoke(form-init1441551262237717699.clj:1)


com.flyingmachine.datomic-junk> (ents [[65] [66]])
({:db/id 65} {:db/id 66})
com.flyingmachine.datomic-junk> (ent-count :test/name)
IllegalArgumentException Insufficient binding of db clause: [?e ?attr] would cause full scan  datomic.datalog/sched-in-order/fn--5700 (datalog.clj:665)
com.flyingmachine.datomic-junk> (ent-count :test/name)
IllegalArgumentException Insufficient binding of db clause: [?e ?attr] would cause full scan  datomic.datalog/sched-in-order/fn--5700 (datalog.clj:665)
com.flyingmachine.datomic-junk> (ent-count :test/name)
IllegalArgumentException Argument count in :find is not a variable  datomic.query/validate-query (query.clj:280)
com.flyingmachine.datomic-junk> (ent-count :test/name)
IllegalArgumentException Argument ?e in :where is not a list  datomic.query/validate-query (query.clj:286)
com.flyingmachine.datomic-junk> (ent-count :test/name)
[[2]]
com.flyingmachine.datomic-junk> (ent-count [:test/name])
CompilerException java.lang.RuntimeException: Unable to resolve symbol: ?c in this context, compiling:(/private/var/folders/5x/4zz4yh2n1k78bpt45tlz62br0000gn/T/form-init1441551262237717699.clj:1:1) 
com.flyingmachine.datomic-junk> (ent-count [:test/name])
((?c :test/name))
com.flyingmachine.datomic-junk> (ent-count [:test/name])
2

FAIL "ent-count returns a count" at (datomic_junk_test.clj:79)
    Expected: 2
      Actual: java.lang.IllegalArgumentException: Don't know how to create ISeq from: clojure.lang.Keyword
              com.google.common.base.Equivalence$Equals.doHash(Equivalence.java:331)
              com.google.common.base.Equivalence.hash(Equivalence.java:104)
              com.google.common.cache.LocalCache.hash(LocalCache.java:1899)
              com.google.common.cache.LocalCache.get(LocalCache.java:3999)
              com.google.common.cache.LocalCache.getOrLoad(LocalCache.java:4004)
              com.google.common.cache.LocalCache$LocalLoadingCache.get(LocalCache.java:4874)
              datomic.cache$fn__921.invoke(cache.clj:68)
              datomic.cache$fn__908$G__903__915.invoke(cache.clj:60)
              datomic.cache.WrappedGCache.valAt(cache.clj:99)
              datomic.query$q.invoke(query.clj:448)
              datomic.api$q.doInvoke(api.clj:31)
              com.flyingmachine.datomic_junk$q.invoke(datomic_junk.clj:16)
              com.flyingmachine.datomic_junk$ent_count.doInvoke(form-init1441551262237717699.clj:4)
              com.flyingmachine.datomic_junk_test$eval16166$base_function__6353__auto____16167$fn__16168$fn__16169.invoke(datomic_junk_test.clj:79)
              com.flyingmachine.datomic_junk_test$eval16166$base_function__6353__auto____16167$fn__16168.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval16166$base_function__6353__auto____16167.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk_test$eval16166.invoke(datomic_junk_test.clj:78)
              com.flyingmachine.datomic_junk$eval15934.invoke(form-init1441551262237717699.clj:1)


FAIL "ent-count returns a count" at (datomic_junk_test.clj:82)
    Expected: 0
      Actual: nil

com.flyingmachine.datomic-junk> not-nil
CompilerException java.lang.RuntimeException: Unable to resolve symbol: not-nil in this context, compiling:(/private/var/folders/5x/4zz4yh2n1k78bpt45tlz62br0000gn/T/form-init1441551262237717699.clj:1:743) 
com.flyingmachine.datomic-junk> not-nil?
CompilerException java.lang.RuntimeException: Unable to resolve symbol: not-nil? in this context, compiling:(/private/var/folders/5x/4zz4yh2n1k78bpt45tlz62br0000gn/T/form-init1441551262237717699.clj:1:743) 
com.flyingmachine.datomic-junk> (not-empty?
                                 1
                                 )
CompilerException java.lang.RuntimeException: Unable to resolve symbol: not-empty? in this context, compiling:(/private/var/folders/5x/4zz4yh2n1k78bpt45tlz62br0000gn/T/form-init1441551262237717699.clj:1:1) 
com.flyingmachine.datomic-junk> (not-empty
                                 1
                                 )
IllegalArgumentException Don't know how to create ISeq from: java.lang.Long  clojure.lang.RT.seqFrom (RT.java:505)

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
Actual result did not agree with the checking function.
        Actual result: java.lang.ClassCastException: clojure.lang.PersistentArrayMap cannot be cast to java.util.List
              datomic.api$transact.invoke(api.clj:74)
              com.flyingmachine.datomic_junk$t.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval17284$base_function__6353__auto____17285$fn__17286$fn__17287.invoke(datomic_junk_test.clj:86)
              com.flyingmachine.datomic_junk_test$eval17284$base_function__6353__auto____17285$fn__17286.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17284$base_function__6353__auto____17285.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17284.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk$eval17036.invoke(form-init1441551262237717699.clj:1)
    Checking function: not-empty

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
    Expected: []
      Actual: java.lang.ClassCastException: clojure.lang.PersistentArrayMap cannot be cast to java.util.List
              datomic.api$transact.invoke(api.clj:74)
              com.flyingmachine.datomic_junk$t.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval17546$base_function__6353__auto____17547$fn__17548$fn__17549.invoke(datomic_junk_test.clj:86)
              com.flyingmachine.datomic_junk_test$eval17546$base_function__6353__auto____17547$fn__17548.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17546$base_function__6353__auto____17547.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17546.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk$eval17298.invoke(form-init1441551262237717699.clj:1)

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
    Expected: ()
      Actual: java.lang.ClassCastException: clojure.lang.PersistentArrayMap cannot be cast to java.util.List
              datomic.api$transact.invoke(api.clj:74)
              com.flyingmachine.datomic_junk$t.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval17808$base_function__6353__auto____17809$fn__17810$fn__17811.invoke(datomic_junk_test.clj:86)
              com.flyingmachine.datomic_junk_test$eval17808$base_function__6353__auto____17809$fn__17810.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17808$base_function__6353__auto____17809.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval17808.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk$eval17560.invoke(form-init1441551262237717699.clj:1)

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
    Expected: nil
      Actual: java.lang.ClassCastException: clojure.lang.PersistentArrayMap cannot be cast to java.util.List
              datomic.api$transact.invoke(api.clj:74)
              com.flyingmachine.datomic_junk$t.invoke(datomic_junk.clj:60)
              com.flyingmachine.datomic_junk_test$eval18074$base_function__6353__auto____18075$fn__18076$fn__18077.invoke(datomic_junk_test.clj:86)
              com.flyingmachine.datomic_junk_test$eval18074$base_function__6353__auto____18075$fn__18076.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval18074$base_function__6353__auto____18075.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk_test$eval18074.invoke(datomic_junk_test.clj:85)
              com.flyingmachine.datomic_junk$eval17826.invoke(form-init1441551262237717699.clj:1)

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
    Expected: nil
      Actual: datomic.promise$settable_future$reify__4417

FAIL "t runs a transaction" at (datomic_junk_test.clj:86)
    Expected: nil
      Actual: datomic.promise$settable_future$reify__4417

com.flyingmachine.datomic-junk> (type (t [{:test/name "Henry" :test/number 123}]))
datomic.promise$settable_future$reify__4417
com.flyingmachine.datomic-junk> (type @(t [{:test/name "Henry" :test/number 123}]))
IllegalArgumentExceptionInfo :db.error/entity-missing-db-id Missing :db/id  datomic.error/arg (error.clj:55)
com.flyingmachine.datomic-junk> (type @(t [{:test/name "Henry" :test/number 123 :db/id (d/tempid :}]))